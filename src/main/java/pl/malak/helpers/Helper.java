package pl.malak.helpers;

import java.util.Date;

/**
 * @author Michał Szpruta - szprutamich@gmail.com
 */
public class Helper {

    public static Date getDate(String text) {
        String[] dates = text.split("-");
        if (dates.length == 3) {
            int i = 0;
            return new Date(Integer.parseInt(dates[i++]) - 1900, Integer.parseInt(dates[i++]),
                    Integer.parseInt(dates[i]));
        }
        return null;
    }

    public static boolean getBoolean(String columnJest, String columnBrak) {
        return !columnBrak.trim().equalsIgnoreCase("BRAK") && (columnJest.trim().equalsIgnoreCase("JEST")
                || columnJest.trim().equalsIgnoreCase("TAK"));
    }

    public static String getTextOrNull(String text) {
        if (text.isEmpty()) {
            return null;
        }
        return text;
    }

}
