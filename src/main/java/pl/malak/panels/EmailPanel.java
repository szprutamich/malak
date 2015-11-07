package pl.malak.panels;

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
import java.util.UUID;

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
        String text = "";
        for (int i = 0; i < 20; i++) {
            text += UUID.randomUUID().toString().replaceAll("-", " ");
            text += "\n";
        }
        this.emailText.setText(text);
    }
}
