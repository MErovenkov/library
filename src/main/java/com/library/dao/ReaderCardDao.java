package com.library.dao;

import com.library.dao.interfaces.IReaderCardDao;
import com.library.model.ReaderCard;
import com.library.model.enums.SortingComparator;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReaderCardDao extends AbstractJpaDao<ReaderCard> implements IReaderCardDao {

    private ReaderCardDao(){
        setClazz(ReaderCard.class);
    }

    @Override
    public List<ReaderCard> findSortReadersCardsList(SortingComparator sortingComparator) {
        return null;
    }
}
