package com.library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class AuthorGenreKey implements Serializable {
    @Column(name = "id_author")
    Long idAuthor;

    @Column(name = "id_genre")
    Long idGenre;

    public AuthorGenreKey() {
    }
}
