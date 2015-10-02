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

    JComboBox<String> nazwa = new JComboBox<>();

    JCheckBox kwsetionariuszOsobowyUbiegajacego = new JCheckBox(
            "<html>Wypełniony kwestionariusz osobowy dla osoby ubiegającej się o zatrudnienie</html>"
    );
    JComboBox<String> kwsetionariuszOsobowyUbiegajacegoUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox podanieOPrace = new JCheckBox("Podanie o pracę");
    JComboBox<String> podanieOPraceUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox zyciorys = new JCheckBox("Życiorys pracownika");
    JComboBox<String> zyciorysUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox swiadectwoPracy = new JCheckBox(
            "<html>Świadectwo pracy z poprzednich miejsc pracy lub inne dokumenty " +
                    "potwierdzające okresy zatrudnienia,<br> w danym roku kalendarzowym</html>"
    );
    JComboBox<String> swiadectwoPracyUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox dokumentyPotwierdzajace = new JCheckBox(
            "<html>Dokumenty potwierdzające kwalifikacje zawodowe, wymagane do wykonywania oferowanej pracy</html>"
    );
    JComboBox<String> dokumentyPotwierdzajaceUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox swiadectwoUkonczeniaGimnazjum = new JCheckBox(
            "<html>Świadectwo ukończenia gimnazjum w przypadku zatrudnienia w celu przygotowania zawodowego</html>"
    );
    JComboBox<String> swiadectwoUkonczeniaGimnazjumUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox podstawaUrlopu = new JCheckBox("Podstawa urlopu - DRUK");
    JComboBox<String> podstawaUrlopuUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox orzeczenieLekarskiePrzeciwskazania = new JCheckBox(
            "<html>Orzeczenie lekarskie stwierdzające brak przeciwwskazań do pracy na określonym stanowisku</html>"
    );
    JComboBox<String> orzeczenieLekarskiePrzeciwskazaniaUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JDatePickerImpl orzeczenieLekarskiePrzeciwskazaniaData = UIHelper.getJDatePicker();
    JCheckBox dowodOsobisty = new JCheckBox("Dowód osobisty");
    JComboBox<String> dowodOsobistyUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox kwsetionariuszOsobowyPracownika = new JCheckBox("Kwestionariusz osobowy pracownika");
    JComboBox<String> kwsetionariuszOsobowyPracownikaUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox umowaOPrace = new JCheckBox("Umowa o pracę + Aneks");
    JComboBox<String> umowaOPraceUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox informacjaOWarunkach = new JCheckBox("Informacja o warunkach zatrudnienia");
    JComboBox<String> informacjaOWarunkachUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox oswiadczenieORyzyku = new JCheckBox(
            "<html>Oświadczenie pracownika o zapoznaniu się z informacją o ryzyku zawodowym</html>"
    );
    JComboBox<String> oswiadczenieORyzykuUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox oswiadczenieOPrzepisach = new JCheckBox(
            "<html>Oświadczenie pracownika o zapoznaniu się z obowiązkowymi " +
                    "przepisami w zakresie równouprawnienia</html>"
    );
    JComboBox<String> oswiadczenieOPrzepisachUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox oswiadczenieOBhp = new JCheckBox("Oświadczenie pracownika o zapoznaniu się z przepisami BHP");
    JComboBox<String> oswiadczenieOBhpUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JDatePickerImpl oswiadczenieOBhpData = UIHelper.getJDatePicker();
    JCheckBox oswiadczenieOPozarze = new JCheckBox(
            "<html>Oświadczenie pracownika o zapoznaniu się przepisami przeciwpozarowymi</html>"
    );
    JComboBox<String> oswiadczenieOPozarzeUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox oswiadczenieOUprawnieniach = new JCheckBox(
            "<html>Oświadczenie pracownika o zapoznaniu się z uprawnieniami rodzicielskimi itp.</html>"
    );
    JComboBox<String> oswiadczenieOUprawnieniachUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox pit = new JCheckBox(
            "<html>PIT-2, czyli wyrażenie zgody pracownika na miesięczne potrącanie " +
                    "kwoty wolnej z zaliczek na podatek dochodowy</html>"
    );
    JComboBox<String> pitUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox oswiadczenieUrzadSkarbowy = new JCheckBox("Oświadczenie o podleganiu pod Urząd Skarbowy");
    JComboBox<String> oswiadczenieUrzadSkarbowyUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox oswiadczenieOKosztach = new JCheckBox(
            "<html>Oświadczenie o podwyższonych kosztach uzyskania przychodów <br>jeśli pracownik mieszka " +
                    "poza miejscowowścią, w której jest siedziba pracodawcy</html>"
    );
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
    JCheckBox oswiadczenieWypowiedzenie = new JCheckBox(
            "<html>Oświadczenie o wypowiedzeniu lub rozwiązaniu umowy o pracę, a także dokumenty potwierdzające " +
                    "zaistnienie zdarzeń powodujących wygaśnięcie <br>stosunku pracy</html>"
    );
    JComboBox<String> oswiadczenieWypowiedzenieUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox kopiaSwiadectwa = new JCheckBox(
            "<html>Kopia wydanego pracownikowi świadectwa pracy oraz ewentualny wniosek o sprostowanie świadectwa" +
                    " i korespondencja <br>w tej sprawie</html>"
    );
    JComboBox<String> kopiaSwiadectwaUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);
    JCheckBox drukZwua = new JCheckBox("Druk ZWUA");
    JComboBox<String> drukZwuaUwagi = new JComboBox<>(Helper.PREDEFINIOWANE_UWAGI);

    JButton zapisz = new JButton("Zapisz");
    JButton wroc = new JButton("Wróć");

    JTabbedPane panel = new JTabbedPane();
    JPanel czescA = new JPanel();
    JPanel czescB = new JPanel();
    JPanel czescC = new JPanel();

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
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        JPanel aPanel = new JPanel();
        JPanel bPanel = new JPanel();
        JPanel cPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.CENTER);
        czescA.setLayout(flowLayout);
        czescB.setLayout(flowLayout);
        czescC.setLayout(flowLayout);
        aPanel.setLayout(layout);
        bPanel.setLayout(layout);
        cPanel.setLayout(layout);
        czescA.add(aPanel);
        czescB.add(bPanel);
        czescC.add(cPanel);


        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(3, 5, 3, 5);
        c.ipady = 0;
        c.ipadx = 0;

        // --------------
        c.gridx = 0;
        c.gridy = wiersz;
        c.gridwidth = 1;
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
        c.gridx = 0;
        c.gridy = wiersz;
        c.gridwidth = 3;

        add(panel, c);
        panel.addTab("Część A", czescA);
        panel.addTab("Część B", czescB);
        panel.addTab("Część C", czescC);

        addPart(wiersz, c, aPanel, 0, 9, true);
        addPart(wiersz, c, bPanel, 9, 26, true);
        addPart(wiersz, c, cPanel, 26, 29, false);

        // --------------
        wiersz++;
        wiersz++;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = wiersz;
        add(zapisz, c);

        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = wiersz;
        add(wroc, c);
    }

    private void addPart(int wiersz, GridBagConstraints c, JPanel panel, int start, int end, boolean labels) {
        if (labels) {
            // --------------
            wiersz++;

            panel.add(new JLabel(), c);

            c.gridwidth = 1;
            c.gridx = 1;
            c.gridy = wiersz;
            panel.add(new JLabel("Uwagi:"), c);

            c.gridx = 2;
            c.gridy = wiersz;
            panel.add(new JLabel("Do kiedy:"), c);
        }

        for (int i = start; i < end; i++) {
            wiersz++;
            UIRow row = rows.get(i);
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = wiersz;
            panel.add(row.getCheckBox(), c);

            c.gridx = 1;
            c.gridy = wiersz;
            panel.add(row.getComboBox(), c);
            row.getComboBox().setEditable(true);

            if (row.getDatePicker() != null) {
                c.gridx = 2;
                c.gridy = wiersz;
                panel.add(row.getDatePicker(), c);
            } else if (labels) {
                panel.add(new JLabel(), c);
            }
        }
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
            String pracaNazwa = UIHelper.getComboText(nazwa);
            if (pracaNazwa == null) {
                UIHelper.displayMessage(this, "Nazwa nie może być pusta!");
                return;
            }
            Praca praca = pracaDao.loadByName(pracaNazwa);
            if (!editMode && praca == null) {
                pracaBean.stworzPrace(
                        kwsetionariuszOsobowyUbiegajacego.isSelected(),
                        UIHelper.getComboText(kwsetionariuszOsobowyUbiegajacegoUwagi),
                        podanieOPrace.isSelected(),
                        UIHelper.getComboText(podanieOPraceUwagi),
                        zyciorys.isSelected(),
                        UIHelper.getComboText(zyciorysUwagi),
                        swiadectwoPracy.isSelected(),
                        UIHelper.getComboText(swiadectwoPracyUwagi),
                        dokumentyPotwierdzajace.isSelected(),
                        UIHelper.getComboText(dokumentyPotwierdzajaceUwagi),
                        swiadectwoUkonczeniaGimnazjum.isSelected(),
                        UIHelper.getComboText(swiadectwoUkonczeniaGimnazjumUwagi),
                        podstawaUrlopu.isSelected(),
                        UIHelper.getComboText(podstawaUrlopuUwagi),
                        orzeczenieLekarskiePrzeciwskazania.isSelected(),
                        UIHelper.getComboText(orzeczenieLekarskiePrzeciwskazaniaUwagi),
                        UIHelper.datePickerGetDate(orzeczenieLekarskiePrzeciwskazaniaData),
                        dowodOsobisty.isSelected(),
                        UIHelper.getComboText(dowodOsobistyUwagi),
                        kwsetionariuszOsobowyPracownika.isSelected(),
                        UIHelper.getComboText(kwsetionariuszOsobowyPracownikaUwagi),
                        umowaOPrace.isSelected(),
                        UIHelper.getComboText(umowaOPraceUwagi),
                        informacjaOWarunkach.isSelected(),
                        UIHelper.getComboText(informacjaOWarunkachUwagi),
                        oswiadczenieORyzyku.isSelected(),
                        UIHelper.getComboText(oswiadczenieORyzykuUwagi),
                        oswiadczenieOPrzepisach.isSelected(),
                        UIHelper.getComboText(oswiadczenieOPrzepisachUwagi),
                        oswiadczenieOBhp.isSelected(),
                        UIHelper.getComboText(oswiadczenieOBhpUwagi),
                        UIHelper.datePickerGetDate(oswiadczenieOBhpData),
                        oswiadczenieOPozarze.isSelected(),
                        UIHelper.getComboText(oswiadczenieOPozarzeUwagi),
                        oswiadczenieOUprawnieniach.isSelected(),
                        UIHelper.getComboText(oswiadczenieOUprawnieniachUwagi),
                        pit.isSelected(),
                        UIHelper.getComboText(pitUwagi),
                        oswiadczenieUrzadSkarbowy.isSelected(),
                        UIHelper.getComboText(oswiadczenieUrzadSkarbowyUwagi),
                        oswiadczenieOKosztach.isSelected(),
                        UIHelper.getComboText(oswiadczenieOKosztachUwagi),
                        zgodaPracownika.isSelected(),
                        UIHelper.getComboText(zgodaPracownikaUwagi),
                        umowaOdpowiedzialnosci.isSelected(),
                        UIHelper.getComboText(umowaOdpowiedzialnosciUwagi),
                        umowaOZakazie.isSelected(),
                        UIHelper.getComboText(umowaOZakazieUwagi),
                        orzeczenieLekarskie.isSelected(),
                        UIHelper.getComboText(orzeczenieLekarskieUwagi),
                        UIHelper.datePickerGetDate(orzeczenieLekarskieData),
                        okresoweBadaniaBhp.isSelected(),
                        UIHelper.getComboText(okresoweBadaniaBhpUwagi),
                        UIHelper.datePickerGetDate(okresoweBadaniaBhpData),
                        drukZua.isSelected(),
                        UIHelper.getComboText(drukZuaUwagi),
                        oswiadczenieWypowiedzenie.isSelected(),
                        UIHelper.getComboText(oswiadczenieWypowiedzenieUwagi),
                        kopiaSwiadectwa.isSelected(),
                        UIHelper.getComboText(kopiaSwiadectwaUwagi),
                        drukZwua.isSelected(),
                        UIHelper.getComboText(drukZwuaUwagi),
                        UIHelper.getComboText(nazwa),
                        obecnyPracodawca.getId()
                );
                UIHelper.displayMessage(this, "Umowa o pracę została dodana pomyślnie.");
            } else if (!editMode && praca.getPracodawca().getId().equals(obecnyPracodawca.getId())) {
                UIHelper.displayMessage(this, "Umowa o pracę o podanej nazwie dla tego pracodawcy już istnieje!");
            } else {
                String nazwaText = UIHelper.getComboText(nazwa);
                pracaBean.uaktualnijPrace(
                        obecnaPraca.getId(),
                        kwsetionariuszOsobowyUbiegajacego.isSelected(),
                        UIHelper.getComboText(kwsetionariuszOsobowyUbiegajacegoUwagi),
                        podanieOPrace.isSelected(),
                        UIHelper.getComboText(podanieOPraceUwagi),
                        zyciorys.isSelected(),
                        UIHelper.getComboText(zyciorysUwagi),
                        swiadectwoPracy.isSelected(),
                        UIHelper.getComboText(swiadectwoPracyUwagi),
                        dokumentyPotwierdzajace.isSelected(),
                        UIHelper.getComboText(dokumentyPotwierdzajaceUwagi),
                        swiadectwoUkonczeniaGimnazjum.isSelected(),
                        UIHelper.getComboText(swiadectwoUkonczeniaGimnazjumUwagi),
                        podstawaUrlopu.isSelected(),
                        UIHelper.getComboText(podstawaUrlopuUwagi),
                        orzeczenieLekarskiePrzeciwskazania.isSelected(),
                        UIHelper.getComboText(orzeczenieLekarskiePrzeciwskazaniaUwagi),
                        UIHelper.datePickerGetDate(orzeczenieLekarskiePrzeciwskazaniaData),
                        dowodOsobisty.isSelected(),
                        UIHelper.getComboText(dowodOsobistyUwagi),
                        kwsetionariuszOsobowyPracownika.isSelected(),
                        UIHelper.getComboText(kwsetionariuszOsobowyPracownikaUwagi),
                        umowaOPrace.isSelected(),
                        UIHelper.getComboText(umowaOPraceUwagi),
                        informacjaOWarunkach.isSelected(),
                        UIHelper.getComboText(informacjaOWarunkachUwagi),
                        oswiadczenieORyzyku.isSelected(),
                        UIHelper.getComboText(oswiadczenieORyzykuUwagi),
                        oswiadczenieOPrzepisach.isSelected(),
                        UIHelper.getComboText(oswiadczenieOPrzepisachUwagi),
                        oswiadczenieOBhp.isSelected(),
                        UIHelper.getComboText(oswiadczenieOBhpUwagi),
                        UIHelper.datePickerGetDate(oswiadczenieOBhpData),
                        oswiadczenieOPozarze.isSelected(),
                        UIHelper.getComboText(oswiadczenieOPozarzeUwagi),
                        oswiadczenieOUprawnieniach.isSelected(),
                        UIHelper.getComboText(oswiadczenieOUprawnieniachUwagi),
                        pit.isSelected(),
                        UIHelper.getComboText(pitUwagi),
                        oswiadczenieUrzadSkarbowy.isSelected(),
                        UIHelper.getComboText(oswiadczenieUrzadSkarbowyUwagi),
                        oswiadczenieOKosztach.isSelected(),
                        UIHelper.getComboText(oswiadczenieOKosztachUwagi),
                        zgodaPracownika.isSelected(),
                        UIHelper.getComboText(zgodaPracownikaUwagi),
                        umowaOdpowiedzialnosci.isSelected(),
                        UIHelper.getComboText(umowaOdpowiedzialnosciUwagi),
                        umowaOZakazie.isSelected(),
                        UIHelper.getComboText(umowaOZakazieUwagi),
                        orzeczenieLekarskie.isSelected(),
                        UIHelper.getComboText(orzeczenieLekarskieUwagi),
                        UIHelper.datePickerGetDate(orzeczenieLekarskieData),
                        okresoweBadaniaBhp.isSelected(),
                        UIHelper.getComboText(okresoweBadaniaBhpUwagi),
                        UIHelper.datePickerGetDate(okresoweBadaniaBhpData),
                        drukZua.isSelected(),
                        UIHelper.getComboText(drukZuaUwagi),
                        oswiadczenieWypowiedzenie.isSelected(),
                        UIHelper.getComboText(oswiadczenieWypowiedzenieUwagi),
                        kopiaSwiadectwa.isSelected(),
                        UIHelper.getComboText(kopiaSwiadectwaUwagi),
                        drukZwua.isSelected(),
                        UIHelper.getComboText(drukZwuaUwagi),
                        nazwaText
                );
                nazwa.removeItem(obecnaPraca.getNazwa());
                nazwa.addItem(nazwaText);
                nazwa.setSelectedItem(nazwaText);
                UIHelper.displayMessage(this, "Umowa o pracę została uaktualniona pomyślnie.");
            }
        }
    }

    protected void init(Pracodawca pracodawca) {
        obecnyPracodawca = pracodawca;
        pracodawcaNazwaLabel.setText("Pracodawca: " + pracodawca.getNazwa());
        String zlecenieNazwa = UIHelper.getComboText(nazwa);
        Praca praca = pracaDao.loadByName(zlecenieNazwa);
        if (praca != null && editMode) {
            obecnaPraca = praca;

            kwsetionariuszOsobowyUbiegajacego.setSelected(praca.getKwsetionariuszOsobowyUbiegajacego());
            podanieOPrace.setSelected(praca.getPodanieOPrace());
            zyciorys.setSelected(praca.getZyciorys());
            swiadectwoPracy.setSelected(praca.getSwiadectwoPracy());
            dokumentyPotwierdzajace.setSelected(praca.getDokumentyPotwierdzajace());
            swiadectwoUkonczeniaGimnazjum.setSelected(praca.getSwiadectwoUkonczeniaGimnazjum());
            podstawaUrlopu.setSelected(praca.getPodstawaUrlopu());
            orzeczenieLekarskiePrzeciwskazania.setSelected(praca.getOrzeczenieLekarskiePrzeciwskazania());
            dowodOsobisty.setSelected(praca.getDowodOsobisty());
            kwsetionariuszOsobowyPracownika.setSelected(praca.getKwsetionariuszOsobowyPracownika());
            umowaOPrace.setSelected(praca.getUmowaOPrace());
            informacjaOWarunkach.setSelected(praca.getInformacjaOWarunkach());
            oswiadczenieORyzyku.setSelected(praca.getOswiadczenieORyzyku());
            oswiadczenieOPrzepisach.setSelected(praca.getOswiadczenieOPrzepisach());
            oswiadczenieOBhp.setSelected(praca.getOswiadczenieOBhp());
            oswiadczenieOPozarze.setSelected(praca.getOswiadczenieOPozarze());
            oswiadczenieOUprawnieniach.setSelected(praca.getOswiadczenieOUprawnieniach());
            pit.setSelected(praca.getPit());
            oswiadczenieUrzadSkarbowy.setSelected(praca.getOswiadczenieUrzadSkarbowy());
            oswiadczenieOKosztach.setSelected(praca.getOswiadczenieOKosztach());
            zgodaPracownika.setSelected(praca.getZgodaPracownika());
            umowaOdpowiedzialnosci.setSelected(praca.getUmowaOdpowiedzialnosci());
            umowaOZakazie.setSelected(praca.getUmowaOZakazie());
            orzeczenieLekarskie.setSelected(praca.getOrzeczenieLekarskie());
            okresoweBadaniaBhp.setSelected(praca.getOkresoweBadaniaBhp());
            drukZua.setSelected(praca.getDrukZua());
            oswiadczenieWypowiedzenie.setSelected(praca.getOswiadczenieWypowiedzenie());
            kopiaSwiadectwa.setSelected(praca.getKopiaSwiadectwa());
            drukZwua.setSelected(praca.getDrukZwua());

            kwsetionariuszOsobowyUbiegajacegoUwagi.setSelectedItem(praca.getKwsetionariuszOsobowyUbiegajacegoUwagi());
            podanieOPraceUwagi.setSelectedItem(praca.getPodanieOPraceUwagi());
            zyciorysUwagi.setSelectedItem(praca.getZyciorysUwagi());
            swiadectwoPracyUwagi.setSelectedItem(praca.getSwiadectwoPracyUwagi());
            dokumentyPotwierdzajaceUwagi.setSelectedItem(praca.getDokumentyPotwierdzajaceUwagi());
            swiadectwoUkonczeniaGimnazjumUwagi.setSelectedItem(praca.getSwiadectwoUkonczeniaGimnazjumUwagi());
            podstawaUrlopuUwagi.setSelectedItem(praca.getPodstawaUrlopuUwagi());
            orzeczenieLekarskiePrzeciwskazaniaUwagi.setSelectedItem(praca.getOrzeczenieLekarskiePrzeciwskazaniaUwagi());
            dowodOsobistyUwagi.setSelectedItem(praca.getDowodOsobistyUwagi());
            kwsetionariuszOsobowyPracownikaUwagi.setSelectedItem(praca.getKwsetionariuszOsobowyPracownikaUwagi());
            umowaOPraceUwagi.setSelectedItem(praca.getUmowaOPraceUwagi());
            informacjaOWarunkachUwagi.setSelectedItem(praca.getInformacjaOWarunkachUwagi());
            oswiadczenieORyzykuUwagi.setSelectedItem(praca.getOswiadczenieORyzykuUwagi());
            oswiadczenieOPrzepisachUwagi.setSelectedItem(praca.getOswiadczenieOPrzepisachUwagi());
            oswiadczenieOBhpUwagi.setSelectedItem(praca.getOswiadczenieOBhpUwagi());
            oswiadczenieOPozarzeUwagi.setSelectedItem(praca.getOswiadczenieOPozarzeUwagi());
            oswiadczenieOUprawnieniachUwagi.setSelectedItem(praca.getOswiadczenieOUprawnieniachUwagi());
            pitUwagi.setSelectedItem(praca.getPitUwagi());
            oswiadczenieUrzadSkarbowyUwagi.setSelectedItem(praca.getOswiadczenieUrzadSkarbowyUwagi());
            oswiadczenieOKosztachUwagi.setSelectedItem(praca.getOswiadczenieOKosztachUwagi());
            zgodaPracownikaUwagi.setSelectedItem(praca.getZgodaPracownikaUwagi());
            umowaOdpowiedzialnosciUwagi.setSelectedItem(praca.getUmowaOdpowiedzialnosciUwagi());
            umowaOZakazieUwagi.setSelectedItem(praca.getUmowaOZakazieUwagi());
            orzeczenieLekarskieUwagi.setSelectedItem(praca.getOrzeczenieLekarskieUwagi());
            okresoweBadaniaBhpUwagi.setSelectedItem(praca.getOkresoweBadaniaBhpUwagi());
            drukZuaUwagi.setSelectedItem(praca.getDrukZuaUwagi());
            oswiadczenieWypowiedzenieUwagi.setSelectedItem(praca.getOswiadczenieWypowiedzenieUwagi());
            kopiaSwiadectwaUwagi.setSelectedItem(praca.getKopiaSwiadectwaUwagi());
            drukZwuaUwagi.setSelectedItem(praca.getDrukZwuaUwagi());

            UIHelper.initDate(orzeczenieLekarskiePrzeciwskazaniaData, praca.getOrzeczenieLekarskiePrzeciwskazaniaData());
            UIHelper.initDate(oswiadczenieOBhpData, praca.getOswiadczenieOBhpData());
            UIHelper.initDate(orzeczenieLekarskieData, praca.getOrzeczenieLekarskieData());
            UIHelper.initDate(okresoweBadaniaBhpData, praca.getOkresoweBadaniaBhpData());
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
