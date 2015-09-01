package pl.malak;

import org.springframework.stereotype.Component;
import pl.malak.helpers.UIHelper;
import pl.malak.model.Pracodawca;
import pl.malak.panels.PracaPanel;
import pl.malak.panels.PracodawcaPanel;
import pl.malak.panels.ZleceniePanel;

import javax.annotation.PostConstruct;
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

    @Resource
    private ZleceniePanel zleceniePanel;

    @Resource
    private PracaPanel pracaPanel;

    public MainFrame() {
        super();
        setLayout(new BorderLayout());
        setTitle("Malak");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(650, 450));

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
        refreshView();
    }

    @PostConstruct
    private void setupPanels() {
        pracodawcaPanel.setFrame(this);
        zleceniePanel.setFrame(this);
        pracaPanel.setFrame(this);
    }

    private void removeAllPanels() {
        remove(pracodawcaPanel);
        remove(zleceniePanel);
        remove(pracaPanel);
    }

    public void initPrzegladaniePracodawcow() {
        removeAllPanels();
        add(pracodawcaPanel);
        pracodawcaPanel.initPrzegladanie();
        refreshView();
    }

    public void initDodajPracodawce() {
        removeAllPanels();
        add(pracodawcaPanel);
        pracodawcaPanel.initDodawanie();
        refreshView();
    }

    public void initPrzeglądanieZlecen(Pracodawca pracodawca) {
        removeAllPanels();
        add(zleceniePanel);
        zleceniePanel.initPrzegladanie(pracodawca);
        refreshView();
    }

    public void initDodajZlecenie(Pracodawca pracodawca) {
        removeAllPanels();
        add(zleceniePanel);
        zleceniePanel.initDodawanie(pracodawca);
        refreshView();
    }

    public void initPrzeglądaniePrac(Pracodawca pracodawca) {
        removeAllPanels();
        add(pracaPanel);
        pracaPanel.initPrzegladanie(pracodawca);
        refreshView();
    }

    public void initDodajPrace(Pracodawca pracodawca) {
        removeAllPanels();
        add(pracaPanel);
        pracaPanel.initDodawanie(pracodawca);
        refreshView();
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
                    initPrzegladaniePracodawcow();
                }
            }
        } else if (source == przegladaj) {
            initPrzegladaniePracodawcow();
        } else if (source == dodaj) {
            initDodajPracodawce();
        } else if (source == zakoncz)
            System.exit(0);
        else if (source == autor) {
            UIHelper.displayMessage(this, AUTHOR);
        }
    }

    private void addActionListeners() {
        importuj.addActionListener(this);
        przegladaj.addActionListener(this);
        dodaj.addActionListener(this);
        zakoncz.addActionListener(this);
        autor.addActionListener(this);
    }

    private void refreshView() {
        revalidate();
        repaint();
        pack();
    }
}
