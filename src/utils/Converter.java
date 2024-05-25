package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Converter {
    public static LocalDate stringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.parse(date, formatter);
    }
}
