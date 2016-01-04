package pl.malak.panels;

import org.jdatepicker.impl.JDatePickerImpl;
import pl.malak.beans.PracodawcaBean;
import pl.malak.beans.dao.PracaDao;
import pl.malak.beans.dao.PracodawcaDao;
import pl.malak.beans.dao.ZlecenieDao;
import pl.malak.helpers.Helper;
import pl.malak.helpers.UIHelper;
import pl.malak.model.Pracodawca;
import pl.malak.panels.model.UIRow;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@org.springframework.stereotype.Component
public class PracodawcaPanel extends FramePanel implements ActionListener {

    @Resource
    private PracodawcaBean pracodawcaBean;

    @Resource
    private PracodawcaDao pracodawcaDao;

    @Resource
    private ZlecenieDao zlecenieDao;

    @Resource
    private PracaDao pracaDao;

    private boolean editMode = true;

    private Pracodawca obecnyPracodawca;

    private ArrayList<UIRow> rows = new ArrayList<>();

    JLabel nazwaLabel = new JLabel("Pracodawca:");
    JLabel uwagiLabel = new JLabel("Uwagi:");
    JLabel dataLabel = new JLabel("Do kiedy:");
    JComboBox<String> nazwa = new JComboBox<>();
    JCheckBox teczka = new JCheckBox("Teczka ogólna");
    JCheckBox ocena = new JCheckBox("Ocena ryzyka");
    JCheckBox szkoleniaOkresowe = new JCheckBox("Szkolenia okresowe");
    JCheckBox szkolenia = new JCheckBox("Szkolenia pracodawcy");
    JCheckBox odziezowka = new JCheckBox("Odzieżówka");
    JComboBox<String> teczkaUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> ocenaUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> szkoleniaOkresoweUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> szkoleniaUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> odziezowkaUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JDatePickerImpl szkoleniaDatePicker = UIHelper.getJDatePicker();
    JButton zapisz = new JButton("Zapisz");
    JButton przegladajPrace = new JButton("Przeglądaj umowy o pracę");
    JButton dodajPrace = new JButton("Dodaj umowę o pracę");
    JButton przegladajZlecenia = new JButton("Przeglądaj umowy zlecenia");
    JButton dodajZlecenie = new JButton("Dodaj umowę zlecenie");
    JButton generujEmail = new JButton("Generuj email");

    JLabel emailLabel = new JLabel("Email:");
    JTextField email = new JTextField();

    public PracodawcaPanel() {
        super();

        teczkaUwagi.setEditable(true);
        ocenaUwagi.setEditable(true);
        szkoleniaOkresoweUwagi.setEditable(true);
        szkoleniaUwagi.setEditable(true);
        odziezowkaUwagi.setEditable(true);
        email.setEditable(true);

        nazwaLabel.setFont(new Font(nazwaLabel.getFont().getFamily(), Font.PLAIN, 25));

        rows.add(new UIRow(teczka, teczkaUwagi, null));
        rows.add(new UIRow(ocena, ocenaUwagi, null));
        rows.add(new UIRow(szkoleniaOkresowe, szkoleniaOkresoweUwagi, null));
        rows.add(new UIRow(szkolenia, szkoleniaUwagi, szkoleniaDatePicker));
        rows.add(new UIRow(odziezowka, odziezowkaUwagi, null));

        nazwa.setEditable(true);

        layoutComponents();
        addListeners();
    }

    private void addListeners() {
        nazwa.addActionListener(this);
        zapisz.addActionListener(this);
        przegladajPrace.addActionListener(this);
        przegladajZlecenia.addActionListener(this);
        dodajZlecenie.addActionListener(this);
        dodajPrace.addActionListener(this);
        generujEmail.addActionListener(this);
    }

    private void layoutComponents() {
        int wiersz = 0;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 15, 5, 15);
        c.ipady = 0;
        c.ipadx = 0;

        // --------------
        c.gridx = 0;
        c.gridy = wiersz;
        c.gridwidth = 1;
        add(nazwaLabel, c);

        c.gridx = 1;
        c.gridy = wiersz;
        c.gridwidth = 2;
        add(nazwa, c);

        // --------------
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(emailLabel, c);
        c.gridwidth = 2;
        c.gridx = 1;
        add(email, c);

        // --------------
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = wiersz;
        add(uwagiLabel, c);

        c.gridx = 2;
        c.gridy = wiersz;
        add(dataLabel, c);

        // --------------

        for (UIRow row : rows) {
            wiersz++;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = wiersz;
            add(row.getCheckBox(), c);

            c.gridx = 1;
            c.gridy = wiersz;
            add(row.getComboBox(), c);
            row.getComboBox().setEditable(true);

            if (row.getDatePicker() != null) {
                c.gridx = 2;
                c.gridy = wiersz;
                add(row.getDatePicker(), c);
            }
        }

        // --------------
        wiersz++;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = wiersz;
        add(zapisz, c);

        // --------------
        wiersz++;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = wiersz;
        add(przegladajPrace, c);

        c.gridx = 2;
        c.gridy = wiersz;
        add(dodajPrace, c);

        // --------------
        wiersz++;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = wiersz;
        add(przegladajZlecenia, c);

        c.gridx = 2;
        c.gridy = wiersz;
        add(dodajZlecenie, c);

