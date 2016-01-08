package pl.malak.model;

import org.apache.poi.ss.usermodel.Sheet;
import pl.malak.Field;
import pl.malak.helpers.SheetHelper;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "prace")
public class Praca {

    @Column(name = "kwestionariusz_osobowy_ubiegajacego")
    private Boolean kwestionariuszOsobowyUbiegajacego = false;

    @Column(name = "kwestionariusz_osobowy_ubiegajacego_uwagi")
    private String kwestionariuszOsobowyUbiegajacegoUwagi;

    @Column(name = "podanie_o_prace")
    private Boolean podanieOPrace = false;

    @Column(name = "podanie_o_prace_uwagi")
    private String podanieOPraceUwagi;

    @Column(name = "zyciorys")
    private Boolean zyciorys = false;

    @Column(name = "zyciorys_uwagi")
    private String zyciorysUwagi;

    @Column(name = "swiadectwo_pracy")
    private Boolean swiadectwoPracy = false;

    @Column(name = "swiadectwo_pracy_uwagi")
    private String swiadectwoPracyUwagi;

    @Column(name = "dokumenty_potwierdzajace")
    private Boolean dokumentyPotwierdzajace = false;

    @Column(name = "dokumenty_potwierdzajace_uwagi")
    private String dokumentyPotwierdzajaceUwagi;

    @Column(name = "swiadectwo_ukonczenia_gimnazjum")
    private Boolean swiadectwoUkonczeniaGimnazjum = false;

    @Column(name = "swiadectwo_ukonczenia_gimnazjum_uwagi")
    private String swiadectwoUkonczeniaGimnazjumUwagi;

    @Column(name = "podstawa_urlopu")
    private Boolean podstawaUrlopu = false;

    @Column(name = "podstawa_urlopu_uwagi")
    private String podstawaUrlopuUwagi;

    @Column(name = "orzeczenie_lekarskie_przeciwskazania")
    private Boolean orzeczenieLekarskiePrzeciwskazania = false;

    @Column(name = "orzeczenie_lekarskie_przeciwskazania_uwagi")
    private String orzeczenieLekarskiePrzeciwskazaniaUwagi;

    @Column(name = "orzeczenie_lekarskie_przeciwskazania_data")
    private Date orzeczenieLekarskiePrzeciwskazaniaData;

    @Column(name = "dowod_osobisty")
    private Boolean dowodOsobisty = false;

    @Column(name = "dowod_osobisty_uwagi")
    private String dowodOsobistyUwagi;

    @Column(name = "kwestionariusz_osobowy_pracownika")
    private Boolean kwestionariuszOsobowyPracownika = false;

    @Column(name = "kwestionariusz_osobowy_pracownika_uwagi")
    private String kwestionariuszOsobowyPracownikaUwagi;

    @Column(name = "umowa_o_prace")
    private Boolean umowaOPrace = false;

    @Column(name = "umowa_o_prace_uwagi")
    private String umowaOPraceUwagi;

    @Column(name = "informacja_o_warunkach")
    private Boolean informacjaOWarunkach = false;

    @Column(name = "informacja_o_warunkach_uwagi")
    private String informacjaOWarunkachUwagi;

    @Column(name = "oswiadczenie_o_ryzyku")
    private Boolean oswiadczenieORyzyku = false;

    @Column(name = "oswiadczenie_o_ryzyku_uwagi")
    private String oswiadczenieORyzykuUwagi;

    @Column(name = "oswiadczenie_o_przepisach")
    private Boolean oswiadczenieOPrzepisach = false;

    @Column(name = "oswiadczenie_o_przepisach_uwagi")
    private String oswiadczenieOPrzepisachUwagi;

    @Column(name = "oswiadczenie_o_bhp")
    private Boolean oswiadczenieOBhp = false;

    @Column(name = "oswiadczenie_o_bhp_uwagi")
    private String oswiadczenieOBhpUwagi;

    @Column(name = "oswiadczenie_o_bhp_data")
    private Date oswiadczenieOBhpData;

    @Column(name = "oswiadczenie_o_pozarze")
    private Boolean oswiadczenieOPozarze = false;

    @Column(name = "oswiadczenie_o_pozarze_uwagi")
    private String oswiadczenieOPozarzeUwagi;

    @Column(name = "oswiadczenie_o_uprawnieniach")
    private Boolean oswiadczenieOUprawnieniach = false;

    @Column(name = "oswiadczenie_o_uprawnieniach_uwagi")
    private String oswiadczenieOUprawnieniachUwagi;

    @Column(name = "pit")
    private Boolean pit = false;

    @Column(name = "pit_uwagi")
    private String pitUwagi;

    @Column(name = "oswiadczenie_urzad_skarbowy")
    private Boolean oswiadczenieUrzadSkarbowy = false;

    @Column(name = "oswiadczenie_urzad_skarbowy_uwagi")
    private String oswiadczenieUrzadSkarbowyUwagi;

    @Column(name = "oswiadczenie_o_kosztach")
    private Boolean oswiadczenieOKosztach = false;

    @Column(name = "oswiadczenie_o_kosztach_uwagi")
    private String oswiadczenieOKosztachUwagi;

    @Column(name = "zgoda_pracownika")
    private Boolean zgodaPracownika = false;

