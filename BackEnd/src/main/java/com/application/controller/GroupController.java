package com.application.controller;

import com.application.dto.GroupDTO;
import com.application.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<GroupDTO> create(@RequestBody @Valid GroupDTO group) {
        return new ResponseEntity<>(groupService.create(group), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> read(@PathVariable("id") Long id) {
        return new ResponseEntity<>(groupService.read(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<GroupDTO>> readAll(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                  @RequestParam(value = "page_size", required = false, defaultValue = "10") int pageSize,
                                                  @RequestParam(value = "sort_by", required = false) String sortBy,
                                                  @RequestParam(value = "order", required = false) String order) {
        Sort sort = sortBy != null && order != null ? Sort.by(Sort.Direction.fromString(order), sortBy) : Sort.unsorted();
        return new ResponseEntity<>(groupService.readAll(PageRequest.of(page, pageSize, sort)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<GroupDTO> update(@RequestBody @Valid GroupDTO group) {
        return new ResponseEntity<>(groupService.update(group), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable("id") Long id) {
        groupService.delete(id);
        return new ResponseEntity<>(Collections.singletonMap("message", "Group with id " + id + " was deleted"), HttpStatus.OK);
    }
}
