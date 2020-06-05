package ru.geniusjohn.phonebook.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.geniusjohn.phonebook.domain.Group;

import java.util.List;

public interface GroupRepository extends CrudRepository<Group, Long> {

    Group findByGroupName (String groupName);

    List<Group> findById (Group groupId);

    List<Group> findAllByOrderByOrderGroup ();

    List<Group> findAll();

}
