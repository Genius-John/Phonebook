package ru.geniusjohn.phonebook.domain;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "YealinkIPPhoneDirectory")
@XmlAccessorType(XmlAccessType.FIELD)
public class Records {

    @XmlElement(name = "DirectoryEntry")
    private List<Record> records = null;

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
