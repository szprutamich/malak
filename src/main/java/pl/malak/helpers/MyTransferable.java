package pl.malak.helpers;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class MyTransferable implements Transferable {

    private static final DataFlavor[] supportedFlavors;

    static {
        try {
            supportedFlavors = new DataFlavor[]{
                    new DataFlavor("text/html;class=java.lang.String"),
                    new DataFlavor("text/plain;class=java.lang.String")
            };
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private final String plainData;
    private final String htmlData;

    public MyTransferable(String plainData, String htmlData) {
        this.plainData = plainData;
        this.htmlData = htmlData;
    }

    public DataFlavor[] getTransferDataFlavors() {
        return supportedFlavors;
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        for (DataFlavor supportedFlavor : supportedFlavors) {
            if (supportedFlavor == flavor) {
                return true;
            }
        }
        return false;
    }

    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(supportedFlavors[0])) {
            return htmlData;
        }
        if (flavor.equals(supportedFlavors[1])) {
            return plainData;
        }
        throw new UnsupportedFlavorException(flavor);
    }
}
