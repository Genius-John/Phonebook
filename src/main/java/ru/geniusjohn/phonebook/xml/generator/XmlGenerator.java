package ru.geniusjohn.phonebook.xml.generator;

import ru.geniusjohn.phonebook.domain.Group;
import ru.geniusjohn.phonebook.domain.PhoneInfo;

import javax.xml.bind.JAXBException;
import java.io.OutputStream;

public interface XmlGenerator {
    void generate(OutputStream outputStream) throws JAXBException;

    void generate(OutputStream outputStream, Group group) throws JAXBException;

}
