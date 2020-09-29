package com.magnit.magnitapp;

import com.magnit.magnitapp.app.MagnitXmlApplication;
import com.magnit.magnitapp.app.MagnitXmlApplicationFactory;
import com.magnit.magnitapp.app.MagnitXmlApplicationFactoryImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MagnitConsoleApplication {
    public static void main(String[] args) {
        try {
            System.out.println("Console application has been started. Please, enter N value (integer greater than 0)");
            Scanner scan = new Scanner(System.in);
            int n = scan.nextInt();
            long startTime = System.currentTimeMillis();
            MagnitXmlApplicationFactory factory = new MagnitXmlApplicationFactoryImpl();
            MagnitXmlApplication app = factory.buildMagnitXmlApplication();
            app.processEntries(n);
            long endTime = System.currentTimeMillis();
            long resultTimeMinutes = (endTime - startTime) / 1000;
            System.out.println("\n>>>Application has been completed at " + resultTimeMinutes + " seconds");
        } catch (InputMismatchException ex) {
            System.out.println("N should be an integer greater than 0");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        System.out.println("\nFinished the application");
    }

}
