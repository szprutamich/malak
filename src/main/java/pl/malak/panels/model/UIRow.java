package pl.malak.panels.model;

import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;

/**
 * @author Michał Szpruta - szprutamich@gmail.com
 */
public class UIRow {

    private JCheckBox checkBox;

    private JComboBox<String> comboBox;

    private JDatePickerImpl datePicker;

    public UIRow(JCheckBox checkBox, JComboBox<String> comboBox, JDatePickerImpl datePicker) {
        this.checkBox = checkBox;
        this.comboBox = comboBox;
        this.datePicker = datePicker;
    }

    public JCheckBox getCheckBox() {
        return checkBox;
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    public JDatePickerImpl getDatePicker() {
        return datePicker;
    }
}
