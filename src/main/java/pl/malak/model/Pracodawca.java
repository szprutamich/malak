package pl.malak.model;

import org.apache.poi.ss.usermodel.Sheet;
import pl.malak.Field;
import pl.malak.helpers.SheetHelper;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Michał Szpruta - szprutamich@gmail.com
 */
@Entity
@Table(name = "pracodawcy")
public class Pracodawca {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "nazwa")
    private String nazwa;

    @Column(name = "teczka")
    private Boolean teczka;

    @Column(name = "teczka_uwagi")
    private String teczkaUwagi;

    @Column(name = "ocena")
    private Boolean ocena;

    @Column(name = "ocena_uwagi")
    private String ocenaUwagi;

    @Column(name = "szkolenia_okresowe")
    private Boolean szkoleniaOkresowe;

    @Column(name = "szkolenia_okresowe_uwagi")
    private String szkoleniaOkresoweUwagi;
    @Column(name = "szkolenia_pracodawcy")
    private Boolean szkoleniaPracodawcy;

    @Column(name = "szkolenia_pracodawcy_data")
    private Date szkoleniaPracodawcyData;

    @Column(name = "szkolenia_pracodawcy_uwagi")
    private String szkoleniaPracodawcyUwagi;

    @Column(name = "odziezowka")
    private Boolean odziezowka;

    @Column(name = "odziezowka_uwagi")
    private String odziezowkaUwagi;

    @OneToMany(mappedBy = "pracodawca")
    protected Set<Zlecenie> zlecenia = new HashSet<>();

    public Pracodawca() {
    }

    public Pracodawca(
            String nazwa, Boolean teczka, String teczkaUwagi, Boolean ocena, String ocenaUwagi,
            Boolean szkoleniaOkresowe, String szkoleniaOkresoweUwagi, Boolean szkoleniaPracodawcy,
            Date szkoleniaPracodawcyData, String szkoleniaPracodawcyUwagi, Boolean odziezowka, String odziezowkaUwagi) {
        this.nazwa = nazwa;
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

    public void addZlecenie(Zlecenie zlecenie) {
        this.zlecenia.add(zlecenie);
    }

    public void setZlecenia(Set<Zlecenie> zlecenia) {
        this.zlecenia = zlecenia;
    }

    public void parse(Sheet sheet) {
        this.nazwa = getTextOrNull(SheetHelper.getCellText(sheet.getRow(Field.W_1.getValue()).getCell(Field.C.getValue())));
        this.teczka = getBoolean(SheetHelper.getCellText(sheet.getRow(Field.W_4.getValue()).getCell(Field.C.getValue())),
                SheetHelper.getCellText(sheet.getRow(Field.W_4.getValue()).getCell(Field.D.getValue())));
        this.teczkaUwagi = getTextOrNull(SheetHelper.getCellText(sheet.getRow(Field.W_4.getValue()).getCell(Field.E.getValue())));
        this.ocena = getBoolean(SheetHelper.getCellText(sheet.getRow(Field.W_5.getValue()).getCell(Field.C.getValue())),
                SheetHelper.getCellText(sheet.getRow(Field.W_5.getValue()).getCell(Field.D.getValue())));
        this.ocenaUwagi = getTextOrNull(SheetHelper.getCellText(sheet.getRow(Field.W_5.getValue()).getCell(Field.E.getValue())));
        this.szkoleniaOkresowe = getBoolean(SheetHelper.getCellText(sheet.getRow(Field.W_6.getValue()).getCell(Field.C.getValue())),
                SheetHelper.getCellText(sheet.getRow(Field.W_6.getValue()).getCell(Field.D.getValue())));
        this.szkoleniaOkresoweUwagi = getTextOrNull(SheetHelper.getCellText(sheet.getRow(Field.W_6.getValue()).getCell(Field.E.getValue())));
        this.szkoleniaPracodawcy = getBoolean(SheetHelper.getCellText(sheet.getRow(Field.W_7.getValue()).getCell(Field.C.getValue())),
                SheetHelper.getCellText(sheet.getRow(Field.W_7.getValue()).getCell(Field.D.getValue())));
        this.szkoleniaPracodawcyData = getDate(SheetHelper.getCellText(sheet.getRow(Field.W_7.getValue()).getCell(Field.E.getValue())));
        this.szkoleniaPracodawcyUwagi = getTextOrNull(SheetHelper.getCellText(sheet.getRow(Field.W_7.getValue()).getCell(Field.E.getValue())));
        this.odziezowka = getBoolean(SheetHelper.getCellText(sheet.getRow(Field.W_8.getValue()).getCell(Field.C.getValue())),
                SheetHelper.getCellText(sheet.getRow(Field.W_8.getValue()).getCell(Field.D.getValue())));
        this.odziezowkaUwagi = getTextOrNull(SheetHelper.getCellText(sheet.getRow(Field.W_8.getValue()).getCell(Field.E.getValue())));
    }

    private Date getDate(String text) {
        String[] dates = text.split("-");
        if (dates.length == 3) {
            int i = 0;
            return new Date(Integer.parseInt(dates[i++]) - 1900, Integer.parseInt(dates[i++]), Integer.parseInt(dates[i]));
        }
        return null;
    }

    private boolean getBoolean(String columnJest, String columnBrak) {
        return !columnBrak.trim().equalsIgnoreCase("BRAK") && (columnJest.trim().equalsIgnoreCase("JEST") || columnJest.trim().equalsIgnoreCase("TAK"));
    }

    private String getTextOrNull(String text) {
        if (text.isEmpty()) {
            return null;
        }
        return text;
    }
}
