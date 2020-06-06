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
import ru.geniusjohn.phonebook.service.GroupService;
import ru.geniusjohn.phonebook.service.RecordService;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Controller
public class RecordController {

    private RecordService recordService;
    private GroupService groupService;
    
    @Autowired
    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }

    @Autowired
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping ("/") // Тестовая страница
    public String phoneBook() {
        return "main";
    }

    @GetMapping("/phonebook") //Вывод записей с фильтром
    public String mainPage(@RequestParam(required = false, defaultValue = "")
                           String filter,
                           Model model) {
        Iterable<Record> records;
        Iterable<Group> groups = groupService.findByOrderByOrderGroup();
        if (filter != null && !filter.isEmpty()) {
            records = recordService.findFilteredByFullNameMobileNumberExNumber(filter, filter, filter);
        }else {
            records = recordService.findByOrderByFullName();
        }
        model.addAttribute("records", records);
        model.addAttribute("filter", filter);
        model.addAttribute("groups", groups);
        return "phonebook";
    }

    @GetMapping("/phonebook/del/{record}") //Удаление записи
    public String delete(@PathVariable("record") Record record) {
        recordService.delete(record);
        return "redirect:/phonebook";
    }
    @GetMapping("/phonebook/{record}") // Страница редактирования записи
    public String update(@PathVariable() Record record, Model model) {
        Iterable<Group> groups = groupService.findByOrderByOrderGroup();
//        Iterable<Record> records = recordRepositories.findAllByOrderByFullName();
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
        Group group = groupService.findByGroupName(groupName);   // есть сомнения...
        Record record = new Record(fullName, exNumber, mobileNumber, group);
        recordService.save(record);
        Iterable<Record> records = recordService.findByOrderByFullName();
        model.put("records", records);
        return "redirect:/phonebook";
    }

    @PostMapping("/phonebook/update/{record}")
    public String save(
            HttpServletResponse response,
            @RequestParam String fullName,
            @RequestParam String exNumber,
            @RequestParam String mobileNumber,
            @RequestParam String groupId,
            @PathVariable("record") Record record
    ) {
        Optional<Group> group = groupService.findById(Long.parseLong(groupId));
        if (!group.isPresent()) {
            response.setStatus(404);
            return "";
        }
        record.setFullName(fullName);
        record.setExNumber(exNumber);
        record.setMobileNumber(mobileNumber);
        record.setGroup(group.get());
        recordService.save(record);
        return "redirect:/phonebook";
    }
}