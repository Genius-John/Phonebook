package ru.geniusjohn.phonebook.xml.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geniusjohn.phonebook.domain.Group;
import ru.geniusjohn.phonebook.domain.Record;
import ru.geniusjohn.phonebook.repositories.RecordRepository;
import ru.geniusjohn.phonebook.xml.element.YealinkRecord;
import ru.geniusjohn.phonebook.xml.element.YealinkRecords;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class XmlYealinkGroupGenerator implements XmlGroupGenerator {


    private RecordRepository recordRepository;

    @Autowired
    public void setRecordRepository(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public void generate(OutputStream outputStream, Group group) throws JAXBException {
        YealinkRecords records = new YealinkRecords();
        List<YealinkRecord> yealinkRecords = new ArrayList<>();
        for (Record record : recordRepository.findAllByGroup(group)) {
            yealinkRecords.add(new YealinkRecord(record));
        }
        records.setRecords(yealinkRecords);
        JAXBContext context = JAXBContext.newInstance(YealinkRecords.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        marshaller.marshal(records, outputStream);
    }
}
