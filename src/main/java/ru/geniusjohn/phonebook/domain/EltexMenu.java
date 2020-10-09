package ru.geniusjohn.phonebook.domain;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "EltexIPPhoneDirectory")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="")
public class EltexMenu {

    @XmlElement(name = "Title")
    private String title = "EltexPhones";

    @XmlElement(name = "Prompt")
    private String prompt="Prompt";

    @XmlElementWrapper(name = "GroupLIst")
    @XmlElement(name = "XPEHb")
    private List<GroupEltex> groupEltex;

    /*
//    @XmlElementWrapper(name = "GroupLIst")
    @XmlElement(name = "XPEHb")
    private List<GroupEltex> groupEltex;
    */

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

    public void setGroupEltex(List<GroupEltex> groupEltex) {
        this.groupEltex = groupEltex;
    }
}
