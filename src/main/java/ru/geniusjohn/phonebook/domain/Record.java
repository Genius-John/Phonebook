package ru.geniusjohn.phonebook.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@XmlRootElement(name="DirectoryEntry")
@XmlType(propOrder = {"fullName", "exNumber", "mobileNumber"})
@XmlAccessorType(XmlAccessType.NONE)
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @XmlElement(name="Name")
    private String fullName;
    @XmlElement(name="Telephone")
    private String exNumber;
    @XmlElement(name="Telephone")
    private String mobileNumber;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private Group group;

    public Record(){
    }

    public Record(String fullName, String exNumber, String mobileNumber, Group group) {
        this.fullName = fullName;
        this.exNumber = exNumber;
        this.mobileNumber = mobileNumber;
        this.group = group;
    }

    public String getGroupName() {
        return group != null ? group.getGroupName() : "<none>";
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getExNumber() {
        return exNumber;
    }

    public void setExNumber(String exNumber) {
        this.exNumber = exNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