    @Column(name = "zgoda_pracownika_uwagi")
    private String zgodaPracownikaUwagi;

    @Column(name = "umowa_odpowiedzialnosci")
    private Boolean umowaOdpowiedzialnosci = false;

    @Column(name = "umowa_odpowiedzialnosci_uwagi")
    private String umowaOdpowiedzialnosciUwagi;

    @Column(name = "umowa_o_zakazie")
    private Boolean umowaOZakazie = false;

    @Column(name = "umowa_o_zakazie_uwagi")
    private String umowaOZakazieUwagi;

    @Column(name = "orzeczenie_lekarskie")
    private Boolean orzeczenieLekarskie = false;

    @Column(name = "orzeczenie_lekarskie_uwagi")
    private String orzeczenieLekarskieUwagi;

    @Column(name = "orzeczenie_lekarskie_data")
    private Date orzeczenieLekarskieData;

    @Column(name = "okresowe_badania_bhp")
    private Boolean okresoweBadaniaBhp = false;

    @Column(name = "okresowe_badania_bhp_uwagi")
    private String okresoweBadaniaBhpUwagi;

    @Column(name = "okresowe_badania_bhp_data")
    private Date okresoweBadaniaBhpData;

    @Column(name = "druk_zua")
    private Boolean drukZua = false;

    @Column(name = "druk_zua_uwagi")
    private String drukZuaUwagi;

    @Column(name = "oswiadczenie_wypowiedzenie")
    private Boolean oswiadczenieWypowiedzenie = false;

    @Column(name = "oswiadczenie_wypowiedzenie_uwagi")
    private String oswiadczenieWypowiedzenieUwagi;

    @Column(name = "kopia_swiadectwa")
    private Boolean kopiaSwiadectwa = false;

    @Column(name = "kopia_swiadectwa_uwagi")
    private String kopiaSwiadectwaUwagi;

    @Column(name = "druk_zwua")
    private Boolean drukZwua = false;

    @Column(name = "druk_zwua_uwagi")
    private String drukZwuaUwagi;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nazwa")
    private String nazwa;

    @Column(name = "data_usuniecia")
    private Date dataUsuniecia;

    @Transient
    private String pracodawcaNazwa;

