package com.magnit.magnitapp.config;

public interface AppConfig {
    String ROOT_PATH = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    String getPrepareTestTableFilePath();

    void setPrepareTestTableFilePath(String prepareTestTableFilePath);

    String getCreateTestProcedureFilePath();

    void setCreateTestProcedureFilePath(String createTestProcedureFilePath);

    String getSelectTestFilePath();

    void setSelectTestFilePath(String selectTestFilePath);

    String getDbPropertiesPath();

    void setDbPropertiesPath(String dbPropertiesPath);

    String getEntriesToXmlFilePath();

    void setEntriesToXmlFilePath(String entriesToXmlFilePath);
}
