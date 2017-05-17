package com.szymczak.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by mateu on 14.05.2017.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomReservationDto {
    private String startTime;
    private String finishTime;
    private String[] rooms;

}
