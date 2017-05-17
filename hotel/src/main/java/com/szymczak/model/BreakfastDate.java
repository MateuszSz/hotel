package com.szymczak.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by mateu on 12.05.2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BreakfastDate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int numberOfPeople;
    private Date date;

    @ManyToOne
    private Reservation reservation;

    public BreakfastDate(int numberOfPeople, Date date) {
        this.numberOfPeople = numberOfPeople;
        this.date = date;
    }
}
