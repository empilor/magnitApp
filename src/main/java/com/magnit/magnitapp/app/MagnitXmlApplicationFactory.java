package com.magnit.magnitapp.app;

import com.magnit.magnitapp.config.AppConfig;
import com.magnit.magnitapp.config.AppConfigImpl;
import com.magnit.magnitapp.config.DbConfig;
import com.magnit.magnitapp.config.DbConfigImpl;
import com.magnit.magnitapp.jdbc.JdbcUtils;
import com.magnit.magnitapp.jdbc.JdbcUtilsImpl;
import com.magnit.magnitapp.xml.EntriesXmlProcessor;
import com.magnit.magnitapp.xml.EntriesXmlProcessorImpl;
import com.magnit.magnitapp.xml.jaxb.JaxbMarshallerFactory;
import com.magnit.magnitapp.xml.jaxb.JaxbMarshallerFactoryImpl;

/**
 * Default factory for creating MagnitXmlApplication
 */
public class MagnitXmlApplicationFactory {

    public static MagnitXmlApplication buildMagnitXmlApplication() {
        JdbcUtils utils = new JdbcUtilsImpl();
        AppConfig appConfig = new AppConfigImpl();
        DbConfig dbConfig = new DbConfigImpl(appConfig);
        utils.setAppConfig(appConfig);
        utils.setDbConfig(dbConfig);
        utils.initProperties();

        EntriesXmlProcessor xmlProcessor = new EntriesXmlProcessorImpl();
        JaxbMarshallerFactory jaxbMarshallerFactory = new JaxbMarshallerFactoryImpl();
        xmlProcessor.setJaxbMarshallerFactory(jaxbMarshallerFactory);
        xmlProcessor.setAppConfig(appConfig);

        MagnitXmlApplication app = new MagnitXmlApplication();
        app.setJdbcUtils(utils);
        app.setProcessor(xmlProcessor);
        return app;
    }
}
