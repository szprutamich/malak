package pl.malak.panels;

import org.jdatepicker.impl.JDatePickerImpl;
import pl.malak.beans.ZlecenieBean;
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
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Michał Szpruta - szprutamich@gmail.com
 */
@org.springframework.stereotype.Component
public class ZleceniePanel extends FramePanel implements ActionListener {

    @Resource
    private ZlecenieBean zlecenieBean;

    @Resource
    private ZlecenieDao zlecenieDao;

    private boolean editMode = true;

    private Zlecenie obecneZlecenie;

    private Pracodawca obecnyPracodawca;

    private ArrayList<UIRow> rows = new ArrayList<>();

    JLabel pracodawcaNazwaLabel = new JLabel("Pracodawca:");
    JLabel nazwaLabel = new JLabel("Pracownik:");
    JLabel uwagiLabel = new JLabel("Uwagi:");
    JLabel dataLabel = new JLabel("Do kiedy:");

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
    JCheckBox zza = new JCheckBox("Zza");
    JCheckBox zwua = new JCheckBox("Zwua");
    JCheckBox dowod = new JCheckBox("Dowód osobisty");
    JCheckBox zyciorys = new JCheckBox("Życiorys");
    JCheckBox zaswiadczenieStudent = new JCheckBox("Zaświadczenie - student");

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
    JComboBox<String> zzaUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> zwuaUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> dowodUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> zyciorysUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JComboBox<String> zaswiadczenieStudentUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);

    JDatePickerImpl badaniaDatePicker = UIHelper.getJDatePicker();
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
        rows.add(new UIRow(zza, zzaUwagi, null));
        rows.add(new UIRow(zwua, zwuaUwagi, null));
        rows.add(new UIRow(dowod, dowodUwagi, null));
        rows.add(new UIRow(zyciorys, zyciorysUwagi, null));
        rows.add(new UIRow(zaswiadczenieStudent, zaswiadczenieStudentUwagi, null));

        Font font = new Font(pracodawcaNazwaLabel.getFont().getFamily(), Font.BOLD, 18);
        Map<TextAttribute, Object> attributes = new HashMap<>();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        pracodawcaNazwaLabel.setFont(font.deriveFont(attributes));
        nazwaLabel.setFont(new Font(nazwaLabel.getFont().getFamily(), Font.PLAIN, 25));
        nazwa.setEditable(true);

        setMinimumSize(new Dimension(500, 770));

        layoutComponents();
        addListeners();
    }

    private void addListeners() {
        wroc.addActionListener(this);
        zapisz.addActionListener(this);
        nazwa.addActionListener(this);
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
        init(pracodawca);
        refreshNazwaList(pracodawca.getId());
        editMode = true;
    }

    public void initDodawanie(Pracodawca pracodawca) {
        nazwa.removeAllItems();
        editMode = false;
        init(pracodawca);
        for (UIRow row : rows) {
            row.getCheckBox().setSelected(false);
            row.getComboBox().setSelectedItem("");
            if (row.getDatePicker() != null) {
                row.getDatePicker().getModel().setSelected(false);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nazwa) {
            init(obecnyPracodawca);
        } else if (e.getSource() == wroc) {
            getFrame().initPrzegladaniePracodawcow(obecnyPracodawca);
        } else if (e.getSource() == zapisz) {
            String zlecenieNazwa = UIHelper.getComboText(nazwa);
            if (zlecenieNazwa == null) {
                UIHelper.displayMessage(this, "Nazwa nie może być pusta!");
                return;
            }
            Zlecenie zlecenie = zlecenieDao.loadByName(zlecenieNazwa);
            if (!editMode && zlecenie == null) {
                zlecenieBean.stworzZlecenie(
                        kwestionariusz.isSelected(),
                        UIHelper.getComboText(kwestionariuszUwagi),
                        kartaSzkolenia.isSelected(),
                        UIHelper.getComboText(kartaSzkoleniaUwagi),
                        UIHelper.datePickerGetDate(kartaSzkoleniaDatePicker),
                        szkolenieOgolne.isSelected(),
                        UIHelper.getComboText(szkolenieOgolneUwagi),
                        instruktaz.isSelected(),
                        UIHelper.getComboText(instruktazUwagi),
                        ryzyko.isSelected(),
                        UIHelper.getComboText(ryzykoUwagi),
                        instrukcje.isSelected(),
                        UIHelper.getComboText(instrukcjeUwagi),
                        szkolenieOkresowe.isSelected(),
                        UIHelper.getComboText(szkolenieOkresoweUwagi),
                        UIHelper.datePickerGetDate(szkolenieOkresoweDatePicker),
                        rachunki.isSelected(),
                        UIHelper.getComboText(rachunkiUwagi),
                        umowa.isSelected(),
                        UIHelper.getComboText(umowaUwagi),
                        UIHelper.datePickerGetDate(umowaDatePicker),
                        odbior.isSelected(),
                        UIHelper.getComboText(odbiorUwagi),
                        UIHelper.datePickerGetDate(odbiorDatePicker),
                        zua.isSelected(),
                        UIHelper.getComboText(zuaUwagi),
                        zza.isSelected(),
                        UIHelper.getComboText(zzaUwagi),
                        zwua.isSelected(),
                        UIHelper.getComboText(zwuaUwagi),
                        UIHelper.getComboText(nazwa),
                        badania.isSelected(),
                        UIHelper.getComboText(badaniaUwagi),
                        UIHelper.datePickerGetDate(badaniaDatePicker),
                        dowod.isSelected(),
                        UIHelper.getComboText(dowodUwagi),
                        zyciorys.isSelected(),
                        UIHelper.getComboText(zyciorysUwagi),
                        zaswiadczenieStudent.isSelected(),
                        UIHelper.getComboText(zaswiadczenieStudentUwagi),
                        obecnyPracodawca.getId()
                );
                UIHelper.displayMessage(this, "Zlecenie zostało dodane pomyślnie.");
            } else if (!editMode && zlecenie.getPracodawca().getId().equals(obecnyPracodawca.getId())) {
                UIHelper.displayMessage(this, "Zlecenie o podanej nazwie dla tego pracodawcy już istnieje!");
            } else {
                String nazwaText = UIHelper.getComboText(nazwa);
                zlecenieBean.uaktualnijZlecenie(
                        obecneZlecenie.getId(),
                        kwestionariusz.isSelected(),
                        UIHelper.getComboText(kwestionariuszUwagi),
                        kartaSzkolenia.isSelected(),
                        UIHelper.getComboText(kartaSzkoleniaUwagi),
                        UIHelper.datePickerGetDate(kartaSzkoleniaDatePicker),
                        szkolenieOgolne.isSelected(),
                        UIHelper.getComboText(szkolenieOgolneUwagi),
                        instruktaz.isSelected(),
                        UIHelper.getComboText(instruktazUwagi),
                        ryzyko.isSelected(),
                        UIHelper.getComboText(ryzykoUwagi),
                        instrukcje.isSelected(),
                        UIHelper.getComboText(instrukcjeUwagi),
                        szkolenieOkresowe.isSelected(),
                        UIHelper.getComboText(szkolenieOkresoweUwagi),
                        UIHelper.datePickerGetDate(szkolenieOkresoweDatePicker),
                        rachunki.isSelected(),
                        UIHelper.getComboText(rachunkiUwagi),
                        umowa.isSelected(),
                        UIHelper.getComboText(umowaUwagi),
                        UIHelper.datePickerGetDate(umowaDatePicker),
                        odbior.isSelected(),
                        UIHelper.getComboText(odbiorUwagi),
                        UIHelper.datePickerGetDate(odbiorDatePicker),
                        zua.isSelected(),
                        UIHelper.getComboText(zuaUwagi),
                        zza.isSelected(),
                        UIHelper.getComboText(zzaUwagi),
                        zwua.isSelected(),
                        UIHelper.getComboText(zwuaUwagi),
                        nazwaText,
                        badania.isSelected(),
                        UIHelper.getComboText(badaniaUwagi),
                        UIHelper.datePickerGetDate(badaniaDatePicker),
                        dowod.isSelected(),
                        UIHelper.getComboText(dowodUwagi),
                        zyciorys.isSelected(),
                        UIHelper.getComboText(zyciorysUwagi),
                        zaswiadczenieStudent.isSelected(),
                        UIHelper.getComboText(zaswiadczenieStudentUwagi)
                );
                nazwa.removeItem(obecneZlecenie.getNazwa());
                nazwa.addItem(nazwaText);
                nazwa.setSelectedItem(nazwaText);
                UIHelper.displayMessage(this, "Zlecenie zostało uaktualnione pomyślnie.");
            }
        }
    }

    protected void init(Pracodawca pracodawca) {
        obecnyPracodawca = pracodawca;
        pracodawcaNazwaLabel.setText("Pracodawca: " + pracodawca.getNazwa());
        String zlecenieNazwa = UIHelper.getComboText(nazwa);
        Zlecenie zlecenie = zlecenieDao.loadByName(zlecenieNazwa);
        if (zlecenie != null && editMode) {
            obecneZlecenie = zlecenie;

            kwestionariusz.setSelected(zlecenie.getKwestionariusz());
            kartaSzkolenia.setSelected(zlecenie.getKartaSzkolenia());
            szkolenieOgolne.setSelected(zlecenie.getSzkolenie());
            instruktaz.setSelected(zlecenie.getInstruktaz());
            ryzyko.setSelected(zlecenie.getRyzyko());
            instrukcje.setSelected(zlecenie.getInstrukcjeBhp());
            szkolenieOkresowe.setSelected(zlecenie.getSzkolenieBhp());
            rachunki.setSelected(zlecenie.getRachunki());
            umowa.setSelected(zlecenie.getUmowa());
            badania.setSelected(zlecenie.getBadania());
            odbior.setSelected(zlecenie.getOdbiorOdziezy());
            zua.setSelected(zlecenie.getZua());
            zza.setSelected(zlecenie.getZza());
            zwua.setSelected(zlecenie.getZwua());
            dowod.setSelected(zlecenie.getDowod());
            zyciorys.setSelected(zlecenie.getZyciorys());
            zaswiadczenieStudent.setSelected(zlecenie.getZaswiadczenieStudent());

            kwestionariuszUwagi.setSelectedItem(zlecenie.getKwestionariuszUwagi());
            kartaSzkoleniaUwagi.setSelectedItem(zlecenie.getKartaSzkoleniaUwagi());
            szkolenieOgolneUwagi.setSelectedItem(zlecenie.getSzkolenieUwagi());
            instruktazUwagi.setSelectedItem(zlecenie.getInstruktazUwagi());
            ryzykoUwagi.setSelectedItem(zlecenie.getRyzykoUwagi());
            instrukcjeUwagi.setSelectedItem(zlecenie.getInstrukcjeBhpUwagi());
            szkolenieOkresoweUwagi.setSelectedItem(zlecenie.getSzkolenieBhpUwagi());
            rachunkiUwagi.setSelectedItem(zlecenie.getRachunkiUwagi());
            umowaUwagi.setSelectedItem(zlecenie.getUmowaUwagi());
            badaniaUwagi.setSelectedItem(zlecenie.getBadaniaUwagi());
            odbiorUwagi.setSelectedItem(zlecenie.getOdbiorOdziezyUwagi());
            zuaUwagi.setSelectedItem(zlecenie.getZuaUwagi());
            zzaUwagi.setSelectedItem(zlecenie.getZzaUwagi());
            zwuaUwagi.setSelectedItem(zlecenie.getZwuaUwagi());
            dowodUwagi.setSelectedItem(zlecenie.getDowodUwagi());
            zyciorysUwagi.setSelectedItem(zlecenie.getZyciorysUwagi());
            zaswiadczenieStudentUwagi.setSelectedItem(zlecenie.getZaswiadczenieStudentUwagi());

            UIHelper.initDate(badaniaDatePicker, zlecenie.getBadaniaData());
            UIHelper.initDate(odbiorDatePicker, zlecenie.getOdbiorOdziezyData());
            UIHelper.initDate(umowaDatePicker, zlecenie.getUmowaData());
            UIHelper.initDate(szkolenieOkresoweDatePicker, zlecenie.getSzkolenieBhpData());
            UIHelper.initDate(kartaSzkoleniaDatePicker, zlecenie.getKartaSzkoleniaData());
        }
    }

    private void refreshNazwaList(Long pracodawcaId) {
        nazwa.removeAllItems();
        java.util.List<String> pracodawcy = zlecenieDao.loadNamesByPracodawcaId(pracodawcaId);
        for (String pracodawca : pracodawcy) {
            nazwa.addItem(pracodawca);
        }
    }
}
