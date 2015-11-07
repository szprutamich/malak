package pl.malak.helpers;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class DateDocument extends PlainDocument {

    private static final long serialVersionUID = 1L;

    public DateDocument() {
        super();
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str != null) {
            if (!this.isValid(str)) {
                return;
            }
            if (super.getLength() >= 10) {
                return;
            }
            super.insertString(offset, str, attr);
        }
    }

    private boolean isValid(String s) {
        return s.matches("\\d{0,4}-?\\d{0,2}-?\\d{0,2}");
    }
}