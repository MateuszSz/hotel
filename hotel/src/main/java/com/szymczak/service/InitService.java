package com.szymczak.service;

import com.szymczak.model.*;
import com.szymczak.repository.*;
import com.szymczak.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
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
public class InitService {


    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private BreakfastDateRepository breakfastDateRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoleRepository roleRepository;




    @PostConstruct
    public void init() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Random random = new Random();
        Person person = new Person("rekrutacja@pgs-soft.com", passwordEncoder.encode("rekrutacja"), "Mateusz Szymczak");
        personRepository.save(person);

        Role clientRole = new Role("CLIENT");
        roleRepository.save(clientRole);


        Calendar startTime = Calendar.getInstance();
        Calendar finishTime = Calendar.getInstance();
        finishTime.add(Calendar.DAY_OF_MONTH, 7);
        Reservation reservation = new Reservation(new Date(startTime.getTimeInMillis()), new Date(finishTime.getTimeInMillis()), true);
        reservation.setPerson(person);
        reservationRepository.save(reservation);
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

            roomRepository.save(room);
        }
        clientRole.getPeople().add(person);
        person.getRoles().add(clientRole);
        roleRepository.save(clientRole);
        personRepository.save(person);

        BreakfastDate breakfastDate = new BreakfastDate(3, new Date(startTime.getTimeInMillis()));
        BreakfastDate breakfastDate2 = new BreakfastDate(2, new Date(finishTime.getTimeInMillis()));
        breakfastDate.setReservation(reservation);
        breakfastDate2.setReservation(reservation);
        breakfastDateRepository.save(breakfastDate);
        breakfastDateRepository.save(breakfastDate2);
        reservation.getBreakfastDates().add(breakfastDate);
        reservation.getBreakfastDates().add(breakfastDate2);
        reservationRepository.save(reservation);
        personRepository.save(person);
        log.trace("Added sample data to BD");
    }
}
