package com.magnit.magnitapp.jdbc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface JdbcUtils {
    void prepareTestTable(Connection conn) throws IOException, SQLException;
    void insertTest(Connection conn, int n) throws IOException, SQLException;
    List<Integer> getEntriesList() throws SQLException, IOException;
    String getQuery(String filePath) throws IOException;
}
