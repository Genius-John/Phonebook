package ru.geniusjohn.phonebook.xml.generator;

import org.springframework.stereotype.Component;
import ru.geniusjohn.phonebook.domain.Group;
import ru.geniusjohn.phonebook.repositories.RecordRepository;
import ru.geniusjohn.phonebook.xml.element.EltexMenu;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.OutputStream;

@Component
public class XmlEltexGenerator implements XmlGenerator {

    private RecordRepository recordRepository;


    @Override
    public void generate(OutputStream outputStream) throws JAXBException {

        EltexMenu eltexMenu = new EltexMenu();
//        eltexMenu.setRecords(recordRepository.findAll());
        JAXBContext context = JAXBContext.newInstance(EltexMenu.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        mar.marshal(eltexMenu, outputStream);
    }

    @Override
    public void generate(OutputStream outputStream, Group group) {

    }
}
