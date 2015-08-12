package pl.malak.panels;

import pl.malak.MainFrame;

import javax.swing.*;

/**
 * @author Michał Szpruta - szprutamich@gmail.com
 */
public abstract class FramePanel extends JPanel {

    private MainFrame mainFrame;

    public void setFrame(MainFrame frame) {
        this.mainFrame = frame;
    }

    protected MainFrame getFrame() {
        return mainFrame;
    }
}
