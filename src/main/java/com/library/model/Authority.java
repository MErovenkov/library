package com.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
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
    private Set<User> users;

    private Authority(){}

    public Authority(String nameAuthority, Set<User> users) {
        this.nameAuthority = nameAuthority;
        this.users = users;
    }
}
