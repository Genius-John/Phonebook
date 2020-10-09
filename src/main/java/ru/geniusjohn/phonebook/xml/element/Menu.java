package ru.geniusjohn.phonebook.xml.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "YealinkIPPhoneMenu")
@XmlAccessorType(XmlAccessType.FIELD)
public class Menu {

    @XmlElement (name = "Title")
    private String title = "XiaMen Yealink";

    @XmlElement(name = "MenuItem")
    private List<MenuItem> menuItems;

    @XmlElement(name = "SoftKeyItem")
    private List<SoftKeyItem> softKeyItems;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
