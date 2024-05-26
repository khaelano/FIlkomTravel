/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Converter {
    public static LocalDate stringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.parse(date, formatter);
    }
}
