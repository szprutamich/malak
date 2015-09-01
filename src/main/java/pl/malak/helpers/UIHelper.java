package pl.malak.helpers;


import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import pl.malak.DateLabelFormatter;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Properties;

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
        UtilDateModel model = (UtilDateModel) datePicker.getModel();
        if (model.isSelected()) {
            date = model.getValue();
        }
        return date;
    }

    public static JDatePickerImpl getJDatePicker() {
        Properties properties = new Properties();
        properties.put("text.today", "Dziś");
        properties.put("text.month", "Miesiąc");
        properties.put("text.year", "Rok");
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setTextEditable(true);
        datePicker.getJFormattedTextField().setHorizontalAlignment(JTextField.CENTER);
        datePicker.getJFormattedTextField().setDocument(new DateDocument());

        return datePicker;
    }

    public static void initDate(JDatePickerImpl datePicker, Date date) {
        if (date != null) {
            ((UtilDateModel) datePicker.getModel()).setValue(date);
            datePicker.getModel().setSelected(true);
        } else {
            datePicker.getModel().setSelected(false);
        }
    }
}
