package com.library.dao;

import com.library.dao.interfaces.IGenreDao;
import com.library.model.Genre;

import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class GenreDao extends AbstractJpaDao<Genre> implements IGenreDao {

    private GenreDao(){
        setClazz(Genre.class);
    }


    @Override
    public Genre findGenreByName(String nameGenre) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Genre> criteriaQuery = criteriaBuilder.createQuery(Genre.class);
        Root<Genre> root = criteriaQuery.from(Genre.class);

        //todo: !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        return null;
    }

}
