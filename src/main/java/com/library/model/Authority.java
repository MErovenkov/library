package com.library.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "authority")
public class Authority implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_authority")
    private Integer id;

    @Column(name = "name_authority")
    private String nameAuthority;

    @ManyToMany(mappedBy = "authority", cascade = CascadeType.ALL)
    private Set<User> users;

    private Authority(){}

    public Authority(String nameAuthority, Set<User> users) {
        this.nameAuthority = nameAuthority;
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameAuthority() {
        return nameAuthority;
    }

    public void setNameAuthority(String nameAuthority) {
        this.nameAuthority = nameAuthority;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
