package com.library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//todo:
/**
 *
 */

@Getter
@Setter
@Entity
@Table(name = "publisher")
public class Publisher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_publisher")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    private List<Book> bookList;

    public Publisher(){}

    public Publisher(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
