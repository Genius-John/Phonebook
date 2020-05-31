package ru.geniusjohn.phonebook.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.geniusjohn.phonebook.domain.Records;

import java.util.List;

public interface RecordRepositories extends CrudRepository<Records, Long> {

    List<Records> findByFullName (String fullName);

    List<Records> findByExNumber (String exNumber);

    List<Records> findByMobileNumber (String mobileNumber);

}
