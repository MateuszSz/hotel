package com.szymczak.controller;

import com.szymczak.dto.RoomReservationDto;
import com.szymczak.exception.DatesException;
import com.szymczak.handler.DateHandler;
import com.szymczak.model.Person;
import com.szymczak.model.Reservation;
import com.szymczak.model.Room;
import com.szymczak.service.PersonService;
import com.szymczak.service.ReservationService;
import com.szymczak.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.text.ParseException;

/**
 * Created by mateu on 13.05.2017.
 */
@Controller
@RequestMapping("/reservation")
@Slf4j
public class ReservationController {

    private static final int MAX_DAYS_OF_RESERVATION = 7;
    private static final int MIN_DAYS_OF_RESERVATION = 1;
    private final PersonService personService;
    private final RoomService roomService;
    private final ReservationService reservationService;
    private final DateHandler dateHandler;

    @Autowired
    public ReservationController(PersonService personService, RoomService roomService, ReservationService reservationService, DateHandler dateHandler) {
        this.personService = personService;
        this.roomService = roomService;
        this.reservationService = reservationService;
        this.dateHandler = dateHandler;
    }


    @RequestMapping("")
    public String reservation() {
        return "reservation";
    }

    @RequestMapping(value = "/selectRoom", method = RequestMethod.POST)
    public String listRoomsToReserve(ModelMap model, @RequestParam(value = "startTime") String startTime, @RequestParam(value = "finishTime") String finishTime) throws ParseException {
        if (startTime.equals("") || finishTime.equals(""))
            throw new DatesException("Dates cant be empty!");

        long daysDiff = dateHandler.calculateDaysBetweenDates(startTime, finishTime);

        if (daysDiff < MIN_DAYS_OF_RESERVATION || daysDiff > MAX_DAYS_OF_RESERVATION) {
            model.addAttribute("message", "bad dates");
            log.debug("Bad dates - days between dates: " + daysDiff + " min 1 day, max 7 days");
            return "reservation";
        }
        log.trace("Listing rooms to reserve");
        model.addAttribute("listOfRooms", roomService.displayRoomsByReservationDate(startTime, finishTime));
        model.addAttribute("startTime", startTime);
        model.addAttribute("finishTime", finishTime);
        return "rooms";
    }


    @RequestMapping(value = "/reserveRoom", method = RequestMethod.POST)
    public String reserveRoom(Model model, Principal principal, @ModelAttribute RoomReservationDto roomReservation) throws ParseException {

        if (roomReservation.getStartTime().equals("") || roomReservation.getFinishTime().equals(""))
            throw new DatesException("Dates cant be empty!");


        Reservation reservation = new Reservation(dateHandler.mapStringToDate(roomReservation.getStartTime()),
                dateHandler.mapStringToDate(roomReservation.getFinishTime()), false);
        Person person = personService.findByEmail(principal.getName());
        reservation.setPerson(person);
        person.getReservations().add(reservation);
        reservationService.insertOrUpdate(reservation);
        personService.insertOrUpdate(person);

        for (String roomIdInString : roomReservation.getRooms()) {
            Room room = roomService.display(Integer.parseInt(roomIdInString));
            room.setIsReserved(true);
            reservation.getRooms().add(room);
            room.getReservations().add(reservation);
            roomService.insertOrUpdate(room);
            log.trace("Added room to reservation");

        }

        reservationService.insertOrUpdate(reservation);
        log.trace("Reservation successfully completed");

        model.addAttribute("startTime", roomReservation.getStartTime());
        model.addAttribute("finishTime", roomReservation.getFinishTime());
        model.addAttribute("reservationId", reservation.getId());


        return "successfulReservation";
    }
}
