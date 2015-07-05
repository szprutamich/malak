package pl.malak;

import pl.malak.persistenceutil.PersistenceManager;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Michał Szpruta - szprutamich@gmail.com
 */
public class MainFrame extends JFrame implements ActionListener {

    private static final String AUTHOR = "Autor: Michał Szpruta";

    private EntityManager entityManager;

    private JMenu[] menu = {new JMenu("Program"), new JMenu("Pomoc")};
    private JMenuItem[][] menuItems = {
            {
                    new JMenuItem("Connect"),
                    new JMenuItem("Logout"),
                    new JMenuItem("Exit")
            },
            {
                    new JMenuItem("Autor")
            }
    };

    private PracodawcaPanel pracodawcaPanel = new PracodawcaPanel();

    public MainFrame() throws Exception {
        super();
        setBounds(200, 200, 690, 575);
        add(pracodawcaPanel);

        setLayout(null);

        setTitle("");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        JMenuBar menuBar = new JMenuBar();
        for (int i = 0; i < menuItems.length; i++) {
            for (int j = 0; j < menuItems[i].length; j++) {
                menuItems[i][j].addActionListener(this);
                menu[i].add(menuItems[i][j]);
            }
            menuBar.add(menu[i]);
        }
        setJMenuBar(menuBar);
    }

    public void actionPerformed(ActionEvent evt) {
        JMenuItem source = (JMenuItem) evt.getSource();

        if (source == menuItems[0][0]) {
            try {
                String url = displayInput("Podaj adres bazy danych");
                if (url != null) {
                    if (url.length() > 0) {
                        entityManager = PersistenceManager.INSTANCE.getCustomEntityManager(url);
                        displayMessage("Połączono");
                    } else {
                        displayMessage("Adres bazy jest pusty!");
                    }
                }
            } catch (Exception e) {
                displayMessage(e.getMessage());
            }
        } else if (source == menuItems[0][1]) {
        } else if (source == menuItems[0][2])
            System.exit(0);
        else if (source == menuItems[1][0]) {
            displayMessage(AUTHOR);
        }
        repaint();
    }

    public void displayMessage(String text) {
        JOptionPane.showMessageDialog(this, text);
    }

    public String displayInput(String text) {
        return JOptionPane.showInputDialog(this, text);
    }
}
