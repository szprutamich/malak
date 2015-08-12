package pl.malak.panels;

import org.jdatepicker.impl.JDatePickerImpl;
import pl.malak.beans.dao.PracodawcaDao;
import pl.malak.beans.dao.ZlecenieDao;
import pl.malak.helpers.Helper;
import pl.malak.helpers.UIHelper;
import pl.malak.model.Pracodawca;
import pl.malak.model.Zlecenie;
import pl.malak.panels.model.UIRow;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Michał Szpruta - szprutamich@gmail.com
 */
@org.springframework.stereotype.Component
public class ZleceniePanel extends FramePanel implements ActionListener {

    @Resource
    private PracodawcaDao pracodawcaDao;

    @Resource
    private ZlecenieDao zlecenieDao;

    private boolean editMode = true;

    private Zlecenie obecneZlecenie;

    private ArrayList<UIRow> rows = new ArrayList<>();

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
    JDatePickerImpl odbiorDatePicker = UIHelper.getJDatePicker();
    JDatePickerImpl umowaDatePicker = UIHelper.getJDatePicker();
    JDatePickerImpl szkolenieOkresoweDatePicker = UIHelper.getJDatePicker();
    JDatePickerImpl kartaSzkoleniaDatePicker = UIHelper.getJDatePicker();

    JButton zapisz = new JButton("Zapisz");
    JButton wroc = new JButton("Wróć");

    public ZleceniePanel() {
        super();

        rows.add(new UIRow(kwestionariusz, kwestionariuszUwagi, null));
        rows.add(new UIRow(kartaSzkolenia, kartaSzkoleniaUwagi, kartaSzkoleniaDatePicker));
        rows.add(new UIRow(szkolenieOgolne, szkolenieOgolneUwagi, null));
        rows.add(new UIRow(instruktaz, instruktazUwagi, null));
        rows.add(new UIRow(ryzyko, ryzykoUwagi, null));
        rows.add(new UIRow(instrukcje, instrukcjeUwagi, null));
        rows.add(new UIRow(szkolenieOkresowe, szkolenieOkresoweUwagi, szkolenieOkresoweDatePicker));
        rows.add(new UIRow(rachunki, rachunkiUwagi, null));
        rows.add(new UIRow(umowa, umowaUwagi, umowaDatePicker));
        rows.add(new UIRow(badania, badaniaUwagi, badaniaDatePicker));
        rows.add(new UIRow(odbior, odbiorUwagi, odbiorDatePicker));
        rows.add(new UIRow(zua, zuaUwagi, null));
        rows.add(new UIRow(zus, zusUwagi, null));
        rows.add(new UIRow(zza, zzaUwagi, null));
        rows.add(new UIRow(zwua, zwuaUwagi, zwuaDatePicker));
        rows.add(new UIRow(legitymacja, legitymacjaUwagi, null));
        rows.add(new UIRow(dowod, dowodUwagi, null));
        rows.add(new UIRow(zyciorys, zyciorysUwagi, null));
        rows.add(new UIRow(zaswiadczenieSanitarne, zaswiadczenieSanitarneUwagi, null));
        rows.add(new UIRow(zaswiadczenieStudent, zaswiadczenieStudentUwagi, null));
        rows.add(new UIRow(wyciagKodeks, wyciagKodeksUwagi, null));

        nazwaLabel.setFont(new Font(nazwaLabel.getFont().getFamily(), Font.PLAIN, 25));

        setMinimumSize(new Dimension(500, 770));

        layoutComponents();
        addListeners();
    }

    private void addListeners() {
        wroc.addActionListener(this);
        zapisz.addActionListener(this);
    }

    private void layoutComponents() {
        int wiersz = 0;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);
        c.ipady = 0;
        c.ipadx = 0;

        // --------------
        c.gridx = 0;
        c.gridy = wiersz;
        c.gridwidth = 3;
        add(pracodawcaNazwaLabel, c);

        // --------------
        wiersz++;
        c.gridx = 0;
        c.gridy = wiersz;
        c.gridwidth = 1;
        add(nazwaLabel, c);

        c.gridx = 1;
        c.gridy = wiersz;
        c.gridwidth = 2;
        add(nazwa, c);

//        // --------------
//        wiersz++;
//        c.gridwidth = 1;
//        c.gridx = 1;
//        c.gridy = wiersz;
//        add(uwagiLabel, c);
//
//        c.gridx = 2;
//        c.gridy = wiersz;
//        add(dataLabel, c);

        // --------------
        c.insets = new Insets(1, 5, 1, 5);

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
        c.insets = new Insets(5, 5, 5, 5);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = wiersz;
        add(zapisz, c);

        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = wiersz;
        add(wroc, c);
    }

    public void initPrzegladanie(Pracodawca pracodawca) {
        java.util.List<String> zlecenia = zlecenieDao.loadNamesByPracodawcaId(pracodawca.getId());
        for (String zlecenie : zlecenia) {
            nazwa.addItem(zlecenie);
        }
        nazwa.setEditable(false);
        editMode = true;
        init(pracodawca);
    }

    public void initDodawanie(Pracodawca pracodawca) {
        nazwa.removeAllItems();
        nazwa.setEditable(true);
        editMode = false;
        init(pracodawca);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == wroc) {
            getFrame().initPrzegladaniePracodawcow();
        } else if (e.getSource() == zapisz) {

        }
    }

    protected void init(Pracodawca pracodawca) {
        obecneZlecenie = null;
        pracodawcaNazwaLabel.setText(pracodawca.getNazwa());
        String zlecenieNazwa = UIHelper.getComboText(nazwa);
        Zlecenie zlecenie = zlecenieDao.loadByName(zlecenieNazwa);
        if (zlecenie != null && editMode) {
            obecneZlecenie = zlecenie;

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
//                ((UtilDateModel) szkoleniaDatePicker.getModel()).setValue(date);
//                szkoleniaDatePicker.getModel().setSelected(true);
//            } else {
//                szkoleniaDatePicker.getModel().setSelected(false);
//            }
        } else {
            for (UIRow row : rows) {
                row.getCheckBox().setSelected(false);
                row.getComboBox().setSelectedItem("");
                if (row.getDatePicker() != null) {
                    row.getDatePicker().getModel().setSelected(false);
                }
            }
        }
    }
}
