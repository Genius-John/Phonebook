package ru.geniusjohn.phonebook.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.geniusjohn.phonebook.domain.Record;

import java.util.List;

public interface RecordRepositories extends CrudRepository<Record, Long> {

    List<Record> findByFullName (String fullName);

    List<Record> findByExNumber (String exNumber);

    List<Record> findByMobileNumber (String mobileNumber);

    Record findByIsGroup (Long id);

    List<Record> findAllByFullNameContainsIgnoreCase (String fullName);

    List<Record> findAllByFullNameContainsIgnoreCaseOrMobileNumberContainsOrExNumberContains (String fullName, String mobileNumber, String exNumber);

}
