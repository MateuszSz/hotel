package com.szymczak.service;

import com.szymczak.model.*;
import com.szymczak.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by mateu on 11.05.2017.
 */
@Service
@Slf4j
public class InitServiceImpl {

    private final PersonRepository personRepository;
    private final ReservationRepository reservationRepository;
    private final BreakfastDateRepository breakfastDateRepository;
    private final RoomRepository roomRepository;
    private final RoleRepository roleRepository;


    @Autowired
    public InitServiceImpl(PersonRepository personRepository, ReservationRepository reservationRepository, BreakfastDateRepository breakfastDateRepository, RoomRepository roomRepository, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.reservationRepository = reservationRepository;
        this.breakfastDateRepository = breakfastDateRepository;
        this.roomRepository = roomRepository;
        this.roleRepository = roleRepository;
    }


    @PostConstruct
    public void init() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Random random = new Random();
        Person person = new Person("rekrutacja@pgs-soft.com", passwordEncoder.encode("rekrutacja"), "Mateusz Szymczak");
        personRepository.insertOrUpdate(person);

        Role clientRole = new Role("CLIENT");
        roleRepository.insertOrUpdate(clientRole);


        Calendar startTime = Calendar.getInstance();
        Calendar finishTime = Calendar.getInstance();
        finishTime.add(Calendar.DAY_OF_MONTH, 7);
        Reservation reservation = new Reservation(new Date(startTime.getTimeInMillis()), new Date(finishTime.getTimeInMillis()), true);
        reservation.setPerson(person);
        reservationRepository.insertOrUpdate(reservation);
        int randInt;
        for (int i = 0; i < 25; i++) {
            Room room = new Room(random.nextInt(4) + 1, WindowsOrientation.NORTH, 100f, randInt = random.nextInt(11));
            if (room.getMaxNumberOfGuests() == 4) {
                room.setPrice(60f);
            } else if (room.getMaxNumberOfGuests() == 3)
                room.setPrice(70f);
            else if (room.getMaxNumberOfGuests() == 2)
                room.setPrice(80f);
            if (i % 3 == 0) {
                reservation.getRooms().add(room);
                room.setIsReserved(true);
                room.getReservations().add(reservation);
            }
            randInt = random.nextInt(4) + 1;
            if (randInt == 1)
                room.setWindowsOrientation(WindowsOrientation.NORTH);
            else if (randInt == 2)
                room.setWindowsOrientation(WindowsOrientation.EAST);
            else if (randInt == 3)
                room.setWindowsOrientation(WindowsOrientation.SOUTH);
            else
                room.setWindowsOrientation(WindowsOrientation.WEST);

            roomRepository.insertOrUpdate(room);
        }
        clientRole.getPeople().add(person);
        person.getRoles().add(clientRole);
        roleRepository.insertOrUpdate(clientRole);
        personRepository.insertOrUpdate(person);

        BreakfastDate breakfastDate = new BreakfastDate(3, new Date(startTime.getTimeInMillis()));
        BreakfastDate breakfastDate2 = new BreakfastDate(2, new Date(finishTime.getTimeInMillis()));
        breakfastDate.setReservation(reservation);
        breakfastDate2.setReservation(reservation);
        breakfastDateRepository.insertOrUpdate(breakfastDate);
        breakfastDateRepository.insertOrUpdate(breakfastDate2);
        reservation.getBreakfastDates().add(breakfastDate);
        reservation.getBreakfastDates().add(breakfastDate2);
        reservationRepository.insertOrUpdate(reservation);
        personRepository.insertOrUpdate(person);
        log.trace("Added sample data to BD");
    }
}
