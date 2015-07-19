package pl.malak.panels;

import org.jdatepicker.DateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import pl.malak.DateLabelFormatter;
import pl.malak.beans.PracodawcaBean;
import pl.malak.dao.PracodawcaDao;
import pl.malak.model.Pracodawca;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;

/**
 * @author Michał Szpruta - szprutamich@gmail.com
 */
@org.springframework.stereotype.Component
public class PracodawcaPanel extends JPanel implements ActionListener {

    @Resource
    private PracodawcaBean pracodawcaBean;

    @Resource
    private PracodawcaDao pracodawcaDao;

    private String[] predefiniowaneUwagi = {
            "",
            "Wypisać",
            "Dopisać",
            "Podpisać",
            "Nie dotyczy"
    };

    JLabel nazwaLabel = new JLabel("Pracodawca:");
    JLabel uwagiLabel = new JLabel("Uwagi:");
    JLabel dataLabel = new JLabel("Do kiedy:");
    JComboBox<String> nazwa = new JComboBox<>();
    JCheckBox teczka = new JCheckBox("Teczka ogólna");
    JCheckBox ocena = new JCheckBox("Ocena ryzyka");
    JCheckBox szkoleniaOkresowe = new JCheckBox("Szkolenia okresowe");
    JCheckBox szkolenia = new JCheckBox("Szkolenia pracodawcy");
    JCheckBox odziezowka = new JCheckBox("Odzieżówka");
    JComboBox<String> teczkaUwagi = new JComboBox<>(predefiniowaneUwagi);
    JComboBox<String> ocenaUwagi = new JComboBox<>(predefiniowaneUwagi);
    JComboBox<String> szkoleniaOkresoweUwagi = new JComboBox<>(predefiniowaneUwagi);
    JComboBox<String> szkoleniaUwagi = new JComboBox<>(predefiniowaneUwagi);
    JComboBox<String> odziezowkaUwagi = new JComboBox<>(predefiniowaneUwagi);
    JDatePickerImpl datePicker = getJDatePicker();
    JButton zapisz = new JButton("Zapisz");

    public PracodawcaPanel() {
        super();

        teczkaUwagi.setEditable(true);
        ocenaUwagi.setEditable(true);
        szkoleniaOkresoweUwagi.setEditable(true);
        szkoleniaUwagi.setEditable(true);
        odziezowkaUwagi.setEditable(true);
        nazwa.setEditable(true);

        nazwaLabel.setFont(new Font(nazwaLabel.getFont().getFamily(), Font.PLAIN, 25));

        layoutComponents();
        addListeners();
    }

    private JDatePickerImpl getJDatePicker() {
        Properties properties = new Properties();
        properties.put("text.today", "Dziś");
        properties.put("text.month", "Miesiąc");
        properties.put("text.year", "Rok");
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.getJFormattedTextField().setHorizontalAlignment(JTextField.CENTER);

        return datePicker;
    }

    private void addListeners() {
        nazwa.addActionListener(this);
        zapisz.addActionListener(this);
    }

