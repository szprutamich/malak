package pl.malak.sheets;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import pl.malak.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michał Szpruta - szprutamich@gmail.com
 */
public class Employer extends SheetWrapper {

    public Employer(Sheet sheet) {
        super(sheet, SheetType.EMPLOYER);
    }

    private List<Work> workers = new ArrayList<>();

    private List<Commission> commissions = new ArrayList<>();

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
        return null;
    }

    @Override
    public Field getAttentionColumn() {
        return Field.E;
    }

    @Override
    public Field getMinRow() {
        return Field.W_4;
    }

    @Override
    public Field getMaxRow() {
        return Field.W_8;
    }

    public void addWorker(Work work) {
        workers.add(work);
    }

    public void addCommission(Commission commission) {
        commissions.add(commission);
    }

    public List<Commission> getCommissions() {
        return commissions;
    }

    public List<Work> getWorkers() {
        return workers;
    }

    public boolean hasSheet() {
        return sheet != null;
    }

    public void update() {
        if (sheet == null) {
            return;
        }
        Row row;
        Cell isCell;
        Cell deficiencyCell;
        Cell attentionCell;
        Cell text;
        for (int i = getMinRow().getValue(); i <= getMaxRow().getValue(); i++) {
            row = sheet.getRow(i);
            if (row != null) {
                text = row.getCell(issueNameColumn.getValue());
                isCell = getIsColumn() != null ? row.getCell(getIsColumn().getValue()) : null;
                deficiencyCell = getDeficiencyColumn() != null ? row.getCell(getDeficiencyColumn().getValue()) : null;
                attentionCell = getAttentionColumn() != null ? row.getCell(getAttentionColumn().getValue()) : null;
                if (attentionCell != null && getCellText(attentionCell).toUpperCase().equals("NIE DOTYCZY")) {
                    continue; // row is fine, skip it
                }
                if ((getCellText(deficiencyCell).toUpperCase().equals("BRAK")) ||
                        (!getCellText(isCell).toUpperCase().equals("JEST")
                                && !getCellText(isCell).toUpperCase().equals("TAK"))) {
                    if (text != null) {
                        addIssue(String.format("%s, JEST: %s, BRAK: %s, UWAGA: %s", getCellText(text),
                                getCellText(isCell), getCellText(deficiencyCell), getCellText(attentionCell)));
                    }
                }
            }
        }
    }
}
