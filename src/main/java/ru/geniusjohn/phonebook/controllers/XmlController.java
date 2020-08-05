package ru.geniusjohn.phonebook.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.geniusjohn.phonebook.domain.Group;
import ru.geniusjohn.phonebook.domain.Menu;
import ru.geniusjohn.phonebook.domain.Records;
import ru.geniusjohn.phonebook.repositories.GroupRepository;
import ru.geniusjohn.phonebook.repositories.RecordRepository;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class XmlController {

    private static final Logger logger = LoggerFactory.getLogger(XmlController.class);

    private RecordRepository recordRepositories;
    private GroupRepository groupRepository;
    private String baseUrl;

    @Autowired
    public void setRecordRepositories(RecordRepository recordRepositories) {
        this.recordRepositories = recordRepositories;
    }

    @Autowired
    public void setGroupRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Value("${ru.omc.webphonebook.base-url}")
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @GetMapping("/getMenuXml")
    public void getMenu(HttpServletResponse response, @RequestHeader Map<String, String> headers) throws JAXBException, IOException {
        logger.info("Header list:");
        headers.forEach((key, value) -> {
            logger.info(key + " = " + value);
        });
        Menu menu = new Menu();
        menu.setMenuItems(new ArrayList<>());
        menu.setSoftKeyItems(new ArrayList<>());
        for (Group group: groupRepository.findByOrderByOrderGroup()) {
            //@TODO надо бы разобраться с этими тремя строками....
            String url = String.format("%s/getGroupXml/%d", baseUrl, group.getId());
            menu.getMenuItems().add(group.mapToItemMenu(url));
            menu.getSoftKeyItems().add(group.mapToSoftKeyMenu(url));
        }
        OutputStream responseOutputStream = response.getOutputStream();
        JAXBContext context = JAXBContext.newInstance(Menu.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        mar.marshal(menu, responseOutputStream);
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
        response.addHeader("Content-Disposition", String.format("attachment; filename=group-%d.xml", group.getId()));
        response.getOutputStream().flush();
    }

}
