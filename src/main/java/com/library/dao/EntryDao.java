package com.library.dao;

import com.library.dao.interfaces.IEntryDao;
import com.library.model.Entry;
import com.library.model.enums.EntryStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class EntryDao extends AbstractJpaDao<Entry> implements IEntryDao {

    private EntryDao() {
        setClazz(Entry.class);
    }

    @Override
    public Entry closedEntryById(Integer idEntry) {
        return null;
    }

    @Override
    public List<Entry> findExpiredEntriesList() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Entry> criteriaQuery = criteriaBuilder.createQuery(Entry.class);
        Root<Entry> root = criteriaQuery.from(Entry.class);

        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("entryStatus"), EntryStatus.EXPIRED));

        return entityManager.createQuery(criteriaQuery)
                .getResultList();
    }
}
