package ru.geniusjohn.phonebook.domain;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

//@XmlRootElement(name = "GroupList")
@XmlAccessorType(XmlAccessType.FIELD)
public class GroupEltex {

    @XmlElement(name = "Group")
    private List<Group> groups = null;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
