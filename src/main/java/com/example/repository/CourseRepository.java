package com.example.repository;


import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer>, PagingAndSortingRepository<CourseEntity, Integer> {

    @Transactional
    @Modifying
    @Query(" from CourseEntity as s where s.name =:name")
    List<CourseEntity> findAllByName(@Param("name") String name);


    @Transactional
    @Modifying
    @Query(" from CourseEntity as s where s.price =:price")
    List<CourseEntity> findAllByPrice(@Param("price") String price);

    @Transactional
    @Modifying
    @Query(" from CourseEntity as s where s.duration =:duration")
    List<CourseEntity> findAllByDuration(@Param("duration") String duration);

    @Transactional
    @Modifying
    @Query(" from CourseEntity as s where s.price between :price1 and :price2")
    List<CourseEntity> findAllByPriceBetween(@Param("price1") String price1, @Param("price2") String price2);

    @Transactional
    @Modifying
    @Query(" from CourseEntity as s where s.createdDate between :price1 and :price2")
    List<CourseEntity> findAllByCreatedDateBetween(@Param("price1") LocalDateTime price1, @Param("price2") LocalDateTime price2);

    @Transactional
    @Modifying
    @Query(" from CourseEntity as s where s.name = :name order by s.createdDate")
    Page<CourseEntity> findAllByNameOrderByCreatedDate(@Param("name") String name, Pageable pageable);


    @Transactional
    @Modifying
    @Query(" from CourseEntity as s where s.price = :price order by s.createdDate")
    Page<CourseEntity> findAllByPriceOrderByCreatedDate(@Param("price") String price, Pageable pageable);

    @Transactional
    @Modifying
    @Query(" from CourseEntity as s where s.price between :price1 and :price2 order by s.createdDate")
    Page<CourseEntity> findAllByPriceBetweenOrderByCreatedDate(@Param("price1") String price, @Param("price2") String price2, Pageable pageable);

    @Transactional
    @Modifying
    @Query(" from CourseEntity as s where s.createdDate between :price1 and :price2 order by s.createdDate")
    Page<CourseEntity> findAllByCreatedDateBetweenOrderByCreatedDate(@Param("price1") LocalDateTime date1 , @Param("price2") LocalDateTime date2, Pageable pageable);

    // List<CourseEntity> findAllByName(String name);

   // List<CourseEntity> findAllByPrice(String price);

   // List<CourseEntity> findAllByDuration(String duration);

   // List<CourseEntity> findAllByPriceBetween(String price1, String price2);

  //  List<CourseEntity> findAllByCreatedDateBetween(LocalDateTime date1, LocalDateTime date2);
   // Page<CourseEntity> findAllByNameOrderByCreatedDate(String name, Pageable pageable);
  //  Page<CourseEntity> findAllByPriceOrderByCreatedDate(String price, Pageable pageable);
   // Page<CourseEntity> findAllByPriceBetweenOrderByCreatedDate(String price1, String price2, Pageable pageable);
   // Page<CourseEntity> findAllByCreatedDateBetweenOrderByCreatedDate(LocalDateTime localDateTime1, LocalDateTime localDateTime2, Pageable pageable);

}
