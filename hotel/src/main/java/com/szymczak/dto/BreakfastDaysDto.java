package com.szymczak.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by mateu on 15.05.2017.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BreakfastDaysDto {
    int reservationId;
    private String[] days;
    private int[] numbersOfPeople;

}
