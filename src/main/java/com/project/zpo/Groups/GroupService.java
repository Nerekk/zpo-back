package com.project.zpo.Groups;

import com.project.zpo.Groups.Requests.GroupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private static GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        GroupService.groupRepository = groupRepository;
    }

    public void addGroup(String name) {
        if (name != null) {
            Group group = new Group();
            group.setName(name);
            groupRepository.save(group);
        }
    }

    public void deleteGroup(Long id) {
        if (id != null) {
            groupRepository.deleteById(id);
        }
    }

    public static Optional<Group> getGroup(Long id) {
        return groupRepository.findById(id);
    }

    public GroupRequest getGroupRequest(Long id) {
        Group group = groupRepository.findById(id).orElse(null);
        if (group != null) {
            GroupRequest groupRequest = new GroupRequest(group.getId(), group.getName(), group.getStudents());
            return groupRequest;
        } else {
            return null;
        }
    }

    public List<GroupRequest> getAllGroups() {
        List<GroupRequest> groupRequests = new ArrayList<>();
        List<Group> groups = groupRepository.findAll();

        for (Group group : groups) {
            groupRequests.add(getGroupRequest(group.getId()));
        }
        return groupRequests;
    }
}
