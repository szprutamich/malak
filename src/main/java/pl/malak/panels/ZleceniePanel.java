package pl.malak.panels;

import org.jdatepicker.impl.JDatePickerImpl;
import pl.malak.beans.PracodawcaBean;
import pl.malak.dao.PracodawcaDao;
import pl.malak.dao.ZlecenieDao;
import pl.malak.helpers.Helper;
import pl.malak.helpers.UIHelper;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Michał Szpruta - szprutamich@gmail.com
 */
@org.springframework.stereotype.Component
public class ZleceniePanel extends JPanel implements ActionListener {

    @Resource
    private PracodawcaBean pracodawcaBean;

    @Resource
    private PracodawcaDao pracodawcaDao;

    @Resource
    private ZlecenieDao zlecenieDao;

    private boolean editMode = true;

    JLabel pracodawcaNazwaLabel = new JLabel("Pracodawca:");
    JLabel nazwaLabel = new JLabel("Pracownik:");
    JLabel uwagiLabel = new JLabel("Uwagi:");
    JLabel dataLabel = new JLabel("Do kiedy:");

    JLabel pracodawca = new JLabel();
    JComboBox<String> nazwa = new JComboBox<>();

    JCheckBox kwestionariusz = new JCheckBox("<html>Kwestionariusz osobowy<br> + oświadczenie zleceniobiorcy</html>");
    JCheckBox kartaSzkolenia = new JCheckBox("Karta szkolenia wstępnego");
    JCheckBox szkolenieOgolne = new JCheckBox("Szkolenie ogólne");
    JCheckBox instruktaz = new JCheckBox("Instruktaż stanowiskowy");
    JCheckBox ryzyko = new JCheckBox("Ryzyko zawodowe");
    JCheckBox instrukcje = new JCheckBox("Instrukcje BHP");
    JCheckBox szkolenieOkresowe = new JCheckBox("Szkolenie okresowe BHP");
    JCheckBox rachunki = new JCheckBox("Rachunki");
    JCheckBox umowa = new JCheckBox("Umowa zlecenie");
    JCheckBox badania = new JCheckBox("Badania lekarskie");
    JCheckBox odbior = new JCheckBox("Odbiór odzieży");
    JCheckBox zua = new JCheckBox("Zua");
    JCheckBox zus = new JCheckBox("Zus");
    JCheckBox zza = new JCheckBox("Zza");
    JCheckBox zwua = new JCheckBox("Zwua");
    JCheckBox legitymacja = new JCheckBox("Legitymacja szkolna");
    JCheckBox dowod = new JCheckBox("Dowód osobisty");
    JCheckBox zyciorys = new JCheckBox("Życiorys");
    JCheckBox zaswiadczenieSanitarne = new JCheckBox("<html>Zaświadczenie<br> sanitarno-epidemiologiczne</html>");
    JCheckBox zaswiadczenieStudent = new JCheckBox("Zaświadczenie - student");
    JCheckBox wyciagKodeks = new JCheckBox("Wyciąg z kodeksu pracy");

    JComboBox<String> kwestionariuszUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> kartaSzkoleniaUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> szkolenieOgolneUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> instruktazUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> ryzykoUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> instrukcjeUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> szkolenieOkresoweUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> rachunkiUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> umowaUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> badaniaUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> odbiorUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> zuaUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> zusUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> zzaUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> zwuaUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> legitymacjaUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> dowodUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> zyciorysUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> zaswiadczenieSanitarneUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> zaswiadczenieStudentUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> wyciagKodeksUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);

    JDatePickerImpl badaniaDatePicker = UIHelper.getJDatePicker();
    JDatePickerImpl zwuaDatePicker = UIHelper.getJDatePicker();
    JDatePickerImpl odbiorOdziezyDatePicker = UIHelper.getJDatePicker();
    JDatePickerImpl umowaDatePicker = UIHelper.getJDatePicker();
    JDatePickerImpl szkolenieBhpDatePicker = UIHelper.getJDatePicker();
    JDatePickerImpl kartaSzkoleniaDatePicker = UIHelper.getJDatePicker();

    JButton zapisz = new JButton("Zapisz");
    JButton wroc = new JButton("Wróć");

    public ZleceniePanel() {
        super();

        kwestionariuszUwagi.setEditable(true);
        kartaSzkoleniaUwagi.setEditable(true);
        szkolenieOgolneUwagi.setEditable(true);
        instruktazUwagi.setEditable(true);
        ryzykoUwagi.setEditable(true);
        instrukcjeUwagi.setEditable(true);
        szkolenieOkresoweUwagi.setEditable(true);
        rachunkiUwagi.setEditable(true);
        umowaUwagi.setEditable(true);
        odbiorUwagi.setEditable(true);
        zuaUwagi.setEditable(true);
        zusUwagi.setEditable(true);
        zzaUwagi.setEditable(true);
        zwuaUwagi.setEditable(true);

        nazwaLabel.setFont(new Font(nazwaLabel.getFont().getFamily(), Font.PLAIN, 25));

        setMinimumSize(new Dimension(500, 770));

        layoutComponents();
        addListeners();
    }

    private void addListeners() {
//        nazwa.addActionListener(this);
//        zapisz.addActionListener(this);
//        przegladajPrace.addActionListener(this);
//        przegladajZlecenia.addActionListener(this);
    }

    private void layoutComponents() {
        int wiersz = 0;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);
        c.ipady = 0;
        c.ipadx = 0;
