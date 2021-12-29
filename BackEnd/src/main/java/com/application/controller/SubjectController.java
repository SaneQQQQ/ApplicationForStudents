package com.application.controller;

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
    public ResponseEntity<Page<SubjectDTO>> readAll(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                                    @RequestParam(value = "sort_by", required = false) String sortBy,
                                                    @RequestParam(value = "order", required = false) String order) {
        Sort sort = sortBy != null && order != null ? Sort.by(Sort.Direction.fromString(order), sortBy) : Sort.unsorted();
        return new ResponseEntity<>(subjectService.readAllSortedByTitle(PageRequest.of(page, pageSize, sort)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<SubjectDTO> update(@RequestBody @Valid SubjectDTO subject) {
        return new ResponseEntity<>(subjectService.update(subject), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        subjectService.delete(id);
        return new ResponseEntity<>("Subject with id " + id + " was deleted", HttpStatus.OK);
    }
}
