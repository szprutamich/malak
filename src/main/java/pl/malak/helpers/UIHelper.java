package pl.malak.helpers;


import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * @author Michał Szpruta - szprutamich@gmail.com
 */
public class UIHelper {

    public static void displayMessage(Component component, String text) {
        JOptionPane.showMessageDialog(component, text);
    }

    public static String displayInput(Component component, String text) {
        return JOptionPane.showInputDialog(component, text);
    }

    public static String getComboText(JComboBox<String> comboBox) {
        return comboBox.getSelectedItem() != null ? comboBox.getSelectedItem().toString() : null;
    }

    public static Date datePickerGetDate(JDatePicker datePicker) {
        Date date = null;
        DateModel model = datePicker.getModel();
        if (model.isSelected()) {
            date = new Date();
            date.setYear(model.getYear() - 1900);
            date.setMonth(model.getMonth());
            date.setDate(model.getDay());
        }
        return date;
    }
}
