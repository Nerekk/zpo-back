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

/**
 * Service class responsible for handling group-related business logic.
 * It interacts with the database via the {@link GroupRepository} and provides various operations related to groups.
 */
@Service
public class GroupService {

    /**
     * Repository for managing Group entities in the database.
     */
    private static GroupRepository groupRepository;

    /**
     * Automatic constructor for the GroupService class.
     *
     * @param groupRepository The GroupRepository instance to be used by the service.
     */
    @Autowired
    public GroupService(GroupRepository groupRepository) {
        GroupService.groupRepository = groupRepository;
    }

    /**
     * Method for adding a new group with the specified name.
     *
     * @param name The name of the group to be added.
     * @return ResponseEntity representing the HTTP response indicating the status of the operation.
     * @see ResponseEntity
     */
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

    /**
     * Method for deleting an existing group with the specified ID.
     *
     * @param id The ID of the group to be deleted.
     * @return ResponseEntity representing the HTTP response indicating the status of the operation.
     * @see ResponseEntity
     */
    public ResponseEntity<String> deleteGroup(Long id) {
        if (id == null)
            return new ResponseEntity<>(GROUP_WRONG_DATA_MESSAGE, HttpStatus.BAD_REQUEST);

        Optional<Group> groupOpt = groupRepository.findById(id);
        if (groupOpt.isEmpty())
            return new ResponseEntity<>(GROUP_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);

        Group group = groupOpt.get();

        if (!group.getStudents().isEmpty())
            return new ResponseEntity<>(GROUP_NOT_EMPTY_MESSAGE, HttpStatus.CONFLICT);

        groupRepository.delete(group);

        return new ResponseEntity<>(GROUP_OK_MESSAGE, HttpStatus.OK);
    }

    /**
     * Method for retrieving a group by its ID.
     *
     * @param id The ID of the group to be retrieved.
     * @return Optional containing the Group object if found, or empty if not found.
     * @see Optional
     */
    public static Optional<Group> getGroup(Long id) {
        return groupRepository.findById(id);
    }

    /**
     * Method for retrieving information about a group by its ID.
     *
     * @param id The ID of the group to be retrieved.
     * @return ResponseEntity containing the GroupResponse with information about the group.
     * @see ResponseEntity
     */
    public ResponseEntity<GroupResponse> getGroupRequest(Long id) {
        Group group = groupRepository.findById(id).orElse(null);

        if (group == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        List<BasicStudentResponse> basicStudentResponseList = new ArrayList<>(group.getStudents().size());

        for (Student student : group.getStudents())
            basicStudentResponseList.add(new BasicStudentResponse(student.getAlbum(), student.getFirstName(), student.getLastName()));

        GroupResponse response = new GroupResponse(group.getId(), group.getName(), basicStudentResponseList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Method for retrieving information about all groups.
     *
     * @return List of GroupResponse objects containing information about all groups.
     */
    public List<GroupResponse> getAllGroups() {
        List<GroupResponse> groupResponses = new ArrayList<>();
        List<Group> groups = groupRepository.findAll();

        for (Group group : groups)
            groupResponses.add(getGroupRequest(group.getId()).getBody());

        return groupResponses;
    }

}
