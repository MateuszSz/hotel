package com.szymczak.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;


    @ManyToMany
    private Set<Role> roles = new HashSet<Role>(0);

    @OneToMany(mappedBy = "person", orphanRemoval = true)
    private Set<Reservation> reservations = new HashSet<Reservation>(0);


    public Person(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
