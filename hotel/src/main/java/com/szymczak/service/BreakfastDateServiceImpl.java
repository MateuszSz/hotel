package com.szymczak.service;

import com.szymczak.dto.BreakfastInformationDto;
import com.szymczak.model.BreakfastDate;
import com.szymczak.repository.BreakfastDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mateu on 12.05.2017.
 */
@Service
public class BreakfastDateServiceImpl implements BreakfastDateService {

    private final BreakfastDateRepository breakfastDateRepository;

    @Autowired
    public BreakfastDateServiceImpl(BreakfastDateRepository breakfastDateRepository) {
        this.breakfastDateRepository = breakfastDateRepository;
    }

    public void insertOrUpdate(BreakfastDate breakfastDate) {
        breakfastDateRepository.insertOrUpdate(breakfastDate);
    }

    public BreakfastDate display(int id) {
        return breakfastDateRepository.display(id);
    }

    public List<BreakfastInformationDto> displayDatesByPersonEmail(String email) {
        return breakfastDateRepository.displayDatesByPersonEmail(email);
    }
}
