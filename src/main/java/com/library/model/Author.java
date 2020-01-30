package com.library.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "author")
public class Author extends Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_author")
    private Integer id;

    @ManyToMany
    @JoinTable(
            name = "author_genre",
            joinColumns =  @JoinColumn(name = "id_author"),
            inverseJoinColumns =  @JoinColumn(name = "id_genre"))
    private List<Genre> genreList;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> bookList;

    private Author(){
        super();
    }

    public Author(String surname, String name, String patronymic,
                  Genre genre) {
        super(surname, name, patronymic);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}