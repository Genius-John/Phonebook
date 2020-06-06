package ru.geniusjohn.phonebook.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.geniusjohn.phonebook.domain.Group;
import ru.geniusjohn.phonebook.domain.Record;

import java.util.List;

public interface RecordRepository extends CrudRepository<Record, Long> {

    List<Record> findAll();

    List<Record> findAllByGroup (Group group);

    List<Record> findAllByOrderByFullName();

    List<Record> findAllByFullNameContainsIgnoreCaseOrMobileNumberContainsOrExNumberContainsOrderByFullName (String fullName, String mobileNumber, String exNumber);

}
