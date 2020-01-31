package com.library.dao;

import com.library.dao.interfaces.IAuthorDao;
import com.library.model.Author;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class AuthorDao extends AbstractJpaDao<Author> implements IAuthorDao {

    private AuthorDao() {
        setClazz(Author.class);
    }

    @Override
    public Author findAuthorByFullName(Author author) {
        //todo: !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        return null;
    }

    @Override
    public boolean duplicateCheck(Author author) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
        Root<Author> root = criteriaQuery.from(Author.class);

        Predicate name = criteriaBuilder.like(root.get("name"), author.getName());
        Predicate surname = criteriaBuilder.like(root.get("surname"), author.getSurname());
        Predicate patronymic = criteriaBuilder.like(root.get("patronymic"), author.getPatronymic());
//+ proverka na Ganre todo:


        return criteriaQuery.select(root).where(criteriaBuilder
                .and(name, surname, patronymic)) != null;
    }
}
