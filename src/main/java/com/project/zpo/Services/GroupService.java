package com.project.zpo.Services;

import com.project.zpo.Repositories.GroupRepository;
import com.project.zpo.RequestsAndResponses.BasicStudentResponse;
import com.project.zpo.RequestsAndResponses.GroupResponse;
import com.project.zpo.Tables.Group;
import com.project.zpo.Tables.Student;
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
        if (name == null)
            return;

        Group group = new Group();
        group.setName(name);
        groupRepository.save(group);
    }

    public void deleteGroup(Long id) {
        if (id != null)
            groupRepository.deleteById(id);
    }

    public static Optional<Group> getGroup(Long id) {
        return groupRepository.findById(id);
    }

    public GroupResponse getGroupRequest(Long id) {
        Group group = groupRepository.findById(id).orElse(null);

        if (group == null)
            return null;

        List<BasicStudentResponse> basicStudentResponseList = new ArrayList<>(group.getStudents().size());

        for (Student student : group.getStudents())
            basicStudentResponseList.add(new BasicStudentResponse(student.getAlbum(), student.getFirstName(), student.getLastName()));

        return new GroupResponse(group.getId(), group.getName(), basicStudentResponseList);
    }

    public List<GroupResponse> getAllGroups() {
        List<GroupResponse> groupResponses = new ArrayList<>();
        List<Group> groups = groupRepository.findAll();

        for (Group group : groups)
            groupResponses.add(getGroupRequest(group.getId()));

        return groupResponses;
    }
}
