package com.library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.model.enums.EntryStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Класс, представляет собой
 * описание свойст записи
 * об аренде книги
 * */

@Getter
@Setter
@Entity
@Table(name = "entry")
public class Entry implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_reader_card")
    private ReaderCard readerCard;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_book")
    private Book book;

    /**
     * @param takeDate
     * обозначающий дату взятия книги
     * */
    @Column(name = "take_date")
    private LocalDate takeDate;

    /**
     * @param returnDatePlanned
     * обозначающий дату, когда пользователь должен вернуть арендованную книгу
     * */
    @Column(name = "return_date_planned")
    private LocalDate returnDatePlanned;

    /**
     * @param returnDate
     * дата, когда польователь
     * фактически вернул кунигу
     * */
    @Column(name = "return_date")
    private LocalDate returnDate;

    /**
     * @param entryStatus
     * свойство, показывающее, в каком состоянии находиться запись
     * (OPEN - открыта
     * CLOSE - закрыта, в том случае, когда книга возвращена
     * EXPIRED - просрочена)
     * */
    @Column(name = "entry_status")
    private EntryStatus entryStatus;

    public Entry(){
        this.takeDate = LocalDate.now();
        this.entryStatus = EntryStatus.OPEN;
    }

    public Entry(ReaderCard readerCard, Book book,
                 LocalDate returnDatePlanned) {
        this.readerCard = readerCard;
        this.book = book;
        this.takeDate = LocalDate.now();
        this.returnDatePlanned = returnDatePlanned;
        this.entryStatus = EntryStatus.OPEN;
    }
}
