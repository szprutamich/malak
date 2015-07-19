package pl.malak.panels;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import pl.malak.DateLabelFormatter;
import pl.malak.beans.PracodawcaBean;
import pl.malak.dao.PracodawcaDao;
import pl.malak.helpers.UIHelper;
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

    private boolean editMode = true;

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
    JButton przegladajPrace = new JButton("Przeglądaj umowy o pracę");
    JButton dodajPrace = new JButton("Dodaj umowę o pracę");
    JButton przegladajZlecenia = new JButton("Przeglądaj umowy zlecenia");
    JButton dodajZlecenie = new JButton("Dodaj umowę zlecenie");

    public PracodawcaPanel() {
        super();

        teczkaUwagi.setEditable(true);
        ocenaUwagi.setEditable(true);
        szkoleniaOkresoweUwagi.setEditable(true);
        szkoleniaUwagi.setEditable(true);
        odziezowkaUwagi.setEditable(true);

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
        przegladajPrace.addActionListener(this);
        przegladajZlecenia.addActionListener(this);
    }

    private void layoutComponents() {
        int wiersz = 0;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(5, 15, 5, 15);
        c.ipady = 10;
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

        //wiersz 10
        wiersz++;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = wiersz;
        add(przegladajPrace, c);

        c.gridx = 2;
        c.gridy = wiersz;
        add(dodajPrace, c);

        //wiersz 11
        wiersz++;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = wiersz;
        add(przegladajZlecenia, c);

        c.gridx = 2;
        c.gridy = wiersz;
        add(dodajZlecenie, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nazwa) {
            initPracodawca();
        } else if (e.getSource() == zapisz) {
            String pracodawcaNazwa = UIHelper.getComboText(nazwa);
            if (pracodawcaNazwa == null) {
                UIHelper.displayMessage(this, "Nazwa nie może być pusta!");
                return;
            }
            Pracodawca pracodawca = pracodawcaDao.loadByName(pracodawcaNazwa);
            if (pracodawca == null) {
                pracodawcaBean.stworzPracodawce(
                        UIHelper.getComboText(nazwa),
                        teczka.isSelected(),
                        UIHelper.getComboText(teczkaUwagi),
                        ocena.isSelected(),
                        UIHelper.getComboText(ocenaUwagi),
                        szkoleniaOkresowe.isSelected(),
                        UIHelper.getComboText(szkoleniaOkresoweUwagi),
                        szkolenia.isSelected(),
                        UIHelper.datePickerGetDate(datePicker),
                        UIHelper.getComboText(szkoleniaUwagi),
                        odziezowka.isSelected(),
                        UIHelper.getComboText(odziezowkaUwagi));
                UIHelper.displayMessage(this, "Pracodawca został dodany pomyślnie.");
            } else if (!editMode) {
                UIHelper.displayMessage(this, "Pracodawca o podanej nazwie już istnieje!");
            } else {
                pracodawcaBean.uaktualnijPracodawce(
                        pracodawca.getId(),
                        UIHelper.getComboText(nazwa),
                        teczka.isSelected(),
                        UIHelper.getComboText(teczkaUwagi),
                        ocena.isSelected(),
                        UIHelper.getComboText(ocenaUwagi),
                        szkoleniaOkresowe.isSelected(),
                        UIHelper.getComboText(szkoleniaOkresoweUwagi),
                        szkolenia.isSelected(),
                        UIHelper.datePickerGetDate(datePicker),
                        UIHelper.getComboText(szkoleniaUwagi),
                        odziezowka.isSelected(),
                        UIHelper.getComboText(odziezowkaUwagi));
                UIHelper.displayMessage(this, "Pracodawca został uaktualniony pomyślnie.");
            }
        } else if (e.getSource() == przegladajZlecenia) {
            UIHelper.displayMessage(this, "Nie zaimplementowane!");
        } else if (e.getSource() == przegladajPrace) {
            UIHelper.displayMessage(this, "Nie zaimplementowane!");
        } else if (e.getSource() == dodajPrace) {
            UIHelper.displayMessage(this, "Nie zaimplementowane!");
        } else if (e.getSource() == dodajZlecenie) {
            UIHelper.displayMessage(this, "Nie zaimplementowane!");
        }
    }

    public void init() {
        java.util.List<String> pracodawcy = pracodawcaBean.listaNazwPracodawcow();
        for (String pracodawca : pracodawcy) {
            nazwa.addItem(pracodawca);
        }
        nazwa.setEditable(false);
        editMode = true;
    }

    public void initEmpty() {
        nazwa.removeAllItems();
        nazwa.setEditable(true);
        editMode = false;
    }

    private void initPracodawca() {
        String pracodawcaNazwa = UIHelper.getComboText(nazwa);
        Pracodawca pracodawca = pracodawcaDao.loadByName(pracodawcaNazwa);
        if (pracodawca != null && editMode) {
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
                ((UtilDateModel)datePicker.getModel()).setValue(date);
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
}
