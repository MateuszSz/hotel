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
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int maxNumberOfGuests;
    private int floor;
    private Float price;
    private Boolean isReserved;

    @Enumerated(EnumType.STRING)
    private WindowsOrientation windowsOrientation;

    @ManyToMany
    private Set<Reservation> reservations = new HashSet<Reservation>(0);


    public Room(int maxNumberOfGuests, WindowsOrientation windowsOrientation, Float price, int floor) {
        this.maxNumberOfGuests = maxNumberOfGuests;
        this.windowsOrientation = windowsOrientation;
        this.floor = floor;
        this.price = price;
        this.isReserved = false;
    }
}
