package com.project.zpo.Groups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public void addGroup(String name) {
        if (name!=null) {
            Group group = new Group();
            group.setName(name);
            groupRepository.save(group);
        }
    }

    public void deleteGroup(Long id) {
        Group group = getGroup(id).orElse(null);
        if (group != null) {
            groupRepository.delete(group);
        }
    }

    public Optional<Group> getGroup(Long id) {
        return groupRepository.findById(id);
    }
}
