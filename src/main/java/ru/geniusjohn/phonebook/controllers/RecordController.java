package ru.geniusjohn.phonebook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geniusjohn.phonebook.domain.Record;
import ru.geniusjohn.phonebook.repositories.RecordRepositories;
import java.util.Map;

@Controller
public class RecordController {

    private RecordRepositories recordRepositories;

    @Autowired
    public void setRecordRepositories(RecordRepositories recordRepositories) {
        this.recordRepositories = recordRepositories;
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
        if (filter != null && !filter.isEmpty()) {
            records = recordRepositories.findByFullName(filter);
        }else {
            records = recordRepositories.findAll();
        }
        model.addAttribute("records", records);
        model.addAttribute("filter", filter);
        return "phonebook";
    }

    @GetMapping("/phonebook/del/{record}") //Удаление записи
    public String delete(@PathVariable("record") Record record){
        recordRepositories.delete(record);
        return "redirect:/phonebook";
    }
    @GetMapping("/phonebook/{record}") // Редактирование записи
    public String update(@PathVariable("record") Record record) {
        return "editRecord";
    }

    @GetMapping ("/groupList")
    public String groupList() {
        return "groupList";
    }

    @PostMapping() //Создание записи
    public String create(
            @RequestParam String fullName,
            @RequestParam String exNumber,
            @RequestParam String mobileNumber,
            Map<String, Object> model) {
        Record record = new Record(fullName, exNumber, mobileNumber);
        recordRepositories.save(record);
        Iterable<Record> records = recordRepositories.findAll();

        model.put("records", records);

        return "redirect:/phonebook";
    }

    @PostMapping("/phonebook/update/{record}")
    public String save(@RequestParam String fullName,
                        @RequestParam String exNumber,
                        @RequestParam String mobileNumber,
                        @PathVariable("record") Record record) {
        record.setFullName(fullName);
        record.setExNumber(exNumber);
        record.setMobileNumber(mobileNumber);
        recordRepositories.save(record);

        return "redirect:/phonebook";
    }
}
