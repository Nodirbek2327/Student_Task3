package com.example.controller;

import com.example.dto.StudentMarkDTO;
import com.example.service.StudentMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/mark")
public class StudentMarkController {
    @Autowired
    private StudentMarkService studentMarkService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody StudentMarkDTO studentMarkDTO) {
        StudentMarkDTO response = studentMarkService.add(studentMarkDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<StudentMarkDTO> all() {
        return studentMarkService.getAll();
    }


    @GetMapping(value = "/id/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentMarkService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        Boolean response = studentMarkService.delete(id);
        if (response) {
            return ResponseEntity.ok("StudentMark deleted");
        }
        return ResponseEntity.badRequest().body("StudentMark Not Found");
    }

    @GetMapping(value = "/studentId/createdDate/{id}/{date}")
    public ResponseEntity<?> getStudentMarkByStudentAndDate(@PathVariable("id") Integer id, @PathVariable("date") LocalDate date) {
        return ResponseEntity.ok(studentMarkService.getByStudentIdAndCreatedDate(id, date));
    }

    @GetMapping(value = "/id/createdDate/{id}/{date1}/{date2}")
    public ResponseEntity<?> getStudentMarkBetweenDates(@PathVariable("id") Integer id, @PathVariable("date1") LocalDate date1, @PathVariable("date2") LocalDate date2) {
        return ResponseEntity.ok(studentMarkService.getByCreatedDateBetween(id, date1, date2));
    }

    @GetMapping(value = "/marks/{id}")
    public ResponseEntity<?> getStudentMarksById(@PathVariable("id") Integer name) {
        return ResponseEntity.ok(studentMarkService.getMarksByCreatedDateDesc(name));
    }

    @GetMapping(value = "/marks")
    public ResponseEntity<?> getAllMarks() {
        return ResponseEntity.ok(studentMarkService.getAllStudentMarkByCreatedDate());
    }

    @GetMapping(value = "/mark/last/{id}")
    public ResponseEntity<?> getStudentLAstMarkById(@PathVariable("id") Integer name) {
        return ResponseEntity.ok(studentMarkService.getStudentLastMark(name));
    }

    @GetMapping(value = "/top3")
    public ResponseEntity<?> getTop3Student() {
        return ResponseEntity.ok(studentMarkService.getTop3StudentByMark());
    }

    @GetMapping(value = "/first/mark/{id}")
    public ResponseEntity<?> getFirstMark(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentMarkService.getStudentFirstMark(id));
    }

    @GetMapping(value = "/first/mark/{name}")
    public ResponseEntity<?> getFirstMark(@PathVariable("name") String name) {
        return ResponseEntity.ok(studentMarkService.getStudentFirstMarkByCourse(name));
    }

    @GetMapping(value = "/first/mark/{id}/{name}")
    public ResponseEntity<?> getFirstMark(@PathVariable("id") Integer id, @PathVariable("name") String name) {
        return ResponseEntity.ok(studentMarkService.getStudentFirstMarkByCourse(id,name));
    }

    @GetMapping(value = "/medium/mark/{id}")
    public ResponseEntity<?> getMediumMark(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentMarkService.getStudentMediumMark(id));
    }

    @GetMapping(value = "/medium/mark/{id}/{name}")
    public ResponseEntity<?> getMediumMark(@PathVariable("id") Integer id, @PathVariable("name") String name) {
        return ResponseEntity.ok(studentMarkService.getStudentMediumMarkByCourse(id,name));
    }


//    @GetMapping(value = "/pagination/{from}/{to}")
//    public ResponseEntity<?> pagination(@PathVariable("from") int from,
//                                        @PathVariable("to") int to) {
//        return ResponseEntity.ok(studentMarkService.studentPagination(from, to));
//    }
//
//    @GetMapping(value = "/pagination/level/{level}/{from}/{to}")
//    public ResponseEntity<?> pagination(@PathVariable("level") String level,
//                                        @PathVariable("from") int from,
//                                        @PathVariable("to") int to) {
//        return ResponseEntity.ok(studentMarkService.studentPaginationByLevel(level, from, to));
//    }
//
//    @GetMapping(value = "/pagination/gender/{gender}/{from}/{to}")
//    public ResponseEntity<?> paginationByGender(@PathVariable("gender") String gender,
//                                                @PathVariable("from") int from,
//                                                @PathVariable("to") int to) {
//        return ResponseEntity.ok(studentMarkService.studentPaginationByGender(gender, from, to));
//    }
//
//    @GetMapping(value = "/pagination/createDate/{date1}/{date2}/{from}/{to}")
//    public ResponseEntity<?> paginationByGender(@PathVariable("date1") LocalDate date1,
//                                                @PathVariable("date2") LocalDate date2,
//                                                @PathVariable("from") int from,
//                                                @PathVariable("to") int to) {
//        return ResponseEntity.ok(studentMarkService.studentPaginationByBetweenDates(date1, date2, from, to));
//    }

}
