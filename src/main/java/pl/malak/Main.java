package pl.malak;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class Main {

    public static void main(String[] args) throws Exception {
        // load context
        new ClassPathXmlApplicationContext("spring/context.xml");
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            System.err.println("Windows L&F failure");
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            } catch (Exception ex1) {
                System.err.println("GTK+ L&F failure");
            }
        }
    }
}
