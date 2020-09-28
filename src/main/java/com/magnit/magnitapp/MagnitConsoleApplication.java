package com.magnit.magnitapp;

import com.magnit.magnitapp.app.MagnitXmlApplication;
import com.magnit.magnitapp.app.MagnitXmlApplicationFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MagnitConsoleApplication {
    public static void main(String[] args) {
        try {
            System.out.println("Console application has been started. Please, enter N value (integer greater than 0)");
            Scanner scan = new Scanner(System.in);
            int n = scan.nextInt();

            MagnitXmlApplication app = MagnitXmlApplicationFactory.buildMagnitXmlApplication();
            app.processEntries(n);
        } catch (InputMismatchException ex) {
            System.out.println("N should be an integer greater than 0");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        System.out.println("\nFinished the application");
    }

}
