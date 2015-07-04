package pl.malak;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import pl.malak.model.Pracodawca;
import pl.malak.sheets.Commission;
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

    public void migrate(File file) {
        long time = System.currentTimeMillis();
        List<Commission> commissionList = new ArrayList<>();
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
                        Commission commission = new Commission(sheet);
                        commissionList.add(commission);
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
//        for (Commission commission : commissionList) {
//            if (commission.getEmployerName() == null) {
//                System.out.println(String.format("Arkusz %s jest nieprawidłowy", commission.getSheetName()));
//            } else {
//                employer = getEmployer(commission.getEmployerName());
//                if (employer == null) {
//                    employer = new Pracodawca(null);
//                    addEmployer(commission.getEmployerName(), employer);
//                }
//                employer.addCommission(commission);
//            }
//        }
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
    }

//    public void printEmployersWithEmployees() {
//        Pracodawca employer;
//        for (String employerName : employers.keySet()) {
//            employer = getEmployer(employerName);
//            System.out.println("-----------" + employerName + "-----------");
//
//            Collections.sort(employer.getWorkers());
//            Collections.sort(employer.getCommissions());
//
//            if (!employer.getWorkers().isEmpty()) {
//                System.out.println("-----------Umowy o pracę:");
//            }
//            for (Work work : employer.getWorkers()) {
//                System.out.println(work.getEmployeeName());
//            }
//            if (!employer.getCommissions().isEmpty()) {
//                System.out.println("-----------Umowy o zlecenia:");
//            }
//            for (Commission commission : employer.getCommissions()) {
//                System.out.println(commission.getEmployeeName());
//            }
//            System.out.println("---------------------------------------------");
//        }
//    }

//    public void printEmployerIssues() {
//        Pracodawca employer;
//        for (String employerName : employers.keySet()) {
//            employer = getEmployer(employerName);
//            System.out.println("-----------" + employerName + "-----------");
//            if (employer.hasSheet()) {
//                if (employer.getIssues().isEmpty()) {
//                    System.out.println(String.format("%s w arkuszu %s nie ma problemów.", employerName,
//                            employer.getSheetName()));
//                } else {
//                    System.out.println(String.format("Problemy %s w arkuszu %s:", employerName, employer.getSheetName()));
//                    for (String issue : employer.getIssues()) {
//                        System.out.println(issue);
//                    }
//                }
//            }
//
//            Collections.sort(employer.getWorkers());
//            Collections.sort(employer.getCommissions());
//
//            for (Work work : employer.getWorkers()) {
//                String employeeName = work.getEmployeeName();
//                String sheetName = work.getSheetName();
//                if (work.getIssues().isEmpty()) {
//                    System.out.println(String.format("%s w arkuszu %s nie ma problemów.", employeeName, sheetName));
//                } else {
//                    System.out.println(String.format("Problemy %s w arkuszu %s:", employeeName, sheetName));
//                    for (String issue : work.getIssues()) {
//                        System.out.println(issue);
//                    }
//                }
//            }
//            for (Commission commission : employer.getCommissions()) {
//                String employeeName = commission.getEmployeeName();
//                String sheetName = commission.getSheetName();
//                if (commission.getIssues().isEmpty()) {
//                    System.out.println(String.format("%s w arkuszu %s nie ma problemów.", employeeName, sheetName));
//                } else {
//                    System.out.println(String.format("Problemy %s w arkuszu %s:", employeeName, sheetName));
//                    for (String issue : commission.getIssues()) {
//                        System.out.println(issue);
//                    }
//                }
//            }
//            System.out.println("---------------------------------------------");
//        }
//    }

    public Collection<Pracodawca> getEmployers() {
        return employers.values();
    }
}
