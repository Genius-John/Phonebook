package ru.geniusjohn.phonebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geniusjohn.phonebook.domain.Group;
import ru.geniusjohn.phonebook.domain.Record;
import ru.geniusjohn.phonebook.repositories.RecordRepository;

import java.util.List;

@Service
public class RecordService {

    private RecordRepository recordRepository;

    @Autowired
    public void setRecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public List<Record> findAll() {
        return recordRepository.findAll();
    }

    public List<Record> findAllByGroup (Group group) {
        return recordRepository.findAllByGroup(group);
    }

    public List<Record> findByOrderByFullName() {
        return recordRepository.findAllByOrderByFullName();
    }

    public List<Record> findFilteredByFullNameMobileNumberExNumber(String fullName, String mobileNumber, String exNumber) {
        return recordRepository.findAllByFullNameContainsIgnoreCaseOrMobileNumberContainsOrExNumberContainsOrderByFullName(fullName, mobileNumber, exNumber);
    }

    public void delete(Record record) {
        recordRepository.delete(record);
    }

    public Record save(Record record) {
        return recordRepository.save(record);
    }
}
