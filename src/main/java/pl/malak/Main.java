package pl.malak;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main {

    public static void main(String[] args) throws Exception {
        // load context
        new ClassPathXmlApplicationContext("spring/context.xml");
    }
}
