package com.library.dao;

import com.library.dao.interfaces.IAuthorityDao;
import com.library.model.Authority;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorityDao extends AbstractJpaDao<Authority> implements IAuthorityDao {

    private AuthorityDao(){
        setClazz(Authority.class);
    }
}
