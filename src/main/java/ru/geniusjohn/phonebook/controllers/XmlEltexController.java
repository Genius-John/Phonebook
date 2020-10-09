package ru.geniusjohn.phonebook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.geniusjohn.phonebook.domain.EltexMenu;
import ru.geniusjohn.phonebook.domain.Group;
import ru.geniusjohn.phonebook.domain.GroupEltex;
import ru.geniusjohn.phonebook.domain.Records;
import ru.geniusjohn.phonebook.repositories.GroupRepository;
import ru.geniusjohn.phonebook.repositories.RecordRepository;
import sun.misc.Contended;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class XmlEltexController {

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

    @GetMapping("/phonebook/getXmlEltex")
    public void getEltex(HttpServletResponse response, @RequestHeader Map<String, String> headers, HttpServletRequest request) throws JAXBException, IOException {
        Date date = new Date();
        String[] userAgent = headers.get("user-agent").split(" ");
        if (userAgent[3].matches("([a-fA-F0-9]{2}:){5}[a-fA-F0-9]{2}")) {
            String vendor = userAgent[0];
            String model = userAgent[1];
            String macAddress = userAgent[3];
            System.out.println("-----------");
            System.out.println(date);
            System.out.println("Vendor: " + vendor);
            System.out.println("Model: " + model);
            System.out.println("MAC-address: " + macAddress);
            System.out.println("IP адрес: " + request.getRemoteAddr());
            System.out.println("-----------");
        } else {
            System.out.println("-----------");
            System.out.println(date);
            System.out.println("IP адрес: " + request.getRemoteAddr());
            System.out.println("Модель телефона не опознана");
            System.out.println("-----------");
        }

        EltexMenu eltexMenu = new EltexMenu();
        eltexMenu.setRecords(new ArrayList<>());
        eltexMenu.setRecords(recordRepository.findAll());
        eltexMenu.setGroupEltex(new ArrayList<>());
        eltexMenu.setGroupEltex(groupRepository.findAll());

        OutputStream responseOutputStream = response.getOutputStream();
        JAXBContext context = JAXBContext.newInstance(EltexMenu.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        mar.marshal(eltexMenu, responseOutputStream);
        response.setContentType("application/xml");
        response.addHeader("Content-Disposition", String.format("attachment; filename=eltexMenu.xml"));
        response.getOutputStream().flush();
    }

}
