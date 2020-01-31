package com.library.service;

import com.library.dao.interfaces.IAuthorDao;
import com.library.model.Author;
import com.library.model.Genre;
import com.library.service.interfaces.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements IAuthorService {

    @Autowired
    private IAuthorDao iAuthorDao;

    @Override
    public void createAuthor(Author author) {
        if (author != null) {
            if (author.getSurname().trim().equals("")
                    || author.getName().trim().equals("")) {
                //ФИ пусто
            } else if (author.getGenreList() == null) {
                //хотябы 1 жанр
                //todo:
            } else if (this.iAuthorDao.duplicateCheck(author)) {
                //автор есть
            } else {
                this.iAuthorDao.create(specialFormatFullName(author));
            }
        }
    }

    private Author specialFormatFullName(Author author) {
        author.setSurname(author.getSurname().substring(0, 1).toUpperCase()
                + author.getSurname().substring(1).toLowerCase());
        author.setName(author.getName().substring(0, 1).toUpperCase()
                + author.getName().substring(1).toLowerCase());

        if (!author.getPatronymic().trim().equals("")) {
            author.setPatronymic(author.getPatronymic().substring(0, 1).toUpperCase()
                    + author.getPatronymic().substring(1).toLowerCase());
        }

        return author;
    }

    @Override
    public Author updateAuthor(Integer idAuthor, Author newDataAuthor) {

        // todo: проверка на наличие

        Author author = this.iAuthorDao.findOneById(idAuthor);

        author.setSurname(newDataAuthor.getSurname());
        author.setName(newDataAuthor.getName());
        author.setPatronymic(newDataAuthor.getPatronymic());

        return this.iAuthorDao.update(author);
    }


    @Override
    public void deleteAuthorById(Integer idAuthor) {
        //
       this.iAuthorDao.deleteById(idAuthor);
    }

    @Override
    public Author findAuthorById(Integer idAuthor) {
        return this.iAuthorDao.findOneById(idAuthor);
    }

    // todo: возможно стоит убрать
    @Override
    public Author findAuthorByFullName(Author author) {

        // todo: проверка на наличие

        return this.iAuthorDao.findAuthorByFullName(author);
    }

    @Override
    public List<Author> findAuthorList() {
        return this.iAuthorDao.findAll();
    }
}
