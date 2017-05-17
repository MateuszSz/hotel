package com.szymczak.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    private Date startDate;
    private Date finishDate;
    private int floor;
    private int maxNumberOfGuests;
    private Float price;
    private String windowsOrientation;
}
