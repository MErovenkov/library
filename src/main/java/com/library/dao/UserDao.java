package com.library.dao;

import com.library.model.User;
import com.library.dao.interfaces.IUserDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserDao extends AbstractJpaDao<User> implements IUserDao {

    private UserDao(){
        setClazz(User.class);
    }

    public User findByUsername(String username) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("userName"), username));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
