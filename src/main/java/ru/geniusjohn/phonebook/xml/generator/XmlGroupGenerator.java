package ru.geniusjohn.phonebook.xml.generator;

import ru.geniusjohn.phonebook.domain.Group;

import javax.xml.bind.JAXBException;
import java.io.OutputStream;

public interface XmlGroupGenerator {
    void generate(OutputStream outputStream, Group group) throws JAXBException;
}
