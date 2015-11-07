package pl.malak.panels;

import pl.malak.MainFrame;

import javax.swing.*;

public abstract class FramePanel extends JPanel {

    private MainFrame mainFrame;

    public void setFrame(MainFrame frame) {
        this.mainFrame = frame;
    }

    protected MainFrame getFrame() {
        return mainFrame;
    }
}
