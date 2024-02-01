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
    public ResponseEntity<String> deleteGroup(@PathVariable("id") Long id) {
        return groupService.deleteGroup(id);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<GroupResponse> getGroupRequest(@PathVariable("id") Long id) {
        return groupService.getGroupRequest(id);
    }

    @GetMapping(path = "all")
    public List<GroupResponse> getAllGroups() {
        return groupService.getAllGroups();
    }

}
