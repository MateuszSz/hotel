package com.szymczak.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mateu on 16.05.2017.
 */
@AllArgsConstructor
@Getter
@Setter
public class DatesException extends RuntimeException {

    private String errorMessage;
}
