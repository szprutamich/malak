package pl.malak.model;

import org.apache.poi.ss.usermodel.Sheet;
import pl.malak.Field;
import pl.malak.helpers.SheetHelper;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pracodawcy")
public class Pracodawca {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "nazwa")
    private String nazwa;

    @Column(name = "teczka")
    private Boolean teczka = false;

    @Column(name = "teczka_uwagi")
    private String teczkaUwagi;

    @Column(name = "ocena")
    private Boolean ocena = false;

    @Column(name = "ocena_uwagi")
    private String ocenaUwagi;

    @Column(name = "szkolenia_okresowe")
    private Boolean szkoleniaOkresowe = false;

    @Column(name = "szkolenia_okresowe_uwagi")
    private String szkoleniaOkresoweUwagi;

    @Column(name = "szkolenia_pracodawcy")
    private Boolean szkoleniaPracodawcy = false;

    @Column(name = "szkolenia_pracodawcy_data")
    private Date szkoleniaPracodawcyData;

    @Column(name = "szkolenia_pracodawcy_uwagi")
    private String szkoleniaPracodawcyUwagi;

    @Column(name = "odziezowka")
    private Boolean odziezowka = false;

    @Column(name = "odziezowka_uwagi")
    private String odziezowkaUwagi;

    @OneToMany(mappedBy = "pracodawca")
    protected Set<Zlecenie> zlecenia = new HashSet<>();

    @OneToMany(mappedBy = "pracodawca")
    protected Set<Praca> prace = new HashSet<>();

    @Column(name = "data_usuniecia")
    private Date dataUsuniecia;

    @Column(name = "email")
    private String email;

    public Pracodawca() {
    }

    public Pracodawca(
            String nazwa, String email, Boolean teczka, String teczkaUwagi, Boolean ocena, String ocenaUwagi,
            Boolean szkoleniaOkresowe, String szkoleniaOkresoweUwagi, Boolean szkoleniaPracodawcy,
            Date szkoleniaPracodawcyData, String szkoleniaPracodawcyUwagi, Boolean odziezowka, String odziezowkaUwagi) {
        this.nazwa = nazwa;
        this.email = email;
        this.teczka = teczka;
        this.teczkaUwagi = teczkaUwagi;
        this.ocena = ocena;
        this.ocenaUwagi = ocenaUwagi;
        this.szkoleniaOkresowe = szkoleniaOkresowe;
        this.szkoleniaOkresoweUwagi = szkoleniaOkresoweUwagi;
        this.szkoleniaPracodawcy = szkoleniaPracodawcy;
        this.szkoleniaPracodawcyData = szkoleniaPracodawcyData;
        this.szkoleniaPracodawcyUwagi = szkoleniaPracodawcyUwagi;
        this.odziezowka = odziezowka;
        this.odziezowkaUwagi = odziezowkaUwagi;
    }

    public Pracodawca(Sheet sheet) {
        parse(sheet);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getTeczka() {
        return teczka;
    }

    public void setTeczka(Boolean teczka) {
        this.teczka = teczka;
    }

    public String getTeczkaUwagi() {
        return teczkaUwagi;
    }

    public void setTeczkaUwagi(String teczkaUwagi) {
        this.teczkaUwagi = teczkaUwagi;
    }

    public Boolean getOcena() {
        return ocena;
    }

    public void setOcena(Boolean ocena) {
        this.ocena = ocena;
    }

    public String getOcenaUwagi() {
        return ocenaUwagi;
    }

    public void setOcenaUwagi(String ocenaUwagi) {
        this.ocenaUwagi = ocenaUwagi;
    }

    public Boolean getSzkoleniaOkresowe() {
        return szkoleniaOkresowe;
    }

    public void setSzkoleniaOkresowe(Boolean szkoleniaOkresowe) {
        this.szkoleniaOkresowe = szkoleniaOkresowe;
    }

    public String getSzkoleniaOkresoweUwagi() {
        return szkoleniaOkresoweUwagi;
    }

    public void setSzkoleniaOkresoweUwagi(String szkoleniaOkresoweUwagi) {
        this.szkoleniaOkresoweUwagi = szkoleniaOkresoweUwagi;
    }

    public Boolean getSzkoleniaPracodawcy() {
        return szkoleniaPracodawcy;
    }

    public void setSzkoleniaPracodawcy(Boolean szkoleniaPracodawcy) {
        this.szkoleniaPracodawcy = szkoleniaPracodawcy;
    }

    public Date getSzkoleniaPracodawcyData() {
        return szkoleniaPracodawcyData;
    }

    public void setSzkoleniaPracodawcyData(Date szkoleniaPracodawcyData) {
        this.szkoleniaPracodawcyData = szkoleniaPracodawcyData;
    }

    public String getSzkoleniaPracodawcyUwagi() {
        return szkoleniaPracodawcyUwagi;
    }

    public void setSzkoleniaPracodawcyUwagi(String szkoleniaPracodawcyUwagi) {
        this.szkoleniaPracodawcyUwagi = szkoleniaPracodawcyUwagi;
    }

    public Boolean getOdziezowka() {
        return odziezowka;
    }

    public void setOdziezowka(Boolean odziezowka) {
        this.odziezowka = odziezowka;
    }

    public String getOdziezowkaUwagi() {
        return odziezowkaUwagi;
    }

    public void setOdziezowkaUwagi(String odziezowkaUwagi) {
        this.odziezowkaUwagi = odziezowkaUwagi;
    }

    public Set<Zlecenie> getZlecenia() {
        return zlecenia;
    }

    public Set<Praca> getPrace() {
        return prace;
    }

    public void addZlecenie(Zlecenie zlecenie) {
        this.zlecenia.add(zlecenie);
    }

    public void addPraca(Praca praca) {
        this.prace.add(praca);
    }

    public void setZlecenia(Set<Zlecenie> zlecenia) {
        this.zlecenia = zlecenia;
    }

    public Date getDataUsuniecia() {
        return dataUsuniecia;
    }

    public void setDataUsuniecia(Date dataUsuniecia) {
        this.dataUsuniecia = dataUsuniecia;
    }

    public void parse(Sheet sheet) {
        this.nazwa = SheetHelper.getTextOrNull(sheet, Field.W_1, Field.C);
        this.teczka = SheetHelper.getBoolean(sheet, Field.W_4);
        this.teczkaUwagi = SheetHelper.getTextOrNull(sheet, Field.W_4, Field.E);
        this.ocena = SheetHelper.getBoolean(sheet, Field.W_5);
        this.ocenaUwagi = SheetHelper.getTextOrNull(sheet, Field.W_5, Field.E);
        this.szkoleniaOkresowe = SheetHelper.getBoolean(sheet, Field.W_6);
        this.szkoleniaOkresoweUwagi = SheetHelper.getTextOrNull(sheet, Field.W_6, Field.E);
        this.szkoleniaPracodawcy = SheetHelper.getBoolean(sheet, Field.W_7);
        this.szkoleniaPracodawcyData = SheetHelper.getDate(sheet, Field.W_7, Field.E);
        this.szkoleniaPracodawcyUwagi = SheetHelper.getTextOrNull(sheet, Field.W_7, Field.E);
        this.odziezowka = SheetHelper.getBoolean(sheet, Field.W_8);
        this.odziezowkaUwagi = SheetHelper.getTextOrNull(sheet, Field.W_8, Field.E);
    }
}