//        c.weightx = 1;

        // --------------
        c.gridx = 0;
        c.gridy = wiersz;
        c.gridwidth = 3;
        add(pracodawcaNazwaLabel, c);

        // --------------
        c.gridx = 0;
        c.gridy = wiersz;
        c.gridwidth = 3;
        add(nazwaLabel, c);

        // --------------
        wiersz++;
        c.gridx = 0;
        c.gridy = wiersz;
        c.gridwidth = 3;
        add(nazwa, c);

        // --------------
        wiersz++;
        c.insets = new Insets(0, 5, 0, 5);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = wiersz;
        add(uwagiLabel, c);

        c.gridx = 2;
        c.gridy = wiersz;
        add(dataLabel, c);

        // --------------
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(kwestionariusz, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(kwestionariuszUwagi, c);

        // --------------
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(kartaSzkolenia, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(kartaSzkoleniaUwagi, c);

        c.gridx = 2;
        c.gridy = wiersz;
        add(kartaSzkoleniaDatePicker, c);

        // --------------
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(szkolenieOgolne, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(szkolenieOgolneUwagi, c);

        // --------------
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(instruktaz, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(instruktazUwagi, c);

        // --------------
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(ryzyko, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(ryzykoUwagi, c);

        // --------------
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(instrukcje, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(instrukcjeUwagi, c);

        // --------------
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(szkolenieOkresowe, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(szkolenieOkresoweUwagi, c);

        c.gridx = 2;
        c.gridy = wiersz;
        add(szkolenieBhpDatePicker, c);

        // --------------
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(rachunki, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(rachunkiUwagi, c);

        // --------------
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(umowa, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(umowaUwagi, c);

        c.gridx = 2;
        c.gridy = wiersz;
        add(umowaDatePicker, c);

        // --------------
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(badania, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(badaniaUwagi, c);

        c.gridx = 2;
        c.gridy = wiersz;
        add(badaniaDatePicker, c);

        // --------------
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(odbior, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(odbiorUwagi, c);

        c.gridx = 2;
        c.gridy = wiersz;
        add(odbiorOdziezyDatePicker, c);

        // --------------
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(zua, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(zuaUwagi, c);


        // --------------
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(zus, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(zusUwagi, c);

        // --------------
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(zza, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(zzaUwagi, c);

        // --------------
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(zwua, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(zwuaUwagi, c);

        c.gridx = 2;
        c.gridy = wiersz;
        add(zwuaDatePicker, c);

        // --------------
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(legitymacja, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(legitymacjaUwagi, c);

        // --------------
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(dowod, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(dowodUwagi, c);

        // --------------
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(zyciorys, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(zyciorysUwagi, c);

        // --------------
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(zaswiadczenieSanitarne, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(zaswiadczenieSanitarneUwagi, c);

        // --------------
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(zaswiadczenieStudent, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(zaswiadczenieStudentUwagi, c);

        // --------------
        wiersz++;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = wiersz;
        add(wyciagKodeks, c);

        c.gridx = 1;
        c.gridy = wiersz;
        add(wyciagKodeksUwagi, c);

        // --------------
        wiersz++;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = wiersz;
        c.weighty = 0.5;
        add(zapisz, c);

        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = wiersz;
        add(wroc, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == nazwa) {
//            initPracodawca();
//        } else if (e.getSource() == zapisz) {
//            String pracodawcaNazwa = UIHelper.getComboText(nazwa);
//            if (pracodawcaNazwa == null) {
//                UIHelper.displayMessage(this, "Nazwa nie może być pusta!");
//                return;
//            }
//            Pracodawca pracodawca = pracodawcaDao.loadByName(pracodawcaNazwa);
//            if (pracodawca == null) {
//                pracodawcaBean.stworzPracodawce(
//                        UIHelper.getComboText(nazwa),
//                        teczka.isSelected(),
//                        UIHelper.getComboText(teczkaUwagi),
//                        ocena.isSelected(),
//                        UIHelper.getComboText(ocenaUwagi),
//                        szkoleniaOkresowe.isSelected(),
//                        UIHelper.getComboText(szkoleniaOkresoweUwagi),
//                        szkolenia.isSelected(),
//                        UIHelper.datePickerGetDate(datePicker),
//                        UIHelper.getComboText(szkoleniaUwagi),
//                        odziezowka.isSelected(),
//                        UIHelper.getComboText(odziezowkaUwagi));
//                UIHelper.displayMessage(this, "Pracodawca został dodany pomyślnie.");
//            } else if (!editMode) {
//                UIHelper.displayMessage(this, "Pracodawca o podanej nazwie już istnieje!");
//            } else {
//                pracodawcaBean.uaktualnijPracodawce(
//                        pracodawca.getId(),
//                        UIHelper.getComboText(nazwa),
//                        teczka.isSelected(),
//                        UIHelper.getComboText(teczkaUwagi),
//                        ocena.isSelected(),
//                        UIHelper.getComboText(ocenaUwagi),
//                        szkoleniaOkresowe.isSelected(),
//                        UIHelper.getComboText(szkoleniaOkresoweUwagi),
//                        szkolenia.isSelected(),
//                        UIHelper.datePickerGetDate(datePicker),
//                        UIHelper.getComboText(szkoleniaUwagi),
//                        odziezowka.isSelected(),
//                        UIHelper.getComboText(odziezowkaUwagi));
//                UIHelper.displayMessage(this, "Pracodawca został uaktualniony pomyślnie.");
//            }
//        } else if (e.getSource() == przegladajZlecenia) {
//            UIHelper.displayMessage(this, "Nie zaimplementowane!");
//        } else if (e.getSource() == przegladajPrace) {
//            UIHelper.displayMessage(this, "Nie zaimplementowane!");
//        } else if (e.getSource() == dodajPrace) {
//            UIHelper.displayMessage(this, "Nie zaimplementowane!");
//        } else if (e.getSource() == dodajZlecenie) {
//            UIHelper.displayMessage(this, "Nie zaimplementowane!");
//        }
    }

//    public void init() {
//        java.util.List<String> pracodawcy = pracodawcaDao.loadAllNames(false);
//        for (String pracodawca : pracodawcy) {
//            nazwa.addItem(pracodawca);
//        }
//        nazwa.setEditable(false);
//        editMode = true;
//        initPracodawca();
//        przegladajPrace.setVisible(true);
//        przegladajZlecenia.setVisible(true);
//        dodajZlecenie.setVisible(true);
//        dodajPrace.setVisible(true);
//    }
//
//    public void initEmpty() {
//        nazwa.removeAllItems();
//        nazwa.setEditable(true);
//        editMode = false;
//        przegladajPrace.setVisible(false);
//        przegladajZlecenia.setVisible(false);
//        dodajZlecenie.setVisible(false);
//        dodajPrace.setVisible(false);
//    }
//
//    private void initPracodawca() {
//        String pracodawcaNazwa = UIHelper.getComboText(nazwa);
//        Pracodawca pracodawca = pracodawcaDao.loadByName(pracodawcaNazwa);
//        if (pracodawca != null && editMode) {
//            teczka.setSelected(pracodawca.getTeczka());
//            ocena.setSelected(pracodawca.getOcena());
//            szkoleniaOkresowe.setSelected(pracodawca.getSzkoleniaOkresowe());
//            szkolenia.setSelected(pracodawca.getSzkoleniaPracodawcy());
//            odziezowka.setSelected(pracodawca.getOdziezowka());
//            teczkaUwagi.setSelectedItem(pracodawca.getTeczkaUwagi());
//            ocenaUwagi.setSelectedItem(pracodawca.getOcenaUwagi());
//            szkoleniaOkresoweUwagi.setSelectedItem(pracodawca.getSzkoleniaOkresoweUwagi());
//            odziezowkaUwagi.setSelectedItem(pracodawca.getOdziezowkaUwagi());
//            Date date = pracodawca.getSzkoleniaPracodawcyData();
//            if (date != null) {
//                ((UtilDateModel)datePicker.getModel()).setValue(date);
//                datePicker.getModel().setSelected(true);
//            } else {
//                datePicker.getModel().setSelected(false);
//            }
//        } else {
//            teczka.setSelected(false);
//            ocena.setSelected(false);
//            szkoleniaOkresowe.setSelected(false);
//            szkolenia.setSelected(false);
//            odziezowka.setSelected(false);
//            teczkaUwagi.setSelectedItem("");
//            ocenaUwagi.setSelectedItem("");
//            szkoleniaOkresoweUwagi.setSelectedItem("");
//            odziezowkaUwagi.setSelectedItem("");
//            datePicker.getModel().setSelected(false);
//        }
//    }
}
