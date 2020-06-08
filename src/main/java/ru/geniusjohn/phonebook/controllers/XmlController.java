package ru.geniusjohn.phonebook.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.geniusjohn.phonebook.domain.Group;
import ru.geniusjohn.phonebook.domain.Menu;
import ru.geniusjohn.phonebook.domain.Records;
import ru.geniusjohn.phonebook.repositories.GroupRepository;
import ru.geniusjohn.phonebook.repositories.RecordRepositories;

import javax.servlet.http.HttpServletRequest;
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
    private String[] userAgent;
    private String macAddress;
    private String vendor;
    private String model;
    private RecordRepositories recordRepositories;
    private GroupRepository groupRepository;
    private String baseUrl;

    @Autowired
    public void setRecordRepositories(RecordRepositories recordRepositories) {
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
    public void getMenu(HttpServletResponse response, @RequestHeader Map<String, String> headers, HttpServletRequest request) throws JAXBException, IOException {
        logger.info("Header list:");
        headers.forEach((key, value) -> {
            logger.info(key + " = " + value);
        });
//        Получение IP
        String remoteAddr = "";
        if (remoteAddr != null) {
            remoteAddr = request.getHeader("X-FORWARDER-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

//данные от телефона
        userAgent = headers.get("user-agent").split(" ");
        if (userAgent[3].matches("([a-fA-F0-9]{2}:){5}[a-fA-F0-9]{2}")) {
            vendor = userAgent[0];
            model = userAgent[1];
            macAddress = userAgent[3];
            System.out.println("___________\n" + "Vendor: " + vendor);
            System.out.println("Model: " + model);
            System.out.println("MAC-address: " + macAddress);
            System.out.println("IP адрес: " + remoteAddr + "\n___________");
        } else {
            System.out.println("___________\n" + "Модель телефона не опознана" + "\n___________");
        }


        Menu menu = new Menu();
        menu.setMenuItems(new ArrayList<>());
        menu.setSoftKeyItems(new ArrayList<>());
        for (Group group: groupRepository.findByOrderByOrderGroup()) {
            //@TODO надо бы разобраться с этими двумя строками....
            menu.getMenuItems().add(group.mapToItemMenu(String.format("%s/getGroupXml/%d", baseUrl, group.getId())));
            menu.getSoftKeyItems().add(group.mapToSoftKeyMenu(String.format("%s/getGroupXml/%d", baseUrl, group.getId())));
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
