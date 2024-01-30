package com.project.zpo.Groups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "groups")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping(path = "add")
    public void addGroup(@RequestBody String name) {
        groupService.addGroup(name);
    }

    @GetMapping(path = "delete/{id}")
    public void deleteGroup(@PathVariable("id") Long id) {
        groupService.deleteGroup(id);
    }

    @GetMapping(path = "get/{id}")
    public Group getGroup(@PathVariable("id") Long id) {
        return groupService.getGroup(id).orElse(null);
    }
}
