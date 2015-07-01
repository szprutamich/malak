package pl.malak;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        File file = new File("WZÓR.xlsm");
        Report report = new Report();
        report.create(file);
        report.printEmployersWithEmployees();
    }
}
