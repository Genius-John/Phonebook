package ru.geniusjohn.phonebook.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.geniusjohn.phonebook.domain.Group;

public interface GroupRepository extends CrudRepository<Group, Long> {

    Group findByGroupName (String groupName);

}
