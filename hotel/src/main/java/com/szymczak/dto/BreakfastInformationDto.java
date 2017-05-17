package com.szymczak.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

/**
 * Created by mateu on 16.05.2017.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BreakfastInformationDto {
    private Date date;
    private int numberOfPeople;
}
