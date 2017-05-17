package com.szymczak.controller;

import com.szymczak.dto.BreakfastDaysDto;
import com.szymczak.exception.DatesException;
import com.szymczak.handler.DateHandler;
import com.szymczak.model.BreakfastDate;
import com.szymczak.model.Reservation;
import com.szymczak.service.BreakfastDateService;
import com.szymczak.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Controller
@RequestMapping("/breakfast")
@Slf4j
public class BreakfastOfferController {

    private final ReservationService reservationService;
    private final BreakfastDateService breakfastDateService;
    private final DateHandler dateHandler;

    @Autowired
    public BreakfastOfferController(ReservationService reservationService, BreakfastDateService breakfastDateService, DateHandler dateHandler) {
        this.reservationService = reservationService;
        this.breakfastDateService = breakfastDateService;
        this.dateHandler = dateHandler;
    }


    @RequestMapping(value = "/selectDays", method = RequestMethod.GET)
    public String selectDate(ModelMap model, @RequestParam(value = "reservationId") int id,
                             @RequestParam(value = "startTime") String startTime,
                             @RequestParam(value = "finishTime") String finishTime) throws ParseException {

        if (startTime.equals("") || finishTime.equals(""))
            throw new DatesException("Dates cant be empty!");

        Calendar c = Calendar.getInstance();
        List<String> reservationDays = new ArrayList<String>();

        c.setTime(dateHandler.stringToUtilDate(startTime));

        for (int i = 0; i < dateHandler.calculateDaysBetweenDates(startTime, finishTime); i++) {
            c.add(Calendar.DATE, 1);
            reservationDays.add(dateHandler.utilDateToString(c.getTime()));
        }
        log.trace("Listing dates to purchase breakfast");
        model.addAttribute("reservationId", id);
        model.addAttribute("days", reservationDays);
        return "selectBreakfastDays";
    }




    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    public String purchaseBreakfast(@ModelAttribute BreakfastDaysDto breakfastDaysDto) throws ParseException {

        Reservation reservation = reservationService.display(breakfastDaysDto.getReservationId());

        for (int i = 0; i < breakfastDaysDto.getDays().length; i++) {
            if(breakfastDaysDto.getNumbersOfPeople()[i]!=0) {
                BreakfastDate breakfastDate = new BreakfastDate(breakfastDaysDto.getNumbersOfPeople()[i], dateHandler.mapStringToDate(breakfastDaysDto.getDays()[i]));

                breakfastDate.setReservation(reservation);
                breakfastDateService.insertOrUpdate(breakfastDate);
                log.trace("Added breakfast date to reservation");
                reservation.getBreakfastDates().add(breakfastDate);
                reservation.setIsWithBreakfast(true);
            }
            else
                log.error("User tried to buy 0 Breakfast!");
        }
        reservationService.insertOrUpdate(reservation);
        log.trace("Reservation successfully updated");

        return "redirect:/index";
    }
}
