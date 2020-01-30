package com.library.dao;

import com.library.model.Authority;
import com.library.model.User;
import com.library.dao.interfaces.IUsersDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UsersDao extends AbstractJpaDao<User> implements IUsersDao {

    private UsersDao(){
        setClazz(User.class);
    }

    public User findByUsername(String username) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("username"), username));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    public Authority findByUser(User user) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Authority> criteriaQuery = criteriaBuilder.createQuery(Authority.class);
        Root<Authority> root = criteriaQuery.from(Authority.class);

        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("id"), user.getId()));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
