package com.magnit.magnitapp.xml;

import com.magnit.magnitapp.config.AppConfig;
import com.magnit.magnitapp.model.Entries;
import com.magnit.magnitapp.model.Entry;
import com.magnit.magnitapp.model.ObjectFactory;
import com.magnit.magnitapp.xml.jaxb.JaxbMarshallerFactory;
import com.magnit.magnitapp.xml.jaxb.JaxbMarshallerFactoryImpl;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class EntriesXmlProcessorImpl implements EntriesXmlProcessor {
    private JaxbMarshallerFactory jaxbMarshallerFactory;
    private AppConfig appConfig;
    private ObjectFactory factory = new ObjectFactory();

    @Override
    public void storeEntriesXml(List<Integer> entriesList) throws JAXBException {
        try {
            Entries entries = factory.createEntries();
            entriesList.stream()
                    .forEach(value -> {
                        Entry entry = factory.createEntry();
                        entry.setField(value);
                        entries.getEntry().add(entry);
                    });

            Marshaller jaxbMarshaller = jaxbMarshallerFactory.getJaxbMarshaller(Entries.class);
            File file = new File(appConfig.getEntriesToXmlFilePath());
            jaxbMarshaller.marshal(entries, file);
        } catch (JAXBException ex) {
            System.out.println("Error converting to xml: " + ex.getMessage());
            throw ex;
        }
    }

    @Override
    public JaxbMarshallerFactory getJaxbMarshallerFactory() {
        return jaxbMarshallerFactory;
    }

    @Override
    public void setJaxbMarshallerFactory(JaxbMarshallerFactory jaxbMarshallerFactory) {
        this.jaxbMarshallerFactory = jaxbMarshallerFactory;
    }

    @Override
    public AppConfig getAppConfig() {
        return appConfig;
    }

    @Override
    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }
}
