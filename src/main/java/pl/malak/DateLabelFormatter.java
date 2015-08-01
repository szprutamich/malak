package pl.malak;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        Object object = dateFormatter.parseObject(text);
        if (Date.class.isInstance(object)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(Date.class.cast(object));
            return cal;
        } else {
            return dateFormatter.parseObject(text);
        }
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}