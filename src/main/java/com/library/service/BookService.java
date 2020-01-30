package com.library.service;

import com.library.dao.interfaces.IBookDao;
import com.library.model.*;
import com.library.service.interfaces.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    private IBookDao iBookRepository;

    @Override
    public void createBook(Book book) {
       // if (book != null) {
           /* if (book.getName().trim().equals(""))
            {
                //книга без названия
            } else if (book.getAuthor() == null) {
                // без автора
            } else if (book.getGenre() == null) {
                /// bez ganra
            } else if (book.getPublishing() == null) {
                //bez publix
            }  else if (book.getNumberPages() == null) {

            } else if (book.getBookCase() == null) {

            } else if (book.getNumberPlace() == null) {

            } else this.iBookDao.create(book);
        } else {
            //NULL
        }*/
           this.iBookRepository.create(book);
    }

    @Override
    public void deleteBookById(Integer idBook) {
        ///
        this.iBookRepository.deleteById(idBook);
    }

    /*
    @Override
    public void removingBookByName(String nameBook) {
        if(this.iBookDao.findOneById(idBook) == null) {

        } else this.iBookDao.deleteById(idBook);
    }*/

    @Override
    public Book getBookById(Integer idBook) {
        if(this.iBookRepository.findOneById(idBook) == null) {
            ///
            return null;
        } else return this.iBookRepository.findOneById(idBook);
    }


    @Override
    public List<Book> getBookList() {
        if(this.iBookRepository.findAll() == null) {
            ///
            return null;
        } else return this.iBookRepository.findAll();

    }
}
