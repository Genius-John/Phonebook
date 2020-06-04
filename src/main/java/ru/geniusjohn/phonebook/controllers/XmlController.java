package ru.geniusjohn.phonebook.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.geniusjohn.phonebook.domain.Group;
import ru.geniusjohn.phonebook.domain.Groups;
import ru.geniusjohn.phonebook.domain.Records;
import ru.geniusjohn.phonebook.repositories.GroupRepository;
import ru.geniusjohn.phonebook.repositories.RecordRepositories;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

@Controller
public class XmlController {

    private static final Logger logger = LoggerFactory.getLogger(XmlController.class);

    private RecordRepositories recordRepositories;

    private GroupRepository groupRepository;

    @Autowired
    public void setRecordRepositories(RecordRepositories recordRepositories) {
        this.recordRepositories = recordRepositories;
    }

    @Autowired
    public void setGroupRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @GetMapping("/getMenuXml")
    public void getMenu(HttpServletResponse response) throws JAXBException, IOException {

        Groups groups = new Groups();
        groups.setGroups(new ArrayList<>());
        groups.setGroups(groupRepository.findAll());
//        for (Group group: groupRepository.findAllOrderByOrder()) {
//
//        }

        response.setContentType("application/xml");
        response.addHeader("Content-Disposition", "attachment; filename=menu.xml");
        response.getOutputStream().flush();
    }

    @GetMapping ("/getGroupXml/{groupId}")
    public void marshal (HttpServletResponse response, @PathVariable("groupId") Group group) throws JAXBException, IOException {

        Records records = new Records();
        records.setRecords(new ArrayList<>());
        records.setRecords(recordRepositories.findAllByGroup(group));

        OutputStream responseOutputStream = response.getOutputStream();

        JAXBContext context = JAXBContext.newInstance(Records.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        mar.marshal(records, responseOutputStream);

        response.setContentType("application/xml");
        response.addHeader("Content-Disposition", String.format("attachment; filename=%s.xml", group.getGroupName()));
        response.getOutputStream().flush();
    }

}
