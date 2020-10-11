package ru.geniusjohn.phonebook.xml.element;

import ru.geniusjohn.phonebook.domain.Group;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.NONE)
public class EltexGroup {
    @XmlAttribute(name = "name")
    private String groupName;

    public EltexGroup() {
    }

    public EltexGroup(Group group) {
        this.groupName = group.getGroupName();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
