package com.szymczak.handler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class DateHandlerTest {


    private DateHandler dateHandler;
    private Date expectedSqlDate;
    private java.util.Date expectedUtilDate;

    @Before
    public void setUp() throws Exception {
        dateHandler = new DateHandler("dd-MM-yyyy");
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        expectedSqlDate = new java.sql.Date(df.parse("02-01-2011").getTime());
        expectedUtilDate = expectedSqlDate;

    }


    @Test
    public void testMapDateToString() throws Exception {

        Date valueToMap = expectedSqlDate;
        String expectedString = "02-01-2011";

        String actualValue = dateHandler.mapDateToString(valueToMap);

        assertEquals(expectedString, actualValue);

    }

    @Test
    public void testMapStringToDate() throws Exception {

        String valueToMap = "02-01-2011";
        Date expectedDate = expectedSqlDate;

        Date actualValue = dateHandler.mapStringToDate(valueToMap);

        assertEquals(expectedDate, actualValue);
    }

    @Test
    public void testCalculateDaysBetweenDates() throws Exception {

        String startDate = "02-01-2011";
        String finishDate = "10-02-2011";
        long expectedValue1 = 39L;
        long expectedValue2 = -39L;

        long actualValue1 = dateHandler.calculateDaysBetweenDates(startDate, finishDate);
        long actualValue2 = dateHandler.calculateDaysBetweenDates(finishDate, startDate);

        assertEquals(expectedValue1, actualValue1);
        assertEquals(expectedValue2, actualValue2);

    }

    @Test
    public void testStringToUtilDate() throws Exception {
        String valueToMap = "02-01-2011";
        java.util.Date expectedDate = expectedUtilDate;

        java.util.Date actualValue = dateHandler.stringToUtilDate(valueToMap);

        assertEquals(expectedDate, actualValue);
    }

    @Test
    public void utilDateToString() throws Exception {
        java.util.Date valueToMap = expectedUtilDate;
        String expectedString = "02-01-2011";

        String actualValue = dateHandler.utilDateToString(valueToMap);

        assertEquals(expectedString, actualValue);
    }

}