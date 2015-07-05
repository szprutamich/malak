//package pl.malak.model;
//
//import org.apache.poi.ss.usermodel.Sheet;
//import pl.malak.Field;
//import pl.malak.helpers.SheetHelper;
//
//import javax.persistence.*;
//import java.util.Date;
//
///**
// * @author Michał Szpruta - szprutamich@gmail.com
// */
//@Entity
//@Table(name = "prace")
//public class Praca {
//
//    @Column(name = "kwestionariusz")
//    private Boolean kwestionariusz;
//
//    @Column(name = "kwestionariusz_uwagi")
//    private String kwestionariuszUwagi;
//
//    @Column(name = "karta_szkolenia")
//    private Boolean kartaSzkolenia;
//
//    @Column(name = "karta_szkolenia_uwagi")
//    private String kartaSzkoleniaUwagi;
//
//    @Column(name = "karta_szkolenia_data")
//    private Date kartaSzkoleniaData;
//
//    @Column(name = "szkolenie")
//    private Boolean szkolenie;
//
//    @Column(name = "szkolenie_uwagi")
//    private String szkolenieUwagi;
//
//    @Column(name = "instruktaz")
//    private Boolean instruktaz;
//
//    @Column(name = "instruktaz_uwagi")
//    private String instruktazUwagi;
//
//    @Column(name = "ryzyko")
//    private Boolean ryzyko;
//
//    @Column(name = "ryzyko_uwagi")
//    private String ryzykoUwagi;
//
//    @Column(name = "instrukcje_bhp")
//    private Boolean instrukcjeBhp;
//
//    @Column(name = "instrukcje_bhp_uwagi")
//    private String instrukcjeBhpUwagi;
//
//    @Column(name = "szkolenie_bhp")
//    private Boolean szkolenieBhp;
//
//    @Column(name = "szkolenie_bhp_uwagi")
//    private String szkolenieBhpUwagi;
//
//    @Column(name = "szkolenie_bhp_data")
//    private Date szkolenieBhpData;
//
//    @Column(name = "rachunki")
//    private Boolean rachunki;
//
//    @Column(name = "rachunki_uwagi")
//    private String rachunkiUwagi;
//
//    @Column(name = "umowa")
//    private Boolean umowa;
//
//    @Column(name = "umowa_uwagi")
//    private String umowaUwagi;
//
//    @Column(name = "umowa_data")
//    private Date umowaData;
//
//    @Column(name = "odbior_odziezy")
//    private Boolean odbiorOdziezy;
//
//    @Column(name = "odbior_odziezy_uwagi")
//    private String odbiorOdziezyUwagi;
//
//    @Column(name = "odbior_odziezy_data")
//    private Date odbiorOdziezyData;
//
//    @Column(name = "zua")
//    private Boolean zua;
//
//    @Column(name = "zua_uwagi")
//    private String zuaUwagi;
//
//    @Column(name = "zwua")
//    private Boolean zwua;
//
//    @Column(name = "zwua_uwagi")
//    private String zwuaUwagi;
//
//    @Column(name = "zwua_data")
//    private Date zwuaData;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    protected Long id;
//
//    @Column(name = "nazwa")
//    private String nazwa;
//
//    @Transient
//    private String pracodawcaNazwa;
//
//    @Transient
//    private String sheetName;
//
//    @Column(name = "badania")
//    private boolean badania;
//
//    @Column(name = "badania_uwagi")
//    private String badaniaUwagi;
//
//    @Column(name = "badania_data")
//    private Date badaniaData;
//
//    @Column(name = "legitymacja")
//    private Boolean legitymacja;
//
//    @Column(name = "legitymacja_uwagi")
//    private String legitymacjaUwagi;
//
//    @Column(name = "dowod")
//    private Boolean dowod;
//
//    @Column(name = "dowod_uwagi")
//    private String dowodUwagi;
//
//    @Column(name = "zyciorys")
//    private Boolean zyciorys;
//
//    @Column(name = "zyciorys_uwagi")
//    private String zyciorysUwagi;
//
//    @Column(name = "zaswiadczenie_sanitarne")
//    private Boolean zaswiadczenieSanitarne;
//
//    @Column(name = "zaswiadczenie_sanitarne_uwagi")
//    private String zaswiadczenieSanitarneUwagi;
//
//    @Column(name = "zaswiadczenie_student")
//    private Boolean zaswiadczenieStudent;
//
//    @Column(name = "zaswiadczenie_student_uwagi")
//    private String zaswiadczenieStudentUwagi;
//
//    @Column(name = "wyciag_kodeks")
//    private Boolean wyciagKodeks;
//
//    @Column(name = "wyciag_kodeks_uwagi")
//    private String wyciagKodeksUwagi;
//
//    @ManyToOne(cascade = CascadeType.REFRESH)
//    @JoinColumn(name = "pracodawca_id", nullable = false)
//    private Pracodawca pracodawca;
//
//    public Praca() {
//    }
//
//    public Praca(Sheet sheet) {
//        parse(sheet);
//        this.sheetName = sheet.getSheetName();
//    }
//
//    public boolean isBadania() {
//        return badania;
//    }
//
//    public void setBadania(boolean badania) {
//        this.badania = badania;
//    }
//
//    public String getBadaniaUwagi() {
//        return badaniaUwagi;
//    }
//
//    public void setBadaniaUwagi(String badaniaUwagi) {
//        this.badaniaUwagi = badaniaUwagi;
//    }
//
//    public Date getBadaniaData() {
//        return badaniaData;
//    }
//
//    public void setBadaniaData(Date badaniaData) {
//        this.badaniaData = badaniaData;
//    }
//
//    public Boolean getLegitymacja() {
//        return legitymacja;
//    }
//
//    public void setLegitymacja(Boolean legitymacja) {
//        this.legitymacja = legitymacja;
//    }
//
//    public String getLegitymacjaUwagi() {
//        return legitymacjaUwagi;
//    }
//
//    public void setLegitymacjaUwagi(String legitymacjaUwagi) {
//        this.legitymacjaUwagi = legitymacjaUwagi;
//    }
//
//    public Boolean getDowod() {
//        return dowod;
//    }
//
//    public void setDowod(Boolean dowod) {
//        this.dowod = dowod;
//    }
//
//    public String getDowodUwagi() {
//        return dowodUwagi;
//    }
//
//    public void setDowodUwagi(String dowodUwagi) {
//        this.dowodUwagi = dowodUwagi;
//    }
//
//    public Boolean getZyciorys() {
//        return zyciorys;
//    }
//
//    public void setZyciorys(Boolean zyciorys) {
//        this.zyciorys = zyciorys;
//    }
//
//    public String getZyciorysUwagi() {
//        return zyciorysUwagi;
//    }
//
//    public void setZyciorysUwagi(String zyciorysUwagi) {
//        this.zyciorysUwagi = zyciorysUwagi;
//    }
//
//    public Boolean getZaswiadczenieSanitarne() {
//        return zaswiadczenieSanitarne;
//    }
//
//    public void setZaswiadczenieSanitarne(Boolean zaswiadczenieSanitarne) {
//        this.zaswiadczenieSanitarne = zaswiadczenieSanitarne;
//    }
//
//    public String getZaswiadczenieSanitarneUwagi() {
//        return zaswiadczenieSanitarneUwagi;
//    }
//
//    public void setZaswiadczenieSanitarneUwagi(String zaswiadczenieSanitarneUwagi) {
//        this.zaswiadczenieSanitarneUwagi = zaswiadczenieSanitarneUwagi;
//    }
//
//    public Boolean getZaswiadczenieStudent() {
//        return zaswiadczenieStudent;
//    }
//
//    public void setZaswiadczenieStudent(Boolean zaswiadczenieStudent) {
//        this.zaswiadczenieStudent = zaswiadczenieStudent;
//    }
//
//    public String getZaswiadczenieStudentUwagi() {
//        return zaswiadczenieStudentUwagi;
//    }
//
//    public void setZaswiadczenieStudentUwagi(String zaswiadczenieStudentUwagi) {
//        this.zaswiadczenieStudentUwagi = zaswiadczenieStudentUwagi;
//    }
//
//    public Boolean getWyciagKodeks() {
//        return wyciagKodeks;
//    }
//
//    public void setWyciagKodeks(Boolean wyciagKodeks) {
//        this.wyciagKodeks = wyciagKodeks;
//    }
//
//    public String getWyciagKodeksUwagi() {
//        return wyciagKodeksUwagi;
//    }
//
//    public void setWyciagKodeksUwagi(String wyciagKodeksUwagi) {
//        this.wyciagKodeksUwagi = wyciagKodeksUwagi;
//    }
//
//    public Pracodawca getPracodawca() {
//        return pracodawca;
//    }
//
//    public void setPracodawca(Pracodawca pracodawca) {
//        this.pracodawca = pracodawca;
//    }
//
//    public String getPracodawcaNazwa() {
//        return pracodawcaNazwa;
//    }
//
//    public void setPracodawcaNazwa(String pracodawcaNazwa) {
//        this.pracodawcaNazwa = pracodawcaNazwa;
//    }
//
//    public Boolean getKwestionariusz() {
//        return kwestionariusz;
//    }
//
//    public void setKwestionariusz(Boolean kwsetionariusz) {
//        this.kwestionariusz = kwsetionariusz;
//    }
//
//    public String getKwestionariuszUwagi() {
//        return kwestionariuszUwagi;
//    }
//
//    public void setKwestionariuszUwagi(String kwsetionariuszUwagi) {
//        this.kwestionariuszUwagi = kwsetionariuszUwagi;
//    }
//
//    public Boolean getKartaSzkolenia() {
//        return kartaSzkolenia;
//    }
//
//    public void setKartaSzkolenia(Boolean kartaSzkolenia) {
//        this.kartaSzkolenia = kartaSzkolenia;
//    }
//
//    public String getKartaSzkoleniaUwagi() {
//        return kartaSzkoleniaUwagi;
//    }
//
//    public void setKartaSzkoleniaUwagi(String kartaSzkoleniaUwagi) {
//        this.kartaSzkoleniaUwagi = kartaSzkoleniaUwagi;
//    }
//
//    public Date getKartaSzkoleniaData() {
//        return kartaSzkoleniaData;
//    }
//
//    public void setKartaSzkoleniaData(Date kartaSzkoleniaData) {
//        this.kartaSzkoleniaData = kartaSzkoleniaData;
//    }
//
//    public Boolean getSzkolenie() {
//        return szkolenie;
//    }
//
//    public void setSzkolenie(Boolean szkolenie) {
//        this.szkolenie = szkolenie;
//    }
//
//    public String getSzkolenieUwagi() {
//        return szkolenieUwagi;
//    }
//
//    public void setSzkolenieUwagi(String szkolenieUwagi) {
//        this.szkolenieUwagi = szkolenieUwagi;
//    }
//
//    public Boolean getInstruktaz() {
//        return instruktaz;
//    }
//
//    public void setInstruktaz(Boolean instruktaz) {
//        this.instruktaz = instruktaz;
//    }
//
//    public String getInstruktazUwagi() {
//        return instruktazUwagi;
//    }
//
//    public void setInstruktazUwagi(String instruktazUwagi) {
//        this.instruktazUwagi = instruktazUwagi;
//    }
//
//    public Boolean getRyzyko() {
//        return ryzyko;
//    }
//
//    public void setRyzyko(Boolean ryzyko) {
//        this.ryzyko = ryzyko;
//    }
//
//    public String getRyzykoUwagi() {
//        return ryzykoUwagi;
//    }
//
//    public void setRyzykoUwagi(String ryzykoUwagi) {
//        this.ryzykoUwagi = ryzykoUwagi;
//    }
//
//    public Boolean getInstrukcjeBhp() {
//        return instrukcjeBhp;
//    }
//
//    public void setInstrukcjeBhp(Boolean instrukacje_bhp) {
//        this.instrukcjeBhp = instrukacje_bhp;
//    }
//
//    public String getInstrukcjeBhpUwagi() {
//        return instrukcjeBhpUwagi;
//    }
//
//    public void setInstrukcjeBhpUwagi(String instrukcjeBhpUwagi) {
//        this.instrukcjeBhpUwagi = instrukcjeBhpUwagi;
//    }
//
//    public Boolean getSzkolenieBhp() {
//        return szkolenieBhp;
//    }
//
//    public void setSzkolenieBhp(Boolean szkolenieBhp) {
//        this.szkolenieBhp = szkolenieBhp;
//    }
//
//    public String getSzkolenieBhpUwagi() {
//        return szkolenieBhpUwagi;
//    }
//
//    public void setSzkolenieBhpUwagi(String szkolenieBhpUwagi) {
//        this.szkolenieBhpUwagi = szkolenieBhpUwagi;
//    }
//
//    public Date getSzkolenieBhpData() {
//        return szkolenieBhpData;
//    }
//
//    public void setSzkolenieBhpData(Date szkolenieBhpData) {
//        this.szkolenieBhpData = szkolenieBhpData;
//    }
//
//    public Boolean getRachunki() {
//        return rachunki;
//    }
//
//    public void setRachunki(Boolean rachunki) {
//        this.rachunki = rachunki;
//    }
//
//    public String getRachunkiUwagi() {
//        return rachunkiUwagi;
//    }
//
//    public void setRachunkiUwagi(String rachunkiUwagi) {
//        this.rachunkiUwagi = rachunkiUwagi;
//    }
//
//    public Boolean getUmowa() {
//        return umowa;
//    }
//
//    public void setUmowa(Boolean umowa) {
//        this.umowa = umowa;
//    }
//
//    public String getUmowaUwagi() {
//        return umowaUwagi;
//    }
//
//    public void setUmowaUwagi(String umowaUwagi) {
//        this.umowaUwagi = umowaUwagi;
//    }
//
//    public Date getUmowaData() {
//        return umowaData;
//    }
//
//    public void setUmowaData(Date umowaData) {
//        this.umowaData = umowaData;
//    }
//
//    public Boolean getOdbiorOdziezy() {
//        return odbiorOdziezy;
//    }
//
//    public void setOdbiorOdziezy(Boolean odbiorOdziezy) {
//        this.odbiorOdziezy = odbiorOdziezy;
//    }
//
//    public String getOdbiorOdziezyUwagi() {
//        return odbiorOdziezyUwagi;
//    }
//
//    public void setOdbiorOdziezyUwagi(String odbiorOdziezyUwagi) {
//        this.odbiorOdziezyUwagi = odbiorOdziezyUwagi;
//    }
//
//    public Date getOdbiorOdziezyData() {
//        return odbiorOdziezyData;
//    }
//
//    public void setOdbiorOdziezyData(Date odbiorOdziezyData) {
//        this.odbiorOdziezyData = odbiorOdziezyData;
//    }
//
//    public Boolean getZua() {
//        return zua;
//    }
//
//    public void setZua(Boolean zua) {
//        this.zua = zua;
//    }
//
//    public String getZuaUwagi() {
//        return zuaUwagi;
//    }
//
//    public void setZuaUwagi(String zuaUwagi) {
//        this.zuaUwagi = zuaUwagi;
//    }
//
//    public Boolean getZwua() {
//        return zwua;
//    }
//
//    public void setZwua(Boolean zwua) {
//        this.zwua = zwua;
//    }
//
//    public String getZwuaUwagi() {
//        return zwuaUwagi;
//    }
//
//    public void setZwuaUwagi(String zwuaUwagi) {
//        this.zwuaUwagi = zwuaUwagi;
//    }
//
//    public Date getZwuaData() {
//        return zwuaData;
//    }
//
//    public void setZwuaData(Date zwuaData) {
//        this.zwuaData = zwuaData;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getNazwa() {
//        return nazwa;
//    }
//
//    public void setNazwa(String nazwa) {
//        this.nazwa = nazwa;
//    }
//
//    public String getSheetName() {
//        return sheetName;
//    }
//
//    public void setSheetName(String sheetName) {
//        this.sheetName = sheetName;
//    }
//
//    public void parse(Sheet sheet) {
//        this.pracodawcaNazwa = getTextOrNull(sheet, Field.W_1, Field.C);
//        this.nazwa = getTextOrNull(sheet, Field.W_2, Field.C);
//
//        this.kwestionariusz = getBoolean(sheet, Field.W_5);
//        this.kwestionariuszUwagi = getTextOrNull(sheet, Field.W_5, Field.E);
//
//        this.kartaSzkolenia = getBoolean(sheet, Field.W_6);
//        this.kartaSzkoleniaUwagi = getTextOrNull(sheet, Field.W_6, Field.E);
//        this.kartaSzkoleniaData = getDate(sheet, Field.W_6, Field.F);
//
//        this.szkolenie = getBoolean(sheet, Field.W_7);
//        this.szkolenieUwagi = getTextOrNull(sheet, Field.W_7, Field.E);
//
//        this.instruktaz = getBoolean(sheet, Field.W_8);
//        this.instruktazUwagi = getTextOrNull(sheet, Field.W_8, Field.E);
//
//        this.ryzyko = getBoolean(sheet, Field.W_9);
//        this.ryzykoUwagi = getTextOrNull(sheet, Field.W_9, Field.E);
//
//        this.instrukcjeBhp = getBoolean(sheet, Field.W_10);
//        this.instrukcjeBhpUwagi = getTextOrNull(sheet, Field.W_10, Field.E);
//
//        this.szkolenieBhp = getBoolean(sheet, Field.W_11);
//        this.szkolenieBhpUwagi = getTextOrNull(sheet, Field.W_11, Field.E);
//        this.szkolenieBhpData = getDate(sheet, Field.W_11, Field.F);
//
//        this.rachunki = getBoolean(sheet, Field.W_12);
//        this.rachunkiUwagi = getTextOrNull(sheet, Field.W_12, Field.E);
//
//        this.umowa = getBoolean(sheet, Field.W_13);
//        this.umowaUwagi = getTextOrNull(sheet, Field.W_13, Field.E);
//        this.umowaData = getDate(sheet, Field.W_13, Field.F);
//
//        for (int wiersz = 13; wiersz <= 16; wiersz++) {
//            String dokument = getTextOrNull(sheet, wiersz, Field.B);
//            if (dokument != null) {
//                switch (dokument.trim()) {
//                    case "BADANIA LEKARSKIE":
//                    case "Zaświadczenie lekarskie":
//                        this.badania = getBoolean(sheet, wiersz);
//                        this.badaniaUwagi = getTextOrNull(sheet, wiersz, Field.E);
//                        this.badaniaData = getDate(sheet, wiersz, Field.F);
//                        break;
//                    case "Legitymacja szkolna":
//                        this.legitymacja = getBoolean(sheet, wiersz);
//                        this.legitymacjaUwagi = getTextOrNull(sheet, wiersz, Field.E);
//                        break;
//                    case "DOWÓD OSOBISTY":
//                        this.dowod = getBoolean(sheet, wiersz);
//                        this.dowodUwagi = getTextOrNull(sheet, wiersz, Field.E);
//                        break;
//                    case "Życiorys":
//                        this.zyciorys = getBoolean(sheet, wiersz);
//                        this.zyciorysUwagi = getTextOrNull(sheet, wiersz, Field.E);
//                        break;
//                    case "Zaświadczenie sanitarno-epidemiologiczne":
//                        this.zaswiadczenieSanitarne = getBoolean(sheet, wiersz);
//                        this.zaswiadczenieSanitarneUwagi = getTextOrNull(sheet, wiersz, Field.E);
//                        break;
//                    case "ZUA":
//                    case "ZUS":
//                    case "ZZA":
//                        this.zua = getBoolean(sheet, wiersz);
//                        this.zuaUwagi = getTextOrNull(sheet, wiersz, Field.E);
//                        break;
//                    case "Zaświadczenie  - student":
//                        this.zaswiadczenieStudent = getBoolean(sheet, wiersz);
//                        this.zaswiadczenieStudentUwagi = getTextOrNull(sheet, wiersz, Field.E);
//                        break;
//                    case "ZWUA":
//                        this.zwua = getBoolean(sheet, wiersz);
//                        this.zwuaUwagi = getTextOrNull(sheet, wiersz, Field.E);
//                        break;
//                    case "Wyciąg z kodeksu pracy":
//                        this.wyciagKodeks = getBoolean(sheet, wiersz);
//                        this.wyciagKodeksUwagi = getTextOrNull(sheet, wiersz, Field.E);
//                        break;
//                    case "ODBIÓR ODZIEŻY":
//                        this.odbiorOdziezy = getBoolean(sheet, wiersz);
//                        this.odbiorOdziezyUwagi = getTextOrNull(sheet, wiersz, Field.E);
//                        this.odbiorOdziezyData = getDate(sheet, wiersz, Field.F);
//                        break;
//                    default:
//                }
//            }
//        }
//    }
//
//    private Date getDate(Sheet sheet, int wiersz, Field kolumna) {
//        String text = SheetHelper.getCellText(sheet.getRow(wiersz).getCell(kolumna.getValue()));
//        String[] dates = text.split("-");
//        if (dates.length == 3) {
//            int i = 0;
//            return new Date(Integer.parseInt(dates[i++]) - 1900, Integer.parseInt(dates[i++]), Integer.parseInt(dates[i]));
//        }
//        return null;
//    }
//
//    private Date getDate(Sheet sheet, Field wiersz, Field kolumna) {
//        return getDate(sheet, wiersz.getValue(), kolumna);
//    }
//
//    private boolean getBoolean(Sheet sheet, int wiersz) {
//        String kolumnaJest = SheetHelper.getCellText(sheet.getRow(wiersz).getCell(Field.C.getValue()));
//        String kolumnaBrak = SheetHelper.getCellText(sheet.getRow(wiersz).getCell(Field.D.getValue()));
//        return !kolumnaBrak.trim().equalsIgnoreCase("BRAK")
//                && (kolumnaJest.trim().equalsIgnoreCase("JEST") || kolumnaJest.trim().equalsIgnoreCase("TAK"));
//    }
//
//    private boolean getBoolean(Sheet sheet, Field wiersz) {
//        return getBoolean(sheet, wiersz.getValue());
//    }
//
//    private String getTextOrNull(Sheet sheet, int wiersz, Field kolumna) {
//        String text = SheetHelper.getCellText(sheet.getRow(wiersz).getCell(kolumna.getValue()));
//        if (text.isEmpty()) {
//            return null;
//        }
//        return text;
//    }
//
//    private String getTextOrNull(Sheet sheet, Field wiersz, Field kolumna) {
//        return getTextOrNull(sheet, wiersz.getValue(), kolumna);
//    }
//}
