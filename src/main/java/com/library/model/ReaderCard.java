package com.library.model;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "readerCard")
    private List<Entry> entryList;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    private ReaderCard(){}

    public ReaderCard(String surname, String name, String patronymic, Integer numberPhone, String email) {
        super(surname, name, patronymic);
        this.numberPhone = numberPhone;
        this.email = email;
        this.penalty = 0;
    }

    public ReaderCard(String surname, String name, String patronymic, Integer numberPhone) {
        super(surname, name, patronymic);
        this.numberPhone = numberPhone;
        this.email = null;
        this.penalty = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(Integer numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPenalty() {
        return penalty;
    }

    public void setPenalty(Integer penalty) {
        this.penalty = penalty;
    }

    public List<Entry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}