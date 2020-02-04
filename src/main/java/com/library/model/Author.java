package com.library.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "author")
public class Author extends Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_author")
    private Integer id;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "author_genre",
            joinColumns =  @JoinColumn(name = "id_genre"),
            inverseJoinColumns =  @JoinColumn(name = "id_author"))
    private List<Genre> genreList;

    @JsonBackReference
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> bookList;

    private Author(){
        super();
    }

    public Author(String surname, String name, String patronymic) {
        super(surname, name, patronymic);
    }
}