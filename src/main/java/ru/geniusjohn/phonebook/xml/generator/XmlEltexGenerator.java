package ru.geniusjohn.phonebook.xml.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geniusjohn.phonebook.domain.Group;
import ru.geniusjohn.phonebook.domain.Record;
import ru.geniusjohn.phonebook.repositories.GroupRepository;
import ru.geniusjohn.phonebook.repositories.RecordRepository;
import ru.geniusjohn.phonebook.xml.element.EltexGroup;
import ru.geniusjohn.phonebook.xml.element.EltexMenu;
import ru.geniusjohn.phonebook.xml.element.EltexRecord;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class XmlEltexGenerator implements XmlMenuGenerator {

    private RecordRepository recordRepository;
    private GroupRepository groupRepository;

    @Autowired
    public void setRecordRepository(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Autowired
    public void setGroupRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public void generate(OutputStream outputStream) throws JAXBException {
        EltexMenu eltexMenu = new EltexMenu();
        List<EltexRecord> eltexRecords = new ArrayList<>();
        for (Record record : recordRepository.findAll()) {
            eltexRecords.add(new EltexRecord(record));
        }
        eltexMenu.setRecords(eltexRecords);
        List<EltexGroup> eltexGroupList = new ArrayList<>();
        for (Group group : groupRepository.findAll()) {
            eltexGroupList.add(new EltexGroup(group));
        }
        eltexMenu.setEltexGroups(eltexGroupList);
        JAXBContext context = JAXBContext.newInstance(EltexMenu.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        mar.marshal(eltexMenu, outputStream);
    }
}
