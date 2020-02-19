package com.library.dao;

import com.library.model.User;
import com.library.dao.interfaces.IUserDao;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDao extends AbstractJpaDao<User> implements IUserDao {

    private UserDao(){
        setClazz(User.class);
    }

    @Override
    public User findByUsername(String username) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("userName"), username));

        User user = entityManager.createQuery(criteriaQuery).getSingleResult();

        if (user != null) {
            Hibernate.initialize(user.getAuthorityList());
        }

        return user;
    }

    @Override
    public User findOneById(Integer idUser) {
        User user = entityManager.find(User.class, idUser);

        if (user != null) {
            Hibernate.initialize(user.getAuthorityList());
        }

        return user;
    }

    @Override
    public List<User> findAll(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        criteriaQuery.select(criteriaQuery.from(User.class));

        List<User> authorityList = entityManager.createQuery(criteriaQuery)
                .getResultList();

        for (User user: authorityList) {
            Hibernate.initialize(user.getAuthorityList());
        }

        return authorityList;
    }
}
