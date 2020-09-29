package com.magnit.magnitapp.config;

public interface DbConfig {
    String getUrl();

    void setUrl(String url);

    String getUser();

    void setUser(String user);

    String getPassword();

    void setPassword(String password);
}
