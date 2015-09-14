package pl.malak.model;

import org.apache.poi.ss.usermodel.Sheet;
import pl.malak.Field;
import pl.malak.helpers.SheetHelper;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Michał Szpruta - szprutamich@gmail.com
 */
@Entity
@Table(name = "zlecenia")
public class Zlecenie {

    @Column(name = "kwestionariusz")
    private Boolean kwestionariusz;

    @Column(name = "kwestionariusz_uwagi")
    private String kwestionariuszUwagi;

    @Column(name = "karta_szkolenia")
    private Boolean kartaSzkolenia;

    @Column(name = "karta_szkolenia_uwagi")
    private String kartaSzkoleniaUwagi;

    @Column(name = "karta_szkolenia_data")
    private Date kartaSzkoleniaData;

    @Column(name = "szkolenie")
    private Boolean szkolenie;

    @Column(name = "szkolenie_uwagi")
    private String szkolenieUwagi;

    @Column(name = "instruktaz")
    private Boolean instruktaz;

    @Column(name = "instruktaz_uwagi")
    private String instruktazUwagi;

    @Column(name = "ryzyko")
    private Boolean ryzyko;

    @Column(name = "ryzyko_uwagi")
    private String ryzykoUwagi;

    @Column(name = "instrukcje_bhp")
    private Boolean instrukcjeBhp;

    @Column(name = "instrukcje_bhp_uwagi")
    private String instrukcjeBhpUwagi;

    @Column(name = "szkolenie_bhp")
    private Boolean szkolenieBhp;

    @Column(name = "szkolenie_bhp_uwagi")
    private String szkolenieBhpUwagi;

    @Column(name = "szkolenie_bhp_data")
    private Date szkolenieBhpData;

    @Column(name = "rachunki")
    private Boolean rachunki;

    @Column(name = "rachunki_uwagi")
    private String rachunkiUwagi;

    @Column(name = "umowa")
    private Boolean umowa;

    @Column(name = "umowa_uwagi")
    private String umowaUwagi;

    @Column(name = "umowa_data")
    private Date umowaData;

    @Column(name = "odbior_odziezy")
    private Boolean odbiorOdziezy;

    @Column(name = "odbior_odziezy_uwagi")
    private String odbiorOdziezyUwagi;

    @Column(name = "odbior_odziezy_data")
    private Date odbiorOdziezyData;

    @Column(name = "zua")
    private Boolean zua;

    @Column(name = "zua_uwagi")
    private String zuaUwagi;

    @Column(name = "zza")
    private Boolean zza;

    @Column(name = "zza_uwagi")
    private String zzaUwagi;

    @Column(name = "zwua")
    private Boolean zwua;

