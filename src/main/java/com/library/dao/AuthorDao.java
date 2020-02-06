package com.library.dao;

import com.library.dao.interfaces.IAuthorDao;
import com.library.model.Author;
import com.library.model.AuthorGenre;
import com.library.model.Genre;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class AuthorDao extends AbstractJpaDao<Author> implements IAuthorDao {

    private AuthorDao() {
        setClazz(Author.class);
    }

    //todo:
    @Override
    public List<Author> findAuthorsListByGenre(Genre genre) {
        return null;
    }

    public List<Genre> findGenreListByAuthor(Author author) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Genre> criteriaQuery = criteriaBuilder.createQuery(Genre.class);
        Root<AuthorGenre> root = criteriaQuery.from(AuthorGenre.class);

        Predicate predicate = criteriaBuilder.equal(root.get("id"), author.getId());

        List<Genre> genres =  entityManager.createQuery(criteriaQuery.select(root.get("id")).where(predicate)).getResultList();
        System.out.println(genres);
return null;
       // return entityManager.createQuery(criteriaQuery.select(root).where(predicate)).getResultList();
    }
}