        // --------------
        wiersz++;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = wiersz;
        add(generujEmail, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nazwa) {
            init();
        } else if (e.getSource() == zapisz) {
            String pracodawcaNazwa = UIHelper.getComboText(nazwa);
            if (pracodawcaNazwa == null) {
                UIHelper.displayMessage(this, "Nazwa nie może być pusta!");
                return;
            }
            Pracodawca pracodawca = pracodawcaDao.loadByName(pracodawcaNazwa);
            if (!editMode && pracodawca == null) {
                pracodawcaBean.stworzPracodawce(
                        UIHelper.getComboText(nazwa),
                        email.getText(),
                        teczka.isSelected(),
                        UIHelper.getComboText(teczkaUwagi),
                        ocena.isSelected(),
                        UIHelper.getComboText(ocenaUwagi),
                        szkoleniaOkresowe.isSelected(),
                        UIHelper.getComboText(szkoleniaOkresoweUwagi),
                        szkolenia.isSelected(),
                        UIHelper.datePickerGetDate(szkoleniaDatePicker),
                        UIHelper.getComboText(szkoleniaUwagi),
                        odziezowka.isSelected(),
                        UIHelper.getComboText(odziezowkaUwagi));
                UIHelper.displayMessage(this, "Pracodawca został dodany pomyślnie.");
            } else if (!editMode) {
                UIHelper.displayMessage(this, "Pracodawca o podanej nazwie już istnieje!");
            } else {
                String nazwaText = UIHelper.getComboText(nazwa);
                pracodawcaBean.uaktualnijPracodawce(
                        obecnyPracodawca.getId(),
                        nazwaText,
                        email.getText(),
                        teczka.isSelected(),
                        UIHelper.getComboText(teczkaUwagi),
                        ocena.isSelected(),
                        UIHelper.getComboText(ocenaUwagi),
                        szkoleniaOkresowe.isSelected(),
                        UIHelper.getComboText(szkoleniaOkresoweUwagi),
                        szkolenia.isSelected(),
                        UIHelper.datePickerGetDate(szkoleniaDatePicker),
                        UIHelper.getComboText(szkoleniaUwagi),
                        odziezowka.isSelected(),
                        UIHelper.getComboText(odziezowkaUwagi));
                nazwa.removeItem(obecnyPracodawca.getNazwa());
                nazwa.addItem(nazwaText);
                nazwa.setSelectedItem(nazwaText);
                UIHelper.displayMessage(this, "Pracodawca został uaktualniony pomyślnie.");
            }
        } else if (e.getSource() == przegladajZlecenia) {
            getFrame().initPrzeglądanieZlecen(obecnyPracodawca);
        } else if (e.getSource() == przegladajPrace) {
            getFrame().initPrzeglądaniePrac(obecnyPracodawca);
        } else if (e.getSource() == dodajPrace) {
            getFrame().initDodajPrace(obecnyPracodawca);
        } else if (e.getSource() == dodajZlecenie) {
            getFrame().initDodajZlecenie(obecnyPracodawca);
        } else if (e.getSource() == generujEmail) {
            getFrame().initGenerujEmail(obecnyPracodawca);
        }
    }

    public void initPrzegladanie(Pracodawca pracodawca) {
        refreshNazwaList();
        if (pracodawca != null) {
            nazwa.setSelectedItem(pracodawca.getNazwa());
        }
        editMode = true;
        init();
        dodajZlecenie.setVisible(true);
        dodajPrace.setVisible(true);
    }

    public void initDodawanie() {
        nazwa.removeAllItems();
        editMode = false;
        for (UIRow row : rows) {
            row.getCheckBox().setSelected(false);
            row.getComboBox().setSelectedItem("");
            if (row.getDatePicker() != null) {
                row.getDatePicker().getModel().setSelected(false);
            }
        }
        przegladajPrace.setVisible(false);
        przegladajZlecenia.setVisible(false);
        dodajZlecenie.setVisible(false);
        dodajPrace.setVisible(false);
    }

    protected void init() {
        String pracodawcaNazwa = UIHelper.getComboText(nazwa);
        Pracodawca pracodawca = pracodawcaDao.loadByName(pracodawcaNazwa);
        if (pracodawca != null && editMode) {
            obecnyPracodawca = pracodawca;

            przegladajZlecenia.setVisible(zlecenieDao.countByPracodawcaId(pracodawca.getId()) > 0);
            przegladajPrace.setVisible(pracaDao.countByPracodawcaId(pracodawca.getId()) > 0);

            email.setText(pracodawca.getEmail());
            teczka.setSelected(pracodawca.getTeczka());
            ocena.setSelected(pracodawca.getOcena());
            szkoleniaOkresowe.setSelected(pracodawca.getSzkoleniaOkresowe());
            szkolenia.setSelected(pracodawca.getSzkoleniaPracodawcy());
            odziezowka.setSelected(pracodawca.getOdziezowka());
            teczkaUwagi.setSelectedItem(pracodawca.getTeczkaUwagi());
            ocenaUwagi.setSelectedItem(pracodawca.getOcenaUwagi());
            szkoleniaUwagi.setSelectedItem(pracodawca.getSzkoleniaPracodawcyUwagi());
            szkoleniaOkresoweUwagi.setSelectedItem(pracodawca.getSzkoleniaOkresoweUwagi());
            odziezowkaUwagi.setSelectedItem(pracodawca.getOdziezowkaUwagi());

            UIHelper.initDate(szkoleniaDatePicker, pracodawca.getSzkoleniaPracodawcyData());
        }
    }

    private void refreshNazwaList() {
        nazwa.removeAllItems();
        java.util.List<String> pracodawcy = pracodawcaDao.loadAllNames(false);
        for (String pracodawca : pracodawcy) {
            nazwa.addItem(pracodawca);
        }
    }
}
