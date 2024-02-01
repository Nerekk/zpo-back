package com.project.zpo.Controllers;

import com.project.zpo.RequestsAndResponses.GroupResponse;
import com.project.zpo.Services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "groups")
@CrossOrigin
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping(path = "{name}")
    public ResponseEntity<String> addGroup(@PathVariable("name") String name) {
        return groupService.addGroup(name);
    }

    @DeleteMapping(path = "{id}")
    public void deleteGroup(@PathVariable("id") Long id) {
        groupService.deleteGroup(id);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<GroupResponse> getGroupRequest(@PathVariable("id") Long id) {

        GroupResponse response = groupService.getGroupRequest(id);
        if (response == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "all")
    public List<GroupResponse> getAllGroups() {
        return groupService.getAllGroups();
    }

}
