package pl.malak;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.malak.beans.ReportBean;
import pl.malak.helpers.UIHelper;
import pl.malak.model.Pracodawca;
import pl.malak.panels.EmailPanel;
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
import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

@Service
public class MainFrame extends JFrame implements ActionListener {

    private enum MinPanelSize {
        PRACODAWCA(650, 470),
        PRACA(1000, 750),
        ZLECENIE(650, 650),
        EMAIL(680, 700);

        private int minWidth;
        private int minHeight;

        private MinPanelSize(int minWidth, int minHeight) {
            this.minHeight = minHeight;
            this.minWidth = minWidth;
        }

        public int getMinWidth() {
            return minWidth;
        }

        public int getMinHeight() {
            return minHeight;
        }
    }

    private static final String AUTHOR = "Autor: Michał Szpruta";

    JMenu program = new JMenu("Program");
    private JMenuItem importuj = new JMenuItem("Importuj z excela");
    private JMenuItem przegladaj = new JMenuItem("Przeglądaj pracodawców");
    private JMenuItem dodaj = new JMenuItem("Dodaj pracodawcę");
    private JMenuItem generuj = new JMenuItem("Generuj raport");
    private JMenuItem zakoncz = new JMenuItem("Zakończ");

    private JMenuItem autor = new JMenuItem("Autor");
    private JFileChooser fileChooser;

    @Resource
    private PracodawcaPanel pracodawcaPanel;

    @Resource
    private ZleceniePanel zleceniePanel;

    @Resource
    private PracaPanel pracaPanel;

    @Resource
    private EmailPanel emailPanel;

    @Resource
    private Migration migration;

    @Resource
    private ReportBean reportBean;

    @Value("${import.enabled}")
    private Boolean importEnabled;

    public MainFrame() {
        super();
        try {
            fileChooser = new JFileChooser(MainFrame.class.getProtectionDomain().getCodeSource()
                    .getLocation().toURI().getPath());
        } catch (URISyntaxException e) {
            fileChooser = new JFileChooser("");
        }
        setLayout(new BorderLayout());
        setTitle("Malak");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        JMenuBar menuBar = new JMenuBar();

        menuBar.add(program);

        program.add(importuj);
        program.add(przegladaj);
        program.add(dodaj);
        program.add(generuj);
        program.add(zakoncz);


        JMenu pomoc = new JMenu("Pomoc");
        menuBar.add(pomoc);

        pomoc.add(autor);

        addActionListeners();

        setJMenuBar(menuBar);
        refreshView(MinPanelSize.PRACODAWCA);
    }

    @PostConstruct
    private void setupPanels() {
        pracodawcaPanel.setFrame(this);
        zleceniePanel.setFrame(this);
        pracaPanel.setFrame(this);
        emailPanel.setFrame(this);
        if (!importEnabled) {
            program.remove(importuj);
        }
    }

    private void removeAllPanels() {
        remove(pracodawcaPanel);
        remove(zleceniePanel);
        remove(pracaPanel);
        remove(emailPanel);
    }

    public void initPrzegladaniePracodawcow() {
        initPrzegladaniePracodawcow(null);
    }

    public void initPrzegladaniePracodawcow(Pracodawca pracodawca) {
        removeAllPanels();
        add(pracodawcaPanel);
        pracodawcaPanel.initPrzegladanie(pracodawca);
        refreshView(MinPanelSize.PRACODAWCA);
    }

    public void initDodajPracodawce() {
        removeAllPanels();
        add(pracodawcaPanel);
        pracodawcaPanel.initDodawanie();
        refreshView(MinPanelSize.PRACODAWCA);
    }

    public void initPrzeglądanieZlecen(Pracodawca pracodawca) {
        removeAllPanels();
        add(zleceniePanel);
        zleceniePanel.initPrzegladanie(pracodawca);
        refreshView(MinPanelSize.ZLECENIE);
    }

    public void initDodajZlecenie(Pracodawca pracodawca) {
        removeAllPanels();
        add(zleceniePanel);
        zleceniePanel.initDodawanie(pracodawca);
        refreshView(MinPanelSize.ZLECENIE);
    }

    public void initPrzeglądaniePrac(Pracodawca pracodawca) {
        removeAllPanels();
        add(pracaPanel);
        pracaPanel.initPrzegladanie(pracodawca);
        refreshView(MinPanelSize.PRACA);
    }

    public void initDodajPrace(Pracodawca pracodawca) {
        removeAllPanels();
        add(pracaPanel);
        pracaPanel.initDodawanie(pracodawca);
        refreshView(MinPanelSize.PRACA);
    }

    public void initGenerujEmail(Pracodawca obecnyPracodawca) {
        removeAllPanels();
        add(emailPanel);
        emailPanel.init(obecnyPracodawca);
        refreshView(MinPanelSize.EMAIL);
    }

    public void actionPerformed(ActionEvent evt) {
        JMenuItem source = (JMenuItem) evt.getSource();
        if (source == importuj) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xlsm");
            fileChooser.setFileFilter(filter);
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String error = migration.migrate(fileChooser.getSelectedFile());
                if (!error.isEmpty()) {
                    UIHelper.displayMessage(this, "Nie udało się wczytać pliku, ponieważ:\n" + error);
                } else {
                    UIHelper.displayMessage(this, "Dane wczytane pomyślnie :D");
                    initPrzegladaniePracodawcow(null);
                }
            }
        } else if (source == przegladaj) {
            initPrzegladaniePracodawcow(null);
        } else if (source == dodaj) {
            initDodajPracodawce();
        } else if (source == generuj) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Csv Files", "csv");
            fileChooser.setFileFilter(filter);
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String extension = FilenameUtils.getExtension(file.getName());
                if (StringUtils.isBlank(extension)) {
                    file = new File(file.getAbsolutePath() + ".csv");
                } else {
                    file = new File(file.getAbsolutePath().replace(extension, "csv"));
                }
                try (Writer writer = new OutputStreamWriter(new FileOutputStream(file),
                        Charset.forName("UTF-8").newEncoder() )) {
                    String report = reportBean.generateReport();
                    writer.write("\ufeff");
                    writer.write(report);
                    UIHelper.displayMessage(this, "Raport został wygenerowany pomyślnie");
                } catch (IOException e) {
                    UIHelper.displayMessage(this, String.format("Nie udało się wygenerować raportu: %s",
                            e.getMessage()));
                }
            }
        } else if (source == zakoncz) {
            System.exit(0);
        } else if (source == autor) {
            UIHelper.displayMessage(this, AUTHOR);
        }
    }

    private void addActionListeners() {
        importuj.addActionListener(this);
        przegladaj.addActionListener(this);
        dodaj.addActionListener(this);
        generuj.addActionListener(this);
        zakoncz.addActionListener(this);
        autor.addActionListener(this);
    }

    private void refreshView(MinPanelSize panelSize) {
        setMinimumSize(new Dimension(panelSize.getMinWidth(), panelSize.getMinHeight()));
        revalidate();
        repaint();
        pack();
    }
}
