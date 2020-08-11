package ru.geniusjohn.phonebook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geniusjohn.phonebook.domain.Group;
import ru.geniusjohn.phonebook.domain.Record;
import ru.geniusjohn.phonebook.repositories.GroupRepository;
import ru.geniusjohn.phonebook.repositories.RecordRepositories;
import java.util.Map;

@Controller
public class RecordController {

    private RecordRepositories recordRepositories;
    private GroupRepository groupRepository;

    @Autowired
    public void setRecordRepositories(RecordRepositories recordRepositories) {
        this.recordRepositories = recordRepositories;
    }

    @Autowired
    public void setGroupRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @GetMapping ("/") // Тестовая страница
    public String phoneBook() {
        return "main";
    }

    @GetMapping("/phonebook") //Вывод записей с фильтром
    public String mainPage(@RequestParam(required = false, defaultValue = "")
                           String filter,
                           @RequestParam(required = false, defaultValue = "")
                           String filterGroupName,
                           Model model) {
        Iterable<Record> records;
        Iterable<Group> groups = groupRepository.findByOrderByOrderGroup();
        Group group = groupRepository.findByGroupName(filterGroupName);

        if (group != null) {
            records = recordRepositories.findAllByGroupOrderByFullName(group);
        } else {
            records = recordRepositories.findAllByFullNameContainsIgnoreCaseOrMobileNumberContainsOrExNumberContainsOrderByFullName (filter, filter, filter);
        }

        //добавить проверку ↑
//        if (filter != null && !filter.isEmpty()) {
//            records = recordRepositories.findAllByFullNameContainsIgnoreCaseOrMobileNumberContainsOrExNumberContainsOrderByFullName (filter, filter, filter);
//        }
//        else {
//            records = recordRepositories.findAllByOrderByFullName();
//        }
        model.addAttribute("records", records);
        model.addAttribute("filter", filter);
        model.addAttribute("groups", groups);
        return "phonebook";
    }

    @GetMapping("/phonebook/del/{record}") //Удаление записи
    public String delete(@PathVariable("record") Record record) {
        recordRepositories.delete(record);
        return "redirect:/phonebook";
    }
    @GetMapping("/phonebook/{record}") // Страница редактирования записи
    public String update(@PathVariable() Record record, Model model) {
        Iterable<Group> groups = groupRepository.findByOrderByOrderGroup();
        model.addAttribute("groups", groups);
        model.addAttribute("record", record);
        return "editRecord";
    }

    @PostMapping() //Создание записи
    public String create(
                        @RequestParam String fullName,
                        @RequestParam String exNumber,
                        @RequestParam String mobileNumber,
                        @RequestParam String groupName,
                        Map<String, Object> model) {
        Group group = groupRepository.findByGroupName(groupName);   //todo есть сомнения...
        Record record = new Record(fullName, exNumber, mobileNumber, group);
        recordRepositories.save(record);
        Iterable<Record> records = recordRepositories.findAllByOrderByFullName();
        model.put("records", records);
        return "redirect:/phonebook";
    }

    @PostMapping("/phonebook/update/{record}")
    public String save(@RequestParam String fullName,
                        @RequestParam String exNumber,
                        @RequestParam String mobileNumber,
                        @RequestParam String groupName,
                        @PathVariable("record") Record record) {
        Group group = groupRepository.findByGroupName(groupName);
        record.setFullName(fullName);
        record.setExNumber(exNumber);
        record.setMobileNumber(mobileNumber);
        record.setGroup(group);
        recordRepositories.save(record);
        return "redirect:/phonebook";
    }
}