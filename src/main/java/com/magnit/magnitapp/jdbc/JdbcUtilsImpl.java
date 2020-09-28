package com.magnit.magnitapp.jdbc;

import com.magnit.magnitapp.config.AppConfig;
import com.magnit.magnitapp.config.DbConfig;
import com.magnit.magnitapp.config.DbConfigImpl;

import java.io.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class JdbcUtilsImpl implements JdbcUtils {
    private static final String FIELD = "FIELD";
    private static final String CALL_FILL_TEST_TABLE_PROCEDURE = "call fill_test_table( ? )";
    private AppConfig appConfig;
    private DbConfig dbConfig;
    private String url;
    private String user;
    private String password;

    @Override
    public void initProperties() {
        url = dbConfig.getUrl();
        user = dbConfig.getUser();
        password = dbConfig.getPassword();
    }

    @Override
    public void prepareTestTable(Connection conn) throws IOException, SQLException {
        String query = getQuery(appConfig.getPrepareTestTableFilePath());
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.execute();
        }
    }

    @Override
    public void insertTest(Connection conn, int n) throws IOException, SQLException {
        createTestProcedure(conn);
        try (CallableStatement statement = conn.prepareCall(CALL_FILL_TEST_TABLE_PROCEDURE)) {
            statement.setInt(1, n);
            statement.execute();
        }
    }

    @Override
    public void createTestProcedure(Connection conn) throws IOException, SQLException {
        String query = getQuery(appConfig.getCreateTestProcedureFilePath());
        try (CallableStatement statement = conn.prepareCall(query)) {
            statement.execute();
        }
    }

    @Override
    public List<Integer> getEntriesList(int n) throws SQLException, IOException {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            prepareTestTable(conn);
            insertTest(conn, n);
            String query = getQuery(appConfig.getSelectTestFilePath());
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.execute();
                ResultSet rs = statement.getResultSet();
                List<Integer> entriesList = new LinkedList<>();
                while (rs.next()) {
                    entriesList.add(rs.getInt(FIELD));
                }
                System.out.println("Retrieved values from DB: " + entriesList);
                return entriesList;
            }
        }
    }

    private String getQuery(String filePath) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
            sb.append(" ");
        }
        String query = sb.toString();
        return query;
    }

    @Override
    public AppConfig getAppConfig() {
        return appConfig;
    }

    @Override
    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    public DbConfig getDbConfig() {
        return dbConfig;
    }

    @Override
    public void setDbConfig(DbConfig dbConfig) {
        this.dbConfig = dbConfig;
    }
}
