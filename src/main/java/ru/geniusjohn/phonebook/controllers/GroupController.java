package ru.geniusjohn.phonebook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geniusjohn.phonebook.domain.Group;
import ru.geniusjohn.phonebook.repositories.GroupRepository;
import java.util.Map;

@Controller
@RequestMapping ("/phonebook")
public class GroupController {

    private GroupRepository groupRepository;

    @Autowired
    public void setGroupRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @GetMapping("/groupList") // List of groups
    public String mainPage(Model model) {
        Iterable<Group> groups = groupRepository.findByOrderByOrderGroup();
        model.addAttribute("groups", groups);
        return "groupList";
    }

    @PostMapping("/groupList")  //Add group
    public String create (@RequestParam Long orderGroup,
                          @RequestParam String groupName,
                          Map<String, Object> model) {
        Group group = new Group(orderGroup, groupName);
        groupRepository.save(group);
        Iterable<Group> groups = groupRepository.findByOrderByOrderGroup();
        model.put("groups", groups);
        return "redirect:/phonebook/groupList";
    }

    @PostMapping("/groupList/{group}")  //Update group
    public String update (@PathVariable("group") Group group, Long orderGroup, String groupName) {
        group.setOrderGroup(orderGroup);
        group.setGroupName(groupName);
        groupRepository.save(group);
        return "redirect:/phonebook/groupList";
    }

    @GetMapping ("/groupList/del/{group}") // Delete group
    public String delete (@PathVariable("group") Group group) {
        groupRepository.delete(group);
        return "redirect:/phonebook/groupList";
    }

}
