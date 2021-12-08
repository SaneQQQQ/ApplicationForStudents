package com.application.controller;

import com.application.model.Student;
import com.application.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/students/", "/"})
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody @Valid Student student, BindingResult result) {
        if (result.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(studentService.create(student), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> read(@PathVariable("id") Long id) {
        return new ResponseEntity<>(studentService.read(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Student> update(@RequestBody @Valid Student student, BindingResult result) {
        if (!studentService.update(student) || result.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> delete(@PathVariable("id") Long id) {
        if (!studentService.delete(id) || id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Student>> readAll() {
        return new ResponseEntity<>(studentService.readAll(), HttpStatus.OK);
    }
}
