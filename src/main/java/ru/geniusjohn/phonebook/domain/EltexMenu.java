package ru.geniusjohn.phonebook.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "EltexIPPhoneDirectory")
@XmlAccessorType(XmlAccessType.FIELD)
public class EltexMenu {

    @XmlElement(name = "Title")
    private String title = "EltexPhones";

    @XmlElement(name = "Prompt")
    private String prompt = "Prompt";

    @XmlElement(name = "DirectoryEntry")
    private List<Record> records;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
