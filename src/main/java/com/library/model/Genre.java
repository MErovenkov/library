package com.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * */

@Getter
@Setter
@Entity
@Table(name = "genre")
public class Genre implements Serializable {

    @Id
    @Column(name = "id_genre")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "genreList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Author> authorList;

    @JsonIgnore
    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Book> bookList;

    private Genre(){}

    public Genre(String name) {
        this.name = name;
    }
}
