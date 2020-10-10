package ru.geniusjohn.phonebook.xml.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class YealinkSoftKeyItem {
    @XmlElement(name = "Name")
    private Long keyNum;
    @XmlElement(name = "URL")
    private String url;

    public YealinkSoftKeyItem() {
    }

    public YealinkSoftKeyItem(Long keyNum, String url) {
        this.keyNum = keyNum;
        this.url = url;
    }

    public Long getKeyNum() {
        return keyNum;
    }

    public void setKeyNum(Long keyNum) {
        this.keyNum = keyNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}