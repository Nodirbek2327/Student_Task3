package com.example.service;

import com.example.dto.CourseDTO;
import com.example.entity.CourseEntity;
import com.example.exp.AppBadRequestException;
import com.example.exp.ItemNotFoundException;
import com.example.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public CourseDTO add(CourseDTO dto) {
        dto.setCreatedDate(LocalDate.now());
        check(dto);
        CourseEntity entity = toEntity(dto);
        courseRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<CourseDTO> getAll() {
        Iterable<CourseEntity> iterable = courseRepository.findAll();
        List<CourseDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }


    public CourseDTO getById(Integer id) {
        Optional<CourseEntity> optional = courseRepository.findById(id);
        if (optional.isEmpty()) {
            throw  new ItemNotFoundException("course not found");
        }
        CourseEntity entity = optional.get();
        return toDTO(entity);
    }


    public Boolean delete(Integer id) {
        Optional<CourseEntity> optional = courseRepository.findById(id);
        if (optional.isEmpty()) {
            return false;
        }
        courseRepository.deleteById(id);
        return true;
    }

//    public Boolean update(Integer id, StudentDTO student) {
//        check(student);
//        Optional<StudentEntity> optional = studentRepository.update(student.getId(), student.getName(), student.getSurname(), student.getAge(), student.getCreated_date(),
//                student.getGender(), student.getLevel(), id);
//        return optional.isPresent();
//    }

    private void check(CourseDTO student) {
        if (student.getName() == null || student.getName().isBlank()) {
            throw new AppBadRequestException("Name qani?");
        }
        if (student.getPrice() == null || student.getPrice().isBlank()) {
            throw new AppBadRequestException("price qani?");
        }
    }

    public CourseDTO toDTO(CourseEntity entity){
        CourseDTO dto = new CourseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDuration(entity.getDuration());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public CourseEntity toEntity(CourseDTO dto){
        CourseEntity entity = new CourseEntity();
        entity.setName(dto.getName());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setPrice(dto.getPrice());
        entity.setDuration(dto.getDuration());
        return entity;
    }

    public List<CourseDTO> getByName(String name) {
        List<CourseEntity> iterable = courseRepository.findAllByName(name);
        return getCourseDTOS(iterable);
    }



    public List<CourseDTO> getByPrice(String price) {
        List<CourseEntity> iterable = courseRepository.findAllByPrice(price);
        return getCourseDTOS(iterable);
    }

    public List<CourseDTO> getByDuration(String duration) {
        List<CourseEntity> iterable = courseRepository.findAllByDuration(duration);
        return getCourseDTOS(iterable);
    }

    public List<CourseDTO> getByBetweenPrices(String price1, String price2) {
        List<CourseEntity> iterable = courseRepository.findAllByPriceBetween(price1, price2);
        return getCourseDTOS(iterable);
    }

    public List<CourseDTO> getByBetweenDates(LocalDate date1, LocalDate date2) {
        List<CourseEntity> list = courseRepository.findAllByCreatedDateBetween(date1, date2);
        return getCourseDTOS(list);
    }

    private List<CourseDTO> getCourseDTOS(List<CourseEntity> list) {
        if (list.isEmpty()) {
            throw  new ItemNotFoundException("Student not found");
        }
        List<CourseDTO> dtoList = new LinkedList<>();
        list.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }
}
