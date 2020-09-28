package com.magnit.magnitapp.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppConfigImpl implements AppConfig {
    private static final String PREPARE_TEST_TABLE_FILE_PATH_PROP = "prepare.test.table.file";
    private static final String CREATE_TEST_PROCEDURE_FILE_PATH_PROP = "create.test.procedure.file";
    private static final String SELECT_TEST_FILE_PATH_PROP = "select.test.file";
    private static final String ENTRIES_TO_XML_FILE_PATH_PROP = "entries.to.xml.file";
    public static final String DB_FILE_PATH_PROP = "db.file";
    private String appPropertiesPath = ROOT_PATH + "application.properties";
    private String dbPropertiesPath;
    private String prepareTestTableFilePath;
    private String createTestProcedureFilePath;
    private String selectTestFilePath;
    private String entriesToXmlFilePath;

    public AppConfigImpl() {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream(appPropertiesPath));
            dbPropertiesPath = ROOT_PATH + props.getProperty(DB_FILE_PATH_PROP);
            prepareTestTableFilePath = ROOT_PATH + props.getProperty(PREPARE_TEST_TABLE_FILE_PATH_PROP);
            createTestProcedureFilePath = ROOT_PATH + props.getProperty(CREATE_TEST_PROCEDURE_FILE_PATH_PROP);
            selectTestFilePath = ROOT_PATH + props.getProperty(SELECT_TEST_FILE_PATH_PROP);
            entriesToXmlFilePath = props.getProperty(ENTRIES_TO_XML_FILE_PATH_PROP);
        } catch (IOException ex) {
            System.out.println("Error loading properties from file: " + appPropertiesPath + ": " + ex);
        }
    }

    @Override
    public String getPrepareTestTableFilePath() {
        return prepareTestTableFilePath;
    }

    @Override
    public void setPrepareTestTableFilePath(String prepareTestTableFilePath) {
        this.prepareTestTableFilePath = prepareTestTableFilePath;
    }

    @Override
    public String getCreateTestProcedureFilePath() {
        return createTestProcedureFilePath;
    }

    @Override
    public void setCreateTestProcedureFilePath(String createTestProcedureFilePath) {
        this.createTestProcedureFilePath = createTestProcedureFilePath;
    }

    @Override
    public String getSelectTestFilePath() {
        return selectTestFilePath;
    }

    @Override
    public void setSelectTestFilePath(String selectTestFilePath) {
        this.selectTestFilePath = selectTestFilePath;
    }

    @Override
    public String getDbPropertiesPath() {
        return dbPropertiesPath;
    }

    @Override
    public void setDbPropertiesPath(String dbPropertiesPath) {
        this.dbPropertiesPath = dbPropertiesPath;
    }

    @Override
    public String getEntriesToXmlFilePath() {
        return entriesToXmlFilePath;
    }

    @Override
    public void setEntriesToXmlFilePath(String entriesToXmlFilePath) {
        this.entriesToXmlFilePath = entriesToXmlFilePath;
    }
}
