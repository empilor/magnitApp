package com.magnit.magnitapp.xml;

import com.magnit.magnitapp.model.Entries;
import com.magnit.magnitapp.model.Entry;
import com.magnit.magnitapp.model.ObjectFactory;
import com.magnit.magnitapp.xml.jaxb.JaxbMarshallerFactory;
import com.magnit.magnitapp.xml.jaxb.JaxbMarshallerFactoryImpl;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class EntriesXmlProcessor {
    private final JaxbMarshallerFactory jaxbMarshallerFactory = new JaxbMarshallerFactoryImpl();
    private ObjectFactory factory = new ObjectFactory();

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
            File file = new File("src/main/resources/xml/1.xml");
            jaxbMarshaller.marshal(entries, file);
        } catch (JAXBException ex) {
            System.out.println("Error converting to xml: " + ex.getMessage());
            throw ex;
        }
    }
}
