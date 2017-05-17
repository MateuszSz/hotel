package com.szymczak.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by mateu on 16.05.2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
    private int id;
    private int floor;
    private int maxNumberOfGuests;
    private Float price;
    private String windowsOrientation;
}
