package com.urianskui.studentweb.controller;

import com.urianskui.studentweb.exceptions.ApiException;
import com.urianskui.studentweb.exceptions.IllegalRequestParameterException;
import com.urianskui.studentweb.model.Student;
import com.urianskui.studentweb.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;


    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable long id) {
        return new ResponseEntity<>(studentService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.create(student), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Student> update(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.update(student), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAll(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "orderBy", required = false) String orderBy,
            @RequestParam(value = "page", required = false) String page) {
        List<Student> students = studentService.findAll(search, orderBy, page);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
