package ru.geniusjohn.phonebook.xml.element;

import ru.geniusjohn.phonebook.domain.Group;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "YealinkIPPhoneMenu")
@XmlAccessorType(XmlAccessType.FIELD)
public class YealinkMenu {

    @XmlElement (name = "Title")
    private String title = "XiaMen Yealink";

    @XmlElement(name = "MenuItem")
    private List<YealinkMenuItem> menuItems;

    @XmlElement(name = "SoftKeyItem")
    private List<YealinkSoftKeyItem> yealinkSoftKeyItems;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<YealinkMenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<YealinkMenuItem> yealinkMenuItems) {
        this.menuItems = yealinkMenuItems;
    }

    public List<YealinkSoftKeyItem> getSoftKeyItems() {
        return yealinkSoftKeyItems;
    }

    public void setSoftKeyItems(List<YealinkSoftKeyItem> yealinkSoftKeyItems) {
        this.yealinkSoftKeyItems = yealinkSoftKeyItems;
    }
}
