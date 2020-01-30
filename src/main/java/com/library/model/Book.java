package com.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book")
    private Integer id;

    @Column(name = "name")
    private String name;
//
    @ManyToOne
    @JoinColumn(name = "id_author")
    private Author author;
//
    @ManyToOne
    @JoinColumn(name = "id_genre")
    private Genre genre;
//
    @ManyToOne
    @JoinColumn(name = "id_publisher")
    private Publisher publisher;

    @Column(name = "short_specification")
    private String shortSpecification;

    @Column(name = "number_pages")
    private Integer numberPages;

    @Column(name = "in_stock")
    private boolean inStock;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<Entry> entry;

    private Book(){}

    public Book(String name, Author author, Genre genre, Publisher publisher, String shortSpecification, Integer numberPages, boolean inStock) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.shortSpecification = shortSpecification;
        this.numberPages = numberPages;
        this.inStock = inStock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortSpecification() {
        return shortSpecification;
    }

    public void setShortSpecification(String shortSpecification) {
        this.shortSpecification = shortSpecification;
    }

    public Integer getNumberPages() {
        return numberPages;
    }

    public void setNumberPages(Integer numberPages) {
        this.numberPages = numberPages;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Publisher getPublishing() {
        return publisher;
    }

    public void setPublishing(Publisher publishing) {
        this.publisher = publishing;
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }
}
