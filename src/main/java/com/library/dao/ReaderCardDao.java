package com.library.dao;

import com.library.dao.interfaces.IReaderCardDao;
import com.library.model.ReaderCard;
import org.springframework.stereotype.Repository;

@Repository
public class ReaderCardDao extends AbstractJpaDao<ReaderCard> implements IReaderCardDao {

    private ReaderCardDao(){
        setClazz(ReaderCard.class);
    }
}
