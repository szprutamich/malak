package pl.malak.panels;

import org.apache.commons.io.FilenameUtils;
import pl.malak.beans.EmailGenerator;
import pl.malak.beans.EmailSender;
import pl.malak.helpers.UIHelper;
import pl.malak.model.Pracodawca;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;

@org.springframework.stereotype.Component
public class EmailPanel extends FramePanel implements ActionListener {

    @Resource
    private EmailSender emailSender;

    @Resource
    private EmailGenerator emailGenerator;

    private Pracodawca obecnyPracodawca;

    private Map<String, BodyPart> zalaczonePliki;
    private List<JButton> zalaczoneButtony;

    private int plikiY;

    private JFileChooser fileChooser;
    private JLabel nazwaLabel = new JLabel();
    private JLabel tytulLabel = new JLabel("Tytuł:");
    private JTextField tytulText = new JTextField();
    private JEditorPane emailText = new JEditorPane();
    private JScrollPane scrollPane = new JScrollPane(emailText);
    private JButton wyslij = new JButton("Wyślij");
    private JButton wroc = new JButton("Wróć");
    private JButton zalaczPlik = new JButton("Załącz plik");
    private JLabel czcionkaLabel = new JLabel("Czcionka:");
    private JComboBox<String> czcionka = new JComboBox<>(new String[]{
            "10px", "12px", "14px", "16px", "18px", "20px"
    });

    public EmailPanel() {
        super();
        try {
            fileChooser = new JFileChooser(EmailPanel.class.getProtectionDomain().getCodeSource()
                    .getLocation().toURI().getPath());
        } catch (URISyntaxException e) {
            fileChooser = new JFileChooser("");
        }
        nazwaLabel.setFont(new Font(nazwaLabel.getFont().getFamily(), Font.PLAIN, 20));
        emailText.setContentType("text/html");

        layoutComponents();
        addListeners();
    }

    private void addListeners() {
        wroc.addActionListener(this);
        wyslij.addActionListener(this);
        zalaczPlik.addActionListener(this);
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

        x = xMargin + xMargin + width;
        y += height + yMargin / 4;
        zalaczPlik.setBounds(x, y, width, height);
        add(zalaczPlik);

        plikiY = y;
    }

    private void layoutFileButtons() {
        int y = plikiY;
        for (JButton button : zalaczoneButtony) {
            button.setBounds(20, y, 300, 20);
            y += 25;
            add(button);
        }
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == wyslij) {
            String tytul = tytulText.getText();
            String tresc = emailText.getText();
            String email = obecnyPracodawca.getEmail();
            String msg = emailSender.send(tytul, tresc, email, zalaczonePliki.values()
                    .toArray(new BodyPart[zalaczonePliki.size()]));
            UIHelper.displayMessage(this, msg);
        } else if (e.getSource() == wroc) {
            getFrame().initPrzegladaniePracodawcow(obecnyPracodawca);
        } else if (e.getSource() == zalaczPlik) {
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String path = fileChooser.getSelectedFile().getAbsolutePath();
                final String name = FilenameUtils.getName(path);
                try {
                    zalaczonePliki.put(name, createBodyPartFromFile(path));
                    final JButton button = new JButton(String.format("Usuń %s", name));
                    zalaczoneButtony.add(button);
                    layoutFileButtons();
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (JButton zalaczony : zalaczoneButtony) {
                                remove(zalaczony);
                            }
                            zalaczonePliki.remove(name);
                            zalaczoneButtony.remove(button);
                            layoutFileButtons();
                        }
                    });
                } catch (MessagingException exc) {
                    UIHelper.displayMessage(this, String.format("Nie udało się załączyć pliku %s", name));
                }
            }
        } else if (e.getSource() == czcionka) {
            Integer rozmiar = Integer.parseInt(UIHelper.getComboText(czcionka).replaceAll("px", ""));
            emailText.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
            emailText.setFont(new Font(emailText.getFont().getFamily(), Font.PLAIN, rozmiar));
        }
    }

    public void init(Pracodawca pracodawca) {
        this.obecnyPracodawca = pracodawca;
        zalaczonePliki = new HashMap<>();
        zalaczoneButtony = new ArrayList<>();
        this.nazwaLabel.setText(String.format("Email do: %s (%s)", pracodawca.getNazwa(), pracodawca.getEmail()));
        this.tytulText.setText(String.format("Informacja o brakach w dokumentacji BHP firmy %s",
                obecnyPracodawca.getNazwa()));
        String finalText = emailGenerator.generujEmail(pracodawca, tytulText.getText());

        this.emailText.setText(finalText);

        czcionka.setSelectedItem("12px");
    }

    private BodyPart createBodyPartFromFile(String filePath) throws MessagingException {
        BodyPart bodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource(filePath);
        bodyPart.setDataHandler(new DataHandler(fds));
        bodyPart.setDescription("attachment");
        bodyPart.setFileName(FilenameUtils.getName(filePath));
        return bodyPart;
    }
}
