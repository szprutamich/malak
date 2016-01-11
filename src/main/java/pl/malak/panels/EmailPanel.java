package pl.malak.panels;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
import pl.malak.beans.EmailSender;
import pl.malak.beans.dao.PracaDao;
import pl.malak.beans.dao.ZlecenieDao;
import pl.malak.helpers.Helper;
import pl.malak.helpers.MyTransferHandler;
import pl.malak.helpers.UIHelper;
import pl.malak.model.Praca;
import pl.malak.model.Pracodawca;
import pl.malak.model.Zlecenie;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Component
public class EmailPanel extends FramePanel implements ActionListener {

    private enum Rodzaj {
        ZENSKI,
        MESKI,
        NIJAKI,
        LICZBA_MNOGA
    }

    @Resource
    private ZlecenieDao zlecenieDao;

    @Resource
    private PracaDao pracaDao;

    @Resource
    private EmailSender emailSender;

    @Value("${koszt.teczka.ogolna}")
    private Double kosztTeczkaOgolna;

    @Value("${koszt.ocena.ryzyka}")
    private Double kosztOcenaRyzyka;

    @Value("${koszt.szkolenia.okresowe}")
    private Double kosztSzkoleniaOkresowe;

    @Value("${koszt.szkolenia.pracodawcy}")
    private Double kosztSzkoleniaPracodawcy;

    @Value("${koszt.odziezowka}")
    private Double kosztOdziezowka;

    @Value("${koszt.badania}")
    private Double kosztBadania;

    @Value("${email.stopka}")
    private String emailStopka;

    private Pracodawca obecnyPracodawca;

    JLabel nazwaLabel = new JLabel();
    JLabel tytulLabel = new JLabel("Tytuł:");
    JTextField tytulText = new JTextField();
    JEditorPane emailText = new JEditorPane();
    JScrollPane scrollPane = new JScrollPane(emailText);
    JButton wyslij = new JButton("Wyślij");
    JButton wroc = new JButton("Wróć");
    JLabel czcionkaLabel = new JLabel("Czcionka:");
    JComboBox<String> czcionka = new JComboBox<>(new String[]{
            "10px", "12px", "14px", "16px", "18px", "20px"
    });

    public EmailPanel() {
        super();
        nazwaLabel.setFont(new Font(nazwaLabel.getFont().getFamily(), Font.PLAIN, 20));
        emailText.setContentType("text/html");
        emailText.setTransferHandler(new MyTransferHandler());

        layoutComponents();
        addListeners();
    }

    private void addListeners() {
        wroc.addActionListener(this);
        wyslij.addActionListener(this);
        czcionka.addActionListener(this);
    }

    private void layoutComponents() {
        setLayout(null);
        int xMargin = 20;
        int yMargin = 20;
        int width = 300;
        int doubleWidth = width * 2 + xMargin;
        int height = 25;
        int scrollPanelHeight = 15 * height;
        int x = xMargin;
        int y = yMargin;

        nazwaLabel.setBounds(x, y, doubleWidth, height);
        add(nazwaLabel);

        y += height + yMargin;
        tytulLabel.setBounds(x, y, width / 4, height);
        add(tytulLabel);

        x += xMargin + width / 4;
        tytulText.setBounds(x, y, width + width / 4 * 3, height);
        add(tytulText);

        y += height + yMargin;
        x = xMargin;
        czcionkaLabel.setBounds(x, y, width / 4, height);
        add(czcionkaLabel);

        x += xMargin + width / 4;
        czcionka.setBounds(x, y, width / 4, height);
        add(czcionka);

        y += height + yMargin;
        x = xMargin;
        scrollPane.setBounds(x, y, doubleWidth, scrollPanelHeight);
        add(scrollPane);

        y += scrollPanelHeight + yMargin;
        wyslij.setBounds(x, y, width, height);
        add(wyslij);

        x += xMargin + width;
        wroc.setBounds(x, y, width, height);
        add(wroc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == wyslij) {
            String tytul = tytulText.getText();
            String tresc = emailText.getText();
            String email = obecnyPracodawca.getEmail();
            String msg = emailSender.send(tytul, tresc, email);
            UIHelper.displayMessage(this, msg);
        } else if (e.getSource() == wroc) {
            getFrame().initPrzegladaniePracodawcow(obecnyPracodawca);
        } else if (e.getSource() == czcionka) {
            Integer rozmiar = Integer.parseInt(UIHelper.getComboText(czcionka).replaceAll("px", ""));
            emailText.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
            emailText.setFont(new Font(emailText.getFont().getFamily(), Font.PLAIN, rozmiar));
        }
    }

