package ru.geniusjohn.phonebook.domain;

import ru.geniusjohn.phonebook.xml.element.YealinkMenuItem;
import ru.geniusjohn.phonebook.xml.element.YealinkSoftKeyItem;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@Table (name = "Groups")
@XmlAccessorType(XmlAccessType.FIELD)
public class Group {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;
    @XmlTransient
    private Long orderGroup;
//    @XmlElementWrapper(name = "GroupList")
    @XmlElement(name = "Group")
    private String groupName;

    public Group() {
    }

    public Group(Long orderGroup, String groupName) {
        this.orderGroup = orderGroup;
        this.groupName = groupName;
    }

    public Long getOrderGroup() {
        return orderGroup;
    }

    public void setOrderGroup(Long orderGroup) {
        this.orderGroup = orderGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public YealinkMenuItem mapToItemMenu(String url) {
        return new YealinkMenuItem(orderGroup + ". " + groupName, url);
    }

    public YealinkSoftKeyItem mapToSoftKeyMenu(String url) {
        return new YealinkSoftKeyItem(orderGroup, url);
    }
}
