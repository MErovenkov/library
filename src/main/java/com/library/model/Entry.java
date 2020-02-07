package com.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.model.enums.EntryStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
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

    public Entry(){}

    public Entry(ReaderCard readerCard, Book book,
                 LocalDate returnDatePlanned, LocalDate returnDate) {
        this.readerCard = readerCard;
        this.book = book;
        this.takeDate = LocalDate.now();
        this.returnDatePlanned = returnDatePlanned;
        this.returnDate = returnDate;
        this.entryStatus = EntryStatus.OPEN;
    }
}
