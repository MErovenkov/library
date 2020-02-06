package com.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "author_genre")
public class AuthorGenre implements Serializable {

    @EmbeddedId
    AuthorGenreKey id;

    @ManyToOne
    @MapsId("id_author")
    @JoinColumn(name = "id_author")
    private Author author;

    @ManyToOne
    @MapsId("id_genre")
    @JoinColumn(name = "id_genre")
    private Genre genre;

    public AuthorGenre(){}
}
