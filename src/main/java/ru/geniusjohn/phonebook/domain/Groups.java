package ru.geniusjohn.phonebook.domain;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "YealinkIPPhoneMenu")
@XmlAccessorType(XmlAccessType.FIELD)
public class Groups {

//    @XmlTransient
//    private String title;

    @XmlElement(name = "MenuItem")
    private List<Group> groups;

//    @XmlTransient
//    private List<MenuItem> menuItems;
//
//    @XmlTransient
//    private List<SoftKeyItem> softKeyItems;
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
//
//    public List<MenuItem> getMenuItems() {
//        return menuItems;
//    }
//
//    public void setMenuItems(List<MenuItem> menuItems) {
//        this.menuItems = menuItems;
//    }
//
//    public List<SoftKeyItem> getSoftKeyItems() {
//        return softKeyItems;
//    }
//
//    public void setSoftKeyItems(List<SoftKeyItem> softKeyItems) {
//        this.softKeyItems = softKeyItems;
//    }
}
