package com.library.service;

import com.library.dao.interfaces.IAuthorDao;
import com.library.model.Author;
import com.library.service.interfaces.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements IAuthorService {

    @Autowired
    private IAuthorDao iAuthorRepository;

    @Override
    public void createAuthor(Author author) {
        if (author != null) {
            if (author.getSurname().trim().equals("")
                    || author.getName().trim().equals("")) {
                //ФИ пусто
            } else if (author.getGenreList() == null) {
                //хотябы 1 жанр
            } else if (this.iAuthorRepository.duplicateCheck(author)) {
                //автор есть
            } else {
                this.iAuthorRepository.create(specialFormatFullName(author));
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
    public void deleteAuthor(Author author) {
//////////
    }

    @Override
    public void deleteAuthorById(Integer idAuthor) {
        //
       this.iAuthorRepository.deleteById(idAuthor);
    }

    @Override
    public Author getAuthorById(Integer idAuthor) {
        return this.iAuthorRepository.findOneById(idAuthor);
    }

    @Override
    public List<Author> getAuthorList() {
        return this.iAuthorRepository.findAll();
    }

    ///
}
