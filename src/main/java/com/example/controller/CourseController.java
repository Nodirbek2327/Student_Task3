package com.example.controller;

import com.example.dto.CourseDTO;
import com.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody CourseDTO courseDTO) {
        CourseDTO response = courseService.add(courseDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/all")
    public List<CourseDTO> all() {
        return courseService.getAll();
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(courseService.getById(id));
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(courseService.getByName(name));
    }

    @GetMapping(value = "/duration/{duration}")
    public ResponseEntity<?> getByDuration(@PathVariable("duration") String duration) {
        return ResponseEntity.ok(courseService.getByDuration(duration));
    }

    @GetMapping(value = "/price/{price}")
    public ResponseEntity<?> getByPrice(@PathVariable("price") String price) {
        return ResponseEntity.ok(courseService.getByPrice(price));
    }

    @GetMapping(value = "/prices/{price1}/{price2}")
    public ResponseEntity<?> getByName(@PathVariable("price1") String price1,
                                       @PathVariable("price2") String price2) {
        return ResponseEntity.ok(courseService.getByBetweenPrices(price1, price2));
    }

    @GetMapping(value = "/dates/{date1}/{date2}")
    public ResponseEntity<?> getByDates(@PathVariable("date1") String date1,
                                        @PathVariable("date2") String date2) {
        return ResponseEntity.ok(courseService.getByBetweenDates(LocalDate.parse(date1), LocalDate.parse(date2)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        Boolean response = courseService.delete(id);
        if (response) {
            return ResponseEntity.ok("course deleted");
        }
        return ResponseEntity.badRequest().body("course Not Found");
    }

    @GetMapping(value = "/pagination/{from}/{to}")
    public ResponseEntity<?> pagination(@PathVariable("from") int from,
                                        @PathVariable("to") int to) {
        return ResponseEntity.ok(courseService.studentPagination(from, to));
    }

    @GetMapping(value = "/pagination/name/{name}/{from}/{to}")
    public ResponseEntity<?> pagination(@PathVariable("name") String name,
                                        @PathVariable("from") int from,
                                        @PathVariable("to") int to) {
        return ResponseEntity.ok(courseService.studentPaginationByName(name, from, to));
    }

    @GetMapping(value = "/pagination/price/{price}/{from}/{to}")
    public ResponseEntity<?> paginationByGender(@PathVariable("price") String price,
                                                @PathVariable("from") int from,
                                                @PathVariable("to") int to) {
        return ResponseEntity.ok(courseService.studentPaginationByPrice(price, from, to));
    }

    @GetMapping(value = "/pagination/prices/{price1}/{price2}/{from}/{to}")
    public ResponseEntity<?> paginationByGender(@PathVariable("price1") String price1,
                                                @PathVariable("price2") String price2,
                                                @PathVariable("from") int from,
                                                @PathVariable("to") int to) {
        return ResponseEntity.ok(courseService.studentPaginationByPrices(price1, price2, from, to));
    }

    @GetMapping(value = "/pagination/createDate/{date1}/{date2}/{from}/{to}")
    public ResponseEntity<?> paginationByGender(@PathVariable("date1") LocalDate date1,
                                                @PathVariable("date2") LocalDate date2,
                                                @PathVariable("from") int from,
                                                @PathVariable("to") int to) {
        return ResponseEntity.ok(courseService.studentPaginationByDates(date1, date2, from, to));
    }


//    @PutMapping(value = "/{id}")
//    public ResponseEntity<?> put(@RequestBody CourseDTO courseDTO,
//                                 @PathVariable("id") String id) {
//        courseService.update(id, courseDTO);
//        return ResponseEntity.ok(true);
//    }


}
