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
    public ResponseEntity<SubjectDTO> create(@RequestBody @Valid SubjectDTO subject) {
        return new ResponseEntity<>(subjectService.create(subject), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> read(@PathVariable("id") Long id) {
        return new ResponseEntity<>(subjectService.read(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> readAll() {
        return new ResponseEntity<>(subjectService.readAll(), HttpStatus.OK);
    }

    @GetMapping(params = {"page", "size", "sort_by", "order"})
    public ResponseEntity<Page<SubjectDTO>> readAllSortedByTitle(@RequestParam(name = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "size", defaultValue = "5") int size,
                                                    @RequestParam(value = "sort_by", defaultValue = "title") String sortBy,
                                                    @RequestParam(value = "order", defaultValue = "asc") String order) {
        return new ResponseEntity<>(subjectService.readAllSortedByTitle(PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sortBy))), HttpStatus.OK);
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
