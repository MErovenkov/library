package com.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Класс, описывающий роль пользователя в приложении
 * */

@Getter
@Setter
@Entity
@Table(name = "authority")
public class Authority implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_authority")
    private Integer id;

    @Column(name = "name")
    private String nameAuthority;

    @JsonIgnore
    @ManyToMany(mappedBy = "authorityList")
    private List<User> usersList;

    public Authority(){}

    public Authority(String nameAuthority, List<User> usersList) {
        this.nameAuthority = nameAuthority;
        this.usersList = usersList;
    }
}
