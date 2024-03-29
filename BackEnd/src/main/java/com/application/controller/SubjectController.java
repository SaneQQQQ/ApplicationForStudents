package com.application.controller;

import com.application.dto.FullSubjectDTO;
import com.application.dto.SubjectDTO;
import com.application.service.SubjectService;
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
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<SubjectDTO> create(@RequestBody @Valid SubjectDTO subject) {
        return new ResponseEntity<>(subjectService.create(subject), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> read(@PathVariable("id") Long id) {
        return new ResponseEntity<>(subjectService.read(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<FullSubjectDTO>> readAll(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                        @RequestParam(value = "page_size", required = false, defaultValue = "10") int pageSize,
                                                        @RequestParam(value = "sort_by", required = false) String sortBy,
                                                        @RequestParam(value = "order", required = false) String order) {
        Sort sort = sortBy != null && order != null ? Sort.by(Sort.Direction.fromString(order), sortBy) : Sort.unsorted();
        return new ResponseEntity<>(subjectService.readAll(PageRequest.of(page, pageSize, sort)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<SubjectDTO> update(@RequestBody @Valid SubjectDTO subject) {
        return new ResponseEntity<>(subjectService.update(subject), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable("id") Long id) {
        subjectService.delete(id);
        return new ResponseEntity<>(Collections.singletonMap("message", "Subject with id " + id + " was deleted"), HttpStatus.OK);
    }
}
