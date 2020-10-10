package ru.geniusjohn.phonebook.xml.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geniusjohn.phonebook.domain.Group;
import ru.geniusjohn.phonebook.repositories.RecordRepository;
import ru.geniusjohn.phonebook.xml.element.YealinkRecords;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.OutputStream;

@Component
public class XmlYealinkGroupGenerator implements XmlGenerator {


    private RecordRepository recordRepository;

    @Autowired
    public void setRecordRepository(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }


    @Override
    public void generate(OutputStream outputStream) {


//        mar.marshal(records, System.out); вывод в консоль
    }

    @Override
    public void generate(OutputStream outputStream, Group group) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(YealinkRecords.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        YealinkRecords records = new YealinkRecords();
        records.setRecords(recordRepository.findAllByGroup(group)); //Где группа....
        mar.marshal(records, outputStream);
    }
}
