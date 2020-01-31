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
    private IBookDao iBookDao;

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
           this.iBookDao.create(book);
    }



    @Override
    public Book updateBook(Integer idBook, Book newDataBook) {

        // todo: проверка на наличие

        Book book = this.iBookDao.findOneById(idBook);



        return this.iBookDao.update(book);
    }




    @Override
    public void deleteBookById(Integer idBook) {
        ///
        this.iBookDao.deleteById(idBook);
    }

    @Override
    public Book findBookById(Integer idBook) {
        if(this.iBookDao.findOneById(idBook) == null) {
            ///
            return null;
        } else return this.iBookDao.findOneById(idBook);
    }





    // todo: возможно стоит убрать
    @Override
    public Book findBookByName(String nameBook) {

        // todo: проверка на наличие

        return this.iBookDao.findBookByName(nameBook);
    }






    @Override
    public List<Book> findBookList() {
        if(this.iBookDao.findAll() == null) {
            ///
            return null;
        } else return this.iBookDao.findAll();

    }

    /*
    @Override
    public void removingBookByName(String nameBook) {
        if(this.iBookDao.findOneById(idBook) == null) {

        } else this.iBookDao.deleteById(idBook);
    }*/
}
