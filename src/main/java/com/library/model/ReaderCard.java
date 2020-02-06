package com.library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "reader_card")
public class ReaderCard extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reader_card")
    private Integer id;

    @Column(name = "number_phone")
    private Integer numberPhone;

    @Column(name = "email")
    private String email;

    @Column(name = "penalty")
    private Integer penalty;

    @Column(name = "max_books_taken")
    private Integer maxBooksTaken;

    @OneToMany(mappedBy = "readerCard")
    private List<Entry> entryList;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    public ReaderCard(){}

    public ReaderCard(String surname, String name, String patronymic, Integer numberPhone, String email) {
        super(surname, name, patronymic);
        this.numberPhone = numberPhone;
        this.email = email;
        this.penalty = 0;
        this.maxBooksTaken = 3;
    }

    public ReaderCard(String surname, String name, String patronymic, Integer numberPhone) {
        super(surname, name, patronymic);
        this.numberPhone = numberPhone;
        this.email = null;
        this.penalty = 0;
        this.maxBooksTaken = 3;
    }
}