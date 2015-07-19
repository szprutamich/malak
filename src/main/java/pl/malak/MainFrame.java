package pl.malak;

import org.springframework.stereotype.Component;
import pl.malak.panels.PracodawcaPanel;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Michał Szpruta - szprutamich@gmail.com
 */
@Component
public class MainFrame extends JFrame implements ActionListener {

    private static final String AUTHOR = "Autor: Michał Szpruta";

    private JMenuItem[][] menuItems = {
            {
                    new JMenuItem("Widok pracodawcy"),
                    new JMenuItem("Logout"),
                    new JMenuItem("Exit")
            },
            {
                    new JMenuItem("Autor")
            }
    };

    @Resource
    private PracodawcaPanel pracodawcaPanel;

    public MainFrame() throws Exception {
        super();
        setLayout(new BorderLayout());
        setTitle("");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(690, 575));

        JMenuBar menuBar = new JMenuBar();
        for (int i = 0; i < menuItems.length; i++) {
            JMenu[] menu = {
                    new JMenu("Program"),
                    new JMenu("Pomoc")
            };
            for (int j = 0; j < menuItems[i].length; j++) {
                menuItems[i][j].addActionListener(this);
                menu[i].add(menuItems[i][j]);
            }
            menuBar.add(menu[i]);
        }
        setJMenuBar(menuBar);
        revalidate();
    }

    public void actionPerformed(ActionEvent evt) {
        JMenuItem source = (JMenuItem) evt.getSource();

        if (source == menuItems[0][0]) {
            add(pracodawcaPanel);
            pracodawcaPanel.init();
        } else if (source == menuItems[0][1]) {
            remove(pracodawcaPanel);
        } else if (source == menuItems[0][2])
            System.exit(0);
        else if (source == menuItems[1][0]) {
            displayMessage(AUTHOR);
        }
        revalidate();
        repaint();
    }

    public void displayMessage(String text) {
        JOptionPane.showMessageDialog(this, text);
    }

    public String displayInput(String text) {
        return JOptionPane.showInputDialog(this, text);
    }
}
