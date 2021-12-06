package com.application.controller;

import com.application.model.Group;
import com.application.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/groups/")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Group> create(@RequestBody @Valid Group group) {
        groupService.create(group);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Group> read(@PathVariable("id") Long id) {
        return new ResponseEntity<>(groupService.read(id), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Group> update(@RequestBody @Valid Group group) {
        groupService.update(group);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Group> delete(@PathVariable("id") Long id) {
        groupService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Group>> getAll() {
        return new ResponseEntity<>(groupService.getAll(), HttpStatus.OK);
    }
}
