package ru.geniusjohn.phonebook.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "YealinkIPPhoneDirectory")
@XmlAccessorType(XmlAccessType.FIELD)
public class Groups {

    @XmlElement(name = "")
    private List<Group> groups = null;

    private String title;

    private List<MenuItem> menuItems;

    private List<SoftKeyItem> softKeyItems;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = "XiaMen Yealink";
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public List<SoftKeyItem> getSoftKeyItems() {
        return softKeyItems;
    }

    public void setSoftKeyItems(List<SoftKeyItem> softKeyItems) {
        this.softKeyItems = softKeyItems;
    }
}