    public void init(Pracodawca pracodawca) {
        this.obecnyPracodawca = pracodawca;
        this.nazwaLabel.setText(String.format("Email do: %s (%s)", pracodawca.getNazwa(), pracodawca.getEmail()));
        int punkt = 1;
        StringBuilder stringBuilder = new StringBuilder();
        wstep(stringBuilder);
        String teczka;
        teczka = obslugaTeczki("Teczka ogólna", pracodawca.getTeczka(), pracodawca.getTeczkaUwagi(), null,
                punkt, kosztTeczkaOgolna, kosztBadania);
        if (StringUtils.isNotBlank(teczka)) {
            stringBuilder.append(teczka);
            punkt++;
        }
        teczka = obslugaTeczki("Ocena ryzyka", pracodawca.getOcena(), pracodawca.getOcenaUwagi(), null,
                punkt, kosztOcenaRyzyka, kosztBadania);
        if (StringUtils.isNotBlank(teczka)) {
            stringBuilder.append(teczka);
            punkt++;
        }

        teczka = obslugaTeczki("Szkolenia okresowe", pracodawca.getSzkoleniaOkresowe(),
                pracodawca.getSzkoleniaOkresoweUwagi(), null, punkt, kosztSzkoleniaOkresowe, kosztBadania);
        if (StringUtils.isNotBlank(teczka)) {
            stringBuilder.append(teczka);
            punkt++;
        }

        teczka = obslugaTeczki("Szkolenia pracodawcy", pracodawca.getSzkoleniaPracodawcy(),
                pracodawca.getSzkoleniaPracodawcyUwagi(), pracodawca.getSzkoleniaPracodawcyData(),
                punkt, kosztSzkoleniaPracodawcy, kosztBadania);
        if (StringUtils.isNotBlank(teczka)) {
            stringBuilder.append(teczka);
            punkt++;
        }

        teczka = obslugaTeczki("Odzieżówka", pracodawca.getOdziezowka(), pracodawca.getOdziezowkaUwagi(), null,
                punkt, kosztOdziezowka, kosztBadania);
        if (StringUtils.isNotBlank(teczka)) {
            stringBuilder.append(teczka);
        }

        String prace = obslugaPracownikow(pracodawca);
        String zlecenia = obslugaZlecen(pracodawca);
        if (StringUtils.isNotBlank(prace)) {
            stringBuilder.append(newLine());
            stringBuilder.append("Jednocześnie informujemy o obowiązku uzupełnienia dokumentów dotyczących umów " +
                    "o pracę:");
            stringBuilder.append(newLine());
            stringBuilder.append(prace);
        }
        if (StringUtils.isNotBlank(zlecenia)) {
            stringBuilder.append(newLine());
            stringBuilder.append("Informujemy także o obowiązku uzupełnienia dokumentów dotyczących umów " +
                    "cywilnoprawnych:");
            stringBuilder.append(newLine());
            stringBuilder.append(zlecenia);
        }

        zakonczenie(stringBuilder);

        String finalText = stringBuilder.toString()
                .replaceAll("podpisać", "prosimy o dostarczenie podpisanych dokumentów")
                .replaceAll("wypisać", "prosimy o dostarczenie wypisanych dokumentów");

        this.emailText.setText(finalText);
        this.tytulText.setText(String.format("Informacja o brakach w dokumentacji BHP firmy %s",
                obecnyPracodawca.getNazwa()));
        czcionka.setSelectedItem("12px");
    }

