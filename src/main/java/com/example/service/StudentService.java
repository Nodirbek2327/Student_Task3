package com.example.service;

import com.example.dto.StudentDTO;
import com.example.entity.StudentEntity;
import com.example.exp.AppBadRequestException;
import com.example.exp.ItemNotFoundException;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO add(StudentDTO dto) {
        dto.setCreatedDate(LocalDate.now());
        check(dto);
        StudentEntity entity = toEntity(dto);
        studentRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }


//    public List<StudentDTO> addAll(List<StudentDTO> list) {
//        for (StudentDTO dto : list) {
//            StudentEntity entity = toEntity(dto);
//            studentRepository.save(entity);
//            dto.setId(entity.getId());
//        }
//        return list;
//    }


    public List<StudentDTO> getAll() {
        Iterable<StudentEntity> iterable = studentRepository.findAll();
        List<StudentDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }


    public StudentDTO getById(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw  new ItemNotFoundException("Student not found");
        }
        StudentEntity entity = optional.get();
        return toDTO(entity);
    }


    public Boolean delete(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }

    public Boolean update(Integer id, StudentDTO student) {
        check(student);
        int effectedRows = studentRepository.updateAttribute(id,  toEntity(student));
        return effectedRows>0;
    }

    private void check(StudentDTO student) {
        if (student.getName() == null || student.getName().isBlank()) {
            throw new AppBadRequestException("Name qani?");
        }
        if (student.getSurname() == null || student.getSurname().isBlank()) {
            throw new AppBadRequestException("Surname qani?");
        }
    }

    public StudentDTO toDTO(StudentEntity entity){
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setAge(entity.getAge());
        dto.setLevel(entity.getLevel());
        dto.setGender(entity.getGender());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public StudentEntity toEntity(StudentDTO dto){
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setLevel(dto.getLevel());
        entity.setGender(dto.getGender());
        return entity;
    }

    public List<StudentDTO> getByName(String name) {
        List<StudentEntity> iterable = studentRepository.findAllByName(name);
        return getStudentDTOS(iterable);
    }

    public List<StudentDTO> getBySurname(String surname) {
        List<StudentEntity> iterable = studentRepository.findAllBySurname(surname);
        return getStudentDTOS(iterable);
    }

    public List<StudentDTO> getByLevel(String level) {
        List<StudentEntity> iterable = studentRepository.findAllByLevel(level);
        return getStudentDTOS(iterable);
    }

    public List<StudentDTO> getByAge(Integer age) {
        List<StudentEntity> iterable = studentRepository.findAllByAge(age);
        return getStudentDTOS(iterable);
    }

    public List<StudentDTO> getByGender(String gender) {
        List<StudentEntity> iterable = studentRepository.findAllByGender(gender);
        return getStudentDTOS(iterable);
    }

    public List<StudentDTO> getByCreated_date(String date) {
        List<StudentEntity> iterable = studentRepository.findAllByCreatedDate(LocalDate.parse(date));
        return getStudentDTOS(iterable);
    }

    public List<StudentDTO> getByBetweenDates(LocalDate date1, LocalDate date2) {
        List<StudentEntity> iterable = studentRepository.findAllByCreatedDateBetween(date1, date2);
        return getStudentDTOS(iterable);
    }
    private List<StudentDTO> getStudentDTOS(List<StudentEntity> list) {
        if (list.isEmpty()) {
            throw  new ItemNotFoundException("Student not found");
        }
        List<StudentDTO> dtoList = new LinkedList<>();
        list.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }
}
