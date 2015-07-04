package pl.malak.helpers;

import org.apache.poi.ss.usermodel.Cell;

import java.util.Date;

/**
 * @author Michał Szpruta - szprutamich@gmail.com
 */
public class SheetHelper {

    public static String getCellText(Cell cell) {
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

}
