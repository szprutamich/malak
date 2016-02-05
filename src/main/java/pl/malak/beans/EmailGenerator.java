package pl.malak.beans;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.malak.beans.dao.PracaDao;
import pl.malak.beans.dao.ZlecenieDao;
import pl.malak.helpers.Helper;
import pl.malak.model.Praca;
import pl.malak.model.Pracodawca;
import pl.malak.model.Zlecenie;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailGenerator {

    private enum Rodzaj {
        ZENSKI,
        MESKI,
        NIJAKI,
        LICZBA_MNOGA
    }

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

    @Value("${email.konto}")
    private String emailKonto;

    @Resource
    private ZlecenieDao zlecenieDao;

    @Resource
    private PracaDao pracaDao;

    public String generujEmail(Pracodawca pracodawca, String tytul) {
        int punkt = 1;
        StringBuilder stringBuilder = new StringBuilder();
        wstep(stringBuilder, tytul, pracodawca.getNazwa());
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

        zakonczenie(stringBuilder, pracodawca.getNazwa());

        return stringBuilder.toString()
                .replaceAll("podpisać", "prosimy o dostarczenie podpisanych dokumentów")
                .replaceAll("wypisać", "prosimy o dostarczenie wypisanych dokumentów");
    }


    private void wstep(StringBuilder stringBuilder, String tytul, String pracodawcaNazwa) {
        stringBuilder.append("<html>");
        stringBuilder.append("<head><title>");
        stringBuilder.append(tytul);
        stringBuilder.append("</title></head>");
        stringBuilder.append("<body>");
        stringBuilder.append("Witam");
        stringBuilder.append(newLine());
        stringBuilder.append(newLine());
        stringBuilder.append("Informujemy, że posiadają Państwo następujące braki w dokumentacji BHP firmy ");
        stringBuilder.append(pracodawcaNazwa);
        stringBuilder.append(":");
        stringBuilder.append(newLine());
    }

    private void zakonczenie(StringBuilder stringBuilder, String pracodawcaNazwa) {
        String text = stringBuilder.toString();
        Double kosztCalkowity = 0.00d;
        Pattern pattern = Pattern.compile("\\d+\\.\\d+zł");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String kwota = matcher.group(0);
            kosztCalkowity += Double.parseDouble(kwota.substring(0, kwota.length() - 2));
        }
        stringBuilder.append(newLine());
        stringBuilder.append(newLine());
        if (kosztCalkowity != 0.00d) {
            DecimalFormat df = new DecimalFormat("#.00");
            stringBuilder.append("Łączny koszt uzupełnienia braków w dokumentacji BHP");
            stringBuilder.append("przez współpracującego z nami BHPowca wynosi: ");
            stringBuilder.append("<b>");
            stringBuilder.append(df.format(kosztCalkowity));
            stringBuilder.append("zł brutto. ");
            stringBuilder.append("</b>");
            stringBuilder.append("Jeżeli życzą sobie Państwo abyśmy przekazali dokumentację do uzupełnienia - ");
            stringBuilder.append("prosimy o dokonanie przedpłaty na konto: ");
            stringBuilder.append(newLine());
            stringBuilder.append(newLine());
            stringBuilder.append(emailKonto);
            stringBuilder.append(newLine());
            stringBuilder.append(newLine());
            stringBuilder.append("Tytułem \"Opłata za uzupełnienie braków w dokumentacji BHP firmy (");
            stringBuilder.append(pracodawcaNazwa);
            stringBuilder.append(")\"");
            stringBuilder.append(newLine());
            stringBuilder.append(newLine());
            stringBuilder.append("<b>Pracę rozpoczniemy dopiero po otrzymaniu całości należności.</b>");
        }
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
        wiersze.add(obslugaWierszaProsta("Karta szkolenia wstępnego",
                false, zlecenie.getKartaSzkoleniaUwagi(), zlecenie.getKartaSzkoleniaData()));
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
                zlecenie.getOdbiorOdziezy(), zlecenie.getOdbiorOdziezyUwagi(), null, Rodzaj.MESKI));
        wiersze.add(obslugaWiersza("Zua",
                zlecenie.getZua(), zlecenie.getZuaUwagi(), null, Rodzaj.MESKI));
        wiersze.add(obslugaWiersza("Zza",
                zlecenie.getZza(), zlecenie.getZzaUwagi(), null, Rodzaj.MESKI));
        wiersze.add(obslugaWiersza("Zwua",
                zlecenie.getZwua(), zlecenie.getZwuaUwagi(), null, Rodzaj.MESKI));
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
