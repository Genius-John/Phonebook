package ru.geniusjohn.phonebook.domain;

import javax.persistence.*;

@Entity
@Table (name = "Groups")
public class Group {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderGroup;
    private String groupName;

    public Group(Long orderGroup, String groupName) {
        this.groupName = groupName;
        this.orderGroup = orderGroup;
    }

    public Group() {
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

//    public ItemMenu mapToItemMenu() {
//        // Url - захардкодить
//        return new ItemMenu(order + ". " + groupName, "http://127.0.0.1/getGroupXml/" + id);
//    }
}
