package ru.geniusjohn.phonebook.domain;

import javax.xml.bind.Marshaller;
import java.io.OutputStream;

public interface XMLGenerator {

    void generate(OutputStream outputStream);
}
