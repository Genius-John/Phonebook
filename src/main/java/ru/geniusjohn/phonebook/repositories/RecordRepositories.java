package ru.geniusjohn.phonebook.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.geniusjohn.phonebook.domain.Group;
import ru.geniusjohn.phonebook.domain.Record;

import java.util.List;

public interface RecordRepositories extends CrudRepository<Record, Long> {

    List<Record> findAll();

    List<Record> findAllByGroup (Group groupId);

    List<Record> findByFullName (String fullName);

    List<Record> findAllByFullNameContainsIgnoreCase (String fullName);

    List<Record> findAllByFullNameContainsIgnoreCaseOrMobileNumberContainsOrExNumberContains (String fullName, String mobileNumber, String exNumber);

}
