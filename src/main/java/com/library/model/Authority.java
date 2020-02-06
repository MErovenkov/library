package com.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "authority")
public class Authority implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_authority")
    private Integer id;

    @Column(name = "name_authority")
    private String nameAuthority;

    @JsonIgnore
    @ManyToMany(mappedBy = "authority", cascade = CascadeType.ALL)
    private List<User> usersList;

    private Authority(){}

    public Authority(String nameAuthority, List<User> usersList) {
        this.nameAuthority = nameAuthority;
        this.usersList = usersList;
    }
}