    @Column(name = "zwua_uwagi")
    private String zwuaUwagi;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "nazwa")
    private String nazwa;

    @Transient
    private String pracodawcaNazwa;

    @Transient
    private String sheetName;

    @Column(name = "badania")
    private Boolean badania;

    @Column(name = "badania_uwagi")
    private String badaniaUwagi;

    @Column(name = "badania_data")
    private Date badaniaData;

    @Column(name = "dowod")
    private Boolean dowod;

    @Column(name = "dowod_uwagi")
    private String dowodUwagi;

    @Column(name = "zyciorys")
    private Boolean zyciorys;

    @Column(name = "zyciorys_uwagi")
    private String zyciorysUwagi;

    @Column(name = "zaswiadczenie_student")
    private Boolean zaswiadczenieStudent;

    @Column(name = "zaswiadczenie_student_uwagi")
    private String zaswiadczenieStudentUwagi;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "pracodawca_id", nullable = false)
    private Pracodawca pracodawca;

    @Column(name = "data_usuniecia")
    private Date dataUsuniecia;

    public Zlecenie() {
    }

    public Zlecenie(Sheet sheet) {
        parse(sheet);
        this.sheetName = sheet.getSheetName();
    }

    public Zlecenie(
            Boolean kwestionariusz, String kwestionariuszUwagi, Boolean kartaSzkolenia,
            String kartaSzkoleniaUwagi, Date kartaSzkoleniaData, Boolean szkolenie, String szkolenieUwagi,
            Boolean instruktaz, String instruktazUwagi, Boolean ryzyko, String ryzykoUwagi, Boolean instrukcjeBhp,
            String instrukcjeBhpUwagi, Boolean szkolenieBhp, String szkolenieBhpUwagi, Date szkolenieBhpData,
            Boolean rachunki, String rachunkiUwagi, Boolean umowa, String umowaUwagi, Date umowaData,
            Boolean odbiorOdziezy, String odbiorOdziezyUwagi, Date odbiorOdziezyData, Boolean zua,
            String zuaUwagi, Boolean zza, String zzaUwagi, Boolean zwua, String zwuaUwagi, String nazwa,
            Boolean badania, String badaniaUwagi, Date badaniaData, Boolean dowod, String dowodUwagi, Boolean zyciorys,
            String zyciorysUwagi, Boolean zaswiadczenieStudent, String zaswiadczenieStudentUwagi) {
        this.kwestionariusz = kwestionariusz;
        this.kwestionariuszUwagi = kwestionariuszUwagi;
        this.kartaSzkolenia = kartaSzkolenia;
        this.kartaSzkoleniaUwagi = kartaSzkoleniaUwagi;
        this.kartaSzkoleniaData = kartaSzkoleniaData;
        this.szkolenie = szkolenie;
        this.szkolenieUwagi = szkolenieUwagi;
        this.instruktaz = instruktaz;
        this.instruktazUwagi = instruktazUwagi;
        this.ryzyko = ryzyko;
        this.ryzykoUwagi = ryzykoUwagi;
        this.instrukcjeBhp = instrukcjeBhp;
        this.instrukcjeBhpUwagi = instrukcjeBhpUwagi;
        this.szkolenieBhp = szkolenieBhp;
        this.szkolenieBhpUwagi = szkolenieBhpUwagi;
        this.szkolenieBhpData = szkolenieBhpData;
        this.rachunki = rachunki;
        this.rachunkiUwagi = rachunkiUwagi;
        this.umowa = umowa;
        this.umowaUwagi = umowaUwagi;
        this.umowaData = umowaData;
        this.odbiorOdziezy = odbiorOdziezy;
        this.odbiorOdziezyUwagi = odbiorOdziezyUwagi;
        this.odbiorOdziezyData = odbiorOdziezyData;
        this.zua = zua;
        this.zuaUwagi = zuaUwagi;
        this.zza = zza;
        this.zzaUwagi = zzaUwagi;
        this.zwua = zwua;
        this.zwuaUwagi = zwuaUwagi;
        this.nazwa = nazwa;
        this.badania = badania;
        this.badaniaUwagi = badaniaUwagi;
        this.badaniaData = badaniaData;
        this.dowod = dowod;
        this.dowodUwagi = dowodUwagi;
        this.zyciorys = zyciorys;
        this.zyciorysUwagi = zyciorysUwagi;
        this.zaswiadczenieStudent = zaswiadczenieStudent;
        this.zaswiadczenieStudentUwagi = zaswiadczenieStudentUwagi;
    }

    public boolean getBadania() {
        return badania;
    }

    public void setBadania(Boolean badania) {
        this.badania = badania;
    }

    public String getBadaniaUwagi() {
        return badaniaUwagi;
    }

    public void setBadaniaUwagi(String badaniaUwagi) {
        this.badaniaUwagi = badaniaUwagi;
    }

    public Date getBadaniaData() {
        return badaniaData;
    }

    public void setBadaniaData(Date badaniaData) {
        this.badaniaData = badaniaData;
    }

    public Boolean getDowod() {
        return dowod;
    }

    public void setDowod(Boolean dowod) {
        this.dowod = dowod;
    }

    public String getDowodUwagi() {
        return dowodUwagi;
    }

    public void setDowodUwagi(String dowodUwagi) {
        this.dowodUwagi = dowodUwagi;
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

    public Boolean getZaswiadczenieStudent() {
        return zaswiadczenieStudent;
    }

    public void setZaswiadczenieStudent(Boolean zaswiadczenieStudent) {
        this.zaswiadczenieStudent = zaswiadczenieStudent;
    }

    public String getZaswiadczenieStudentUwagi() {
        return zaswiadczenieStudentUwagi;
    }

    public void setZaswiadczenieStudentUwagi(String zaswiadczenieStudentUwagi) {
        this.zaswiadczenieStudentUwagi = zaswiadczenieStudentUwagi;
    }

    public Pracodawca getPracodawca() {
        return pracodawca;
    }

    public void setPracodawca(Pracodawca pracodawca) {
        this.pracodawca = pracodawca;
    }

    public String getPracodawcaNazwa() {
        return pracodawcaNazwa;
    }

    public void setPracodawcaNazwa(String pracodawcaNazwa) {
        this.pracodawcaNazwa = pracodawcaNazwa;
    }

    public Boolean getKwestionariusz() {
        return kwestionariusz;
    }

    public void setKwestionariusz(Boolean kwsetionariusz) {
        this.kwestionariusz = kwsetionariusz;
    }

    public String getKwestionariuszUwagi() {
        return kwestionariuszUwagi;
    }

    public void setKwestionariuszUwagi(String kwsetionariuszUwagi) {
        this.kwestionariuszUwagi = kwsetionariuszUwagi;
    }

    public Boolean getKartaSzkolenia() {
        return kartaSzkolenia;
    }

    public void setKartaSzkolenia(Boolean kartaSzkolenia) {
        this.kartaSzkolenia = kartaSzkolenia;
    }

    public String getKartaSzkoleniaUwagi() {
        return kartaSzkoleniaUwagi;
    }

    public void setKartaSzkoleniaUwagi(String kartaSzkoleniaUwagi) {
        this.kartaSzkoleniaUwagi = kartaSzkoleniaUwagi;
    }

    public Date getKartaSzkoleniaData() {
        return kartaSzkoleniaData;
    }

    public void setKartaSzkoleniaData(Date kartaSzkoleniaData) {
        this.kartaSzkoleniaData = kartaSzkoleniaData;
    }

    public Boolean getSzkolenie() {
        return szkolenie;
    }

    public void setSzkolenie(Boolean szkolenie) {
        this.szkolenie = szkolenie;
    }

    public String getSzkolenieUwagi() {
        return szkolenieUwagi;
    }

    public void setSzkolenieUwagi(String szkolenieUwagi) {
        this.szkolenieUwagi = szkolenieUwagi;
    }

    public Boolean getInstruktaz() {
        return instruktaz;
    }

    public void setInstruktaz(Boolean instruktaz) {
        this.instruktaz = instruktaz;
    }

    public String getInstruktazUwagi() {
        return instruktazUwagi;
    }

    public void setInstruktazUwagi(String instruktazUwagi) {
        this.instruktazUwagi = instruktazUwagi;
    }

    public Boolean getRyzyko() {
        return ryzyko;
    }

    public void setRyzyko(Boolean ryzyko) {
        this.ryzyko = ryzyko;
    }

    public String getRyzykoUwagi() {
        return ryzykoUwagi;
    }

    public void setRyzykoUwagi(String ryzykoUwagi) {
        this.ryzykoUwagi = ryzykoUwagi;
    }

    public Boolean getInstrukcjeBhp() {
        return instrukcjeBhp;
    }

    public void setInstrukcjeBhp(Boolean instrukacje_bhp) {
        this.instrukcjeBhp = instrukacje_bhp;
    }

    public String getInstrukcjeBhpUwagi() {
        return instrukcjeBhpUwagi;
    }

    public void setInstrukcjeBhpUwagi(String instrukcjeBhpUwagi) {
        this.instrukcjeBhpUwagi = instrukcjeBhpUwagi;
    }

    public Boolean getSzkolenieBhp() {
        return szkolenieBhp;
    }

    public void setSzkolenieBhp(Boolean szkolenieBhp) {
        this.szkolenieBhp = szkolenieBhp;
    }

    public String getSzkolenieBhpUwagi() {
        return szkolenieBhpUwagi;
    }

    public void setSzkolenieBhpUwagi(String szkolenieBhpUwagi) {
        this.szkolenieBhpUwagi = szkolenieBhpUwagi;
    }

    public Date getSzkolenieBhpData() {
        return szkolenieBhpData;
    }

    public void setSzkolenieBhpData(Date szkolenieBhpData) {
        this.szkolenieBhpData = szkolenieBhpData;
    }

    public Boolean getRachunki() {
        return rachunki;
    }

    public void setRachunki(Boolean rachunki) {
        this.rachunki = rachunki;
    }

    public String getRachunkiUwagi() {
        return rachunkiUwagi;
    }

    public void setRachunkiUwagi(String rachunkiUwagi) {
        this.rachunkiUwagi = rachunkiUwagi;
    }

    public Boolean getUmowa() {
        return umowa;
    }

    public void setUmowa(Boolean umowa) {
        this.umowa = umowa;
    }

    public String getUmowaUwagi() {
        return umowaUwagi;
    }

    public void setUmowaUwagi(String umowaUwagi) {
        this.umowaUwagi = umowaUwagi;
    }

    public Date getUmowaData() {
        return umowaData;
    }

    public void setUmowaData(Date umowaData) {
        this.umowaData = umowaData;
    }

    public Boolean getOdbiorOdziezy() {
        return odbiorOdziezy;
    }

    public void setOdbiorOdziezy(Boolean odbiorOdziezy) {
        this.odbiorOdziezy = odbiorOdziezy;
    }

    public String getOdbiorOdziezyUwagi() {
        return odbiorOdziezyUwagi;
    }

    public void setOdbiorOdziezyUwagi(String odbiorOdziezyUwagi) {
        this.odbiorOdziezyUwagi = odbiorOdziezyUwagi;
    }

    public Date getOdbiorOdziezyData() {
        return odbiorOdziezyData;
    }

    public void setOdbiorOdziezyData(Date odbiorOdziezyData) {
        this.odbiorOdziezyData = odbiorOdziezyData;
    }

    public Boolean getZua() {
        return zua;
    }

    public void setZua(Boolean zua) {
        this.zua = zua;
    }

    public String getZuaUwagi() {
        return zuaUwagi;
    }

    public void setZuaUwagi(String zuaUwagi) {
        this.zuaUwagi = zuaUwagi;
    }

    public Boolean getZza() {
        return zza;
    }

    public void setZza(Boolean zza) {
        this.zza = zza;
    }

    public String getZzaUwagi() {
        return zzaUwagi;
    }

    public void setZzaUwagi(String zzaUwagi) {
        this.zzaUwagi = zzaUwagi;
    }

    public Boolean getZwua() {
        return zwua;
    }

    public void setZwua(Boolean zwua) {
        this.zwua = zwua;
    }

    public String getZwuaUwagi() {
        return zwuaUwagi;
    }

    public void setZwuaUwagi(String zwuaUwagi) {
        this.zwuaUwagi = zwuaUwagi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
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

    public void parse(Sheet sheet) {
        this.pracodawcaNazwa = SheetHelper.getTextOrNull(sheet, Field.W_1, Field.C);
        this.nazwa = SheetHelper.getTextOrNull(sheet, Field.W_2, Field.C);

        this.kwestionariusz = SheetHelper.getBoolean(sheet, Field.W_5);
        this.kwestionariuszUwagi = SheetHelper.getTextOrNull(sheet, Field.W_5, Field.E);

        this.kartaSzkolenia = SheetHelper.getBoolean(sheet, Field.W_6);
        this.kartaSzkoleniaUwagi = SheetHelper.getTextOrNull(sheet, Field.W_6, Field.E);
        this.kartaSzkoleniaData = SheetHelper.getDate(sheet, Field.W_6, Field.F);

        this.szkolenie = SheetHelper.getBoolean(sheet, Field.W_7);
        this.szkolenieUwagi = SheetHelper.getTextOrNull(sheet, Field.W_7, Field.E);

        this.instruktaz = SheetHelper.getBoolean(sheet, Field.W_8);
        this.instruktazUwagi = SheetHelper.getTextOrNull(sheet, Field.W_8, Field.E);

        this.ryzyko = SheetHelper.getBoolean(sheet, Field.W_9);
        this.ryzykoUwagi = SheetHelper.getTextOrNull(sheet, Field.W_9, Field.E);

        this.instrukcjeBhp = SheetHelper.getBoolean(sheet, Field.W_10);
        this.instrukcjeBhpUwagi = SheetHelper.getTextOrNull(sheet, Field.W_10, Field.E);

        this.szkolenieBhp = SheetHelper.getBoolean(sheet, Field.W_11);
        this.szkolenieBhpUwagi = SheetHelper.getTextOrNull(sheet, Field.W_11, Field.E);
        this.szkolenieBhpData = SheetHelper.getDate(sheet, Field.W_11, Field.F);

        this.rachunki = SheetHelper.getBoolean(sheet, Field.W_12);
        this.rachunkiUwagi = SheetHelper.getTextOrNull(sheet, Field.W_12, Field.E);

        this.umowa = SheetHelper.getBoolean(sheet, Field.W_13);
        this.umowaUwagi = SheetHelper.getTextOrNull(sheet, Field.W_13, Field.E);
        this.umowaData = SheetHelper.getDate(sheet, Field.W_13, Field.F);

        for (int wiersz = 13; wiersz <= 16; wiersz++) {
            String dokument = SheetHelper.getTextOrNull(sheet, wiersz, Field.B);
            if (dokument != null) {
                switch (dokument.trim()) {
                    case "BADANIA LEKARSKIE":
                    case "Zaświadczenie lekarskie":
                        this.badania = SheetHelper.getBoolean(sheet, wiersz);
                        this.badaniaUwagi = SheetHelper.getTextOrNull(sheet, wiersz, Field.E);
                        this.badaniaData = SheetHelper.getDate(sheet, wiersz, Field.F);
                        break;
                    case "DOWÓD OSOBISTY":
                        this.dowod = SheetHelper.getBoolean(sheet, wiersz);
                        this.dowodUwagi = SheetHelper.getTextOrNull(sheet, wiersz, Field.E);
                        break;
                    case "Życiorys":
                        this.zyciorys = SheetHelper.getBoolean(sheet, wiersz);
                        this.zyciorysUwagi = SheetHelper.getTextOrNull(sheet, wiersz, Field.E);
                        break;
                    case "ZUA":
                        this.zua = SheetHelper.getBoolean(sheet, wiersz);
                        this.zuaUwagi = SheetHelper.getTextOrNull(sheet, wiersz, Field.E);
                        break;
                    case "ZZA":
                        this.zza = SheetHelper.getBoolean(sheet, wiersz);
                        this.zzaUwagi = SheetHelper.getTextOrNull(sheet, wiersz, Field.E);
                        break;
                    case "Zaświadczenie  - student":
                        this.zaswiadczenieStudent = SheetHelper.getBoolean(sheet, wiersz);
                        this.zaswiadczenieStudentUwagi = SheetHelper.getTextOrNull(sheet, wiersz, Field.E);
                        break;
                    case "ZWUA":
                        this.zwua = SheetHelper.getBoolean(sheet, wiersz);
                        this.zwuaUwagi = SheetHelper.getTextOrNull(sheet, wiersz, Field.E);
                        break;
                    case "ODBIÓR ODZIEŻY":
                        this.odbiorOdziezy = SheetHelper.getBoolean(sheet, wiersz);
                        this.odbiorOdziezyUwagi = SheetHelper.getTextOrNull(sheet, wiersz, Field.E);
                        this.odbiorOdziezyData = SheetHelper.getDate(sheet, wiersz, Field.F);
                        break;
                    default:
                }
            }
        }
    }
}
