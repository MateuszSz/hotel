package com.szymczak.service;

import com.szymczak.dto.ReservationDto;
import com.szymczak.model.Reservation;
import com.szymczak.repository.ReservationRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Created by mateu on 16.05.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceImplTest {

    @Mock
    private ReservationRepositoryImpl reservationRepository;

    @Mock
    private Reservation expectedReservation;

    @Mock
    private ReservationDto expectedReservationDto;

    @Before
    public void setUp() {

        List<ReservationDto> expectedListOfReservationDto = Arrays.asList(expectedReservationDto, expectedReservationDto);

        when(reservationRepository.display(Matchers.anyInt())).thenReturn(expectedReservation);
        when(reservationRepository.findReservationsByPersonEmail(Matchers.anyString())).thenReturn(expectedListOfReservationDto);
        Mockito.doNothing().when(reservationRepository).insertOrUpdate(expectedReservation);
    }

    @Test
    public void testDisplay() throws Exception {

        Reservation actualReservation = reservationRepository.display(1);

        assertNotNull(actualReservation);
        assertEquals(expectedReservation.getBreakfastDates(), actualReservation.getBreakfastDates());
        assertEquals(expectedReservation.getStartDate(), actualReservation.getStartDate());
        assertEquals(expectedReservation.getFinishDate(), actualReservation.getFinishDate());
        assertEquals(expectedReservation.getId(), actualReservation.getId());
        assertEquals(expectedReservation.getPerson(), actualReservation.getPerson());
        assertEquals(expectedReservation.getIsWithBreakfast(), actualReservation.getIsWithBreakfast());

    }

    @Test
    public void testFindReservationsByPersonEmail() {
        List<ReservationDto> expectedReservationDtos = reservationRepository.findReservationsByPersonEmail("Mateusz Szymczak");
        assertEquals(2, expectedReservationDtos.size());
        ReservationDto actualReservationDto = expectedReservationDtos.get(0);
        assertNotNull(actualReservationDto);
        assertEquals(expectedReservationDto.getFloor(), actualReservationDto.getFloor());
        assertEquals(expectedReservationDto.getStartDate(), actualReservationDto.getStartDate());
        assertEquals(expectedReservationDto.getFinishDate(), actualReservationDto.getFinishDate());
        assertEquals(expectedReservationDto.getPrice(), actualReservationDto.getPrice());
        assertEquals(expectedReservationDto.getWindowsOrientation(), actualReservationDto.getWindowsOrientation());

    }

    @Test
    public void testInsertOrUpdate() {
        reservationRepository.insertOrUpdate(expectedReservation);
    }


}