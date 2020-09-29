package com.magnit.magnitapp.xml.jaxb;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public interface JaxbMarshallerFactory {
    <T> Marshaller getJaxbMarshaller(Class<T> clazz) throws JAXBException;
}
