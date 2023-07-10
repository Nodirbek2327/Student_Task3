package com.example.service;

import com.example.dto.StudentMarkDTO;
import com.example.entity.StudentMarkEntity;
import com.example.exp.AppBadRequestException;
import com.example.exp.ItemNotFoundException;
import com.example.repository.StudentMarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentMarkService {
    @Autowired
    private StudentMarkRepository studentMarkRepository;


    public StudentMarkDTO add(StudentMarkDTO studentMarkDTO) {
        studentMarkDTO.setCreatedDate(LocalDateTime.now());
        check(studentMarkDTO);
        StudentMarkEntity entity = toEntity(studentMarkDTO);
        studentMarkRepository.save(entity);
        studentMarkDTO.setId(entity.getId());
        return studentMarkDTO;
    }


    public List<StudentMarkDTO> getAll() {
        Iterable<StudentMarkEntity> iterable = studentMarkRepository.getStudentList();
        List<StudentMarkDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }


    public StudentMarkDTO  getById(Integer id) {
        List<StudentMarkEntity> optional = studentMarkRepository.getStudentById(id);
        if (optional.isEmpty()) {
            throw  new ItemNotFoundException("Student not found");
        }
        StudentMarkEntity entity = optional.get(0);
        return toDTO(entity);
    }


    public Boolean delete(Integer id) {
        int effectedRows = studentMarkRepository.deleteStudentById(id);
        return effectedRows>0;
    }

    public List<StudentMarkDTO> getByStudentIdAndCreatedDate(Integer id,  LocalDate localDateTime) {
        LocalDateTime localDateTime1 = LocalDateTime.of(localDateTime, LocalTime.MIN);
        LocalDateTime localDateTime2 = LocalDateTime.of(localDateTime, LocalTime.MAX);
        List<StudentMarkEntity> iterable = studentMarkRepository.getStudentMarkByDate(id, localDateTime1, localDateTime2);
        return getStudentMarkDTOS(iterable);
    }

    public List<StudentMarkDTO> getByCreatedDateBetween(Integer id,  LocalDate localDateTime, LocalDate localDate) {
        LocalDateTime localDateTime1 = LocalDateTime.of(localDateTime, LocalTime.MIN);
        LocalDateTime localDateTime2 = LocalDateTime.of(localDate, LocalTime.MAX);
        List<StudentMarkEntity> iterable = studentMarkRepository.getStudentMarkByBetweenDates(id, localDateTime1, localDateTime2);
        return getStudentMarkDTOS(iterable);
    }

    public List<Double> getMarksByCreatedDateDesc(Integer id) {
        return studentMarkRepository.getStudentMarkByCreatedDate(id);
    }


////    public Boolean update(Integer id, StudentDTO student) {
////        check(student);
////        int effectedRows = studentRepository.updateAttribute(id,  toEntity(student));
////        return effectedRows>0;
////    }
//
//
//
//    public List<StudentDTO> getByName(String name) {
//        List<StudentEntity> iterable = studentRepository.findAllByName(name);
//        return getStudentDTOS(iterable);
//    }
//
//    public List<StudentDTO> getBySurname(String surname) {
//        List<StudentEntity> iterable = studentRepository.findAllBySurname(surname);
//        return getStudentDTOS(iterable);
//    }
//
//    public List<StudentDTO> getByLevel(String level) {
//        List<StudentEntity> iterable = studentRepository.findAllByLevel(level);
//        return getStudentDTOS(iterable);
//    }
//
//    public List<StudentDTO> getByAge(Integer age) {
//        List<StudentEntity> iterable = studentRepository.findAllByAge(age);
//        return getStudentDTOS(iterable);
//    }
//
//    public List<StudentDTO> getByGender(String gender) {
//        List<StudentEntity> iterable = studentRepository.findAllByGender(gender);
//        return getStudentDTOS(iterable);
//    }
//
//    public List<StudentDTO> getByCreated_date(String date) {
//        List<StudentEntity> iterable = studentRepository.findAllByCreatedDateStartsWith(date);
//        return getStudentDTOS(iterable);
//    }
//
//    public List<StudentDTO> getBetweenDates(LocalDate date1, LocalDate date2) {
//        LocalDateTime from = LocalDateTime.of(date1, LocalTime.MIN);
//        LocalDateTime to = LocalDateTime.of(date2, LocalTime.MAX);
//        List<StudentEntity> iterable = studentRepository.findAllByCreatedDateBetween(from, to);
//        return getStudentDTOS(iterable);
//    }
//
//
//    public ResponseEntity<?> studentPagination(int page, int size){
//        Pageable pageable = PageRequest.of(page, size);
//        Page<StudentEntity> pageObj  = studentRepository.findAll(pageable);
//        List<StudentEntity> entities = pageObj.getContent();
//        long totalCount = pageObj.getTotalElements();
//        return ResponseEntity.ok("totalCount = "+ totalCount+ " \n "+ getStudentDTOS(entities));
//    }
//
//    public ResponseEntity<?> studentPaginationByLevel(String level, int from, int to){
//        Pageable pageable = PageRequest.of(from, to);
//        Page<StudentEntity> pageObj  = studentRepository.findStudentEntitiesByLevelOrderById(level, pageable);
//        List<StudentEntity> entities = pageObj.getContent();
//        long totalCount = pageObj.getTotalElements();
//        return ResponseEntity.ok("totalCount = "+ totalCount+ " \n "+ getStudentDTOS(entities));
//    }
//
//
//
//    public ResponseEntity<?> studentPaginationByGender(String gender, int from, int to){
//        Pageable pageable = PageRequest.of(from, to);
//        Page<StudentEntity> pageObj  = studentRepository.findAllByGenderOrderByCreatedDateAsc(gender, pageable);
//        List<StudentEntity> entities = pageObj.getContent();
//        long totalCount = pageObj.getTotalElements();
//        return ResponseEntity.ok("totalCount = "+ totalCount+ " \n "+ getStudentDTOS(entities));
//    }
//
//    public ResponseEntity<?> studentPaginationByBetweenDates(LocalDate date1, LocalDate date2,  int from, int to){
//        LocalDateTime startTime = LocalDateTime.of(date1, LocalTime.MIN);
//        LocalDateTime endTime = LocalDateTime.of(date2, LocalTime.MAX);
//        Pageable pageable = PageRequest.of(from, to);
//        Page<StudentEntity> pageObj  = studentRepository.findAllByCreatedDateBetweenOrderByCreatedDateAsc(startTime,
//                endTime, pageable);
//        List<StudentEntity> entities = pageObj.getContent();
//        long totalCount = pageObj.getTotalElements();
//        return ResponseEntity.ok("totalCount = "+ totalCount+ " \n "+ getStudentDTOS(entities));
//    }

    private void check(StudentMarkDTO student) {
        if (student.getCourseId() == null) {
            throw new AppBadRequestException("course qani?");
        }
        if (student.getStudentId() == null ) {
            throw new AppBadRequestException("student qani?");
        }
    }

    public StudentMarkDTO toDTO(StudentMarkEntity entity){
        StudentMarkDTO dto = new StudentMarkDTO();
        dto.setId(entity.getId());
        dto.setCourseId(entity.getCourseId());
        dto.setMark(entity.getMark());
        dto.setStudentId(entity.getStudentId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public StudentMarkEntity toEntity(StudentMarkDTO dto){
        StudentMarkEntity entity = new StudentMarkEntity();
        entity.setStudentId(dto.getStudentId());
        entity.setMark(dto.getMark());
        entity.setCourseId(dto.getCourseId());
        entity.setCreatedDate(dto.getCreatedDate());
        return entity;
    }

    private List<StudentMarkDTO> getStudentMarkDTOS(List<StudentMarkEntity> list) {
        if (list.isEmpty()) {
            throw  new ItemNotFoundException("StudentMark not found");
        }
        List<StudentMarkDTO> dtoList = new LinkedList<>();
        list.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }


}
