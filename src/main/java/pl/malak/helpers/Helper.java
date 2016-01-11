package pl.malak.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {

    public static String[] PREDEFINIOWANE_UWAGI = {
            "",
            "Wypisać",
            "Podpisać",
            "Uzupełnić",
            "Wypełnić",
            "Nie dotyczy"
    };

    public static String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
