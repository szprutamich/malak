package pl.malak.panels;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import pl.malak.DateLabelFormatter;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Properties;

/**
 * @author Michał Szpruta - szprutamich@gmail.com
 */
@org.springframework.stereotype.Component
public class PracodawcaPanel extends JPanel {

    private Boolean teczka;
    private String teczkaUwagi;
    private Boolean ocena;
    private String ocenaUwagi;
    private Boolean szkoleniaOkresowe;
    private String szkoleniaOkresoweUwagi;
    private Boolean szkoleniaPracodawcy;
    private Date szkoleniaPracodawcyData;
    private String szkoleniaPracodawcyUwagi;
    private Boolean odziezowka;
    private String odziezowkaUwagi;

    public PracodawcaPanel() {
        super();
        int margin = 10;
        int panelWidth = 660;
        int panelHeight = 500;
        setLayout(null);
        setBounds(margin, margin, panelWidth, panelHeight);

//        setBackground(Color.PINK);

        int x1 = 30;
        int y1 = 100;
        int y = 30;
        int width = 200;
        int x2 = x1 + width;
        int x3 = x2 + width;

        JLabel nazwaLabel = new JLabel("Pracodawca:");
        JLabel uwagiLabel = new JLabel("Uwagi:");
        JLabel dataLabel = new JLabel("Do kiedy:");
        JTextField nazwa = new JTextField("4HOUSE4 SP.Z O.O.");
        nazwa.setHorizontalAlignment(JTextField.CENTER);
        JCheckBox teczka = new JCheckBox("Teczka ogólna");
        JCheckBox ocena = new JCheckBox("Ocena ryzyka");
        JCheckBox szkoleniaOkresowe = new JCheckBox("Szkolenia okresowe");
        JCheckBox szkolenia = new JCheckBox("Szkolenia pracodawcy");
        JCheckBox odziezowka = new JCheckBox("Odzieżówka");

        JTextField teczkaUwagi = new JTextField();
        JTextField ocenaUwagi = new JTextField();
        JTextField szkoleniaOkresoweUwagi = new JTextField();
        JTextField szkoleniaUwagi = new JTextField();
        JTextField odziezowkaUwagi = new JTextField();

        JButton zapisz = new JButton("Zapisz");

        nazwaLabel.setBounds(x1, 20, width, 30);
        nazwaLabel.setFont(new Font(nazwaLabel.getFont().getFamily(), Font.PLAIN, 25));
        nazwa.setBounds(x2, 20, panelWidth - width - x1 * 2, 30);

        uwagiLabel.setBounds(x2, y1 - y, width, y);
        dataLabel.setBounds(x3, y1 - y, width, y);

        teczka.setBounds(x1, y1, width, y);
        teczkaUwagi.setBounds(x2, y1, width, y);
        y1 = y1 + y;
        ocena.setBounds(x1, y1, width, y);
        ocenaUwagi.setBounds(x2, y1, width, y);
        y1 = y1 + y;
        szkoleniaOkresowe.setBounds(x1, y1, width, y);
        szkoleniaOkresoweUwagi.setBounds(x2, y1, width, y);
        y1 = y1 + y;
        szkolenia.setBounds(x1, y1, width, y);
        szkoleniaUwagi.setBounds(x2, y1, width, y);
        JDatePickerImpl datePicker = getJDatePicker();
        datePicker.setBounds(x3, y1, width, y);
        y1 = y1 + y;
        odziezowka.setBounds(x1, y1, width, y);
        odziezowkaUwagi.setBounds(x2, y1, width, y);
        y1 = y1 + y + y;

        zapisz.setBounds(x1, y1, panelWidth - x1 * 2, y);

        add(nazwaLabel);
        add(uwagiLabel);
        add(dataLabel);
        add(nazwa);
        add(teczka);
        add(ocena);
        add(szkoleniaOkresowe);
        add(szkolenia);
        add(odziezowka);
        add(zapisz);
        add(datePicker);
        add(teczkaUwagi);
        add(ocenaUwagi);
        add(szkoleniaOkresoweUwagi);
        add(szkoleniaUwagi);
        add(odziezowkaUwagi);
    }

    private JDatePickerImpl getJDatePicker() {
        Properties properties = new Properties();
        properties.put("text.today", "Dziś");
        properties.put("text.month", "Miesiąc");
        properties.put("text.year", "Rok");
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.getJFormattedTextField().setHorizontalAlignment(JTextField.CENTER);

        return datePicker;
    }

//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2D = (Graphics2D) g;
//        BasicStroke stroke = new BasicStroke(3);
//        g2D.setColor(Color.black);
//        g2D.setStroke(stroke);
//        g2D.drawRect(0 , 0, 470, 470);
//        g2D.setColor(Color.GREEN);
//        g2D.fillOval(445, 5, 20, 20);
//    }
}
