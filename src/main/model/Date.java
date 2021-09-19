package model;

import org.json.JSONObject;
import persistence.WritableForModel;


// Represents a date with valid year, month, and date
public class Date implements WritableForModel {
    int month;
    int day;
    int year;

    // REQUIRES: m, d, y must have valid integer to represent month, day, and year
    // EFFECTS: creates a date with given integer of month, day, and year
    public Date(int m, int d, int y) {
        month = m;
        day = d;
        year = y;
    }

    // EFFECTS: returns month of a date
    public int getMonth() {
        return month;
    }

    // EFFECTS: returns day of a date
    public int getDay() {
        return day;
    }

    // EFFECTS: returns year of a date
    public int getYear() {
        return year;
    }

    // EFFECTS: returns true if parameter date is the same as the variable date, false otherwise
    public boolean sameDate(Date date) {
        return month == date.getMonth() && day == date.getDay() && year == date.getYear();
    }

    // EFFECTS: returns true if parameter date has the same month and day as the variable date, false otherwise
    public boolean sameMonthSameDay(Date date) {
        return month == date.getMonth() && day == date.getDay();
    }

    // EFFECTS: returns true if parameter date is more than 1 year after the variable date, false otherwise
    public boolean passOneYear(Date date) {
        return ((date.getYear() - year) >= 1 && (date.getMonth() - month) >= 0 && (date.getDay() - day) >= 0)
                | ((date.getYear() - year) >= 1 && (date.getMonth() - month) >= 1)
                | ((date.getYear() - year) >= 2);
    }


    // EFFECTS: write this into JSON format and return JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("day", day);
        json.put("month", month);
        json.put("year", year);
        return json;
    }
}
