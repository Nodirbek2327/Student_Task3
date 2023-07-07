package com.example.repository;


import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer>, PagingAndSortingRepository<CourseEntity, Integer> {

    List<CourseEntity> findAllByName(String name);

    List<CourseEntity> findAllByPrice(String price);

    List<CourseEntity> findAllByDuration(String duration);

    List<CourseEntity> findAllByPriceBetween(String price1, String price2);

    List<CourseEntity> findAllByCreatedDateBetween(LocalDateTime date1, LocalDateTime date2);
    Page<CourseEntity> findAllByNameOrderByCreatedDate(String name, Pageable pageable);
    Page<CourseEntity> findAllByPriceOrderByCreatedDate(String price, Pageable pageable);
    Page<CourseEntity> findAllByPriceBetweenOrderByCreatedDate(String price1, String price2, Pageable pageable);
    Page<CourseEntity> findAllByCreatedDateBetweenOrderByCreatedDate(LocalDateTime localDateTime1, LocalDateTime localDateTime2, Pageable pageable);

}
