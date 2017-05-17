package com.szymczak.controller;

import com.szymczak.dto.RoomDto;
import com.szymczak.handler.DateHandler;
import com.szymczak.service.RoomService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class ReservationControllerTest {

    @InjectMocks
    private ReservationController reservationController;

    @Mock
    private RoomService mockRoomService;

    @Mock
    private View mockView;

    private MockMvc mockMvc;

    @Mock
    private DateHandler dateHandler;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(reservationController).setSingleView(mockView).build();

    }

    @Test
    public void testListRoomsToReserveWithGoodDates() throws Exception {
        String startTime = "2017-06-20";
        String finishTime = "2017-06-22";
        List<RoomDto> expectedRooms = Collections.singletonList(new RoomDto());

        when(mockRoomService.displayRoomsByReservationDate(startTime, finishTime)).thenReturn(expectedRooms);
        when(dateHandler.calculateDaysBetweenDates(startTime, finishTime)).thenReturn(2L);

        mockMvc.perform(post("/reservation/selectRoom").param("startTime", startTime)
                .param("finishTime", finishTime))
                .andExpect(status().isOk())
                .andExpect(model().attribute("listOfRooms", expectedRooms))
                .andExpect(model().attribute("startTime", startTime))
                .andExpect(model().attribute("finishTime", finishTime))
                .andExpect(view().name("rooms"));


    }

    @Test
    public void testListRoomsToReserveWithWrongDates() throws Exception {
        String startTime = "2017-07-10";
        String finishTime = "2017-07-26";
        List<RoomDto> expectedRooms = Collections.singletonList(new RoomDto());

        when(mockRoomService.displayRoomsByReservationDate(startTime, finishTime)).thenReturn(expectedRooms);
        when(dateHandler.calculateDaysBetweenDates(startTime, finishTime)).thenReturn(16L);

        mockMvc.perform(post("/reservation/selectRoom").param("startTime", startTime)
                .param("finishTime", finishTime))
                .andExpect(status().isOk())
                .andExpect(view().name("reservation"))
                .andExpect(model().attribute("message", "bad dates"));
    }
}
