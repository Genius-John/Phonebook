package ru.geniusjohn.phonebook.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.geniusjohn.phonebook.domain.Group;
import ru.geniusjohn.phonebook.domain.PhoneInfo;
import ru.geniusjohn.phonebook.repositories.RecordRepository;
import ru.geniusjohn.phonebook.xml.XmlGeneratorFactory;
import ru.geniusjohn.phonebook.xml.generator.XmlGroupGenerator;
import ru.geniusjohn.phonebook.xml.generator.XmlMenuGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
public class XmlController {

    private static final Logger logger = LoggerFactory.getLogger(XmlController.class);

    private RecordRepository recordRepository;
    private XmlGeneratorFactory xmlGeneratorFactory;

    @Autowired
    public void setXmlGeneratorFactory(XmlGeneratorFactory xmlGeneratorFactory) {
        this.xmlGeneratorFactory = xmlGeneratorFactory;
    }

    @Autowired
    public void setRecordRepositories(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    private PhoneInfo getPhoneFromHeader(Map<String, String> headers, HttpServletRequest request) {
        PhoneInfo phoneInfo = new PhoneInfo();
        Date date = new Date();
        //сведения о клиенте menuXml
        String[] userAgent = headers.get("user-agent").split(" ");
        if (userAgent[3].matches("([a-fA-F0-9]{2}:){5}[a-fA-F0-9]{2}")) {
            phoneInfo.setMacAddress(userAgent[3]);
            phoneInfo.setModelPhone(userAgent[1]);
            phoneInfo.setVendor(userAgent[0]);
            System.out.println("-----------");
            System.out.println(date);
            System.out.println("Vendor: " + phoneInfo.getVendor());
            System.out.println("Model: " + phoneInfo.getModelPhone());
            System.out.println("MAC-address: " + phoneInfo.getMacAddress());
            System.out.println("IP адрес: " + request.getRemoteAddr());
            System.out.println("-----------");
        } else {
            System.out.println("-----------");
            System.out.println(date);
            System.out.println("IP адрес: " + request.getRemoteAddr());
            System.out.println("Модель телефона не опознана");
            System.out.println("-----------");
        }
        return phoneInfo;
    }

    @GetMapping("/phonebook/getMenuXml")
    public void getMenu(HttpServletResponse response, @RequestHeader Map<String, String> headers,  HttpServletRequest request) throws JAXBException, IOException {
        //нужно ли?
        logger.info("Header list:");
        headers.forEach((key, value) -> {
            logger.info(key + " = " + value);
        });
        PhoneInfo phoneInfo = getPhoneFromHeader(headers, request);
        XmlMenuGenerator generator = xmlGeneratorFactory.getMenuGenerator(phoneInfo);
        generator.generate(response.getOutputStream());
        response.setContentType("application/xml");
        response.addHeader("Content-Disposition", "attachment; filename=menu.xml");
        response.getOutputStream().flush();
    }

    @GetMapping ("/phonebook/getGroupXml/{groupId}")
    public void marshal (HttpServletResponse response,
                         @PathVariable("groupId") Group group,
                         @RequestHeader Map<String, String> headers,
                         HttpServletRequest request) throws JAXBException, IOException {
        PhoneInfo phoneInfo = getPhoneFromHeader(headers, request);
        XmlGroupGenerator generator = xmlGeneratorFactory.getGroupGenerator(phoneInfo); // EmptyXMLGenerator, YealinkXMLGenerator .... todo
        generator.generate(response.getOutputStream(), group);
        response.setContentType("application/xml");
        response.addHeader("Content-Disposition", String.format("attachment; filename=group-%d.xml", group.getId()));
        response.getOutputStream().flush();
    }

}
