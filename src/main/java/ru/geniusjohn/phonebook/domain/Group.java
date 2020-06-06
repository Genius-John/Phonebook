package ru.geniusjohn.phonebook.domain;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Table (name = "Groups")
public class Group {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderGroup;
    private String groupName;

    public Group() {
    }

    public Group(Long orderGroup, String groupName) {
        this.orderGroup = orderGroup;
        this.groupName = groupName;
    }

    public Long getOrderGroup() {
        return orderGroup;
    }

    public void setOrderGroup(Long orderGroup) {
        this.orderGroup = orderGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public MenuItem mapToItemMenu(String url) {
        return new MenuItem(groupName, url);
    }

    public SoftKeyItem mapToSoftKeyMenu(String url) {
        return new SoftKeyItem(orderGroup, url);
    }
}
