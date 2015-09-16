package pl.malak;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.malak.beans.dao.PracaDao;
import pl.malak.beans.dao.PracodawcaDao;
import pl.malak.beans.dao.ZlecenieDao;
import pl.malak.model.Praca;
import pl.malak.model.Pracodawca;
import pl.malak.model.Zlecenie;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Michał Szpruta - szprutamich@gmail.com
 */
@Service
public class Migration {

    @Resource
    private PracodawcaDao pracodawcaDao;

    @Resource
    private ZlecenieDao zlecenieDao;

    @Resource
    private PracaDao pracaDao;

    private Map<String, Pracodawca> employers = new TreeMap<>();

    public Migration() {
    }

    private String getCell(Sheet sheet, Field x, Field y) {
        Row row = sheet.getRow(y.getValue());
        if (row != null) {
            Cell cell = row.getCell(x.getValue());
            if (cell != null) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
                return cell.getStringCellValue();
            }
        }
        return "";
    }

    private void addEmployer(String name, Pracodawca employer) {
        employers.put(name, employer);
    }

    private Pracodawca getEmployer(String name) {
        if (name == null) {
            return null;
        }
        return employers.get(name);
    }

    @Transactional
    public String migrate(File file) {
        String error = "";
        long time = System.currentTimeMillis();
        Map<String, Zlecenie> zlecenia = new TreeMap<>();
        Map<String, Praca> prace = new TreeMap<>();
        try {
            Workbook wb = WorkbookFactory.create(file);
            int sheetSize = wb.getNumberOfSheets();
            Sheet sheet;
            for (int i = 0; i < sheetSize; i++) {
                sheet = wb.getSheetAt(i);
                System.out.println("Sheet: " + sheet.getSheetName());
                if ("Pracodawca".equals(sheet.getSheetName()) || "Umowa o pracę".equals(sheet.getSheetName()) ||
                        "Zlecenie, dzieło".equals(sheet.getSheetName())) {
                    continue;
                }
                String text = getCell(sheet, Field.A, Field.W_3);
                Pracodawca employer;
                switch (text) {
                    case "":
                        Zlecenie zlecenie = new Zlecenie(sheet);
                        zlecenia.put(zlecenie.getNazwa(), zlecenie);
                        break;
                    case "CZĘŚĆ":
                        Praca praca = new Praca(sheet);
                        prace.put(praca.getNazwa(), praca);
                        break;
                    case "Lp.":
                        employer = new Pracodawca(sheet);
                        if (employer.getNazwa() != null) {
                            addEmployer(employer.getNazwa(), employer);
                        } else {
                            error += String.format("Arkusz pracodawcy '%s' jest nieprawidłowy\n", sheet.getSheetName());
                        }
                        break;
                }
            }
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
        Pracodawca employer;
        for (Zlecenie zlecenie : zlecenia.values()) {
            if (zlecenie.getPracodawcaNazwa() == null) {
                error += String.format("Arkusz %s jest nieprawidłowy\n", zlecenie.getSheetName());
                System.out.println();
            } else {
                employer = getEmployer(zlecenie.getPracodawcaNazwa());
                if (employer == null) {
                    error += String.format("Arkusz pracodawcy nie istnieje: %s Błąd w arkuszu: %s\n",
                            zlecenie.getPracodawcaNazwa(), zlecenie.getSheetName());
                } else {
                    System.out.println(String.format("Zlecenie: %s, pracodawca: %s jest ok", zlecenie.getNazwa(),
                            zlecenie.getPracodawcaNazwa()));
                    employer.addZlecenie(zlecenie);
                    zlecenie.setPracodawca(employer);
                }
            }
        }
        for (Praca praca : prace.values()) {
            if (praca.getPracodawcaNazwa() == null) {
                error += String.format("Arkusz %s jest nieprawidłowy\n", praca.getSheetName());
                System.out.println();
            } else {
                employer = getEmployer(praca.getPracodawcaNazwa());
                if (employer == null) {
                    error += String.format("Arkusz pracodawcy nie istnieje: %s. Błąd w arkuszu: %s\n",
                            praca.getPracodawcaNazwa(), praca.getSheetName());
                } else {
                    System.out.println(String.format("Praca: %s, pracodawca: %s jest ok", praca.getNazwa(),
                            praca.getPracodawcaNazwa()));
                    employer.addPraca(praca);
                    praca.setPracodawca(employer);
                }
            }
        }
        System.out.println("Time: " + (System.currentTimeMillis() - time));

        if (error.isEmpty()) {
            zlecenieDao.deleteAll();
            pracaDao.deleteAll();
            pracodawcaDao.deleteAll();
            for (Pracodawca pracodawca : getEmployers()) {
                pracodawcaDao.create(pracodawca);
                for (Zlecenie zlecenie : pracodawca.getZlecenia()) {
                    if (zlecenie.getPracodawca() != null) {
                        zlecenieDao.create(zlecenie);
                    }
                }
                for (Praca praca : pracodawca.getPrace()) {
                    if (praca.getPracodawca() != null) {
                        pracaDao.create(praca);
                    }
                }
            }
        }
        return error;
    }

    public Collection<Pracodawca> getEmployers() {
        return employers.values();
    }
}
