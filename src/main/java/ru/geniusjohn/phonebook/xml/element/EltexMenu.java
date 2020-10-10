package ru.geniusjohn.phonebook.xml.element;

import ru.geniusjohn.phonebook.domain.Record;

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
    private List<EltexGroup> eltexGroups;

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

    public void setGroupEltex(List<EltexGroup> eltexGroups) {
        this.eltexGroups = eltexGroups;
    }
}