    private void wstep(StringBuilder stringBuilder) {
        stringBuilder.append("<html>");
        stringBuilder.append("<head><title>");
        stringBuilder.append(tytulText.getText());
        stringBuilder.append("</title></head>");
        stringBuilder.append("<body>");
        stringBuilder.append("Witam");
        stringBuilder.append(newLine());
        stringBuilder.append(newLine());
        stringBuilder.append("Informujemy, że posiadają Państwo następujące braki w dokumentacji BHP firmy ");
        stringBuilder.append(obecnyPracodawca.getNazwa());
        stringBuilder.append(":");
        stringBuilder.append(newLine());
    }

    private void zakonczenie(StringBuilder stringBuilder) {
        stringBuilder.append(newLine());
        stringBuilder.append(newLine());
        stringBuilder.append(emailStopka);
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");
    }

    private String obslugaTeczki(
            String text, Boolean teczka, String uwagi, Date data, int punkt, Double kosztTeczki, Double kosztBadania) {
        if (StringUtils.isNotBlank(uwagi) && (StringUtils.equalsIgnoreCase("nie dotyczy",
                uwagi.trim()) || StringUtils.equalsIgnoreCase("n/d", uwagi.trim()))) {
            return null;
        }
        if (StringUtils.isNotBlank(uwagi) || !teczka || data != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(punkt).append(". ").append(text);
            if (StringUtils.isNotBlank(uwagi)) {
                stringBuilder.append(" - ").append(uwagi.toLowerCase()
                        .replaceAll("podpisać", "prosimy o dostarczenie podpisanych dokumentów"));
            }
            if (!teczka) {
                stringBuilder.append(" - brak");
                stringBuilder.append(" - koszt sporządzenia ");
                DecimalFormat df = new DecimalFormat("#.00");
                stringBuilder.append(df.format(kosztTeczki));
                stringBuilder.append("zł brutto.");
            }
            if (data != null) {
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime dateTime = LocalDateTime.fromDateFields(data);
                if (now.isAfter(dateTime)) {
                    stringBuilder.append(" - straciły ważność dnia: ");
                    stringBuilder.append(Helper.formatDate(data));
                    stringBuilder.append(" - koszt odnowienia ");
                    DecimalFormat df = new DecimalFormat("#.00");
                    stringBuilder.append(df.format(kosztBadania));
                    stringBuilder.append("zł brutto.");
                } else if (now.plusDays(60).isAfter(dateTime)) {
                    stringBuilder.append(" - tracą ważność ważność dnia: ");
                    stringBuilder.append(Helper.formatDate(data));
                    stringBuilder.append(" - koszt odnowienia ");
                    DecimalFormat df = new DecimalFormat("#.00");
                    stringBuilder.append(df.format(kosztBadania));
                    stringBuilder.append("zł brutto.");
                } else {
                    stringBuilder.append(" - ważne są do dnia: ");
                    stringBuilder.append(Helper.formatDate(data));
                }
            }
            stringBuilder.append(newLine());
            return stringBuilder.toString();
        }
        return null;
    }

    private String obslugaPracownikow(Pracodawca pracodawca) {
        StringBuilder stringBuilder = new StringBuilder();
        int punkt = 1;
        List<Praca> prace = pracaDao.loadByPracodawcaId(pracodawca.getId());
        for (Praca praca : prace) {
            String text = oblugaPracownika(praca);
            if (StringUtils.isNotBlank(text)) {
                stringBuilder.append(newLine()).append(punkt++).append(". ")
                        .append(praca.getNazwa()).append(newLine()).append(text);
            }
        }
        return stringBuilder.toString();
    }

    private String obslugaZlecen(Pracodawca pracodawca) {
        StringBuilder stringBuilder = new StringBuilder();
        int punkt = 1;
        List<Zlecenie> zlecenia = zlecenieDao.loadByPracodawcaId(pracodawca.getId());
        for (Zlecenie zlecenie : zlecenia) {
            String text = obslugaZlecenia(zlecenie);
            if (StringUtils.isNotBlank(text)) {
                stringBuilder.append(newLine()).append(punkt++).append(". ")
                        .append(zlecenie.getNazwa()).append(newLine()).append(text);
            }
        }
        return stringBuilder.toString();
    }

