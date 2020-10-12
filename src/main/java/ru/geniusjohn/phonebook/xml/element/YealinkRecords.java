package ru.geniusjohn.phonebook.xml.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "YealinkIPPhoneDirectory")
@XmlAccessorType(XmlAccessType.FIELD)
public class YealinkRecords {

    @XmlElement(name = "DirectoryEntry")
    private List<YealinkRecord> records = null;



    public List<YealinkRecord> getRecords() {
        return records;
    }

    public void setRecords(List<YealinkRecord> records) {
        this.records = records;
    }
}
