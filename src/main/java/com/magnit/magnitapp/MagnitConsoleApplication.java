package com.magnit.magnitapp;

import com.magnit.magnitapp.app.MagnitXmlApplication;
import com.magnit.magnitapp.jdbc.JdbcUtilsImpl;
import com.magnit.magnitapp.jdbc.JdbcUtils;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class MagnitConsoleApplication {
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Console application has been started");
            MagnitXmlApplication app = new MagnitXmlApplication();
            app.processEntries();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        System.out.println("\nFinished the application");
    }
}
