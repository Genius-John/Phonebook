package ru.geniusjohn.phonebook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geniusjohn.phonebook.domain.Group;
import ru.geniusjohn.phonebook.repositories.GroupRepository;
import ru.geniusjohn.phonebook.repositories.RecordRepositories;

import java.util.Map;

@Controller
public class GroupController {

    private GroupRepository groupRepository;
    private RecordRepositories recordRepositories;

    @Autowired
    public void setGroupRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Autowired
    public void setRecordRepositories(RecordRepositories recordRepositories) {
        this.recordRepositories = recordRepositories;
    }

    @GetMapping("/groupList") // List of groups
    public String mainPage(Model model) {
//        Iterable<Group> groups = groupRepository.findAll();
        Iterable<Group> groups = groupRepository.findAllByOrderByOrderGroup();
        model.addAttribute("groups", groups);
        return "groupList";
    }

    @PostMapping("/groupList")  //Add group
    public String create (@RequestParam Long orderGroup,
                          @RequestParam String groupName,
                          Map<String, Object> model) {
        Group group = new Group(orderGroup, groupName);
        groupRepository.save(group);
        Iterable<Group> groups = groupRepository.findAll();
        model.put("groups", groups);
        return "redirect:/groupList";
    }

    @PostMapping("/groupList/{group}")  //Update group
    public String update (@PathVariable("group") Group group, Long orderGroup, String groupName) {
        group.setOrderGroup(orderGroup);
        group.setGroupName(groupName);
        groupRepository.save(group);
        return "redirect:/groupList";
    }

    @GetMapping ("/groupList/del/{group}") // Delete group
    public String delete (@PathVariable("group") Group group) {
        groupRepository.delete(group);
        return "redirect:/groupList";
    }

}
