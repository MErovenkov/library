package com.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "id_author")
    private Author author;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "id_genre")
    private Genre genre;

    @JsonManagedReference
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

    public Book(){}

    public Book(String name, Author author, Genre genre, Publisher publisher, String shortSpecification, Integer numberPages) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.shortSpecification = shortSpecification;
        this.numberPages = numberPages;
        this.inStock = true;
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

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }
}
