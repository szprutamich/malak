package pl.malak;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import pl.malak.model.Pracodawca;
import pl.malak.model.Zlecenie;
import pl.malak.sheets.Work;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Michał Szpruta - szprutamich@gmail.com
 */
public class Migration {

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

    public String migrate(File file) {
        String error = "";
        long time = System.currentTimeMillis();
        Map<String, Zlecenie> zlecenia = new TreeMap<>();
        List<Work> workList = new ArrayList<>();
        try {
            Workbook wb = WorkbookFactory.create(file);
            int sheetSize = wb.getNumberOfSheets();
            Sheet sheet;
            for (int i = 0; i < sheetSize; i++) {
                sheet = wb.getSheetAt(i);
                String text = getCell(sheet, Field.A, Field.W_3);
                Pracodawca employer;
                switch (text) {
                    case "":
                        Zlecenie zlecenie = new Zlecenie(sheet);
                        zlecenia.put(zlecenie.getNazwa(), zlecenie);
                        break;
                    case "CZĘŚĆ":
                        Work work = new Work(sheet);
                        workList.add(work);
                        break;
                    case "Lp.":
                        employer = new Pracodawca(sheet);
                        addEmployer(employer.getNazwa(), employer);
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
                    error += String.format("Arkusz pracodawcy nie istnieje: %s\n", zlecenie.getPracodawcaNazwa());
                } else {
                    System.out.println(String.format("Zlecenie: %s, pracodawca: %s jest ok", zlecenie.getNazwa(),
                            zlecenie.getPracodawcaNazwa()));
                    employer.addZlecenie(zlecenie);
                    zlecenie.setPracodawca(employer);
                }
            }
        }
//        for (Work work : workList) {
//            if (work.getEmployerName() == null) {
//                System.out.println(String.format("Arkusz %s jest nieprawidłowy", work.getSheetName()));
//            } else {
//                employer = getEmployer(work.getEmployerName());
//                if (employer == null) {
//                    employer = new Pracodawca(null);
//                    addEmployer(work.getEmployerName(), employer);
//                }
//                employer.addWorker(work);
//            }
//        }
        System.out.println("Time: " + (System.currentTimeMillis() - time));

        return error;
    }

    public Collection<Pracodawca> getEmployers() {
        return employers.values();
    }
}
