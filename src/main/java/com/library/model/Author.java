package com.library.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

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
    @ManyToMany
    @JoinTable(name = "author_genre",
            joinColumns = @JoinColumn(name = "id_author"),
            inverseJoinColumns = @JoinColumn(name = "id_genre"))
    private List<Genre> genreList;

    @JsonBackReference
    @OneToMany(mappedBy = "author", cascade = CascadeType.REFRESH)
    private List<Book> bookList;

    public Author(){
        super();
    }

    public Author(String surname, String name, String patronymic) {
        super(surname, name, patronymic);
    }
}