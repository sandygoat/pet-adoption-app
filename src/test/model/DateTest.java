package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateTest {

    private Date defaultDate;

    @BeforeEach
    void runBefore() {
        defaultDate = new Date(10, 10, 2020);
    }

    @Test
    public void testSameDateTrue() {
        Date today = new Date(10,10,2020);
        assertTrue(defaultDate.sameDate(today));
    }

    @Test
    public void testSameDateFalse() {
        Date d1 = new Date(11,10,2020);
        Date d2 = new Date(10,3,2020);
        Date d3 = new Date(10,10,2022);

        assertFalse(defaultDate.sameDate(d1));
        assertFalse(defaultDate.sameDate(d2));
        assertFalse(defaultDate.sameDate(d3));
    }

    @Test
    public void testSameMonthSameDayTrue() {
        Date today = new Date(10,10,2025);
        assertTrue(defaultDate.sameMonthSameDay(today));
    }

    @Test
    public void testSameMonthSameDayFalse() {
        Date today = new Date(10,9,2025);
        Date someday = new Date(5,10,2020);

        assertFalse(defaultDate.sameMonthSameDay(today));
        assertFalse(defaultDate.sameMonthSameDay(someday));
    }

    @Test
    public void testPassOneYearTrue() {
        Date today = new Date(10,10,2021);
        Date someday = new Date(1,1,2022);
        Date anotherDay = new Date(11,1,2021);

        assertTrue(defaultDate.passOneYear(today));
        assertTrue(defaultDate.passOneYear(someday));
        assertTrue(defaultDate.passOneYear(anotherDay));
    }

    @Test
    public void testPassOneYearFalse() {
        Date d1 = new Date(10,9,2021);
        Date d2 = new Date(3,10,2021);
        Date d3 = new Date(10,10,2020);

        assertFalse(defaultDate.passOneYear(d1));
        assertFalse(defaultDate.passOneYear(d2));
        assertFalse(defaultDate.passOneYear(d3));
    }
}
