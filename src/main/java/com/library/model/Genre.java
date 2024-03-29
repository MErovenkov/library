package com.library.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *  Класс Жанр,
 *  служит для обозначния принадлежности книг
 *  и авторов к какому-то определённому направлению.
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
    @ManyToMany(mappedBy = "genreList", cascade = CascadeType.REFRESH)
    private List<Author> authorList;

    @JsonIgnore
    @OneToMany(mappedBy = "genre", cascade = CascadeType.REFRESH)
    private List<Book> bookList;

    public Genre(){}

    public Genre(String name) {
        this.name = name;
    }
}
