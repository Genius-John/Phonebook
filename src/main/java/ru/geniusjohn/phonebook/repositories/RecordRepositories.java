package ru.geniusjohn.phonebook.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.geniusjohn.phonebook.domain.Record;

import java.util.List;

public interface RecordRepositories extends CrudRepository<Record, Long> {

    List<Record> findByFullName (String fullName);

    List<Record> findByExNumber (String exNumber);

    List<Record> findByMobileNumber (String mobileNumber);

}
