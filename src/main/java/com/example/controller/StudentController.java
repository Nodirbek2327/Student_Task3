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
        return ResponseEntity.ok(studentService.getBetweenDates(LocalDate.parse(date1),LocalDate.parse(date2)));
    }


    @GetMapping(value = "/pagination/{from}/{to}")
    public ResponseEntity<?> pagination(@PathVariable("from") int from,
                                        @PathVariable("to") int to) {
        return ResponseEntity.ok(studentService.studentPagination(from-1, to));
    }

    @GetMapping(value = "/pagination/level/{level}/{from}/{to}")
    public ResponseEntity<?> pagination(@PathVariable("level") String level,
                                        @PathVariable("from") int from,
                                        @PathVariable("to") int to) {
        return ResponseEntity.ok(studentService.studentPaginationByLevel(level, from-1, to));
    }

    @GetMapping(value = "/pagination/gender/{gender}/{from}/{to}")
    public ResponseEntity<?> paginationByGender(@PathVariable("gender") String gender,
                                        @PathVariable("from") int from,
                                        @PathVariable("to") int to) {
        return ResponseEntity.ok(studentService.studentPaginationByGender(gender, from-1, to));
    }

    @GetMapping(value = "/pagination/createDate/{date1}/{date2}/{from}/{to}")
    public ResponseEntity<?> paginationByGender(@PathVariable("date1") LocalDate date1,
                                                @PathVariable("date2") LocalDate date2,
                                                @PathVariable("from") int from,
                                                @PathVariable("to") int to) {
        return ResponseEntity.ok(studentService.studentPaginationByBetweenDates(date1, date2, from-1, to));
    }


//************************************  Another  project   **************************************
//    @GetMapping(value = "/ages/{from}/{to}")
//    public ResponseEntity<?> getByAges(@PathVariable("from") Integer from,
//                                       @PathVariable("to") Integer to) {
//        return ResponseEntity.ok(studentService.getBetweenAge(from, to));
//    }
//
////    @GetMapping(value = "/date/{date}")
////    public ResponseEntity<?> getByDate(@PathVariable("date") String date) {
////        return ResponseEntity.ok(studentService.getBetweenDate(LocalDate.parse(date)));
////    }
//
//    @GetMapping(value = "/isAfter/{date}")
//    public ResponseEntity<?> getByDate(@PathVariable("date") LocalDate date) {
//        return ResponseEntity.ok(studentService.getDateIsAfter(date));
//    }
//
//    @GetMapping(value = "/likeName/{name}")
//    public ResponseEntity<?> getNameLike(@PathVariable("name") String name) {
//        return ResponseEntity.ok(studentService.getNameLike(name));
//    }

    @PostMapping(value = "/ageIn")
    public ResponseEntity<?> getByDate(@RequestBody List<Integer> idList) {
//        studentService.getAllDateFrom(idList);
        return ResponseEntity.ok().build();
    }


//    @PostMapping("/create/all")
//    public ResponseEntity<?> create(@RequestBody List<StudentDTO> list) {
//        return ResponseEntity.ok(studentService.addAll(list));
//    }



}
