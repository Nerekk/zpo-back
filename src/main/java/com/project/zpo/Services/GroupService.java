package com.project.zpo.Services;

import com.project.zpo.Repositories.GroupRepository;
import com.project.zpo.RequestsAndResponses.BasicStudentResponse;
import com.project.zpo.RequestsAndResponses.GroupResponse;
import com.project.zpo.Tables.Group;
import com.project.zpo.Tables.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.project.zpo.Messages.GroupMessages.*;

@Service
public class GroupService {
    private static GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        GroupService.groupRepository = groupRepository;
    }

    public ResponseEntity<String> addGroup(String name) {
        if (name == null)
            return new ResponseEntity<>(GROUP_WRONG_DATA_MESSAGE, HttpStatus.BAD_REQUEST);

        Group existingGroup = groupRepository.findByName(name);
        if (existingGroup != null)
            return new ResponseEntity<>(GROUP_NAME_COLLISION_MESSAGE, HttpStatus.CONFLICT);

        Group group = new Group();
        group.setName(name);
        groupRepository.save(group);

        return new ResponseEntity<>(GROUP_OK_MESSAGE, HttpStatus.OK);
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
