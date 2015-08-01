package pl.malak.helpers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import pl.malak.Field;

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

    public static Date getDate(Sheet sheet, int wiersz, Field kolumna) {
        String text = SheetHelper.getCellText(sheet.getRow(wiersz).getCell(kolumna.getValue()));
        String[] dates = text.split("-");
        if (dates.length == 3) {
            int i = 0;
            return new Date(Integer.parseInt(dates[i++]) - 1900, Integer.parseInt(dates[i++]), Integer.parseInt(dates[i]));
        }
        return null;
    }

    public static Date getDate(Sheet sheet, Field wiersz, Field kolumna) {
        return getDate(sheet, wiersz.getValue(), kolumna);
    }

    public static boolean getBoolean(Sheet sheet, int wiersz) {
        String kolumnaJest = SheetHelper.getCellText(sheet.getRow(wiersz).getCell(Field.C.getValue()));
        String kolumnaBrak = SheetHelper.getCellText(sheet.getRow(wiersz).getCell(Field.D.getValue()));
        return !kolumnaBrak.trim().equalsIgnoreCase("BRAK")
                && (kolumnaJest.trim().equalsIgnoreCase("JEST") || kolumnaJest.trim().equalsIgnoreCase("TAK"));
    }

    public static boolean getBoolean(Sheet sheet, Field wiersz) {
        return getBoolean(sheet, wiersz.getValue());
    }

    public static String getTextOrNull(Sheet sheet, int wiersz, Field kolumna) {
        String text = SheetHelper.getCellText(sheet.getRow(wiersz).getCell(kolumna.getValue()));
        if (text.isEmpty()) {
            return null;
        }
        return text;
    }

    public static String getTextOrNull(Sheet sheet, Field wiersz, Field kolumna) {
        return getTextOrNull(sheet, wiersz.getValue(), kolumna);
    }

}
