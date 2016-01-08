package pl.malak.beans;

import org.springframework.stereotype.Service;
import pl.malak.beans.dao.PracaDao;
import pl.malak.beans.dao.PracodawcaDao;
import pl.malak.model.Praca;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;

@Service
public class PracaBean {

    @Resource
    private PracodawcaDao pracodawcaDao;

    @Resource
    private PracaDao pracaDao;

    @Transactional
    public Praca stworzPrace(
            Boolean kwsetionariuszOsobowyUbiegajacego, String kwsetionariuszOsobowyUbiegajacegoUwagi,
            Boolean podanieOPrace, String podanieOPraceUwagi, Boolean zyciorys, String zyciorysUwagi,
            Boolean swiadectwoPracy, String swiadectwoPracyUwagi, Boolean dokumentyPotwierdzajace,
            String dokumentyPotwierdzajaceUwagi, Boolean swiadectwoUkonczeniaGimnazjum,
            String swiadectwoUkonczeniaGimnazjumUwagi, Boolean podstawaUrlopu, String podstawaUrlopuUwagi,
            Boolean orzeczenieLekarskiePrzeciwskazania, String orzeczenieLekarskiePrzeciwskazaniaUwagi,
            Date orzeczenieLekarskiePrzeciwskazaniaData, Boolean dowodOsobisty, String dowodOsobistyUwagi,
            Boolean kwsetionariuszOsobowyPracownika, String kwsetionariuszOsobowyPracownikaUwagi, Boolean umowaOPrace,
            String umowaOPraceUwagi, Boolean informacjaOWarunkach, String informacjaOWarunkachUwagi,
            Boolean oswiadczenieORyzyku, String oswiadczenieORyzykuUwagi, Boolean oswiadczenieOPrzepisach,
            String oswiadczenieOPrzepisachUwagi, Boolean oswiadczenieOBhp, String oswiadczenieOBhpUwagi,
            Date oswiadczenieOBhpData, Boolean oswiadczenieOPozarze, String oswiadczenieOPozarzeUwagi,
            Boolean oswiadczenieOUprawnieniach, String oswiadczenieOUprawnieniachUwagi, Boolean pit, String pitUwagi,
            Boolean oswiadczenieUrzadSkarbowy, String oswiadczenieUrzadSkarbowyUwagi, Boolean oswiadczenieOKosztach,
            String oswiadczenieOKosztachUwagi, Boolean zgodaPracownika, String zgodaPracownikaUwagi,
            Boolean umowaOdpowiedzialnosci, String umowaOdpowiedzialnosciUwagi, Boolean umowaOZakazie,
            String umowaOZakazieUwagi, Boolean orzeczenieLekarskie, String orzeczenieLekarskieUwagi,
            Date orzeczenieLekarskieData, Boolean okresoweBadaniaBhp, String okresoweBadaniaBhpUwagi,
            Date okresoweBadaniaBhpData, Boolean drukZua, String drukZuaUwagi, Boolean oswiadczenieWypowiedzenie,
            String oswiadczenieWypowiedzenieUwagi, Boolean kopiaSwiadectwa, String kopiaSwiadectwaUwagi,
            Boolean drukZwua, String drukZwuaUwagi, String nazwa, Long pracodawcaId) {
        Praca praca = new Praca(
                kwsetionariuszOsobowyUbiegajacego, kwsetionariuszOsobowyUbiegajacegoUwagi, podanieOPrace,
                podanieOPraceUwagi, zyciorys, zyciorysUwagi, swiadectwoPracy, swiadectwoPracyUwagi,
                dokumentyPotwierdzajace, dokumentyPotwierdzajaceUwagi, swiadectwoUkonczeniaGimnazjum,
                swiadectwoUkonczeniaGimnazjumUwagi, podstawaUrlopu, podstawaUrlopuUwagi,
                orzeczenieLekarskiePrzeciwskazania, orzeczenieLekarskiePrzeciwskazaniaUwagi,
                orzeczenieLekarskiePrzeciwskazaniaData, dowodOsobisty, dowodOsobistyUwagi,
                kwsetionariuszOsobowyPracownika, kwsetionariuszOsobowyPracownikaUwagi, umowaOPrace, umowaOPraceUwagi,
                informacjaOWarunkach, informacjaOWarunkachUwagi, oswiadczenieORyzyku, oswiadczenieORyzykuUwagi,
                oswiadczenieOPrzepisach, oswiadczenieOPrzepisachUwagi, oswiadczenieOBhp, oswiadczenieOBhpUwagi,
                oswiadczenieOBhpData, oswiadczenieOPozarze, oswiadczenieOPozarzeUwagi, oswiadczenieOUprawnieniach,
                oswiadczenieOUprawnieniachUwagi, pit, pitUwagi, oswiadczenieUrzadSkarbowy,
                oswiadczenieUrzadSkarbowyUwagi, oswiadczenieOKosztach, oswiadczenieOKosztachUwagi, zgodaPracownika,
                zgodaPracownikaUwagi, umowaOdpowiedzialnosci, umowaOdpowiedzialnosciUwagi, umowaOZakazie,
                umowaOZakazieUwagi, orzeczenieLekarskie, orzeczenieLekarskieUwagi, orzeczenieLekarskieData,
                okresoweBadaniaBhp, okresoweBadaniaBhpUwagi, okresoweBadaniaBhpData, drukZua, drukZuaUwagi,
                oswiadczenieWypowiedzenie, oswiadczenieWypowiedzenieUwagi, kopiaSwiadectwa, kopiaSwiadectwaUwagi,
                drukZwua, drukZwuaUwagi, nazwa
        );

        praca.setPracodawca(pracodawcaDao.lazyLoad(pracodawcaId));
        pracaDao.create(praca);
        return praca;
    }

