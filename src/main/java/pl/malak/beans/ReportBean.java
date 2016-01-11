package pl.malak.beans;

import org.springframework.stereotype.Service;
import pl.malak.beans.dao.PracaDao;
import pl.malak.beans.dao.ZlecenieDao;
import pl.malak.model.ReportRow;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReportBean {

    @Resource
    private ZlecenieDao zlecenieDao;

    @Resource
    private PracaDao pracaDao;

    public String generateReport() {
        List<ReportRow> zlecenia = zlecenieDao.generateReport();
        List<ReportRow> prace = pracaDao.generateReport();
        StringBuilder result = new StringBuilder();
        result.append("Firma;").append("Pracownik;").append("Do kiedy ważne szkolenie bhp;").append("Do kiedy ważne " +
                "badania lekarskie;").append("Rodzaj umowy").append( newLine());
        for (ReportRow row : prace) {
            result.append(row.toCsv()).append("Umowa o pracę").append( newLine());
        }
        for (ReportRow row : zlecenia) {
            result.append(row.toCsv()).append("Umowa zlecenie").append( newLine());
        }
        return result.toString();
    }

    private String newLine() {
        return System.lineSeparator();
    }
}
