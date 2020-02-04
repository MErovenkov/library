package com.library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.library.model.enums.EntryStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "entry")
public class Entry implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //
    @ManyToOne
    @JoinColumn(name = "id_reader_card")
    private ReaderCard readerCard;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_book")
    private Book book;

    @Column(name = "take_date")
    private LocalDate takeDate;

    @Column(name = "return_date_planned")
    private LocalDate returnDatePlanned;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "entry_status")
    private EntryStatus entryStatus;

    private Entry(){}

    public Entry(ReaderCard readerCard, Book book, LocalDate takeDate,
                 LocalDate returnDatePlanned, LocalDate returnDate, EntryStatus entryStatus) {
        this.readerCard = readerCard;
        this.book = book;
        this.takeDate = takeDate;
        this.returnDatePlanned = returnDatePlanned;
        this.returnDate = returnDate;
        this.entryStatus = entryStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReaderCard getReaderCard() {
        return readerCard;
    }

    public void setReaderCard(ReaderCard readerCard) {
        this.readerCard = readerCard;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getTakeDate() {
        return takeDate;
    }

    public void setTakeDate(LocalDate takeDate) {
        this.takeDate = takeDate;
    }

    public LocalDate getReturnDatePlanned() {
        return returnDatePlanned;
    }

    public void setReturnDatePlanned(LocalDate returnDatePlanned) {
        this.returnDatePlanned = returnDatePlanned;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public EntryStatus getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(EntryStatus entryStatus) {
        this.entryStatus = entryStatus;
    }
}
