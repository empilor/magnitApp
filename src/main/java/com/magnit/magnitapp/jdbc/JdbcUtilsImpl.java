package com.magnit.magnitapp.jdbc;

import java.io.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class JdbcUtilsImpl implements JdbcUtils {
    public static final String FIELD = "FIELD";
    private static final String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final String APP_PROPERTIES_PATH = rootPath + "application.properties";
    private String url;
    private String user;
    private String password;

    public JdbcUtilsImpl() {
        initProperties();
    }

    private void initProperties() {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(APP_PROPERTIES_PATH));
            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");
        } catch (IOException ex) {
            System.out.println("Error loading jdbc parameters from file properties: " + ex);
        }
    }

    @Override
    public void prepareTestTable(Connection conn) throws IOException, SQLException {
        String query = getQuery(rootPath + "prepareTestTable.txt");
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.execute();
        }
    }

    @Override
    public void insertTest(Connection conn, int n) throws IOException, SQLException {
        createTestProcedure(conn);
        try (CallableStatement statement = conn.prepareCall("call fill_test_table( ? )")) {
            statement.setInt(1, n);
            statement.execute();
        }
    }

    private void createTestProcedure(Connection conn) throws IOException, SQLException {
        String query = getQuery(rootPath + "createTestProcedure.txt");
        try (CallableStatement statement = conn.prepareCall(query)) {
            statement.execute();
        }
    }

    @Override
    public List<Integer> getEntriesList() throws SQLException, IOException {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            prepareTestTable(conn);
            insertTest(conn, 20);
            String query = getQuery(rootPath + "selectTest.txt");
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.execute();
                ResultSet rs = statement.getResultSet();
                List<Integer> entriesList = new LinkedList<>();
                while (rs.next()) {
                    System.out.print(rs.getInt(FIELD) + "#");
                    entriesList.add(rs.getInt(FIELD));
                }
                System.out.println("Retrieved values from DB: " + entriesList);
                return entriesList;
            }
        }
    }

    @Override
    public String getQuery(String filePath) throws IOException {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