    private void layoutComponents() {
        int wiersz = 0;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(5, 15, 5, 15);
        c.ipady = 12;
        c.weightx = 0.5;

        // wiersz 1
        c.gridx = 0;
        c.gridy = wiersz;
        c.gridwidth = 3;
        add(nazwaLabel, c);

        // wiersz 2
        wiersz++;
        c.gridx = 0;
        c.gridy = wiersz;
        c.gridwidth = 3;
        add(nazwa, c);

        //wiersz 3
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = wiersz;
        add(uwagiLabel, c);

        c.gridx = 2;
        c.gridy = wiersz;
        add(dataLabel, c);

        //wiersz 4
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(teczka, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(teczkaUwagi, c);

        //wiersz 5
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(ocena, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(ocenaUwagi, c);

        //wiersz 6
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(szkoleniaOkresowe, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(szkoleniaOkresoweUwagi, c);

        //wiersz 7
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(szkolenia, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(szkoleniaUwagi, c);

        c.gridx = 2;
        c.gridy = wiersz;
        add(datePicker, c);

        //wiersz 8
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(odziezowka, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(odziezowkaUwagi, c);

        //wiersz 9
        wiersz++;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = wiersz;
        c.weighty = 1.0;
        add(zapisz, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nazwa) {
            initPracodawca();
        } else if (e.getSource() == zapisz) {
            String pracodawcaNazwa = nazwa.getSelectedItem().toString();
            Pracodawca pracodawca = pracodawcaDao.loadByName(pracodawcaNazwa);
            if (pracodawca == null) {
                pracodawca = pracodawcaBean.stworzPracodawce(
                        getComboText(nazwa),
                        teczka.isSelected(),
                        getComboText(teczkaUwagi),
                        ocena.isSelected(),
                        getComboText(ocenaUwagi),
                        szkoleniaOkresowe.isSelected(),
                        getComboText(szkoleniaOkresoweUwagi),
                        szkolenia.isSelected(),
                        datePickerGetDate(),
                        getComboText(szkoleniaUwagi),
                        odziezowka.isSelected(),
                        getComboText(odziezowkaUwagi));
                nazwa.addItem(pracodawca.getNazwa());
            } else {
                pracodawcaBean.uaktualnijPracodawce(
                        pracodawca.getId(),
                        getComboText(nazwa),
                        teczka.isSelected(),
                        getComboText(teczkaUwagi),
                        ocena.isSelected(),
                        getComboText(ocenaUwagi),
                        szkoleniaOkresowe.isSelected(),
                        getComboText(szkoleniaOkresoweUwagi),
                        szkolenia.isSelected(),
                        datePickerGetDate(),
                        getComboText(szkoleniaUwagi),
                        odziezowka.isSelected(),
                        getComboText(odziezowkaUwagi));
            }
        }
    }

    public void init() {
        java.util.List<String> pracodawcy = pracodawcaBean.listaNazwPracodawcow();
        for (String pracodawca : pracodawcy) {
            nazwa.addItem(pracodawca);
        }
    }

    public void initPracodawca() {
        String pracodawcaNazwa = nazwa.getSelectedItem().toString();
        Pracodawca pracodawca = pracodawcaDao.loadByName(pracodawcaNazwa);
        if (pracodawca != null) {
            teczka.setSelected(pracodawca.getTeczka());
            ocena.setSelected(pracodawca.getOcena());
            szkoleniaOkresowe.setSelected(pracodawca.getSzkoleniaOkresowe());
            szkolenia.setSelected(pracodawca.getSzkoleniaPracodawcy());
            odziezowka.setSelected(pracodawca.getOdziezowka());
            teczkaUwagi.setSelectedItem(pracodawca.getTeczkaUwagi());
            ocenaUwagi.setSelectedItem(pracodawca.getOcenaUwagi());
            szkoleniaOkresoweUwagi.setSelectedItem(pracodawca.getSzkoleniaOkresoweUwagi());
            odziezowkaUwagi.setSelectedItem(pracodawca.getOdziezowkaUwagi());
            Date date = pracodawca.getSzkoleniaPracodawcyData();
            if (date != null) {
                datePicker.getModel().setDate(date.getYear() + 1900, date.getMonth(), date.getDate());
                datePicker.getModel().setSelected(true);
            } else {
                datePicker.getModel().setSelected(false);
            }
        } else {
            teczka.setSelected(false);
            ocena.setSelected(false);
            szkoleniaOkresowe.setSelected(false);
            szkolenia.setSelected(false);
            odziezowka.setSelected(false);
            teczkaUwagi.setSelectedItem("");
            ocenaUwagi.setSelectedItem("");
            szkoleniaOkresoweUwagi.setSelectedItem("");
            odziezowkaUwagi.setSelectedItem("");
            datePicker.getModel().setSelected(false);
        }
    }

    private Date datePickerGetDate() {
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

    private String getComboText(JComboBox<String> comboBox) {
        return comboBox.getSelectedItem() != null ? comboBox.getSelectedItem().toString() : null;
    }
}
