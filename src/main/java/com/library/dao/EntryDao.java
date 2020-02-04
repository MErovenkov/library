package com.library.dao;

import com.library.dao.interfaces.IEntryDao;
import com.library.model.Entry;
import com.library.model.enums.SortingComparator;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntryDao extends AbstractJpaDao<Entry> implements IEntryDao {

    private EntryDao() {
        setClazz(Entry.class);
    }

    @Override
    public List<Entry> findEntriesByReaderCard(Integer idReaderCard) {
        return null;
    }

    @Override
    public List<Entry> findSortEntriesList(SortingComparator sortingComparator) {
        return null;
    }
}