    private String oblugaPracownika(Praca praca) {
        List<String> wiersze = new ArrayList<>();
        wiersze.add(obslugaWiersza("Wypełniony kwestionariusz osobowy dla osoby ubiegającej się o zatrudnienie",
                praca.getKwestionariuszOsobowyUbiegajacego(), praca.getKwestionariuszOsobowyUbiegajacegoUwagi(),
                null, Rodzaj.MESKI));
        wiersze.add(obslugaWiersza("Podanie o pracę", praca.getPodanieOPrace(), praca
                .getPodanieOPraceUwagi(), null, Rodzaj.NIJAKI));
        wiersze.add(obslugaWiersza("Życiorys pracownika", praca.getZyciorys(), praca
                .getZyciorysUwagi(), null, Rodzaj.MESKI));
        wiersze.add(obslugaWiersza("Świadectwo pracy z poprzednich miejsc pracy lub inne dokumenty potwierdzające " +
                "okresy zatrudnienia, w danym roku kalendarzowym", praca.getSwiadectwoPracy(), praca
                .getSwiadectwoPracyUwagi(), null, Rodzaj.NIJAKI));
        wiersze.add(obslugaWiersza("Dokumenty potwierdzające kwalifikacje zawodowe, " +
                "wymagane do wykonywania oferowanej pracy", praca.getDokumentyPotwierdzajace(), praca
                .getDokumentyPotwierdzajaceUwagi(), null, Rodzaj.LICZBA_MNOGA));
        wiersze.add(obslugaWiersza("Świadectwo ukończenia gimnazjum w przypadku zatrudnienia w celu przygotowania " +
                "zawodowego", praca.getSwiadectwoUkonczeniaGimnazjum(), praca
                .getSwiadectwoUkonczeniaGimnazjumUwagi(), null, Rodzaj.NIJAKI));
        wiersze.add(obslugaWiersza("Podstawa urlopu - DRUK", praca.getPodstawaUrlopu(), praca
                .getPodstawaUrlopuUwagi(), null, Rodzaj.ZENSKI));
        wiersze.add(obslugaWierszaProsta("Orzeczenie lekarskie stwierdzające brak przeciwwskazań do pracy na określonym " +
                "stanowisku", praca.getOrzeczenieLekarskiePrzeciwskazania(), praca
                .getOrzeczenieLekarskiePrzeciwskazaniaUwagi(), praca
                .getOrzeczenieLekarskiePrzeciwskazaniaData()));
        wiersze.add(obslugaWiersza("Dowód osobisty", praca.getDowodOsobisty(), praca
                .getDowodOsobistyUwagi(), null, Rodzaj.NIJAKI));

        wiersze.add(obslugaWiersza("Kwestionariusz osobowy pracownika", praca.getKwestionariuszOsobowyPracownika(),
                praca.getKwestionariuszOsobowyPracownikaUwagi(), null, Rodzaj.MESKI));
        wiersze.add(obslugaWiersza("Umowa o pracę + Aneks", praca.getUmowaOPrace(), praca
                .getUmowaOPraceUwagi(), null, Rodzaj.ZENSKI));
        wiersze.add(obslugaWiersza("Informacja o warunkach zatrudnienia", praca.getInformacjaOWarunkach(), praca
                .getInformacjaOWarunkachUwagi(), null, Rodzaj.ZENSKI));
        wiersze.add(obslugaWiersza("Oświadczenie pracownika o zapoznaniu się z informacją o ryzyku zawodowym",
                praca.getOswiadczenieORyzyku(), praca.getOswiadczenieORyzykuUwagi(), null, Rodzaj.NIJAKI));
        wiersze.add(obslugaWiersza("Oświadczenie pracownika o zapoznaniu się z obowiązkowymi przepisami w zakresie " +
                "równouprawnienia", praca.getOswiadczenieOPrzepisach(), praca
                .getOswiadczenieOPrzepisachUwagi(), null, Rodzaj.NIJAKI));
        wiersze.add(obslugaWierszaProsta("Oświadczenie pracownika o zapoznaniu się z przepisami BHP", praca
                .getOswiadczenieOBhp(), praca.getOswiadczenieOBhpUwagi(), praca
                .getOswiadczenieOBhpData()));
        wiersze.add(obslugaWiersza("Oświadczenie pracownika o zapoznaniu się przepisami przeciwpozarowymi", praca
                .getOswiadczenieOPozarze(), praca.getOswiadczenieOPozarzeUwagi(), null, Rodzaj.NIJAKI));
        wiersze.add(obslugaWiersza("Oświadczenie pracownika o zapoznaniu się z uprawnieniami rodzicielskimi itp.", praca
                .getOswiadczenieOUprawnieniach(), praca.getOswiadczenieOUprawnieniachUwagi(), null, Rodzaj.NIJAKI));
        wiersze.add(obslugaWiersza("PIT-2, czyli wyrażenie zgody pracownika na miesięczne potrącanie kwoty wolnej " +
                "z zaliczek na podatek dochodowy", praca.getPit(), praca.getPitUwagi(), null, Rodzaj.NIJAKI));
        wiersze.add(obslugaWiersza("Oświadczenie o podleganiu pod Urząd Skarbowy", praca
                .getOswiadczenieUrzadSkarbowy(), praca.getOswiadczenieUrzadSkarbowyUwagi(), null, Rodzaj.NIJAKI));
        wiersze.add(obslugaWiersza("Oświadczenie o podwyższonych kosztach uzyskania przychodów jeśli pracownik " +
                "mieszka poza miejscowowścią, w której jest siedziba pracodawcy", praca
                .getOswiadczenieOKosztach(), praca.getOswiadczenieOKosztachUwagi(), null, Rodzaj.NIJAKI));
        wiersze.add(obslugaWiersza("Zgoda pracownika na wypłatę wynagrodzenia na konto bankowe",
                praca.getZgodaPracownika(), praca.getZgodaPracownikaUwagi(), null, Rodzaj.ZENSKI));
        wiersze.add(obslugaWiersza("Umowa o odpowiedzialności materialnej za powierzone mienie", praca
                .getUmowaOdpowiedzialnosci(), praca.getUmowaOdpowiedzialnosciUwagi(), null, Rodzaj.ZENSKI));
        wiersze.add(obslugaWiersza("Umowa o zakazie konkurencji", praca.getUmowaOZakazie(), praca
                .getUmowaOZakazieUwagi(), null, Rodzaj.ZENSKI));
        wiersze.add(obslugaWiersza("Orzeczenie lekarskie dotyczace badań okresowych", praca.getOrzeczenieLekarskie(),
                praca.getOrzeczenieLekarskieUwagi(), praca.getOrzeczenieLekarskieData(), Rodzaj.NIJAKI));
        wiersze.add(obslugaWiersza("Okresowe badania BHP", praca.getOkresoweBadaniaBhp(), praca
                .getOkresoweBadaniaBhpUwagi(), praca.getOkresoweBadaniaBhpData(), Rodzaj.LICZBA_MNOGA));
        wiersze.add(obslugaWiersza("DRUK ZUA+ZCNA", praca.getDrukZua(), praca
                .getDrukZuaUwagi(), null, Rodzaj.MESKI));

        wiersze.add(obslugaWiersza("Oświadczenie o wypowiedzeniu lub rozwiązaniu umowy o pracę, a także dokumenty " +
                        "potwierdzające zaistnienie zdarzeń powodujących wygaśnięcie stosunku pracy",
                praca.getOswiadczenieWypowiedzenie(), praca.getOswiadczenieWypowiedzenieUwagi(), null, Rodzaj.NIJAKI
        ));
        wiersze.add(obslugaWiersza("Kopia wydanego pracownikowi świadectwa pracy oraz ewentualny wniosek o " +
                "sprostowanie świadectwa i korespondencja w tej sprawie", praca.getKopiaSwiadectwa(), praca
                .getKopiaSwiadectwaUwagi(), null, Rodzaj.ZENSKI));
        wiersze.add(obslugaWiersza("Druk ZWUA", praca.getDrukZwua(), praca.getDrukZwuaUwagi(), null, Rodzaj.MESKI));

        StringBuilder stringBuilder = new StringBuilder();
        for (String wiersz : wiersze) {
            stringBuilder.append(wiersz);
        }
        return stringBuilder.toString();
    }

