package pl.malak.helpers;

import javax.swing.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

public class MyTransferHandler extends TransferHandler {

    protected Transferable createTransferable(JComponent c) {
        final JEditorPane pane = (JEditorPane) c;
        final String htmlText = pane.getText();
        final String plainText = extractText(new StringReader(htmlText));
        return new MyTransferable(plainText, htmlText);
    }

    public String extractText(Reader reader) {
        final ArrayList<String> list = new ArrayList<>();

        HTMLEditorKit.ParserCallback parserCallback = new HTMLEditorKit.ParserCallback() {
            public void handleText(final char[] data, final int pos) {
                list.add(new String(data));
            }

            public void handleStartTag(HTML.Tag tag, MutableAttributeSet attribute, int pos) {
            }

            public void handleEndTag(HTML.Tag t, final int pos) {
            }

            public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, final int pos) {
                if (t.equals(HTML.Tag.BR)) {
                    list.add("\n");
                }
            }

            public void handleComment(final char[] data, final int pos) {
            }

            public void handleError(final String errMsg, final int pos) {
            }
        };
        try {
            new ParserDelegator().parse(reader, parserCallback, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = "";
        for (String s : list) {
            result += s;
        }
        return result;
    }


    @Override
    public void exportToClipboard(JComponent comp, Clipboard clip, int action) throws IllegalStateException {
        if (action == COPY) {
            clip.setContents(this.createTransferable(comp), null);
        }
    }

    @Override
    public int getSourceActions(JComponent c) {
        return COPY;
    }
}