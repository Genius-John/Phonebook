package ru.geniusjohn.phonebook.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geniusjohn.phonebook.domain.Group;
import ru.geniusjohn.phonebook.domain.PhoneInfo;
import ru.geniusjohn.phonebook.domain.Record;
import ru.geniusjohn.phonebook.xml.element.Records;
import ru.geniusjohn.phonebook.xml.generator.XmlEltexGenerator;
import ru.geniusjohn.phonebook.xml.generator.XmlGenerator;
import ru.geniusjohn.phonebook.xml.generator.XmlYealinkGroupGenerator;
import ru.geniusjohn.phonebook.xml.generator.XmlYealinkMenuGenerator;

import java.util.List;

@Component
public class XmlGeneratorFactory {

    private XmlYealinkMenuGenerator xmlYealinkMenuGenerator;
    private XmlYealinkGroupGenerator xmlYealinkGroupGenerator;

    @Autowired
    public void setXmlYealinkMenuGenerator(XmlYealinkMenuGenerator xmlYealinkMenuGenerator) {
        this.xmlYealinkMenuGenerator = xmlYealinkMenuGenerator;
    }

    @Autowired
    public void setXmlYealinkGroupGenerator(XmlYealinkGroupGenerator xmlYealinkGroupGenerator) {
        this.xmlYealinkGroupGenerator = xmlYealinkGroupGenerator;
    }

    public XmlGenerator getMenuGenerator(PhoneInfo phoneInfo) {
        if (phoneInfo.getVendor() == null) {
            return new XmlYealinkMenuGenerator();
        }
        switch (phoneInfo.getVendor().toLowerCase()) {
            case "yealink" : return xmlYealinkMenuGenerator;
            case "eltex" : return  new XmlEltexGenerator();
            default: return new XmlYealinkMenuGenerator();
        }
//        return new XmlYealinkGenerator(groups, baseUrl);
    }

    public XmlGenerator getGroupGenerator(PhoneInfo phoneInfo){
        return xmlYealinkGroupGenerator;
    }
}
