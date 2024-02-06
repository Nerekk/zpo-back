package com.project.zpo.Controllers;

import com.project.zpo.RequestsAndResponses.GroupResponse;
import com.project.zpo.Services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class responsible for handling group-related HTTP requests.
 * This controller is mapped to the "/groups" endpoint.
 * <p>
 * The controller provides endpoints for adding, deleting, and retrieving groups.
 * It utilizes the {@link GroupService} layer to handle business logic related to group management.
 */
@RestController
@RequestMapping(path = "groups")
@CrossOrigin
public class GroupController {
    /**
     * Service responsible for handling group-related business logic.
     */
    private final GroupService groupService;

    /**
     * Automatic constructor for the GroupController class.
     *
     * @param groupService The GroupService instance to be used by the controller.
     */
    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    /**
     * Endpoint for adding a new group.
     *
     * @param name The name of the group to be added.
     * @return ResponseEntity representing the HTTP response.
     * @see ResponseEntity
     */
    @PostMapping(path = "{name}")
    public ResponseEntity<String> addGroup(@PathVariable("name") String name) {
        return groupService.addGroup(name);
    }

    /**
     * Endpoint for deleting an existing group.
     *
     * @param id The ID of the group to be deleted.
     * @return ResponseEntity representing the HTTP response.
     * @see ResponseEntity
     */
    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteGroup(@PathVariable("id") Long id) {
        return groupService.deleteGroup(id);
    }

    /**
     * Endpoint for retrieving information about a specific group.
     *
     * @param id The ID of the group to be retrieved.
     * @return ResponseEntity containing information about the group.
     * @see ResponseEntity
     */
    @GetMapping(path = "{id}")
    public ResponseEntity<GroupResponse> getGroupRequest(@PathVariable("id") Long id) {
        return groupService.getGroupRequest(id);
    }

    /**
     * Endpoint for retrieving information about all groups.
     *
     * @return List of GroupResponse objects containing information about all groups.
     */
    @GetMapping(path = "all")
    public List<GroupResponse> getAllGroups() {
        return groupService.getAllGroups();
    }

}
