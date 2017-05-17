package com.szymczak.handler;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created by mateu on 15.05.2017.
 */
@Getter
@Setter
public class DateHandler {

    private SimpleDateFormat dateFormat;

    public DateHandler(String pattern) {
        this.dateFormat = new SimpleDateFormat(pattern);
    }

    public String mapDateToString(Date date) {
        return dateFormat.format(date);
    }

    public Date mapStringToDate(String date) throws ParseException {
        java.util.Date parsedDate = dateFormat.parse(date);
        return new Date(parsedDate.getTime());
    }

    public long calculateDaysBetweenDates(String stringDate1, String stringDate2) throws ParseException {

        java.util.Date utilDate1 = dateFormat.parse(stringDate1);
        java.util.Date utilDate2 = dateFormat.parse(stringDate2);


        long diff = utilDate2.getTime() - utilDate1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public java.util.Date stringToUtilDate(String stringDate) throws ParseException {
        return dateFormat.parse(stringDate);
    }

    public String utilDateToString(java.util.Date utilDate) {
        return dateFormat.format(utilDate);
    }

}
