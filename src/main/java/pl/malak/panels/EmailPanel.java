package pl.malak.panels;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import pl.malak.beans.EmailSender;
import pl.malak.beans.PracodawcaBean;
import pl.malak.beans.dao.PracaDao;
import pl.malak.beans.dao.PracodawcaDao;
import pl.malak.beans.dao.ZlecenieDao;
import pl.malak.helpers.UIHelper;
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

    @Value("${koszt.teczka.ogolna}")
    private Double kosztTeczkaOgolna;

    @Value("${koszt.ocena.ryzyka}")
    private Double kosztOcenaRyzyka;

    @Value("${koszt.szkolenia.okresowe}")
    private Double kosztSzkoleniaOkresowe;

    @Value("${koszt.szkolenia.pracodawcy}")
    private Double kosztSzkoleniaPracodawcy;

    @Value("${koszt.odziezowka}")
    private Double kosztOdziezowka;

    @Value("${koszt.badania}")
    private Double kosztBadania;

    @Value("${email.stopka}")
    private String emailStopka;

    private Pracodawca obecnyPracodawca;

    JLabel nazwaLabel = new JLabel();
    JLabel tytulLabel = new JLabel("Tytuł:");
    JTextField tytulText = new JTextField();
    JEditorPane emailText = new JEditorPane();
    JScrollPane scrollPane = new JScrollPane(emailText);
    JButton wyslij = new JButton("Wyślij");
    JButton wroc = new JButton("Wróć");
    JLabel czcionkaLabel = new JLabel("Czcionka:");
    JComboBox<String> czcionka = new JComboBox<>(new String[]{
            "10px", "12px", "14px", "16px", "18px", "20px"
    });

    public EmailPanel() {
        super();
        nazwaLabel.setFont(new Font(nazwaLabel.getFont().getFamily(), Font.PLAIN, 20));
        emailText.setContentType("text/html");

        layoutComponents();
        addListeners();
    }

    private void addListeners() {
        wroc.addActionListener(this);
        wyslij.addActionListener(this);
        czcionka.addActionListener(this);
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
        czcionkaLabel.setBounds(x, y, width / 4, height);
        add(czcionkaLabel);

        x += xMargin + width / 4;
        czcionka.setBounds(x, y, width / 4, height);
        add(czcionka);

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
            String tytul = tytulText.getText();
            String tresc = emailText.getText();
            String email = obecnyPracodawca.getEmail();
            String msg = emailSender.send(tytul, tresc, email);
            UIHelper.displayMessage(this, msg);
        } else if (e.getSource() == wroc) {
            getFrame().initPrzegladaniePracodawcow(obecnyPracodawca);
        } else if (e.getSource() == czcionka) {
            Integer rozmiar = Integer.parseInt(UIHelper.getComboText(czcionka).replaceAll("px", ""));
            emailText.setFont(new Font(emailText.getFont().getFamily(), Font.PLAIN, rozmiar));
        }
    }

    public void init(Pracodawca pracodawca) {
        this.obecnyPracodawca = pracodawca;
        this.nazwaLabel.setText(String.format("Email do: %s (%s)", pracodawca.getNazwa(), pracodawca.getEmail()));
        int punkt = 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>");
        stringBuilder.append("<head><title>");
        stringBuilder.append(tytulText.getText());
        stringBuilder.append("</title></head>");
        stringBuilder.append("<body>");
        stringBuilder.append("Witam");
        stringBuilder.append("<br/>");
        stringBuilder.append("<br/>");
        stringBuilder.append("Informujemy, że posiadają Państwo następujące braki w dokumentacji BHP firmy ");
        stringBuilder.append(obecnyPracodawca.getNazwa());
        stringBuilder.append(":");
        stringBuilder.append("<br/>");
        if (!pracodawca.getTeczka()) {
            stringBuilder.append(punkt++).append(". ");
            stringBuilder.append("Teczka ogólna - ");
            if (StringUtils.isNotBlank(pracodawca.getTeczkaUwagi())) {
                stringBuilder.append(pracodawca.getTeczkaUwagi().toLowerCase());
            } else {
                stringBuilder.append("brak");
                stringBuilder.append(" - koszt sporządzenia ");
                DecimalFormat df = new DecimalFormat("#.00");
                stringBuilder.append(df.format(kosztTeczkaOgolna));
                stringBuilder.append("zł netto.");
            }
            stringBuilder.append("<br/>");
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
                stringBuilder.append(" - koszt sporządzenia ");
                DecimalFormat df = new DecimalFormat("#.00");
                stringBuilder.append(df.format(kosztOcenaRyzyka));
                stringBuilder.append("zł netto.");
            }
            stringBuilder.append("<br/>");
        }
        if (!pracodawca.getSzkoleniaOkresowe()) {
            stringBuilder.append(punkt++).append(". ");
            stringBuilder.append("Szkolenia okresowe - ");
            if (StringUtils.isNotBlank(pracodawca.getSzkoleniaOkresoweUwagi())) {
                stringBuilder.append(pracodawca.getSzkoleniaOkresoweUwagi().toLowerCase());
            } else {
                stringBuilder.append("brak");
                stringBuilder.append(" - koszt sporządzenia ");
                DecimalFormat df = new DecimalFormat("#.00");
                stringBuilder.append(df.format(kosztSzkoleniaOkresowe));
                stringBuilder.append("zł netto.");
            }
            stringBuilder.append("<br/>");
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
            stringBuilder.append(" - koszt sporządzenia ");
            DecimalFormat df = new DecimalFormat("#.00");
            stringBuilder.append(df.format(kosztSzkoleniaPracodawcy));
            stringBuilder.append("zł netto.");
        }
        stringBuilder.append("<br/>");
        if (!pracodawca.getOdziezowka() && !StringUtils.equalsIgnoreCase("nie dotyczy",
                pracodawca.getOdziezowkaUwagi())) {
            stringBuilder.append(punkt).append(". ");
            stringBuilder.append("Odzieżówka - ");
            if (StringUtils.isNotBlank(pracodawca.getOdziezowkaUwagi())) {
                stringBuilder.append(pracodawca.getOdziezowkaUwagi().toLowerCase());
            } else {
                stringBuilder.append("brak");
                stringBuilder.append(" - koszt sporządzenia ");
                DecimalFormat df = new DecimalFormat("#.00");
                stringBuilder.append(df.format(kosztOdziezowka));
                stringBuilder.append("zł netto.");
            }
            stringBuilder.append("<br/>");
        }
        stringBuilder.append("<br/>");
        stringBuilder.append("<br/>");
        stringBuilder.append(emailStopka);
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");
        this.emailText.setText(stringBuilder.toString());
        this.tytulText.setText(String.format("Informacja o brakach w dokumentacji BHP firmy %s",
                obecnyPracodawca.getNazwa()));
        czcionka.setSelectedItem("12px");
    }

    private String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
