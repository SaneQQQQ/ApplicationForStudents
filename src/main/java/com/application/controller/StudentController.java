package com.application.controller;

import com.application.model.Student;
import com.application.model.StudentSubject;
import com.application.service.StudentService;
import com.application.service.StudentSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/students/", "/"})
public class StudentController {

    private final StudentService studentService;
    private final StudentSubjectService studentSubjectService;

    @Autowired
    public StudentController(StudentService studentService, StudentSubjectService studentSubjectService) {
        this.studentService = studentService;
        this.studentSubjectService = studentSubjectService;
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody @Valid Student student) {
        return new ResponseEntity<>(studentService.create(student), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> read(@PathVariable("id") Long id) {
        return new ResponseEntity<>(studentService.read(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Student> update(@RequestBody @Valid Student student) {
        if (!studentService.update(student))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> delete(@PathVariable("id") Long id) {
        if (!studentService.delete(id))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Student>> readAll() {
        return new ResponseEntity<>(studentService.readAll(), HttpStatus.OK);
    }

    @PostMapping("/marks")
    public ResponseEntity<StudentSubject> createMark(@RequestBody @Valid StudentSubject studentSubject) {
        return new ResponseEntity<>(studentSubjectService.create(studentSubject), HttpStatus.OK);
    }

    @GetMapping("{student_id}/marks/{subject_id}")
    public ResponseEntity<StudentSubject> readMark(@PathVariable("student_id") Long studentId, @PathVariable("subject_id") Long subjectId) {
        return new ResponseEntity<>(studentSubjectService.read(studentId, subjectId), HttpStatus.OK);
    }

    @PutMapping("/marks")
    public ResponseEntity<StudentSubject> updateMark(@RequestBody @Valid StudentSubject studentSubject) {
        if (!studentSubjectService.update(studentSubject))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(studentSubject, HttpStatus.OK);
    }

    @DeleteMapping("{student_id}/marks/{subject_id}")
    public ResponseEntity<StudentSubject> deleteMark(@PathVariable("student_id") Long studentId, @PathVariable("subject_id") Long subjectId) {
        if (!studentSubjectService.delete(studentId, subjectId))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/marks")
    public ResponseEntity<List<StudentSubject>> readAllMarksByStudent() {
        return new ResponseEntity<>(studentSubjectService.readAll(), HttpStatus.OK);
    }

    @GetMapping("{student_id}/marks")
    public ResponseEntity<List<StudentSubject>> readAllMarksByStudent(@PathVariable("student_id") Long studentId) {
        return new ResponseEntity<>(studentSubjectService.readAllByStudentId(studentId), HttpStatus.OK);
    }
}
