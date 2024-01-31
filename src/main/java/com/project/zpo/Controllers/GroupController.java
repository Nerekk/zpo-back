package com.project.zpo.Controllers;

import com.project.zpo.Services.GroupService;
import com.project.zpo.Requests.GroupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "groups")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping(path = "{name}")
    public void addGroup(@PathVariable("name") String name) {
        groupService.addGroup(name);
    }

    @DeleteMapping(path = "{id}")
    public void deleteGroup(@PathVariable("id") Long id) {
        groupService.deleteGroup(id);
    }

    @GetMapping(path = "{id}")
    public GroupRequest getGroupRequest(@PathVariable("id") Long id) {
        return groupService.getGroupRequest(id);
    }

    @GetMapping(path = "all")
    public List<GroupRequest> getAllGroups() {
        return groupService.getAllGroups();
    }
}
