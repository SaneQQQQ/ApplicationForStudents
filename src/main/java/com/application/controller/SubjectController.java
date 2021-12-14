package com.application.controller;

import com.application.model.Subject;
import com.application.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid Subject subject) {
        return new ResponseEntity<>("Subject with id " + subjectService.create(subject) + " was created", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> read(@PathVariable("id") Long id) {
        return new ResponseEntity<>(subjectService.read(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Subject>> readAll() {
        return new ResponseEntity<>(subjectService.readAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Subject> update(@RequestBody @Valid Subject subject) {
        return new ResponseEntity<>(subjectService.update(subject), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        subjectService.delete(id);
        return new ResponseEntity<>("Subject with id " + id + " was deleted", HttpStatus.OK);
    }
}
