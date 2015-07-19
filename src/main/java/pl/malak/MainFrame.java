package pl.malak;

import org.springframework.stereotype.Component;
import pl.malak.helpers.UIHelper;
import pl.malak.panels.PracodawcaPanel;

import javax.annotation.Resource;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Michał Szpruta - szprutamich@gmail.com
 */
@Component
public class MainFrame extends JFrame implements ActionListener {

    private static final String AUTHOR = "Autor: Michał Szpruta";

    private JMenuItem importuj = new JMenuItem("Importuj z excela");
    private JMenuItem przegladaj = new JMenuItem("Przeglądaj pracodawców");
    private JMenuItem dodaj = new JMenuItem("Dodaj pracodawcę");
    private JMenuItem zakoncz = new JMenuItem("Zakończ");

    private JMenuItem autor = new JMenuItem("Autor");

    private JFileChooser fileChooser = new JFileChooser("");

    @Resource
    private PracodawcaPanel pracodawcaPanel;

    public MainFrame() {
        super();
        setLayout(new BorderLayout());
        setTitle("Malak");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(700, 600));

        JMenuBar menuBar = new JMenuBar();

        JMenu program = new JMenu("Program");
        menuBar.add(program);

        program.add(importuj);
        program.add(przegladaj);
        program.add(dodaj);
        program.add(zakoncz);


        JMenu pomoc = new JMenu("Pomoc");
        menuBar.add(pomoc);

        pomoc.add(autor);

        addActionListeners();

        setJMenuBar(menuBar);
        revalidate();
    }

    public void init() {
        remove(pracodawcaPanel);
        add(pracodawcaPanel);
        pracodawcaPanel.init();
    }

    public void actionPerformed(ActionEvent evt) {
        JMenuItem source = (JMenuItem) evt.getSource();
        if (source == importuj) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xlsm");
            fileChooser.setFileFilter(filter);
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                Migration migration = new Migration();
                String error = migration.migrate(fileChooser.getSelectedFile());
                if (!error.isEmpty()) {
                    UIHelper.displayMessage(this, "Nie udało się wczytać pliku, ponieważ:\n" + error);
                } else {
                    UIHelper.displayMessage(this, "Dane wczytane pomyślnie");
                    init();
                }
            }
        } else if (source == przegladaj) {
            remove(pracodawcaPanel);
            add(pracodawcaPanel);
            pracodawcaPanel.init();
        } else if (source == dodaj) {
            remove(pracodawcaPanel);
            add(pracodawcaPanel);
            pracodawcaPanel.initEmpty();
        } else if (source == zakoncz)
            System.exit(0);
        else if (source == autor) {
            UIHelper.displayMessage(this, AUTHOR);
        }

        revalidate();
        repaint();
    }

    private void addActionListeners() {
        importuj.addActionListener(this);
        przegladaj.addActionListener(this);
        dodaj.addActionListener(this);
        zakoncz.addActionListener(this);
        autor.addActionListener(this);
    }
}
