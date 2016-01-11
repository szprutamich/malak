package pl.malak.model;

import pl.malak.helpers.Helper;

import java.util.Date;

public class ReportRow {

    private String firma;

    private String pracownik;

    private Date bhp;

    private Date badania;

    public ReportRow(String firma, String pracownik, Date bhp, Date badania) {
        this.firma = firma;
        this.pracownik = pracownik;
        this.bhp = bhp;
        this.badania = badania;
    }

    public String toCsv() {
        return String.format("%s;%s;%s;%s;", firma, pracownik, bhp != null ? Helper.formatDate(bhp) : "",
                badania != null ? Helper.formatDate(badania) : "");
    }
}
