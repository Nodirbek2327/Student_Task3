package com.example.repository;


import com.example.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer> {

    List<CourseEntity> findAllByName(String name);

    List<CourseEntity> findAllByPrice(String price);

    List<CourseEntity> findAllByDuration(String duration);

    List<CourseEntity> findAllByPriceBetween(String price1, String price2);

    List<CourseEntity> findAllByCreatedDateBetween(LocalDate date1, LocalDate date2);
}
