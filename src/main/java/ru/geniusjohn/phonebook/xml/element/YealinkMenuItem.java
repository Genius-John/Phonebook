package ru.geniusjohn.phonebook.xml.element;

import ru.geniusjohn.phonebook.domain.Group;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class YealinkMenuItem {
    @XmlElement(name = "Name")
    private String name;
    @XmlElement(name = "URL")
    private String url = "URL";

    public YealinkMenuItem() {
    }

    @Deprecated
    public YealinkMenuItem(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public YealinkMenuItem(Group group, String url) {
        // @TODO Implement
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
