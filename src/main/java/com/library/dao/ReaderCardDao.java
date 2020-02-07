package com.library.dao;

import com.library.dao.interfaces.IReaderCardDao;
import com.library.model.Book;
import com.library.model.Entry;
import com.library.model.ReaderCard;
import com.library.model.enums.SortingComparator;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReaderCardDao extends AbstractJpaDao<ReaderCard> implements IReaderCardDao {

    private ReaderCardDao(){
        setClazz(ReaderCard.class);
    }

    @Override
    public List<Entry> findEntriesByReaderCard(Integer idReaderCard) {
        ReaderCard readerCard = this.findOneById(idReaderCard);

        if (readerCard != null) {
            Hibernate.initialize(readerCard.getEntryList());
            return readerCard.getEntryList();
        }

        return null;
    }

    @Override
    public List<ReaderCard> findSortReadersCardsList(SortingComparator sortingComparator) {
        return null;
    }

}
