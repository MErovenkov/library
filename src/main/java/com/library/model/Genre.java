package com.library.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

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

    @Column(name = "name", unique = true)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "genreList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Author> authorList;

    @JsonBackReference
    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> bookList;

    private Genre(){}

    public Genre(String name) {
        this.name = name;
    }
}
