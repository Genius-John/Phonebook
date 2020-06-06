package ru.geniusjohn.phonebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geniusjohn.phonebook.domain.Group;
import ru.geniusjohn.phonebook.repositories.GroupRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private GroupRepository groupRepository;

    @Autowired
    public void setGroupRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Optional<Group> findById(Long id) {
        return groupRepository.findById(id);
    }

    public Group findByGroupName (String groupName) {
        return groupRepository.findByGroupName(groupName);
    }

    public List<Group> findByOrderByOrderGroup() {
        return groupRepository.findByOrderByOrderGroup();
    }

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public Group save(Group group) {
        return groupRepository.save(group);
    }

    public void delete(Group group) {
        groupRepository.delete(group);
    }
}
