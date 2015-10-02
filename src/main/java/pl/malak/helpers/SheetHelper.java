package pl.malak.helpers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import pl.malak.Field;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Michał Szpruta - szprutamich@gmail.com
 */
public class SheetHelper {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static String getCellText(Cell cell) {
        if (cell != null) {
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                return DATE_FORMAT.format(cell.getDateCellValue());
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
            try {
                return DATE_FORMAT.parse(text);
            } catch (ParseException e) {
                return null;
            }
        } else if (text != null && text.length() > 0) {
            try {
                Integer days = Integer.parseInt(text);
                String dt = "1900-01-01";  // Start date
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                c.setTime(sdf.parse(dt));
                c.add(Calendar.DATE, days - 2);
                return c.getTime();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public static Date getDate(Sheet sheet, Field wiersz, Field kolumna) {
        return getDate(sheet, wiersz.getValue(), kolumna);
    }

    public static boolean getBoolean(Sheet sheet, int wiersz) {
        return getBoolean(sheet, wiersz, Field.C.getValue(), Field.D.getValue());
    }

    public static boolean getBoolean(Sheet sheet, Field wiersz) {
        return getBoolean(sheet, wiersz.getValue());
    }

    public static boolean getBoolean(Sheet sheet, int wiersz, int colY, int colL) {
        String kolumnaJest = SheetHelper.getCellText(sheet.getRow(wiersz).getCell(colY)).trim().toUpperCase();
        String kolumnaBrak = SheetHelper.getCellText(sheet.getRow(wiersz).getCell(colL)).trim().toUpperCase();
        return !kolumnaBrak.contains("BRAK") && (kolumnaJest.contains("JEST") || kolumnaJest.contains("TAK"));
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
