package com.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Класс, характеризующий сущность книги в приложении
 * */
@Getter
@Setter
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

    /**
     * @param shortSpecification
     * обозначает перемунную, содержащую краткое описание книги
     * */
    @Column(name = "short_specification")
    private String shortSpecification;

    @Column(name = "number_pages")
    private Integer numberPages;


    /**
     * @param inStock
     * показывает состояние книги: в наличии или на руках
     * */
    @Column(name = "in_stock")
    private boolean inStock;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<Entry> entryList;

    public Book(){
    }

    public Book(String name, Author author, Genre genre, Publisher publisher, String shortSpecification, Integer numberPages) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.shortSpecification = shortSpecification;
        this.numberPages = numberPages;
        this.inStock = true;
    }
}
