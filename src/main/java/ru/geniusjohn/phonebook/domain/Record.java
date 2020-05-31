package ru.geniusjohn.phonebook.domain;

import javax.persistence.*;

@Entity
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    public Record(String fullName, String exNumber, String mobileNumber) {
        this.fullName = fullName;
        this.exNumber = exNumber;
        this.mobileNumber = mobileNumber;
    }

    private String exNumber;
    private String mobileNumber;

    public Record(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getExNumber() {
        return exNumber;
    }

    public void setExNumber(String exNumber) {
        this.exNumber = exNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
