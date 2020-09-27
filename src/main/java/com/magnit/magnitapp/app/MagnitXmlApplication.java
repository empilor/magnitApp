package com.magnit.magnitapp.app;

import com.magnit.magnitapp.jdbc.JdbcUtilsImpl;
import com.magnit.magnitapp.jdbc.JdbcUtils;
import com.magnit.magnitapp.xml.EntriesXmlProcessor;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * This is the main Magnit Application
 */
public class MagnitXmlApplication {

    private JdbcUtils jdbcUtils;
    private EntriesXmlProcessor processor;

    public void processEntries() throws SQLException, JAXBException, IOException {
        List<Integer> entriesList = getJdbcUtils().getEntriesList();
        getProcessor().storeEntriesXml(entriesList);
    }

    public JdbcUtils getJdbcUtils() {
        if (jdbcUtils == null) {
            return new JdbcUtilsImpl();
        }
        return jdbcUtils;
    }

    public void setJdbcUtils(JdbcUtils jdbcUtils) {
        this.jdbcUtils = jdbcUtils;
    }

    public EntriesXmlProcessor getProcessor() {
        if (processor == null) {
            return new EntriesXmlProcessor();
        }
        return processor;
    }

    public void setProcessor(EntriesXmlProcessor processor) {
        this.processor = processor;
    }
}
