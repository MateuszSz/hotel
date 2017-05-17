package com.szymczak.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mateu on 12.05.2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date startDate;
    private Date finishDate;
    private Boolean isWithBreakfast;


    @ManyToOne
    private Person person;

    @ManyToMany(mappedBy = "reservations")
    private Set<Room> rooms = new HashSet<Room>(0);

    @OneToMany(mappedBy = "reservation", orphanRemoval = true)
    private Set<BreakfastDate> breakfastDates = new HashSet<BreakfastDate>(0);

    public Reservation(Date startDate, Date finishDate, Boolean isWithBreakfast) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.isWithBreakfast = isWithBreakfast;
    }
}
