package com.magnit.magnitapp.jdbc;

import com.magnit.magnitapp.config.AppConfig;
import com.magnit.magnitapp.config.DbConfig;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface JdbcUtils {
    void initProperties();

    void prepareTestTable(Connection conn) throws IOException, SQLException;

    void insertTest(Connection conn, int n) throws IOException, SQLException;

    void createTestProcedure(Connection conn) throws IOException, SQLException;

    List<Integer> getEntriesList(int n) throws SQLException, IOException;

    AppConfig getAppConfig();

    void setAppConfig(AppConfig appConfig);

    DbConfig getDbConfig();

    void setDbConfig(DbConfig dbConfig);
}
