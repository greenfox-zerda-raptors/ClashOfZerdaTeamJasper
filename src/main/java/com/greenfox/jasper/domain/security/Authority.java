package com.greenfox.jasper.domain.security;

import com.greenfox.jasper.domain.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by almasics on 2017.02.02..
 */
@Entity
@Table(name = "AUTHORITY")
@SequenceGenerator(name="authority_seq", initialValue=100)
public class Authority {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_seq")
    //@SequenceGenerator(name = "authority_seq", sequenceName = "authority_seq", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthorityName name;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthorityName getName() {
        return name;
    }

    public void setName(AuthorityName name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
