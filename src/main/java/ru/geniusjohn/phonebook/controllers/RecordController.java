package ru.geniusjohn.phonebook.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import ru.geniusjohn.phonebook.repositories.RecordRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class RecordController {

    private static final Logger logger = LoggerFactory.getLogger(RecordController.class);
    private RecordRepository recordRepository;
    private GroupRepository groupRepository;

    @Autowired
    public void setRecordRepositories(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
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
                           Model model,
                           HttpServletRequest request) {
        Iterable<Record> records;
        Iterable<Group> groups = groupRepository.findByOrderByOrderGroup();
        Group group = groupRepository.findByGroupName(filterGroupName);
        logger.info("--------");
        logger.info("Home page visitor:");
        logger.info("IP address: " + request.getRemoteAddr());
        logger.info("--------");
        if (group != null) {
            records = recordRepository.findAllByGroupOrderByFullName(group);
        } else if (filter != null && !filter.isEmpty()) {
            records = recordRepository.findAllByFullNameContainsIgnoreCaseOrMobileNumberContainsOrExNumberContainsOrderByFullName (filter, filter, filter);
        } else
            records = recordRepository.findAllByOrderByFullName();
        model.addAttribute("records", records);
        model.addAttribute("filter", filter);
        model.addAttribute("groups", groups);
        return "phonebook";
    }

    @GetMapping("/phonebook/del/{record}") //Удаление записи
    public String delete(@PathVariable("record") Record record,
                         HttpServletRequest request) {
        recordRepository.delete(record);
        logger.info("--------");
        logger.info("Record deleted: " + record.getFullName());
        logger.info("IP address: " + request.getRemoteAddr());
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
                        Map<String, Object> model,
                        HttpServletRequest request) {
        Group group = groupRepository.findByGroupName(groupName);
        Record record = new Record(fullName, exNumber, mobileNumber, group);
        recordRepository.save(record);
        Iterable<Record> records = recordRepository.findAllByOrderByFullName();
        model.put("records", records);
        logger.info("--------");
        logger.info("Record created: " + record.getFullName());
        logger.info("IP address: " + request.getRemoteAddr());
        return "redirect:/phonebook";
    }

    @PostMapping("/phonebook/update/{record}")
    public String save(@RequestParam String fullName,
                       @RequestParam String exNumber,
                       @RequestParam String mobileNumber,
                       @RequestParam String groupName,
                       @PathVariable("record") Record record,
                       HttpServletRequest request) {
        Group group = groupRepository.findByGroupName(groupName);
        record.setFullName(fullName);
        record.setExNumber(exNumber);
        record.setMobileNumber(mobileNumber);
        record.setGroup(group);
        recordRepository.save(record);
        logger.info("--------");
        logger.info("Record saved: " + record.getFullName());
        logger.info("IP address: " + request.getRemoteAddr());
        logger.info("--------");
        return "redirect:/phonebook";
    }
}