    private String obslugaZlecenia(Zlecenie zlecenie) {
        List<String> wiersze = new ArrayList<>();
        wiersze.add(obslugaWiersza("Kwestionariusz osobowy + oświadczenie zleceniobiorcy",
                zlecenie.getKwestionariusz(), zlecenie.getKwestionariuszUwagi(), null, Rodzaj.MESKI));
        wiersze.add(obslugaWiersza("Karta szkolenia wstępnego",
                zlecenie.getKartaSzkolenia(), zlecenie.getKartaSzkoleniaUwagi(), zlecenie
                        .getKartaSzkoleniaData(), Rodzaj.ZENSKI));
        wiersze.add(obslugaWiersza("Szkolenie ogólne",
                zlecenie.getSzkolenie(), zlecenie.getSzkolenieUwagi(), null, Rodzaj.MESKI));
        wiersze.add(obslugaWiersza("Instruktaż stanowiskowy",
                zlecenie.getInstruktaz(), zlecenie.getInstruktazUwagi(), null, Rodzaj.MESKI));
        wiersze.add(obslugaWiersza("Ryzyko zawodowe",
                zlecenie.getRyzyko(), zlecenie.getRyzykoUwagi(), null, Rodzaj.NIJAKI));
        wiersze.add(obslugaWiersza("Instrukcje BHP",
                zlecenie.getInstrukcjeBhp(), zlecenie.getInstrukcjeBhpUwagi(), null, Rodzaj.LICZBA_MNOGA));
        wiersze.add(obslugaWiersza("Szkolenie okresowe BHP",
                zlecenie.getSzkolenieBhp(), zlecenie.getSzkolenieBhpUwagi(), zlecenie.getSzkolenieBhpData(),
                Rodzaj.NIJAKI));
        wiersze.add(obslugaWiersza("Rachunki",
                zlecenie.getRachunki(), zlecenie.getRachunkiUwagi(), null, Rodzaj.LICZBA_MNOGA));
        wiersze.add(obslugaWiersza("Umowa zlecenie",
                zlecenie.getUmowa(), zlecenie.getUmowaUwagi(), zlecenie.getUmowaData(), Rodzaj.ZENSKI));
        wiersze.add(obslugaWiersza("Badania lekarskie",
                zlecenie.getBadania(), zlecenie.getBadaniaUwagi(), zlecenie.getBadaniaData(), Rodzaj.LICZBA_MNOGA));
        wiersze.add(obslugaWiersza("Odbiór odzieży",
                zlecenie.getOdbiorOdziezy(), zlecenie.getOdbiorOdziezyUwagi(), zlecenie
                .getOdbiorOdziezyData(), Rodzaj.MESKI));
        wiersze.add(obslugaWiersza("Zua",
                zlecenie.getZua(), zlecenie.getZuaUwagi(), null, Rodzaj.MESKI));
        wiersze.add(obslugaWiersza("Zza",
                zlecenie.getZza(), zlecenie.getZzaUwagi(), null, Rodzaj.MESKI));
        wiersze.add(obslugaWiersza("Zwua",
                zlecenie.getZwua(), zlecenie.getZwuaUwagi(), null, Rodzaj.MESKI));
        wiersze.add(obslugaWiersza("Dowód osobisty",
                zlecenie.getDowod(), zlecenie.getDowodUwagi(), null, Rodzaj.MESKI));
        wiersze.add(obslugaWiersza("Życiorys",
                zlecenie.getZyciorys(), zlecenie.getZyciorysUwagi(), null, Rodzaj.MESKI));
        wiersze.add(obslugaWiersza("Zaświadczenie - student",
                zlecenie.getZaswiadczenieStudent(), zlecenie.getZaswiadczenieStudentUwagi(), null, Rodzaj.NIJAKI));

        StringBuilder stringBuilder = new StringBuilder();
        for (String wiersz : wiersze) {
            stringBuilder.append(wiersz);
        }
        return stringBuilder.toString();
    }

