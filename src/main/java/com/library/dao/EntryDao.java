package com.library.dao;

import com.library.dao.interfaces.IEntryDao;
import com.library.model.Entry;
import org.springframework.stereotype.Repository;

@Repository
public class EntryDao extends AbstractJpaDao<Entry> implements IEntryDao {

    private EntryDao() {
        setClazz(Entry.class);
    }
}
