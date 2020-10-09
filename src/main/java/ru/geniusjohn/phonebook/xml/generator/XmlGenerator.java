package ru.geniusjohn.phonebook.xml.generator;

import javax.xml.bind.JAXBException;
import java.io.OutputStream;

public interface XmlGenerator {
    void generate(OutputStream outputStream) throws JAXBException;

}
