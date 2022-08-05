package com.application.controller;

import com.application.dto.FullStudentDTO;
import com.application.dto.FullStudentSubjectDTO;
import com.application.dto.StudentDTO;
import com.application.dto.StudentSubjectDTO;
import com.application.service.StudentService;
import com.application.service.StudentSubjectService;
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
@RequestMapping({"/students"})
public class StudentController {

    private final StudentService studentService;
    private final StudentSubjectService studentSubjectService;

    @Autowired
    public StudentController(StudentService studentService, StudentSubjectService studentSubjectService) {
        this.studentService = studentService;
        this.studentSubjectService = studentSubjectService;
    }

    @PostMapping
    public ResponseEntity<FullStudentDTO> create(@RequestBody @Valid FullStudentDTO student) {
        return new ResponseEntity<>(studentService.create(student), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FullStudentDTO> read(@PathVariable("id") Long id) {
        return new ResponseEntity<>(studentService.read(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<FullStudentDTO>> readAll(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                        @RequestParam(value = "page_size", required = false, defaultValue = "10") int pageSize,
                                                        @RequestParam(value = "sort_by", required = false) String sortBy,
                                                        @RequestParam(value = "order", required = false) String order) {
        Sort sort = sortBy != null && order != null ? Sort.by(Sort.Direction.fromString(order), sortBy) : Sort.unsorted();
        return new ResponseEntity<>(studentService.readAll(PageRequest.of(page, pageSize, sort)), HttpStatus.OK);
    }

    @GetMapping("/group/{group_id}")
    public ResponseEntity<Page<StudentDTO>> readAllByGroupId(@PathVariable("group_id") Long groupId,
                                                             @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                             @RequestParam(value = "page_size", required = false, defaultValue = "10") int pageSize,
                                                             @RequestParam(value = "sort_by", required = false) String sortBy,
                                                             @RequestParam(value = "order", required = false) String order) {
        Sort sort = sortBy != null && order != null ? Sort.by(Sort.Direction.fromString(order), sortBy) : Sort.unsorted();
        return new ResponseEntity<>(studentService.readAllByGroupId(groupId, PageRequest.of(page, pageSize, sort)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<FullStudentDTO> update(@RequestBody @Valid FullStudentDTO student) {
        return new ResponseEntity<>(studentService.update(student), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable("id") Long id) {
        studentService.delete(id);
        return new ResponseEntity<>(Collections.singletonMap("message", "Student with id " + id + " was deleted"), HttpStatus.OK);
    }

    @PostMapping("/marks")
    public ResponseEntity<FullStudentSubjectDTO> createMark(@RequestBody @Valid FullStudentSubjectDTO studentSubject) {
        return new ResponseEntity<>(studentSubjectService.create(studentSubject), HttpStatus.OK);
    }

    @GetMapping("/{student_id}/marks/{subject_id}")
    public ResponseEntity<FullStudentSubjectDTO> readMark(@PathVariable("student_id") Long studentId, @PathVariable("subject_id") Long subjectId) {
        return new ResponseEntity<>(studentSubjectService.read(studentId, subjectId), HttpStatus.OK);
    }

    @GetMapping("/{student_id}/marks")
    public ResponseEntity<Page<StudentSubjectDTO>> readAllMarksByStudent(@PathVariable("student_id") Long studentId,
                                                                         @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                                         @RequestParam(value = "page_size", required = false, defaultValue = "10") int pageSize,
                                                                         @RequestParam(value = "sort_by", required = false) String sortBy,
                                                                         @RequestParam(value = "order", required = false) String order) {
        Sort sort = sortBy != null && order != null ? Sort.by(Sort.Direction.fromString(order), sortBy) : Sort.unsorted();
        return new ResponseEntity<>(studentSubjectService.readAllByStudentId(studentId, PageRequest.of(page, pageSize, sort)), HttpStatus.OK);
    }

    @PutMapping("/marks")
    public ResponseEntity<FullStudentSubjectDTO> updateMark(@RequestBody @Valid FullStudentSubjectDTO studentSubject) {
        return new ResponseEntity<>(studentSubjectService.update(studentSubject), HttpStatus.OK);
    }

    @DeleteMapping("/{student_id}/marks/{subject_id}")
    public ResponseEntity<Map<String, String>> deleteMark(@PathVariable("student_id") Long studentId, @PathVariable("subject_id") Long subjectId) {
        studentSubjectService.delete(studentId, subjectId);
        return new ResponseEntity<>(Collections.singletonMap("message", "Mark with student_id " + studentId + " and subject_id " + subjectId + " was deleted"), HttpStatus.OK);
    }
}