    @Transient
    private String sheetName;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "pracodawca_id", nullable = false)
    private Pracodawca pracodawca;

    public Praca() {
    }

    public Praca(
            Boolean kwestionariuszOsobowyUbiegajacego, String kwestionariuszOsobowyUbiegajacegoUwagi,
            Boolean podanieOPrace, String podanieOPraceUwagi, Boolean zyciorys, String zyciorysUwagi,
            Boolean swiadectwoPracy, String swiadectwoPracyUwagi, Boolean dokumentyPotwierdzajace,
            String dokumentyPotwierdzajaceUwagi, Boolean swiadectwoUkonczeniaGimnazjum,
            String swiadectwoUkonczeniaGimnazjumUwagi, Boolean podstawaUrlopu, String podstawaUrlopuUwagi,
            Boolean orzeczenieLekarskiePrzeciwskazania, String orzeczenieLekarskiePrzeciwskazaniaUwagi,
            Date orzeczenieLekarskiePrzeciwskazaniaData, Boolean dowodOsobisty, String dowodOsobistyUwagi,
            Boolean kwestionariuszOsobowyPracownika, String kwestionariuszOsobowyPracownikaUwagi,
            Boolean umowaOPrace, String umowaOPraceUwagi, Boolean informacjaOWarunkach,
            String informacjaOWarunkachUwagi, Boolean oswiadczenieORyzyku, String oswiadczenieORyzykuUwagi,
            Boolean oswiadczenieOPrzepisach, String oswiadczenieOPrzepisachUwagi, Boolean oswiadczenieOBhp,
            String oswiadczenieOBhpUwagi, Date oswiadczenieOBhpData, Boolean oswiadczenieOPozarze,
            String oswiadczenieOPozarzeUwagi, Boolean oswiadczenieOUprawnieniach,
            String oswiadczenieOUprawnieniachUwagi, Boolean pit, String pitUwagi,
            Boolean oswiadczenieUrzadSkarbowy, String oswiadczenieUrzadSkarbowyUwagi,
            Boolean oswiadczenieOKosztach, String oswiadczenieOKosztachUwagi, Boolean zgodaPracownika,
            String zgodaPracownikaUwagi, Boolean umowaOdpowiedzialnosci, String umowaOdpowiedzialnosciUwagi,
            Boolean umowaOZakazie, String umowaOZakazieUwagi, Boolean orzeczenieLekarskie,
            String orzeczenieLekarskieUwagi, Date orzeczenieLekarskieData, Boolean okresoweBadaniaBhp,
            String okresoweBadaniaBhpUwagi, Date okresoweBadaniaBhpData, Boolean drukZua, String drukZuaUwagi,
            Boolean oswiadczenieWypowiedzenie, String oswiadczenieWypowiedzenieUwagi, Boolean kopiaSwiadectwa,
            String kopiaSwiadectwaUwagi, Boolean drukZwua, String drukZwuaUwagi, String nazwa) {
        this.kwestionariuszOsobowyUbiegajacego = kwestionariuszOsobowyUbiegajacego;
        this.kwestionariuszOsobowyUbiegajacegoUwagi = kwestionariuszOsobowyUbiegajacegoUwagi;
        this.podanieOPrace = podanieOPrace;
        this.podanieOPraceUwagi = podanieOPraceUwagi;
        this.zyciorys = zyciorys;
        this.zyciorysUwagi = zyciorysUwagi;
        this.swiadectwoPracy = swiadectwoPracy;
        this.swiadectwoPracyUwagi = swiadectwoPracyUwagi;
        this.dokumentyPotwierdzajace = dokumentyPotwierdzajace;
        this.dokumentyPotwierdzajaceUwagi = dokumentyPotwierdzajaceUwagi;
        this.swiadectwoUkonczeniaGimnazjum = swiadectwoUkonczeniaGimnazjum;
        this.swiadectwoUkonczeniaGimnazjumUwagi = swiadectwoUkonczeniaGimnazjumUwagi;
        this.podstawaUrlopu = podstawaUrlopu;
        this.podstawaUrlopuUwagi = podstawaUrlopuUwagi;
        this.orzeczenieLekarskiePrzeciwskazania = orzeczenieLekarskiePrzeciwskazania;
        this.orzeczenieLekarskiePrzeciwskazaniaUwagi = orzeczenieLekarskiePrzeciwskazaniaUwagi;
        this.orzeczenieLekarskiePrzeciwskazaniaData = orzeczenieLekarskiePrzeciwskazaniaData;
        this.dowodOsobisty = dowodOsobisty;
        this.dowodOsobistyUwagi = dowodOsobistyUwagi;
        this.kwestionariuszOsobowyPracownika = kwestionariuszOsobowyPracownika;
        this.kwestionariuszOsobowyPracownikaUwagi = kwestionariuszOsobowyPracownikaUwagi;
        this.umowaOPrace = umowaOPrace;
        this.umowaOPraceUwagi = umowaOPraceUwagi;
        this.informacjaOWarunkach = informacjaOWarunkach;
        this.informacjaOWarunkachUwagi = informacjaOWarunkachUwagi;
        this.oswiadczenieORyzyku = oswiadczenieORyzyku;
        this.oswiadczenieORyzykuUwagi = oswiadczenieORyzykuUwagi;
        this.oswiadczenieOPrzepisach = oswiadczenieOPrzepisach;
        this.oswiadczenieOPrzepisachUwagi = oswiadczenieOPrzepisachUwagi;
        this.oswiadczenieOBhp = oswiadczenieOBhp;
        this.oswiadczenieOBhpUwagi = oswiadczenieOBhpUwagi;
        this.oswiadczenieOBhpData = oswiadczenieOBhpData;
        this.oswiadczenieOPozarze = oswiadczenieOPozarze;
        this.oswiadczenieOPozarzeUwagi = oswiadczenieOPozarzeUwagi;
        this.oswiadczenieOUprawnieniach = oswiadczenieOUprawnieniach;
        this.oswiadczenieOUprawnieniachUwagi = oswiadczenieOUprawnieniachUwagi;
        this.pit = pit;
        this.pitUwagi = pitUwagi;
        this.oswiadczenieUrzadSkarbowy = oswiadczenieUrzadSkarbowy;
        this.oswiadczenieUrzadSkarbowyUwagi = oswiadczenieUrzadSkarbowyUwagi;
        this.oswiadczenieOKosztach = oswiadczenieOKosztach;
        this.oswiadczenieOKosztachUwagi = oswiadczenieOKosztachUwagi;
        this.zgodaPracownika = zgodaPracownika;
        this.zgodaPracownikaUwagi = zgodaPracownikaUwagi;
        this.umowaOdpowiedzialnosci = umowaOdpowiedzialnosci;
        this.umowaOdpowiedzialnosciUwagi = umowaOdpowiedzialnosciUwagi;
        this.umowaOZakazie = umowaOZakazie;
        this.umowaOZakazieUwagi = umowaOZakazieUwagi;
        this.orzeczenieLekarskie = orzeczenieLekarskie;
        this.orzeczenieLekarskieUwagi = orzeczenieLekarskieUwagi;
        this.orzeczenieLekarskieData = orzeczenieLekarskieData;
        this.okresoweBadaniaBhp = okresoweBadaniaBhp;
        this.okresoweBadaniaBhpUwagi = okresoweBadaniaBhpUwagi;
        this.okresoweBadaniaBhpData = okresoweBadaniaBhpData;
        this.drukZua = drukZua;
        this.drukZuaUwagi = drukZuaUwagi;
        this.oswiadczenieWypowiedzenie = oswiadczenieWypowiedzenie;
        this.oswiadczenieWypowiedzenieUwagi = oswiadczenieWypowiedzenieUwagi;
        this.kopiaSwiadectwa = kopiaSwiadectwa;
        this.kopiaSwiadectwaUwagi = kopiaSwiadectwaUwagi;
        this.drukZwua = drukZwua;
        this.drukZwuaUwagi = drukZwuaUwagi;
        this.nazwa = nazwa;
    }

    public Praca(Sheet sheet) {
        parse(sheet);
        this.sheetName = sheet.getSheetName();
    }

    public Long getId() {
        return id;
    }

    public Boolean getKwestionariuszOsobowyUbiegajacego() {
        return kwestionariuszOsobowyUbiegajacego;
    }

    public void setKwestionariuszOsobowyUbiegajacego(Boolean kwsetionariuszOsobowyUbiegajacego) {
        this.kwestionariuszOsobowyUbiegajacego = kwsetionariuszOsobowyUbiegajacego;
    }

    public String getKwestionariuszOsobowyUbiegajacegoUwagi() {
        return kwestionariuszOsobowyUbiegajacegoUwagi;
    }

    public void setKwestionariuszOsobowyUbiegajacegoUwagi(String kwsetionariuszOsobowyUbiegajacegoUwagi) {
        this.kwestionariuszOsobowyUbiegajacegoUwagi = kwsetionariuszOsobowyUbiegajacegoUwagi;
    }

    public Boolean getPodanieOPrace() {
        return podanieOPrace;
    }

    public void setPodanieOPrace(Boolean podanieOPrace) {
        this.podanieOPrace = podanieOPrace;
    }

    public String getPodanieOPraceUwagi() {
        return podanieOPraceUwagi;
    }

    public void setPodanieOPraceUwagi(String podanieOPraceUwagi) {
        this.podanieOPraceUwagi = podanieOPraceUwagi;
    }

    public Boolean getZyciorys() {
        return zyciorys;
    }

    public void setZyciorys(Boolean zyciorys) {
        this.zyciorys = zyciorys;
    }

    public String getZyciorysUwagi() {
        return zyciorysUwagi;
    }

    public void setZyciorysUwagi(String zyciorysUwagi) {
        this.zyciorysUwagi = zyciorysUwagi;
    }

    public Boolean getSwiadectwoPracy() {
        return swiadectwoPracy;
    }

    public void setSwiadectwoPracy(Boolean swiadectwoPracy) {
        this.swiadectwoPracy = swiadectwoPracy;
    }

    public String getSwiadectwoPracyUwagi() {
        return swiadectwoPracyUwagi;
    }

    public void setSwiadectwoPracyUwagi(String swiadectwoPracyUwagi) {
        this.swiadectwoPracyUwagi = swiadectwoPracyUwagi;
    }

    public Boolean getDokumentyPotwierdzajace() {
        return dokumentyPotwierdzajace;
    }

    public void setDokumentyPotwierdzajace(Boolean dokumentyPotwierdzajace) {
        this.dokumentyPotwierdzajace = dokumentyPotwierdzajace;
    }

    public String getDokumentyPotwierdzajaceUwagi() {
        return dokumentyPotwierdzajaceUwagi;
    }

    public void setDokumentyPotwierdzajaceUwagi(String dokumentyPotwierdzajaceUwagi) {
        this.dokumentyPotwierdzajaceUwagi = dokumentyPotwierdzajaceUwagi;
    }

    public Boolean getSwiadectwoUkonczeniaGimnazjum() {
        return swiadectwoUkonczeniaGimnazjum;
    }

    public void setSwiadectwoUkonczeniaGimnazjum(Boolean swiadectwoUkonczeniaGimnazjum) {
        this.swiadectwoUkonczeniaGimnazjum = swiadectwoUkonczeniaGimnazjum;
    }

    public String getSwiadectwoUkonczeniaGimnazjumUwagi() {
        return swiadectwoUkonczeniaGimnazjumUwagi;
    }

    public void setSwiadectwoUkonczeniaGimnazjumUwagi(String swiadectwoUkonczeniaGimnazjumUwagi) {
        this.swiadectwoUkonczeniaGimnazjumUwagi = swiadectwoUkonczeniaGimnazjumUwagi;
    }

    public Boolean getPodstawaUrlopu() {
        return podstawaUrlopu;
    }

    public void setPodstawaUrlopu(Boolean podstawaUrlopu) {
        this.podstawaUrlopu = podstawaUrlopu;
    }

    public String getPodstawaUrlopuUwagi() {
        return podstawaUrlopuUwagi;
    }

    public void setPodstawaUrlopuUwagi(String podstawaUrlopuUwagi) {
        this.podstawaUrlopuUwagi = podstawaUrlopuUwagi;
    }

    public Boolean getOrzeczenieLekarskiePrzeciwskazania() {
        return orzeczenieLekarskiePrzeciwskazania;
    }

    public void setOrzeczenieLekarskiePrzeciwskazania(Boolean orzeczenieLekarskiePrzeciwskazania) {
        this.orzeczenieLekarskiePrzeciwskazania = orzeczenieLekarskiePrzeciwskazania;
    }

    public String getOrzeczenieLekarskiePrzeciwskazaniaUwagi() {
        return orzeczenieLekarskiePrzeciwskazaniaUwagi;
    }

    public void setOrzeczenieLekarskiePrzeciwskazaniaUwagi(String orzeczenieLekarskiePrzeciwskazaniaUwagi) {
        this.orzeczenieLekarskiePrzeciwskazaniaUwagi = orzeczenieLekarskiePrzeciwskazaniaUwagi;
    }

    public Date getOrzeczenieLekarskiePrzeciwskazaniaData() {
        return orzeczenieLekarskiePrzeciwskazaniaData;
    }

    public void setOrzeczenieLekarskiePrzeciwskazaniaData(Date orzeczenieLekarskiePrzeciwskazaniaData) {
        this.orzeczenieLekarskiePrzeciwskazaniaData = orzeczenieLekarskiePrzeciwskazaniaData;
    }

    public Boolean getDowodOsobisty() {
        return dowodOsobisty;
    }

    public void setDowodOsobisty(Boolean dowodOsobisty) {
        this.dowodOsobisty = dowodOsobisty;
    }

    public String getDowodOsobistyUwagi() {
        return dowodOsobistyUwagi;
    }

    public void setDowodOsobistyUwagi(String dowodOsobistyUwagi) {
        this.dowodOsobistyUwagi = dowodOsobistyUwagi;
    }

    public Boolean getKwestionariuszOsobowyPracownika() {
        return kwestionariuszOsobowyPracownika;
    }

    public void setKwestionariuszOsobowyPracownika(Boolean kwsetionariuszOsobowyPracownika) {
        this.kwestionariuszOsobowyPracownika = kwsetionariuszOsobowyPracownika;
    }

    public String getKwestionariuszOsobowyPracownikaUwagi() {
        return kwestionariuszOsobowyPracownikaUwagi;
    }

    public void setKwestionariuszOsobowyPracownikaUwagi(String kwsetionariuszOsobowyPracownikaUwagi) {
        this.kwestionariuszOsobowyPracownikaUwagi = kwsetionariuszOsobowyPracownikaUwagi;
    }

    public Boolean getUmowaOPrace() {
        return umowaOPrace;
    }

    public void setUmowaOPrace(Boolean umowaOPrace) {
        this.umowaOPrace = umowaOPrace;
    }

    public String getUmowaOPraceUwagi() {
        return umowaOPraceUwagi;
    }

    public void setUmowaOPraceUwagi(String umowaOPraceUwagi) {
        this.umowaOPraceUwagi = umowaOPraceUwagi;
    }

    public Boolean getInformacjaOWarunkach() {
        return informacjaOWarunkach;
    }

    public void setInformacjaOWarunkach(Boolean informacjaOWarunkach) {
        this.informacjaOWarunkach = informacjaOWarunkach;
    }

    public String getInformacjaOWarunkachUwagi() {
        return informacjaOWarunkachUwagi;
    }

    public void setInformacjaOWarunkachUwagi(String informacjaOWarunkachUwagi) {
        this.informacjaOWarunkachUwagi = informacjaOWarunkachUwagi;
    }

    public Boolean getOswiadczenieORyzyku() {
        return oswiadczenieORyzyku;
    }

    public void setOswiadczenieORyzyku(Boolean oswiadczenieORyzyku) {
        this.oswiadczenieORyzyku = oswiadczenieORyzyku;
    }

    public String getOswiadczenieORyzykuUwagi() {
        return oswiadczenieORyzykuUwagi;
    }

    public void setOswiadczenieORyzykuUwagi(String oswiadczenieORyzykuUwagi) {
        this.oswiadczenieORyzykuUwagi = oswiadczenieORyzykuUwagi;
    }

    public Boolean getOswiadczenieOPrzepisach() {
        return oswiadczenieOPrzepisach;
    }

    public void setOswiadczenieOPrzepisach(Boolean oswiadczenieOPrzepisach) {
        this.oswiadczenieOPrzepisach = oswiadczenieOPrzepisach;
    }

    public String getOswiadczenieOPrzepisachUwagi() {
        return oswiadczenieOPrzepisachUwagi;
    }

    public void setOswiadczenieOPrzepisachUwagi(String oswiadczenieOPrzepisachUwagi) {
        this.oswiadczenieOPrzepisachUwagi = oswiadczenieOPrzepisachUwagi;
    }

    public Boolean getOswiadczenieOBhp() {
        return oswiadczenieOBhp;
    }

    public void setOswiadczenieOBhp(Boolean oswiadczenieOBhp) {
        this.oswiadczenieOBhp = oswiadczenieOBhp;
    }

    public String getOswiadczenieOBhpUwagi() {
        return oswiadczenieOBhpUwagi;
    }

    public void setOswiadczenieOBhpUwagi(String oswiadczenieOBhpUwagi) {
        this.oswiadczenieOBhpUwagi = oswiadczenieOBhpUwagi;
    }

    public Date getOswiadczenieOBhpData() {
        return oswiadczenieOBhpData;
    }

    public void setOswiadczenieOBhpData(Date oswiadczenieOBhpData) {
        this.oswiadczenieOBhpData = oswiadczenieOBhpData;
    }

    public Boolean getOswiadczenieOPozarze() {
        return oswiadczenieOPozarze;
    }

    public void setOswiadczenieOPozarze(Boolean oswiadczenieOPozarze) {
        this.oswiadczenieOPozarze = oswiadczenieOPozarze;
    }

    public String getOswiadczenieOPozarzeUwagi() {
        return oswiadczenieOPozarzeUwagi;
    }

    public void setOswiadczenieOPozarzeUwagi(String oswiadczenieOPozarzeUwagi) {
        this.oswiadczenieOPozarzeUwagi = oswiadczenieOPozarzeUwagi;
    }

    public Boolean getOswiadczenieOUprawnieniach() {
        return oswiadczenieOUprawnieniach;
    }

    public void setOswiadczenieOUprawnieniach(Boolean oswiadczenieOUprawnieniach) {
        this.oswiadczenieOUprawnieniach = oswiadczenieOUprawnieniach;
    }

    public String getOswiadczenieOUprawnieniachUwagi() {
        return oswiadczenieOUprawnieniachUwagi;
    }

    public void setOswiadczenieOUprawnieniachUwagi(String oswiadczenieOUprawnieniachUwagi) {
        this.oswiadczenieOUprawnieniachUwagi = oswiadczenieOUprawnieniachUwagi;
    }

    public Boolean getPit() {
        return pit;
    }

    public void setPit(Boolean pit) {
        this.pit = pit;
    }

    public String getPitUwagi() {
        return pitUwagi;
    }

    public void setPitUwagi(String pitUwagi) {
        this.pitUwagi = pitUwagi;
    }

    public Boolean getOswiadczenieUrzadSkarbowy() {
        return oswiadczenieUrzadSkarbowy;
    }

    public void setOswiadczenieUrzadSkarbowy(Boolean oswiadczenieUrzadSkarbowy) {
        this.oswiadczenieUrzadSkarbowy = oswiadczenieUrzadSkarbowy;
    }

    public String getOswiadczenieUrzadSkarbowyUwagi() {
        return oswiadczenieUrzadSkarbowyUwagi;
    }

    public void setOswiadczenieUrzadSkarbowyUwagi(String oswiadczenieUrzadSkarbowyUwagi) {
        this.oswiadczenieUrzadSkarbowyUwagi = oswiadczenieUrzadSkarbowyUwagi;
    }

    public Boolean getOswiadczenieOKosztach() {
        return oswiadczenieOKosztach;
    }

    public void setOswiadczenieOKosztach(Boolean oswiadczenieOKosztach) {
        this.oswiadczenieOKosztach = oswiadczenieOKosztach;
    }

    public String getOswiadczenieOKosztachUwagi() {
        return oswiadczenieOKosztachUwagi;
    }

    public void setOswiadczenieOKosztachUwagi(String oswiadczenieOKosztachUwagi) {
        this.oswiadczenieOKosztachUwagi = oswiadczenieOKosztachUwagi;
    }

    public Boolean getZgodaPracownika() {
        return zgodaPracownika;
    }

    public void setZgodaPracownika(Boolean zgodaPracownika) {
        this.zgodaPracownika = zgodaPracownika;
    }

    public String getZgodaPracownikaUwagi() {
        return zgodaPracownikaUwagi;
    }

    public void setZgodaPracownikaUwagi(String zgodaPracownikaUwagi) {
        this.zgodaPracownikaUwagi = zgodaPracownikaUwagi;
    }

    public Boolean getUmowaOdpowiedzialnosci() {
        return umowaOdpowiedzialnosci;
    }

    public void setUmowaOdpowiedzialnosci(Boolean umowaOdpowiedzialnosci) {
        this.umowaOdpowiedzialnosci = umowaOdpowiedzialnosci;
    }

    public String getUmowaOdpowiedzialnosciUwagi() {
        return umowaOdpowiedzialnosciUwagi;
    }

    public void setUmowaOdpowiedzialnosciUwagi(String umowaOdpowiedzialnosciUwagi) {
        this.umowaOdpowiedzialnosciUwagi = umowaOdpowiedzialnosciUwagi;
    }

    public Boolean getUmowaOZakazie() {
        return umowaOZakazie;
    }

    public void setUmowaOZakazie(Boolean umowaOZakazie) {
        this.umowaOZakazie = umowaOZakazie;
    }

    public String getUmowaOZakazieUwagi() {
        return umowaOZakazieUwagi;
    }

    public void setUmowaOZakazieUwagi(String umowaOZakazieUwagi) {
        this.umowaOZakazieUwagi = umowaOZakazieUwagi;
    }

    public Boolean getOrzeczenieLekarskie() {
        return orzeczenieLekarskie;
    }

    public void setOrzeczenieLekarskie(Boolean orzeczenieLekarskie) {
        this.orzeczenieLekarskie = orzeczenieLekarskie;
    }

    public String getOrzeczenieLekarskieUwagi() {
        return orzeczenieLekarskieUwagi;
    }

    public void setOrzeczenieLekarskieUwagi(String orzeczenieLekarskieUwagi) {
        this.orzeczenieLekarskieUwagi = orzeczenieLekarskieUwagi;
    }

    public Date getOrzeczenieLekarskieData() {
        return orzeczenieLekarskieData;
    }

    public void setOrzeczenieLekarskieData(Date orzeczenieLekarskieData) {
        this.orzeczenieLekarskieData = orzeczenieLekarskieData;
    }

    public Boolean getOkresoweBadaniaBhp() {
        return okresoweBadaniaBhp;
    }

    public void setOkresoweBadaniaBhp(Boolean okresoweBadaniaBhp) {
        this.okresoweBadaniaBhp = okresoweBadaniaBhp;
    }

    public String getOkresoweBadaniaBhpUwagi() {
        return okresoweBadaniaBhpUwagi;
    }

    public void setOkresoweBadaniaBhpUwagi(String okresoweBadaniaBhpUwagi) {
        this.okresoweBadaniaBhpUwagi = okresoweBadaniaBhpUwagi;
    }

    public Date getOkresoweBadaniaBhpData() {
        return okresoweBadaniaBhpData;
    }

    public void setOkresoweBadaniaBhpData(Date okresoweBadaniaBhpData) {
        this.okresoweBadaniaBhpData = okresoweBadaniaBhpData;
    }

    public Boolean getDrukZua() {
        return drukZua;
    }

    public void setDrukZua(Boolean drukZua) {
        this.drukZua = drukZua;
    }

    public String getDrukZuaUwagi() {
        return drukZuaUwagi;
    }

    public void setDrukZuaUwagi(String drukZuaUwagi) {
        this.drukZuaUwagi = drukZuaUwagi;
    }

    public Boolean getOswiadczenieWypowiedzenie() {
        return oswiadczenieWypowiedzenie;
    }

    public void setOswiadczenieWypowiedzenie(Boolean oswiadczenieWypowiedzenie) {
        this.oswiadczenieWypowiedzenie = oswiadczenieWypowiedzenie;
    }

    public String getOswiadczenieWypowiedzenieUwagi() {
        return oswiadczenieWypowiedzenieUwagi;
    }

    public void setOswiadczenieWypowiedzenieUwagi(String oswiadczenieWypowiedzenieUwagi) {
        this.oswiadczenieWypowiedzenieUwagi = oswiadczenieWypowiedzenieUwagi;
    }

    public Boolean getKopiaSwiadectwa() {
        return kopiaSwiadectwa;
    }

    public void setKopiaSwiadectwa(Boolean kopiaSwiadectwa) {
        this.kopiaSwiadectwa = kopiaSwiadectwa;
    }

    public String getKopiaSwiadectwaUwagi() {
        return kopiaSwiadectwaUwagi;
    }

    public void setKopiaSwiadectwaUwagi(String kopiaSwiadectwaUwagi) {
        this.kopiaSwiadectwaUwagi = kopiaSwiadectwaUwagi;
    }

    public Boolean getDrukZwua() {
        return drukZwua;
    }

    public void setDrukZwua(Boolean drukZwua) {
        this.drukZwua = drukZwua;
    }

    public String getDrukZwuaUwagi() {
        return drukZwuaUwagi;
    }

    public void setDrukZwuaUwagi(String drukZwuaUwagi) {
        this.drukZwuaUwagi = drukZwuaUwagi;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getPracodawcaNazwa() {
        return pracodawcaNazwa;
    }

    public void setPracodawcaNazwa(String pracodawcaNazwa) {
        this.pracodawcaNazwa = pracodawcaNazwa;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Date getDataUsuniecia() {
        return dataUsuniecia;
    }

    public void setDataUsuniecia(Date dataUsuniecia) {
        this.dataUsuniecia = dataUsuniecia;
    }

    public Pracodawca getPracodawca() {
        return pracodawca;
    }

    public void setPracodawca(Pracodawca pracodawca) {
        this.pracodawca = pracodawca;
    }

    public void parse(Sheet sheet) {
        this.pracodawcaNazwa = getTextOrNull(sheet, Field.W_1, Field.C);
        this.nazwa = getTextOrNull(sheet, Field.W_2, Field.C);

        //region Czesc A

        int wiersz = Field.W_4.getValue();
        this.kwestionariuszOsobowyUbiegajacego = getBoolean(sheet, wiersz);
        this.kwestionariuszOsobowyUbiegajacegoUwagi = getUwagi(sheet, wiersz);

        wiersz++;
        this.podanieOPrace = getBoolean(sheet, wiersz);
        this.podanieOPraceUwagi = getUwagi(sheet, wiersz);

        wiersz++;
        this.zyciorys = getBoolean(sheet, wiersz);
        this.zyciorysUwagi = getUwagi(sheet, wiersz);

        wiersz++;
        this.swiadectwoPracy = getBoolean(sheet, wiersz);
        this.swiadectwoPracyUwagi = getUwagi(sheet, wiersz);

        wiersz++;
        this.dokumentyPotwierdzajace = getBoolean(sheet, wiersz);
        this.dokumentyPotwierdzajaceUwagi = getUwagi(sheet, wiersz);

        wiersz++;
        this.swiadectwoUkonczeniaGimnazjum = getBoolean(sheet, wiersz);
        this.swiadectwoUkonczeniaGimnazjumUwagi = getUwagi(sheet, wiersz);

        wiersz++;
        this.podstawaUrlopu = getBoolean(sheet, wiersz);
        this.podstawaUrlopuUwagi = getUwagi(sheet, wiersz);

        wiersz++;
        this.orzeczenieLekarskiePrzeciwskazania = getBoolean(sheet, wiersz);
        this.orzeczenieLekarskiePrzeciwskazaniaUwagi = getUwagi(sheet, wiersz);
        this.orzeczenieLekarskiePrzeciwskazaniaData = getDate(sheet, wiersz);

        wiersz++;
        wiersz++;
        this.dowodOsobisty = getBoolean(sheet, wiersz);
        this.dowodOsobistyUwagi = getUwagi(sheet, wiersz);

        //endregion

        //region Czesc B

        wiersz = Field.W_16.getValue();
        this.kwestionariuszOsobowyPracownika = getBoolean(sheet, wiersz);
        this.kwestionariuszOsobowyPracownikaUwagi = getUwagi(sheet, wiersz);

        wiersz++;
        this.umowaOPrace = getBoolean(sheet, wiersz);
        this.umowaOPraceUwagi = getUwagi(sheet, wiersz);

        wiersz++;
        this.informacjaOWarunkach = getBoolean(sheet, wiersz);
        this.informacjaOWarunkachUwagi = getUwagi(sheet, wiersz);

        wiersz++;
        wiersz++;
        this.oswiadczenieORyzyku = getBoolean(sheet, wiersz);
        this.oswiadczenieORyzykuUwagi = getUwagi(sheet, wiersz);

        wiersz++;
        this.oswiadczenieOPrzepisach = getBoolean(sheet, wiersz);
        this.oswiadczenieOPrzepisachUwagi = getUwagi(sheet, wiersz);

        wiersz++;
        this.oswiadczenieOBhp = getBoolean(sheet, wiersz);
        this.oswiadczenieOBhpUwagi = getUwagi(sheet, wiersz);
        this.oswiadczenieOBhpData = getDate(sheet, wiersz);

        wiersz++;
        this.oswiadczenieOPozarze = getBoolean(sheet, wiersz);
        this.oswiadczenieOPozarzeUwagi = getUwagi(sheet, wiersz);
        if (oswiadczenieOBhpData == null) {
            this.oswiadczenieOBhpData = getDate(sheet, wiersz);
        }

        wiersz++;
        this.oswiadczenieOUprawnieniach = getBoolean(sheet, wiersz);
        this.oswiadczenieOUprawnieniachUwagi = getUwagi(sheet, wiersz);

        wiersz++;
        this.pit = getBoolean(sheet, wiersz);
        this.pitUwagi = getUwagi(sheet, wiersz);

        wiersz++;
        this.oswiadczenieUrzadSkarbowy = getBoolean(sheet, wiersz);
        this.oswiadczenieUrzadSkarbowyUwagi = getUwagi(sheet, wiersz);

        wiersz++;
        this.oswiadczenieOKosztach = getBoolean(sheet, wiersz);
        this.oswiadczenieOKosztachUwagi = getUwagi(sheet, wiersz);

        wiersz++;
        this.zgodaPracownika = getBoolean(sheet, wiersz);
        this.zgodaPracownikaUwagi = getUwagi(sheet, wiersz);

        wiersz++;
        this.umowaOdpowiedzialnosci = getBoolean(sheet, wiersz);
        this.umowaOdpowiedzialnosciUwagi = getUwagi(sheet, wiersz);

        wiersz++;
        this.umowaOZakazie = getBoolean(sheet, wiersz);
        this.umowaOZakazieUwagi = getUwagi(sheet, wiersz);

        wiersz++;
        this.orzeczenieLekarskie = getBoolean(sheet, wiersz);
        this.orzeczenieLekarskieUwagi = getUwagi(sheet, wiersz);
        this.orzeczenieLekarskieData = getDate(sheet, wiersz);
        if (this.orzeczenieLekarskieData != null) {
            this.orzeczenieLekarskie = true;
        }

        wiersz++;
        this.okresoweBadaniaBhp = getBoolean(sheet, wiersz);
        this.okresoweBadaniaBhpUwagi = getUwagi(sheet, wiersz);
        this.okresoweBadaniaBhpData = getDate(sheet, wiersz);

        wiersz++;
        this.drukZua = getBoolean(sheet, wiersz);
        this.drukZuaUwagi = getUwagi(sheet, wiersz);

        //endregion

        //region C

        wiersz++;
        this.oswiadczenieWypowiedzenie = getBoolean(sheet, wiersz);
        this.oswiadczenieWypowiedzenieUwagi = getUwagi(sheet, wiersz);

        wiersz++;
        this.kopiaSwiadectwa = getBoolean(sheet, wiersz);
        this.kopiaSwiadectwaUwagi = getUwagi(sheet, wiersz);

        wiersz++;
        this.drukZwua = getBoolean(sheet, wiersz);
        this.drukZwuaUwagi = getUwagi(sheet, wiersz);

        //endregion

    }

    private Date getDate(Sheet sheet, int wiersz) {
        return SheetHelper.getDate(sheet, wiersz, Field.C);
    }

    private boolean getBoolean(Sheet sheet, int wiersz) {
        return SheetHelper.getBoolean(sheet, wiersz, Field.D.getValue(), Field.E.getValue());
    }

    private String getUwagi(Sheet sheet, int wiersz) {
        return getTextOrNull(sheet, wiersz, Field.F);
    }

    private String getTextOrNull(Sheet sheet, int wiersz, Field kolumna) {
        String text = SheetHelper.getCellText(sheet.getRow(wiersz).getCell(kolumna.getValue()));
        if (text.isEmpty()) {
            return null;
        }
        return text;
    }

    private String getTextOrNull(Sheet sheet, Field wiersz, Field kolumna) {
        return getTextOrNull(sheet, wiersz.getValue(), kolumna);
    }
}
