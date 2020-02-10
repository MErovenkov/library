package com.library.dao;

import com.library.dao.interfaces.IReaderCardDao;
import com.library.model.Entry;
import com.library.model.ReaderCard;
import com.library.model.enums.EntryStatus;
import com.library.model.enums.SortingComparator;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ReaderCardDao extends AbstractJpaDao<ReaderCard> implements IReaderCardDao {

    private ReaderCardDao(){
        setClazz(ReaderCard.class);
    }

    ///
    @Override
    public List<ReaderCard> findSortReadersCardsList(SortingComparator sortingComparator) {
        return null;
    }

    @Override
    public List<Entry> findEntriesByReaderCardId(Integer idReaderCard) {
        ReaderCard readerCard = this.findOneById(idReaderCard);

        if (readerCard != null) {
            Hibernate.initialize(readerCard.getEntryList());
            return readerCard.getEntryList();
        }

        return null;
    }

    @Override
    public List<Entry> findExpiredEntriesListByReaderCardId(Integer idReaderCard) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Entry> criteriaQuery = criteriaBuilder.createQuery(Entry.class);
        Root<Entry> root = criteriaQuery.from(Entry.class);

        Predicate readerCard = criteriaBuilder.equal(root.get("idReaderCard"), idReaderCard);
        Predicate expiredEntry = criteriaBuilder.equal(root.get("entryStatus"), EntryStatus.EXPIRED);

        criteriaQuery.select(root)
                .where(criteriaBuilder.and(readerCard, expiredEntry));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Entry> findOpenedEntriesListByReaderCardId(Integer idReaderCard) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Entry> criteriaQuery = criteriaBuilder.createQuery(Entry.class);
        Root<Entry> root = criteriaQuery.from(Entry.class);

        Predicate readerCard = criteriaBuilder.equal(root.get("idReaderCard"), idReaderCard);
        Predicate expiredEntry = criteriaBuilder.equal(root.get("entryStatus"), EntryStatus.OPEN);

        criteriaQuery.select(root)
                .where(criteriaBuilder.and(readerCard, expiredEntry));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
