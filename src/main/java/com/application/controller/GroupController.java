package com.application.controller;

import com.application.model.Group;
import com.application.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

    @PostMapping
    public ResponseEntity<Group> create(@RequestBody @Valid Group group, BindingResult result) {
        if (result.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        groupService.create(group);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Group> read(@PathVariable("id") Long id) {
        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Group group = groupService.read(id);
        if (group == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Group> update(@RequestBody @Valid Group group, BindingResult result) {
        if (result.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        groupService.update(group);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Group> delete(@PathVariable("id") Long id) {
        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        groupService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping//????
    public ResponseEntity<List<Group>> getAll() {
        return new ResponseEntity<>(groupService.getAll(), HttpStatus.OK);
    }
}
