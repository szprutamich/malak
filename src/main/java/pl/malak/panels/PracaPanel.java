package pl.malak.panels;

import org.jdatepicker.impl.JDatePickerImpl;
import pl.malak.beans.PracaBean;
import pl.malak.beans.dao.PracaDao;
import pl.malak.helpers.Helper;
import pl.malak.helpers.UIHelper;
import pl.malak.model.Praca;
import pl.malak.model.Pracodawca;
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
public class PracaPanel extends FramePanel implements ActionListener {

    @Resource
    private PracaBean pracaBean;

    @Resource
    private PracaDao pracaDao;

    private boolean editMode = true;

    private Praca obecnaPraca;

    private Pracodawca obecnyPracodawca;

    private ArrayList<UIRow> rows = new ArrayList<>();

    JLabel pracodawcaNazwaLabel = new JLabel("Pracodawca:");
    JLabel nazwaLabel = new JLabel("Pracownik:");
    JLabel uwagiLabel = new JLabel("Uwagi:");
    JLabel dataLabel = new JLabel("Do kiedy:");

    JComboBox<String> nazwa = new JComboBox<>();

    JCheckBox kwsetionariuszOsobowyUbiegajacego = new JCheckBox("Wypełniony kwestionariusz osobowy dla osoby ubiegającej się o zatrudnienie");
    JComboBox<String> kwsetionariuszOsobowyUbiegajacegoUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox podanieOPrace = new JCheckBox("Podanie o pracę");
    JComboBox<String> podanieOPraceUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox zyciorys = new JCheckBox("Życiorys pracownika");
    JComboBox<String> zyciorysUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox swiadectwoPracy = new JCheckBox("Świadectwo pracy z poprzednich miejsc pracy lub inne dokumenty potwierdzające okresy zatrudnienia, obejmujace okresy pracy przypadające w roku kalendarzowym, w którym pracownik ubiega się o zatrudnienie");
    JComboBox<String> swiadectwoPracyUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox dokumentyPotwierdzajace = new JCheckBox("Dokumenty potwierdzające kwalifikacje zawodowe, wymagane do wykonywania oferowanej pracy");
    JComboBox<String> dokumentyPotwierdzajaceUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox swiadectwoUkonczeniaGimnazjum = new JCheckBox("Świadectwo ukończenia gimnazjum w przypadku zatrudnienia w celu przygotowania zawodowego");
    JComboBox<String> swiadectwoUkonczeniaGimnazjumUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox podstawaUrlopu = new JCheckBox("Podstawa urlopu - DRUK");
    JComboBox<String> podstawaUrlopuUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox orzeczenieLekarskiePrzeciwskazania = new JCheckBox("Orzeczenie lekarskie stwierdzające brak przeciwwskazań do pracy na okreslonym stanowisku");
    JComboBox<String> orzeczenieLekarskiePrzeciwskazaniaUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JDatePickerImpl orzeczenieLekarskiePrzeciwskazaniaData = UIHelper.getJDatePicker();
    JCheckBox dowodOsobisty = new JCheckBox("Dowód osobisty");
    JComboBox<String> dowodOsobistyUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox kwsetionariuszOsobowyPracownika = new JCheckBox("Kwestionariusz osobowy pracownika ");
    JComboBox<String> kwsetionariuszOsobowyPracownikaUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox umowaOPrace = new JCheckBox("Umowa o pracę + Aneks");
    JComboBox<String> umowaOPraceUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox informacjaOWarunkach = new JCheckBox("Informacja o warunkach zatrudnienia");
    JComboBox<String> informacjaOWarunkachUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox oswiadczenieORyzyku = new JCheckBox("Oświadczenie pracownika o zapoznaniu się z informacją o ryzyku zawodowym na danym stanowisku");
    JComboBox<String> oswiadczenieORyzykuUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox oswiadczenieOPrzepisach = new JCheckBox("Oświadczenie pracownika o zapoznaniu się z obowiązkowymi przepisami w zakresie równouprawnienia");
    JComboBox<String> oswiadczenieOPrzepisachUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox oswiadczenieOBhp = new JCheckBox("Oświadczenie pracownika o zapoznaniu się w zakresie BHP");
    JComboBox<String> oswiadczenieOBhpUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JDatePickerImpl oswiadczenieOBhpData = UIHelper.getJDatePicker();
    JCheckBox oswiadczenieOPozarze = new JCheckBox("Oświadczenie pracownika o zapoznaniu się przepisów przeciwpozarowych");
    JComboBox<String> oswiadczenieOPozarzeUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox oswiadczenieOUprawnieniach = new JCheckBox("Oświadczenie pracownika o zapoznaniu się związanych z uprawnieniami rodzicielskimi itp.");
    JComboBox<String> oswiadczenieOUprawnieniachUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox pit = new JCheckBox("PIT-2, czyli wyrażenie zgody pracownika na miesięczne potrącanie kwoty wolnej z zaliczek na podatek dochodowy");
    JComboBox<String> pitUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox oswiadczenieUrzadSkarbowy = new JCheckBox("Oświadczenie o podleganiu pod Urząd Skarbowy");
    JComboBox<String> oswiadczenieUrzadSkarbowyUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox oswiadczenieOKosztach = new JCheckBox("Oświadczenie o podwyższonych kosztach uzyskania przychodów jeśli pracownik mieszka poza miejscowowścią w której jest siedziba pracodawcy");
    JComboBox<String> oswiadczenieOKosztachUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox zgodaPracownika = new JCheckBox("Zgoda pracownika na wypłatę wynagrodzenia na konto bankowe");
    JComboBox<String> zgodaPracownikaUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox umowaOdpowiedzialnosci = new JCheckBox("Umowa o odpowiedzialności materialnej za powierzone mienie");
    JComboBox<String> umowaOdpowiedzialnosciUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox umowaOZakazie = new JCheckBox("Umowa o zakazie konkurencji");
    JComboBox<String> umowaOZakazieUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox orzeczenieLekarskie = new JCheckBox("Orzeczenie lekarskie dotyczace badań okresowych");
    JComboBox<String> orzeczenieLekarskieUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JDatePickerImpl orzeczenieLekarskieData = UIHelper.getJDatePicker();
    JCheckBox okresoweBadaniaBhp = new JCheckBox("Okresowe badania BHP");
    JComboBox<String> okresoweBadaniaBhpUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JDatePickerImpl okresoweBadaniaBhpData = UIHelper.getJDatePicker();
    JCheckBox drukZua = new JCheckBox("DRUK ZUA+ZCNA");
    JComboBox<String> drukZuaUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox oswiadczenieWypowiedzenie = new JCheckBox("Oświadczenie o wypowiedzeniu lub rozwiązaniu umowy o pracę, a także dokumenty potwierdzające zaistnienie zdarzeń powodujących wygaśnięcie stosunku pracy");
    JComboBox<String> oswiadczenieWypowiedzenieUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox kopiaSwiadectwa = new JCheckBox("Kopia wydanego pracownikowi świadectwa pracy oraz ewentualny wniosek o sprostowanie świadectwa i korespondencja w tej sprawie");
    JComboBox<String> kopiaSwiadectwaUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox drukZwua = new JCheckBox("Druk ZWUA");
    JComboBox<String> drukZwuaUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);

    JButton zapisz = new JButton("Zapisz");
    JButton wroc = new JButton("Wróć");

    public PracaPanel() {
        super();

        rows.add(new UIRow(kwsetionariuszOsobowyUbiegajacego, kwsetionariuszOsobowyUbiegajacegoUwagi, null));
        rows.add(new UIRow(podanieOPrace, podanieOPraceUwagi, null));
        rows.add(new UIRow(zyciorys, zyciorysUwagi, null));
        rows.add(new UIRow(swiadectwoPracy, swiadectwoPracyUwagi, null));
        rows.add(new UIRow(dokumentyPotwierdzajace, dokumentyPotwierdzajaceUwagi, null));
        rows.add(new UIRow(swiadectwoUkonczeniaGimnazjum, swiadectwoUkonczeniaGimnazjumUwagi, null));
        rows.add(new UIRow(podstawaUrlopu, podstawaUrlopuUwagi, null));
        rows.add(new UIRow(orzeczenieLekarskiePrzeciwskazania, orzeczenieLekarskiePrzeciwskazaniaUwagi, orzeczenieLekarskiePrzeciwskazaniaData));
        rows.add(new UIRow(dowodOsobisty, dowodOsobistyUwagi, null));
        rows.add(new UIRow(kwsetionariuszOsobowyPracownika, kwsetionariuszOsobowyPracownikaUwagi, null));
        rows.add(new UIRow(umowaOPrace, umowaOPraceUwagi, null));
        rows.add(new UIRow(informacjaOWarunkach, informacjaOWarunkachUwagi, null));
        rows.add(new UIRow(oswiadczenieORyzyku, oswiadczenieORyzykuUwagi, null));
        rows.add(new UIRow(oswiadczenieOPrzepisach, oswiadczenieOPrzepisachUwagi, null));
        rows.add(new UIRow(oswiadczenieOBhp, oswiadczenieOBhpUwagi, oswiadczenieOBhpData));

        rows.add(new UIRow(oswiadczenieOPozarze, oswiadczenieOPozarzeUwagi, null));
        rows.add(new UIRow(oswiadczenieOUprawnieniach, oswiadczenieOUprawnieniachUwagi, null));
        rows.add(new UIRow(pit, pitUwagi, null));
        rows.add(new UIRow(oswiadczenieUrzadSkarbowy, oswiadczenieUrzadSkarbowyUwagi, null));
        rows.add(new UIRow(oswiadczenieOKosztach, oswiadczenieOKosztachUwagi, null));
        rows.add(new UIRow(zgodaPracownika, zgodaPracownikaUwagi, null));
        rows.add(new UIRow(umowaOdpowiedzialnosci, umowaOdpowiedzialnosciUwagi, null));
        rows.add(new UIRow(umowaOZakazie, umowaOZakazieUwagi, null));
        rows.add(new UIRow(orzeczenieLekarskie, orzeczenieLekarskieUwagi, orzeczenieLekarskieData));
        rows.add(new UIRow(okresoweBadaniaBhp, okresoweBadaniaBhpUwagi, okresoweBadaniaBhpData));
        rows.add(new UIRow(drukZua, drukZuaUwagi, null));
        rows.add(new UIRow(oswiadczenieWypowiedzenie, oswiadczenieWypowiedzenieUwagi, null));
        rows.add(new UIRow(kopiaSwiadectwa, kopiaSwiadectwaUwagi, null));
        rows.add(new UIRow(drukZwua, drukZwuaUwagi, null));

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
            getFrame().initPrzegladaniePracodawcow();
//        } else if (e.getSource() == zapisz) {
//            String zlecenieNazwa = UIHelper.getComboText(nazwa);
//            if (zlecenieNazwa == null) {
//                UIHelper.displayMessage(this, "Nazwa nie może być pusta!");
//                return;
//            }
//            Zlecenie zlecenie = zlecenieDao.loadByName(zlecenieNazwa);
//            if (!editMode && zlecenie == null) {
//                zlecenieBean.stworzZlecenie(
//                        kwestionariusz.isSelected(),
//                        UIHelper.getComboText(kwestionariuszUwagi),
//                        kartaSzkolenia.isSelected(),
//                        UIHelper.getComboText(kartaSzkoleniaUwagi),
//                        UIHelper.datePickerGetDate(kartaSzkoleniaDatePicker),
//                        szkolenieOgolne.isSelected(),
//                        UIHelper.getComboText(szkolenieOgolneUwagi),
//                        instruktaz.isSelected(),
//                        UIHelper.getComboText(instruktazUwagi),
//                        ryzyko.isSelected(),
//                        UIHelper.getComboText(ryzykoUwagi),
//                        instrukcje.isSelected(),
//                        UIHelper.getComboText(instrukcjeUwagi),
//                        szkolenieOkresowe.isSelected(),
//                        UIHelper.getComboText(szkolenieOkresoweUwagi),
//                        UIHelper.datePickerGetDate(szkolenieOkresoweDatePicker),
//                        rachunki.isSelected(),
//                        UIHelper.getComboText(rachunkiUwagi),
//                        umowa.isSelected(),
//                        UIHelper.getComboText(umowaUwagi),
//                        UIHelper.datePickerGetDate(umowaDatePicker),
//                        odbior.isSelected(),
//                        UIHelper.getComboText(odbiorUwagi),
//                        UIHelper.datePickerGetDate(odbiorDatePicker),
//                        zua.isSelected(),
//                        UIHelper.getComboText(zuaUwagi),
//                        zus.isSelected(),
//                        UIHelper.getComboText(zusUwagi),
//                        zza.isSelected(),
//                        UIHelper.getComboText(zzaUwagi),
//                        zwua.isSelected(),
//                        UIHelper.getComboText(zwuaUwagi),
//                        UIHelper.datePickerGetDate(zwuaDatePicker),
//                        UIHelper.getComboText(nazwa),
//                        badania.isSelected(),
//                        UIHelper.getComboText(badaniaUwagi),
//                        UIHelper.datePickerGetDate(badaniaDatePicker),
//                        legitymacja.isSelected(),
//                        UIHelper.getComboText(legitymacjaUwagi),
//                        dowod.isSelected(),
//                        UIHelper.getComboText(dowodUwagi),
//                        zyciorys.isSelected(),
//                        UIHelper.getComboText(zyciorysUwagi),
//                        zaswiadczenieSanitarne.isSelected(),
//                        UIHelper.getComboText(zaswiadczenieSanitarneUwagi),
//                        zaswiadczenieStudent.isSelected(),
//                        UIHelper.getComboText(zaswiadczenieStudentUwagi),
//                        wyciagKodeks.isSelected(),
//                        UIHelper.getComboText(wyciagKodeksUwagi),
//                        obecnyPracodawca.getId()
//                );
//                UIHelper.displayMessage(this, "Zlecenie zostało dodane pomyślnie.");
//            } else if (!editMode && zlecenie.getPracodawca().getId().equals(obecnyPracodawca.getId())) {
//                UIHelper.displayMessage(this, "Zlecenie o podanej nazwie dla tego pracodawcy już istnieje!");
//            } else {
//                String nazwaText = UIHelper.getComboText(nazwa);
//                zlecenieBean.uaktualnijZlecenie(
//                        obecneZlecenie.getId(),
//                        kwestionariusz.isSelected(),
//                        UIHelper.getComboText(kwestionariuszUwagi),
//                        kartaSzkolenia.isSelected(),
//                        UIHelper.getComboText(kartaSzkoleniaUwagi),
//                        UIHelper.datePickerGetDate(kartaSzkoleniaDatePicker),
//                        szkolenieOgolne.isSelected(),
//                        UIHelper.getComboText(szkolenieOgolneUwagi),
//                        instruktaz.isSelected(),
//                        UIHelper.getComboText(instruktazUwagi),
//                        ryzyko.isSelected(),
//                        UIHelper.getComboText(ryzykoUwagi),
//                        instrukcje.isSelected(),
//                        UIHelper.getComboText(instrukcjeUwagi),
//                        szkolenieOkresowe.isSelected(),
//                        UIHelper.getComboText(szkolenieOkresoweUwagi),
//                        UIHelper.datePickerGetDate(szkolenieOkresoweDatePicker),
//                        rachunki.isSelected(),
//                        UIHelper.getComboText(rachunkiUwagi),
//                        umowa.isSelected(),
//                        UIHelper.getComboText(umowaUwagi),
//                        UIHelper.datePickerGetDate(umowaDatePicker),
//                        odbior.isSelected(),
//                        UIHelper.getComboText(odbiorUwagi),
//                        UIHelper.datePickerGetDate(odbiorDatePicker),
//                        zua.isSelected(),
//                        UIHelper.getComboText(zuaUwagi),
//                        zus.isSelected(),
//                        UIHelper.getComboText(zusUwagi),
//                        zza.isSelected(),
//                        UIHelper.getComboText(zzaUwagi),
//                        zwua.isSelected(),
//                        UIHelper.getComboText(zwuaUwagi),
//                        UIHelper.datePickerGetDate(zwuaDatePicker),
//                        nazwaText,
//                        badania.isSelected(),
//                        UIHelper.getComboText(badaniaUwagi),
//                        UIHelper.datePickerGetDate(badaniaDatePicker),
//                        legitymacja.isSelected(),
//                        UIHelper.getComboText(legitymacjaUwagi),
//                        dowod.isSelected(),
//                        UIHelper.getComboText(dowodUwagi),
//                        zyciorys.isSelected(),
//                        UIHelper.getComboText(zyciorysUwagi),
//                        zaswiadczenieSanitarne.isSelected(),
//                        UIHelper.getComboText(zaswiadczenieSanitarneUwagi),
//                        zaswiadczenieStudent.isSelected(),
//                        UIHelper.getComboText(zaswiadczenieStudentUwagi),
//                        wyciagKodeks.isSelected(),
//                        UIHelper.getComboText(wyciagKodeksUwagi)
//                );
//                nazwa.removeItem(obecneZlecenie.getNazwa());
//                nazwa.addItem(nazwaText);
//                nazwa.setSelectedItem(nazwaText);
//                UIHelper.displayMessage(this, "Zlecenie zostało uaktualnione pomyślnie.");
//            }
        }
    }

    protected void init(Pracodawca pracodawca) {
        obecnyPracodawca = pracodawca;
        pracodawcaNazwaLabel.setText("Pracodawca: " + pracodawca.getNazwa());
        String zlecenieNazwa = UIHelper.getComboText(nazwa);
        Praca praca = pracaDao.loadByName(zlecenieNazwa);
        if (praca != null && editMode) {
            obecnaPraca = praca;
//
//            kwestionariusz.setSelected(zlecenie.getKwestionariusz());
//            kartaSzkolenia.setSelected(zlecenie.getKartaSzkolenia());
//            szkolenieOgolne.setSelected(zlecenie.getSzkolenie());
//            instruktaz.setSelected(zlecenie.getInstruktaz());
//            ryzyko.setSelected(zlecenie.getRyzyko());
//            instrukcje.setSelected(zlecenie.getInstrukcjeBhp());
//            szkolenieOkresowe.setSelected(zlecenie.getSzkolenieBhp());
//            rachunki.setSelected(zlecenie.getRachunki());
//            umowa.setSelected(zlecenie.getUmowa());
//            badania.setSelected(zlecenie.getBadania());
//            odbior.setSelected(zlecenie.getOdbiorOdziezy());
//            zua.setSelected(zlecenie.getZua());
//            zus.setSelected(zlecenie.getZus());
//            zza.setSelected(zlecenie.getZza());
//            zwua.setSelected(zlecenie.getZwua());
//            legitymacja.setSelected(zlecenie.getLegitymacja());
//            dowod.setSelected(zlecenie.getDowod());
//            zyciorys.setSelected(zlecenie.getZyciorys());
//            zaswiadczenieSanitarne.setSelected(zlecenie.getZaswiadczenieSanitarne());
//            zaswiadczenieStudent.setSelected(zlecenie.getZaswiadczenieStudent());
//            wyciagKodeks.setSelected(zlecenie.getWyciagKodeks());
//
//            kwestionariuszUwagi.setSelectedItem(zlecenie.getKwestionariuszUwagi());
//            kartaSzkoleniaUwagi.setSelectedItem(zlecenie.getKartaSzkoleniaUwagi());
//            szkolenieOgolneUwagi.setSelectedItem(zlecenie.getSzkolenieUwagi());
//            instruktazUwagi.setSelectedItem(zlecenie.getInstruktazUwagi());
//            ryzykoUwagi.setSelectedItem(zlecenie.getRyzykoUwagi());
//            instrukcjeUwagi.setSelectedItem(zlecenie.getInstrukcjeBhpUwagi());
//            szkolenieOkresoweUwagi.setSelectedItem(zlecenie.getSzkolenieBhpUwagi());
//            rachunkiUwagi.setSelectedItem(zlecenie.getRachunkiUwagi());
//            umowaUwagi.setSelectedItem(zlecenie.getUmowaUwagi());
//            badaniaUwagi.setSelectedItem(zlecenie.getBadaniaUwagi());
//            odbiorUwagi.setSelectedItem(zlecenie.getOdbiorOdziezyUwagi());
//            zuaUwagi.setSelectedItem(zlecenie.getZuaUwagi());
//            zusUwagi.setSelectedItem(zlecenie.getZusUwagi());
//            zzaUwagi.setSelectedItem(zlecenie.getZzaUwagi());
//            zwuaUwagi.setSelectedItem(zlecenie.getZwuaUwagi());
//            legitymacjaUwagi.setSelectedItem(zlecenie.getLegitymacjaUwagi());
//            dowodUwagi.setSelectedItem(zlecenie.getDowodUwagi());
//            zyciorysUwagi.setSelectedItem(zlecenie.getZyciorysUwagi());
//            zaswiadczenieSanitarneUwagi.setSelectedItem(zlecenie.getZaswiadczenieSanitarneUwagi());
//            zaswiadczenieStudentUwagi.setSelectedItem(zlecenie.getZaswiadczenieStudentUwagi());
//            wyciagKodeksUwagi.setSelectedItem(zlecenie.getWyciagKodeksUwagi());
//
//            initDate(badaniaDatePicker, zlecenie.getBadaniaData());
//            initDate(zwuaDatePicker, zlecenie.getZwuaData());
//            initDate(odbiorDatePicker, zlecenie.getOdbiorOdziezyData());
//            initDate(umowaDatePicker, zlecenie.getUmowaData());
//            initDate(szkolenieOkresoweDatePicker, zlecenie.getSzkolenieBhpData());
//            initDate(kartaSzkoleniaDatePicker, zlecenie.getKartaSzkoleniaData());
        }
    }

    private void refreshNazwaList(Long pracodawcaId) {
        nazwa.removeAllItems();
        java.util.List<String> pracodawcy = pracaDao.loadNamesByPracodawcaId(pracodawcaId);
        for (String pracodawca : pracodawcy) {
            nazwa.addItem(pracodawca);
        }
    }
}