    @Transactional
    public Praca uaktualnijPrace(
            Long id, Boolean kwsetionariuszOsobowyUbiegajacego, String kwsetionariuszOsobowyUbiegajacegoUwagi,
            Boolean podanieOPrace, String podanieOPraceUwagi, Boolean zyciorys, String zyciorysUwagi,
            Boolean swiadectwoPracy, String swiadectwoPracyUwagi, Boolean dokumentyPotwierdzajace,
            String dokumentyPotwierdzajaceUwagi, Boolean swiadectwoUkonczeniaGimnazjum,
            String swiadectwoUkonczeniaGimnazjumUwagi, Boolean podstawaUrlopu, String podstawaUrlopuUwagi,
            Boolean orzeczenieLekarskiePrzeciwskazania, String orzeczenieLekarskiePrzeciwskazaniaUwagi,
            Date orzeczenieLekarskiePrzeciwskazaniaData, Boolean dowodOsobisty, String dowodOsobistyUwagi,
            Boolean kwsetionariuszOsobowyPracownika, String kwsetionariuszOsobowyPracownikaUwagi, Boolean umowaOPrace,
            String umowaOPraceUwagi, Boolean informacjaOWarunkach, String informacjaOWarunkachUwagi,
            Boolean oswiadczenieORyzyku, String oswiadczenieORyzykuUwagi, Boolean oswiadczenieOPrzepisach,
            String oswiadczenieOPrzepisachUwagi, Boolean oswiadczenieOBhp, String oswiadczenieOBhpUwagi,
            Date oswiadczenieOBhpData, Boolean oswiadczenieOPozarze, String oswiadczenieOPozarzeUwagi,
            Boolean oswiadczenieOUprawnieniach, String oswiadczenieOUprawnieniachUwagi, Boolean pit, String pitUwagi,
            Boolean oswiadczenieUrzadSkarbowy, String oswiadczenieUrzadSkarbowyUwagi, Boolean oswiadczenieOKosztach,
            String oswiadczenieOKosztachUwagi, Boolean zgodaPracownika, String zgodaPracownikaUwagi,
            Boolean umowaOdpowiedzialnosci, String umowaOdpowiedzialnosciUwagi, Boolean umowaOZakazie,
            String umowaOZakazieUwagi, Boolean orzeczenieLekarskie, String orzeczenieLekarskieUwagi,
            Date orzeczenieLekarskieData, Boolean okresoweBadaniaBhp, String okresoweBadaniaBhpUwagi,
            Date okresoweBadaniaBhpData, Boolean drukZua, String drukZuaUwagi, Boolean oswiadczenieWypowiedzenie,
            String oswiadczenieWypowiedzenieUwagi, Boolean kopiaSwiadectwa, String kopiaSwiadectwaUwagi,
            Boolean drukZwua, String drukZwuaUwagi, String nazwa) {
        Praca praca = pracaDao.load(id);

        praca.setKwestionariuszOsobowyUbiegajacego(kwsetionariuszOsobowyUbiegajacego);
        praca.setKwestionariuszOsobowyUbiegajacegoUwagi(kwsetionariuszOsobowyUbiegajacegoUwagi);
        praca.setPodanieOPrace(podanieOPrace);
        praca.setPodanieOPraceUwagi(podanieOPraceUwagi);
        praca.setZyciorys(zyciorys);
        praca.setZyciorysUwagi(zyciorysUwagi);
        praca.setSwiadectwoPracy(swiadectwoPracy);
        praca.setSwiadectwoPracyUwagi(swiadectwoPracyUwagi);
        praca.setDokumentyPotwierdzajace(dokumentyPotwierdzajace);
        praca.setDokumentyPotwierdzajaceUwagi(dokumentyPotwierdzajaceUwagi);
        praca.setSwiadectwoUkonczeniaGimnazjum(swiadectwoUkonczeniaGimnazjum);
        praca.setSwiadectwoUkonczeniaGimnazjumUwagi(swiadectwoUkonczeniaGimnazjumUwagi);
        praca.setPodstawaUrlopu(podstawaUrlopu);
        praca.setPodstawaUrlopuUwagi(podstawaUrlopuUwagi);
        praca.setOrzeczenieLekarskiePrzeciwskazania(orzeczenieLekarskiePrzeciwskazania);
        praca.setOrzeczenieLekarskiePrzeciwskazaniaUwagi(orzeczenieLekarskiePrzeciwskazaniaUwagi);
        praca.setOrzeczenieLekarskiePrzeciwskazaniaData(orzeczenieLekarskiePrzeciwskazaniaData);
        praca.setDowodOsobisty(dowodOsobisty);
        praca.setDowodOsobistyUwagi(dowodOsobistyUwagi);
        praca.setKwestionariuszOsobowyPracownika(kwsetionariuszOsobowyPracownika);
        praca.setKwestionariuszOsobowyPracownikaUwagi(kwsetionariuszOsobowyPracownikaUwagi);
        praca.setUmowaOPrace(umowaOPrace);
        praca.setUmowaOPraceUwagi(umowaOPraceUwagi);
        praca.setInformacjaOWarunkach(informacjaOWarunkach);
        praca.setInformacjaOWarunkachUwagi(informacjaOWarunkachUwagi);
        praca.setOswiadczenieORyzyku(oswiadczenieORyzyku);
        praca.setOswiadczenieORyzykuUwagi(oswiadczenieORyzykuUwagi);
        praca.setOswiadczenieOPrzepisach(oswiadczenieOPrzepisach);
        praca.setOswiadczenieOPrzepisachUwagi(oswiadczenieOPrzepisachUwagi);
        praca.setOswiadczenieOBhp(oswiadczenieOBhp);
        praca.setOswiadczenieOBhpUwagi(oswiadczenieOBhpUwagi);
        praca.setOswiadczenieOBhpData(oswiadczenieOBhpData);
        praca.setOswiadczenieOPozarze(oswiadczenieOPozarze);
        praca.setOswiadczenieOPozarzeUwagi(oswiadczenieOPozarzeUwagi);
        praca.setOswiadczenieOUprawnieniach(oswiadczenieOUprawnieniach);
        praca.setOswiadczenieOUprawnieniachUwagi(oswiadczenieOUprawnieniachUwagi);
        praca.setPit(pit);
        praca.setPitUwagi(pitUwagi);
        praca.setOswiadczenieUrzadSkarbowy(oswiadczenieUrzadSkarbowy);
        praca.setOswiadczenieUrzadSkarbowyUwagi(oswiadczenieUrzadSkarbowyUwagi);
        praca.setOswiadczenieOKosztach(oswiadczenieOKosztach);
        praca.setOswiadczenieOKosztachUwagi(oswiadczenieOKosztachUwagi);
        praca.setZgodaPracownika(zgodaPracownika);
        praca.setZgodaPracownikaUwagi(zgodaPracownikaUwagi);
        praca.setUmowaOdpowiedzialnosci(umowaOdpowiedzialnosci);
        praca.setUmowaOdpowiedzialnosciUwagi(umowaOdpowiedzialnosciUwagi);
        praca.setUmowaOZakazie(umowaOZakazie);
        praca.setUmowaOZakazieUwagi(umowaOZakazieUwagi);
        praca.setOrzeczenieLekarskie(orzeczenieLekarskie);
        praca.setOrzeczenieLekarskieUwagi(orzeczenieLekarskieUwagi);
        praca.setOrzeczenieLekarskieData(orzeczenieLekarskieData);
        praca.setOkresoweBadaniaBhp(okresoweBadaniaBhp);
        praca.setOkresoweBadaniaBhpUwagi(okresoweBadaniaBhpUwagi);
        praca.setOkresoweBadaniaBhpData(okresoweBadaniaBhpData);
        praca.setDrukZua(drukZua);
        praca.setDrukZuaUwagi(drukZuaUwagi);
        praca.setOswiadczenieWypowiedzenie(oswiadczenieWypowiedzenie);
        praca.setOswiadczenieWypowiedzenieUwagi(oswiadczenieWypowiedzenieUwagi);
        praca.setKopiaSwiadectwa(kopiaSwiadectwa);
        praca.setKopiaSwiadectwaUwagi(kopiaSwiadectwaUwagi);
        praca.setDrukZwua(drukZwua);
        praca.setDrukZwuaUwagi(drukZwuaUwagi);
        praca.setNazwa(nazwa);

        return praca;
    }
}
