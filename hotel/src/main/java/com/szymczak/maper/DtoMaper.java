package com.szymczak.maper;

import com.szymczak.dto.BreakfastInformationDto;
import com.szymczak.dto.ReservationDto;
import com.szymczak.dto.RoomDto;
import com.szymczak.handler.DateHandler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mateu on 25.05.2017.
 */
public class DtoMaper {

    private DateHandler dateHandler;

    public DtoMaper(){
        dateHandler = new DateHandler("yyyy-MM-dd");
    }
    public List<BreakfastInformationDto> toBreakfastInformationDto(List list) throws ParseException {
        List<BreakfastInformationDto> dtos = new ArrayList<BreakfastInformationDto>();
        BreakfastInformationDto dto;

        Object[] arrayOfData;
        for(Object o : list){
            arrayOfData = (Object[]) o;
            dateHandler.mapStringToDate(arrayOfData[0].toString());

            dto = new BreakfastInformationDto(dateHandler.mapStringToDate(arrayOfData[0].toString()), Integer.parseInt(arrayOfData[1].toString()));
            dtos.add(dto);
        }
        return dtos;
    }

    public List<ReservationDto> toReservationDto(List list) throws ParseException {
        List<ReservationDto> dtos = new ArrayList<ReservationDto>();
        ReservationDto dto;

        Object[] arrayOfData;
        for(Object o : list){
            arrayOfData = (Object[]) o;
            dateHandler.mapStringToDate(arrayOfData[0].toString());

            dto = new ReservationDto(dateHandler.mapStringToDate(arrayOfData[0].toString()), dateHandler.mapStringToDate(arrayOfData[1].toString()),
                                    Integer.parseInt(arrayOfData[2].toString()), Integer.parseInt(arrayOfData[3].toString()), Float.parseFloat(arrayOfData[4].toString()),
                                            arrayOfData[5].toString());

            dtos.add(dto);
        }
        return dtos;
    }



    public List<RoomDto> toRoomDto(List list) throws ParseException {
        List<RoomDto> dtos = new ArrayList<RoomDto>();
        RoomDto dto;

        Object[] arrayOfData;
        for(Object o : list){
            arrayOfData = (Object[]) o;

            dto = new RoomDto(Integer.parseInt(arrayOfData[0].toString()), Integer.parseInt(arrayOfData[1].toString()),
                    Integer.parseInt(arrayOfData[2].toString()), Float.parseFloat(arrayOfData[3].toString()), arrayOfData[4].toString());

            dtos.add(dto);
        }
        return dtos;
    }
}
