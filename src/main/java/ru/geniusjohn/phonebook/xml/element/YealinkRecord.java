package ru.geniusjohn.phonebook.xml.element;

import ru.geniusjohn.phonebook.domain.Record;
import javax.xml.bind.annotation.*;

@XmlType(propOrder = {"fullName", "exNumber", "mobileNumber"})
@XmlAccessorType(XmlAccessType.NONE)
public class YealinkRecord {
    @XmlElement(name = "Name")
    private String fullName;
    @XmlElement(name = "Telephone")
    private String exNumber;
    @XmlElement(name = "Telephone")
    private String mobileNumber;

    public YealinkRecord() {
    }

    public YealinkRecord(Record record) {
        this.fullName = record.getFullName();
        this.exNumber = record.getExNumber();
        this.mobileNumber = record.getMobileNumber();
    }

    public String getFullName() {
        return fullName;
    }

    public String getExNumber() {
        return exNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
}
