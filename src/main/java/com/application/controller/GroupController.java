package com.application.controller;

import com.application.model.Group;
import com.application.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<Group> create(@RequestBody @Valid Group group) {
        return new ResponseEntity<>(groupService.create(group), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> read(@PathVariable("id") Long id) {
        return new ResponseEntity<>(groupService.read(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Group> update(@RequestBody @Valid Group group) {
        return new ResponseEntity<>(groupService.update(group), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Group> delete(@PathVariable("id") Long id) {
        if (!groupService.delete(id))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Group>> readAll() {
        return new ResponseEntity<>(groupService.readAll(), HttpStatus.OK);
    }
}
