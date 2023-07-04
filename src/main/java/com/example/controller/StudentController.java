package com.example.controller;
import com.example.dto.StudentDTO;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody StudentDTO student) {
        StudentDTO response = studentService.add(student);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<StudentDTO> all() {
        return studentService.getAll();
    }


    @GetMapping(value = "/id/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.getById(id));
    }


    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> put(@RequestBody StudentDTO student,
                                 @PathVariable("id") Integer id) {
        return ResponseEntity.ok( studentService.update(id, student));
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        Boolean response = studentService.delete(id);
        if (response) {
            return ResponseEntity.ok("Student deleted");
        }
        return ResponseEntity.badRequest().body("Student Not Found");
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(studentService.getByName(name));
    }

    @GetMapping(value = "/surname/{surname}")
    public ResponseEntity<?> getBySurname(@PathVariable("surname") String surname) {
        return ResponseEntity.ok(studentService.getBySurname(surname));
    }

    @GetMapping(value = "/level/{level}")
    public ResponseEntity<?> getByLevel(@PathVariable("level") String level) {
        return ResponseEntity.ok(studentService.getByLevel(level));
    }

    @GetMapping(value = "/age/{age}")
    public ResponseEntity<?> getByAge(@PathVariable("age") Integer age) {
        return ResponseEntity.ok(studentService.getByAge(age));
    }

    @GetMapping(value = "/gender/{gender}")
    public ResponseEntity<?> getByGender(@PathVariable("gender") String gender) {
        return ResponseEntity.ok(studentService.getByGender(gender));
    }

    @GetMapping(value = "/date/{createdDate}")
    public ResponseEntity<?> getByCreated_date(@PathVariable("createdDate") String date) {
        return ResponseEntity.ok(studentService.getByCreated_date(date));
    }

    @GetMapping(value = "/dates/{date1}/{date2}")
    public ResponseEntity<?> getByDates(@PathVariable("date1") String date1,
                                        @PathVariable("date2") String date2) {
        return ResponseEntity.ok(studentService.getByBetweenDates(LocalDate.parse(date1), LocalDate.parse(date2)));
    }
//    @PostMapping("/create/all")
//    public ResponseEntity<?> create(@RequestBody List<StudentDTO> list) {
//        return ResponseEntity.ok(studentService.addAll(list));
//    }



}
