package pl.malak.sheets;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import pl.malak.Field;

/**
 * @author Michał Szpruta - szprutamich@gmail.com
 */
public class Commission extends SheetWrapper implements Comparable {

    public Commission(Sheet sheet) {
        super(sheet, SheetType.COMMISSION);
    }

    @Override
    public void update() {
        Row row;
        Cell isCell;
        Cell deficiencyCell;
        Cell commentsCell;
        Cell attentionCell;
        Cell text;
        for (int i = getMinRow().getValue(); i <= getMaxRow().getValue(); i++) {
            row = sheet.getRow(i);
            if (row != null) {
                text = row.getCell(issueNameColumn.getValue());
                isCell = getIsColumn() != null ? row.getCell(getIsColumn().getValue()) : null;
                deficiencyCell = getDeficiencyColumn() != null ? row.getCell(getDeficiencyColumn().getValue()) : null;
                commentsCell = getCommentsColumn() != null ? row.getCell(getCommentsColumn().getValue()) : null;
                attentionCell = getAttentionColumn() != null ? row.getCell(getAttentionColumn().getValue()) : null;
                if (getCellText(attentionCell).toUpperCase().equals("NIE DOTYCZY")) {
                    continue; // row is fine, skip it
                }
                if (getCellText(commentsCell).toUpperCase().equals("NIE DOTYCZY")) {
                    continue; // row is fine, skip it
                }
                if ((getCellText(deficiencyCell).toUpperCase().equals("BRAK")) ||
                        (!getCellText(isCell).toUpperCase().equals("JEST")
                                && !getCellText(isCell).toUpperCase().equals("TAK"))) {
                    if (text != null && !"Inne dokumenty:".toUpperCase().equals(getCellText(text).toUpperCase())
                            && !"Oświadczenie pracownika o zapoznaniu się:".toUpperCase().equals(getCellText(text)
                            .toUpperCase())) {
                        addIssue(String.format("%s, JEST: %s, BRAK: %s, UWAGA: %s", getCellText(text),
                                getCellText(isCell), getCellText(deficiencyCell), getCellText(attentionCell)));
                    }
                }
            }
        }
    }

    @Override
    public Field getIsColumn() {
        return Field.C;
    }

    @Override
    public Field getDeficiencyColumn() {
        return Field.D;
    }

    @Override
    public Field getCommentsColumn() {
        return Field.E;
    }

    @Override
    public Field getAttentionColumn() {
        return Field.G;
    }

    @Override
    public Field getMinRow() {
        return Field.W_5;
    }

    @Override
    public Field getMaxRow() {
        return Field.W_17;
    }

    @Override
    public int compareTo(Object o) {
        Commission commission = (Commission) o;
        return getEmployeeName().compareTo(commission.getEmployeeName());
    }
}
