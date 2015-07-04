package pl.malak.sheets;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import pl.malak.Field;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Michał Szpruta - szprutamich@gmail.com
 */
public abstract class SheetWrapper {

    protected Sheet sheet;

    protected SheetType sheetType;

    protected Field issueNameColumn = Field.B;

    private Set<String> issues = new HashSet<>();

    public SheetWrapper() {

    }

    public SheetWrapper(Sheet sheet, SheetType sheetType) {
        this.sheet = sheet;
        this.sheetType = sheetType;
//        update();
    }

    public Set<String> getIssues() {
        return issues;
    }

    public void addIssue(String issue) {
        this.issues.add(issue);
    }

    public String getSheetName() {
        return sheet.getSheetName();
    }

    public String getEmployerName() {
        Row row = sheet.getRow(Field.W_1.getValue());
        if (row != null) {
            Cell cell = row.getCell(Field.C.getValue());
            return getCellText(cell);
        }
        return null;
    }

    public String getEmployeeName() {
        switch (sheetType) {
            case WORK:
            case COMMISSION:
                Row row = sheet.getRow(Field.W_2.getValue());
                if (row != null) {
                    Cell cell = row.getCell(Field.C.getValue());
                    return getCellText(cell);
                }
                return "";
            default:
                return null;
        }
    }

    protected String getCellText(Cell cell) {
        if (cell != null) {
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                Date date = cell.getDateCellValue();
                return String.format("%s-%s-%s", date.getYear() + 1900, date.getMonth(), date.getDate());
            } else {
                cell.setCellType(Cell.CELL_TYPE_STRING);
                if (cell.toString().startsWith("DO ")) {
                    return cell.toString().replaceAll("DO ", "");
                }
                return cell.toString();
            }
        } else {
            return "";
        }
    }

    public abstract void update();

    public abstract Field getIsColumn();

    public abstract Field getDeficiencyColumn();

    public abstract Field getCommentsColumn();

    public abstract Field getAttentionColumn();

    public abstract Field getMinRow();

    public abstract Field getMaxRow();
}
