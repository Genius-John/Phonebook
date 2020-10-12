package ru.geniusjohn.phonebook.xml.element;

import ru.geniusjohn.phonebook.domain.Record;
import javax.xml.bind.annotation.XmlElement;

public class EltexRecord {

    @XmlElement(name = "Name")
    private String name;
    @XmlElement(name = "Telephone")
    private String telephone;
//    @XmlElement(name = "Telephone") //todo проверить работу с двумя номерами
//    private String mobile;
    @XmlElement(name = "Group")
    private String group;

    public EltexRecord() {
    }

    public EltexRecord(Record record) {
        this.name = record.getFullName();
        this.telephone = record.getExNumber();
//        this.mobile = record.getMobileNumber();
        this.group = record.getGroupName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

//    public void setMobile(String mobile) {
//        this.mobile = mobile;
//    }

    public void setGroup(String group) {
        this.group = group;
    }
}
