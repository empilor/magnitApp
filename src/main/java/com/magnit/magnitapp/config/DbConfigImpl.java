package com.magnit.magnitapp.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DbConfigImpl implements DbConfig {
    private static final String ROOT_PATH = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    public static final String DB_URL_PROP = "db.url";
    public static final String DB_USER_PROP = "db.user";
    public static final String DB_PASSWORD_PROP = "db.password";
    private AppConfig config;
    private String url;
    private String user;
    private String password;

    public DbConfigImpl(AppConfig config) {
        this.config = config;
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(config.getDbPropertiesPath()));
            url = props.getProperty(DB_URL_PROP);
            user = props.getProperty(DB_USER_PROP);
            password = props.getProperty(DB_PASSWORD_PROP);
        } catch (IOException ex) {
            System.out.println("Error loading properties from file: " + config.getDbPropertiesPath() + ": " + ex);
        }
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}