package com.magnit.magnitapp.xml;

import com.magnit.magnitapp.config.AppConfig;
import com.magnit.magnitapp.xml.jaxb.JaxbMarshallerFactory;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface EntriesXmlProcessor {
    void storeEntriesXml(List<Integer> entriesList) throws JAXBException;

    JaxbMarshallerFactory getJaxbMarshallerFactory();

    void setJaxbMarshallerFactory(JaxbMarshallerFactory jaxbMarshallerFactory);

    AppConfig getAppConfig();

    void setAppConfig(AppConfig appConfig);
}
