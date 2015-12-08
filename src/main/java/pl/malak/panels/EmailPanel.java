package pl.malak.panels;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import pl.malak.beans.EmailSender;
import pl.malak.beans.PracodawcaBean;
import pl.malak.beans.dao.PracaDao;
import pl.malak.beans.dao.PracodawcaDao;
import pl.malak.beans.dao.ZlecenieDao;
import pl.malak.model.Pracodawca;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@org.springframework.stereotype.Component
public class EmailPanel extends FramePanel implements ActionListener {

    @Resource
    private PracodawcaBean pracodawcaBean;

    @Resource
    private PracodawcaDao pracodawcaDao;

    @Resource
    private ZlecenieDao zlecenieDao;

    @Resource
    private PracaDao pracaDao;

    @Resource
    private EmailSender emailSender;

    @Value("${koszt.teczka}")
    private Double kosztTeczki;

    private Pracodawca obecnyPracodawca;

    JLabel nazwaLabel = new JLabel();
    JLabel tytulLabel = new JLabel("Tytuł:");
    JTextField tytulText = new JTextField();
    JTextArea emailText = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(emailText);
    JButton wyslij = new JButton("Wyślij");
    JButton wroc = new JButton("Wróć");

    public EmailPanel() {
        super();
        nazwaLabel.setFont(new Font(nazwaLabel.getFont().getFamily(), Font.PLAIN, 20));

        layoutComponents();
        addListeners();
    }

    private void addListeners() {
        wroc.addActionListener(this);
        wyslij.addActionListener(this);
    }

    private void layoutComponents() {
        setLayout(null);
        int xMargin = 20;
        int yMargin = 20;
        int width = 300;
        int doubleWidth = width * 2 + xMargin;
        int height = 25;
        int scrollPanelHeight = 15 * height;
        int x = xMargin;
        int y = yMargin;

        nazwaLabel.setBounds(x, y, doubleWidth, height);
        add(nazwaLabel);

        y += height + yMargin;
        tytulLabel.setBounds(x, y, width / 4, height);
        add(tytulLabel);

        x += xMargin + width / 4;
        tytulText.setBounds(x, y, width + width / 4 * 3, height);
        add(tytulText);

        y += height + yMargin;
        x = xMargin;
        scrollPane.setBounds(x, y, doubleWidth, scrollPanelHeight);
        add(scrollPane);

        y += scrollPanelHeight + yMargin;
        wyslij.setBounds(x, y, width, height);
        add(wyslij);

        x += xMargin + width;
        wroc.setBounds(x, y, width, height);
        add(wroc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == wyslij) {
            String tytul = "TEST";
            String tresc = emailText.getText();
            String email = obecnyPracodawca.getEmail();
            emailSender.send(tytul, tresc, email);
        } else if (e.getSource() == wroc) {
            getFrame().initPrzegladaniePracodawcow(obecnyPracodawca);
        }
    }

    public void init(Pracodawca pracodawca) {
        this.obecnyPracodawca = pracodawca;
        this.nazwaLabel.setText(String.format("Email do: %s (%s)", pracodawca.getNazwa(), pracodawca.getEmail()));
        int punkt = 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Witam");
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("Informujemy, że posiadają Państwo następujące braki w dokumentacji BHP:");
        stringBuilder.append(System.lineSeparator());
        if (!pracodawca.getTeczka()) {
            stringBuilder.append(punkt++).append(". ");
            stringBuilder.append("Teczka ogólna - ");
            if (StringUtils.isNotBlank(pracodawca.getTeczkaUwagi())) {
                stringBuilder.append(pracodawca.getTeczkaUwagi().toLowerCase());
            } else {
                stringBuilder.append("brak");
            }
            stringBuilder.append(System.lineSeparator());
        }
        if (!pracodawca.getOcena()) {
            stringBuilder.append(punkt++).append(". ");
            stringBuilder.append("Ocena ryzyka - ");
            if (StringUtils.isNotBlank(pracodawca.getOcenaUwagi())) {
                if ("podpisać".equals(pracodawca.getOcenaUwagi().toLowerCase())) {
                    stringBuilder.append("brak podpisów");
                } else {
                    stringBuilder.append(pracodawca.getOcenaUwagi().toLowerCase());
                }
            } else {
                stringBuilder.append("brak");
            }
            stringBuilder.append(System.lineSeparator());
        }
        if (!pracodawca.getSzkoleniaOkresowe()) {
            stringBuilder.append(punkt++).append(". ");
            stringBuilder.append("Szkolenia okresowe - ");
            if (StringUtils.isNotBlank(pracodawca.getSzkoleniaOkresoweUwagi())) {
                stringBuilder.append(pracodawca.getSzkoleniaOkresoweUwagi().toLowerCase());
            } else {
                stringBuilder.append("brak");
            }
            stringBuilder.append(" - koszt sporządzenia ");
            DecimalFormat df = new DecimalFormat("#.00");
            stringBuilder.append(df.format(kosztTeczki));
            stringBuilder.append("zł netto/szt.");
            stringBuilder.append(System.lineSeparator());
        }
        stringBuilder.append(punkt++).append(". ");
        stringBuilder.append("Szkolenia pracodawcy - ");
        if (pracodawca.getSzkoleniaPracodawcyData() != null) {
            stringBuilder.append("ważne są do dnia: ");
            stringBuilder.append(formatDate(pracodawca.getSzkoleniaPracodawcyData()));
        } else if (StringUtils.isNotBlank(pracodawca.getSzkoleniaPracodawcyUwagi())) {
            stringBuilder.append(pracodawca.getSzkoleniaPracodawcyUwagi().toLowerCase());
        } else {
            stringBuilder.append("brak");
        }
        stringBuilder.append(System.lineSeparator());
        if (!pracodawca.getOdziezowka() && !StringUtils.equalsIgnoreCase("nie dotyczy",
                pracodawca.getOdziezowkaUwagi())) {
            stringBuilder.append(punkt).append(". ");
            stringBuilder.append("Odzieżówka - ");
            if (StringUtils.isNotBlank(pracodawca.getOdziezowkaUwagi())) {
                stringBuilder.append(pracodawca.getOdziezowkaUwagi().toLowerCase());
            } else {
                stringBuilder.append("brak");
            }
            stringBuilder.append(System.lineSeparator());
        }
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("Z poważaniem");
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("Tomasz Malak");
        this.emailText.setText(stringBuilder.toString());
    }

    private String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
