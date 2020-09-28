package com.magnit.magnitapp.app;

import com.magnit.magnitapp.jdbc.JdbcUtilsImpl;
import com.magnit.magnitapp.jdbc.JdbcUtils;
import com.magnit.magnitapp.xml.EntriesXmlProcessor;
import com.magnit.magnitapp.xml.EntriesXmlProcessorImpl;

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

    public void processEntries(int n) throws SQLException, JAXBException, IOException {
        if(n <= 0) {
            throw new IllegalArgumentException("N value can't be less than 0");
        }
        //get entries from TEST table
        List<Integer> entriesList = jdbcUtils.getEntriesList(n);
        //process List of entries to xml file
        processor.storeEntriesXml(entriesList);
    }

    public JdbcUtils getJdbcUtils() {
        return jdbcUtils;
    }

    public void setJdbcUtils(JdbcUtils jdbcUtils) {
        this.jdbcUtils = jdbcUtils;
    }

    public EntriesXmlProcessor getProcessor() {
        return processor;
    }

    public void setProcessor(EntriesXmlProcessor processor) {
        this.processor = processor;
    }
}