    private String obslugaWierszaProsta(String text, Boolean value, String uwagi, Date data) {
        if (StringUtils.isNotBlank(uwagi) && (StringUtils.equalsIgnoreCase("nie dotyczy",
                uwagi.trim()) || StringUtils.equalsIgnoreCase("n/d", uwagi.trim()))) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (StringUtils.isNotBlank(uwagi)) {
            stringBuilder.append(" - ").append(text);
            if (StringUtils.isNotBlank(uwagi)) {
                stringBuilder.append(" - ").append(uwagi.toLowerCase());
            }
        }
        if (!value && data == null) {
            stringBuilder.append(" - brak");
        }
        return stringBuilder.toString();
    }

    private String obslugaWiersza(String text, Boolean value, String uwagi, Date data, Rodzaj rodzaj) {
        if (StringUtils.isNotBlank(uwagi) && (StringUtils.equalsIgnoreCase("nie dotyczy",
                uwagi.trim()) || StringUtils.equalsIgnoreCase("n/d", uwagi.trim()))) {
            return "";
        }
        if (StringUtils.isNotBlank(uwagi) || !value || data != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" - ").append(text);
            if (StringUtils.isNotBlank(uwagi)) {
                stringBuilder.append(" - ").append(uwagi.toLowerCase());
            }
            if (!value) {
                stringBuilder.append(" - brak");
            }
            if (data != null) {
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime dateTime = LocalDateTime.fromDateFields(data);
                if (now.isAfter(dateTime)) {
                    switch (rodzaj) {
                        case MESKI:
                            stringBuilder.append(" - stracił ważność dnia: ");
                            break;
                        case NIJAKI:
                            stringBuilder.append(" - straciło ważność dnia: ");
                            break;
                        case ZENSKI:
                            stringBuilder.append(" - straciła ważność dnia: ");
                            break;
                        case LICZBA_MNOGA:
                            stringBuilder.append(" - straciły ważność dnia: ");
                            break;
                    }
                    stringBuilder.append(Helper.formatDate(data)).append(" - koszt odnowienia ");
                    DecimalFormat df = new DecimalFormat("#.00");
                    stringBuilder.append(df.format(kosztBadania));
                    stringBuilder.append("zł brutto.");
                } else if (now.plusDays(60).isAfter(dateTime)) {
                    switch (rodzaj) {
                        case MESKI:
                        case NIJAKI:
                        case ZENSKI:
                            stringBuilder.append(" - traci ważność ważność dnia: ");
                            break;
                        case LICZBA_MNOGA:
                            stringBuilder.append(" - tracą ważność ważność dnia: ");
                            break;
                    }
                    stringBuilder.append(Helper.formatDate(data));
                    stringBuilder.append(" - koszt odnowienia ");
                    DecimalFormat df = new DecimalFormat("#.00");
                    stringBuilder.append(df.format(kosztBadania));
                    stringBuilder.append("zł brutto.");
                } else {
                    switch (rodzaj) {
                        case MESKI:
                            stringBuilder.append(" - ważny jest do dnia: ");
                            break;
                        case NIJAKI:
                            stringBuilder.append(" - ważne jest do dnia: ");
                            break;
                        case ZENSKI:
                            stringBuilder.append(" - ważna jest do dnia: ");
                            break;
                        case LICZBA_MNOGA:
                            stringBuilder.append(" - ważne są do dnia: ");
                            break;
                    }
                    stringBuilder.append(Helper.formatDate(data));
                }
            }
            stringBuilder.append(newLine());
            return stringBuilder.toString();
        }
        return "";
    }

    private String newLine() {
        return "<br/>";
    }
}
