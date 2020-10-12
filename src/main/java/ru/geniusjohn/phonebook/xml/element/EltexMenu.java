package ru.geniusjohn.phonebook.xml.element;

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
    @XmlElementWrapper(name = "Grouplist")
    @XmlElement(name = "Group")
    private List<EltexGroup> eltexGroups;
    @XmlElement(name = "DirectoryEntry")
    private List<EltexRecord> records;

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

    public void setRecords(List<EltexRecord> records) {
        this.records = records;
    }

    public List<EltexGroup> getEltexGroups() {
        return eltexGroups;
    }

    public void setEltexGroups(List<EltexGroup> eltexGroups) {
        this.eltexGroups = eltexGroups;
    }
}
