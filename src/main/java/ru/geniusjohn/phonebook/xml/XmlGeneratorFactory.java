package ru.geniusjohn.phonebook.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geniusjohn.phonebook.domain.PhoneInfo;
import ru.geniusjohn.phonebook.xml.generator.*;

@Component
public class XmlGeneratorFactory {

    private XmlYealinkMenuGenerator xmlYealinkMenuGenerator;
    private XmlYealinkGroupGenerator xmlYealinkGroupGenerator;
    private XmlEltexGenerator xmlEltexGenerator;

    @Autowired
    public void setXmlYealinkMenuGenerator(XmlYealinkMenuGenerator xmlYealinkMenuGenerator) {
        this.xmlYealinkMenuGenerator = xmlYealinkMenuGenerator;
    }

    @Autowired
    public void setXmlYealinkGroupGenerator(XmlYealinkGroupGenerator xmlYealinkGroupGenerator) {
        this.xmlYealinkGroupGenerator = xmlYealinkGroupGenerator;
    }

    @Autowired
    public void setXmlEltexGenerator(XmlEltexGenerator xmlEltexGenerator) {
        this.xmlEltexGenerator = xmlEltexGenerator;
    }

    public XmlMenuGenerator getMenuGenerator(PhoneInfo phoneInfo) {
        if (phoneInfo.getVendor() == null) {
            return new XmlYealinkMenuGenerator();
        }
        switch (phoneInfo.getVendor().toLowerCase()) {
            case "yealink": return xmlYealinkMenuGenerator;
            case "eltex": return xmlEltexGenerator;
            default: return xmlYealinkMenuGenerator;
        }
    }

    public XmlGroupGenerator getGroupGenerator(PhoneInfo phoneInfo){
        switch (phoneInfo.getVendor().toLowerCase()) {
            case "yealink": return xmlYealinkGroupGenerator;
            case "eltex": return null;
            default: return xmlYealinkGroupGenerator;
        }
    }
}
