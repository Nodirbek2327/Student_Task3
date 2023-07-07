package com.example.repository;

import com.example.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface StudentRepository extends CrudRepository<StudentEntity, Integer>, PagingAndSortingRepository<StudentEntity, Integer> {
//    @Modifying
//    @Query("UPDATE StudentEntity as s SET s.id = ?1, s.name = ?2, s.surname = ?3, s.age = ?4, " +
//            "s.created_date = ?5, s.gender = ?6, s.level = ?7 WHERE s.id = ?8")
//    Optional<StudentEntity> update(Integer id, String name, String surname, Integer age, LocalDateTime createdDate, String gender, String level, Integer old_id );
//    @Modifying
//    @Query("UPDATE StudentEntity e SET e = :newEntity WHERE e.id = :entityId")
//    int updateAttribute(@Param("entityId") Integer entityId, @Param("newEntity") StudentEntity newEntity);

    List<StudentEntity> findAllByName(String name);

    List<StudentEntity> findAllBySurname(String surname);
    List<StudentEntity> findAllByLevel(String level);
    List<StudentEntity> findAllByAge(Integer age);
    List<StudentEntity> findAllByGender(String gender);
    List<StudentEntity> findAllByCreatedDateStartsWith(String date);
    List<StudentEntity> findAllByCreatedDateBetween(LocalDateTime date1, LocalDateTime date2);

    //*********************************    Another   ***************************************
    List<StudentEntity> findAllByAgeBetween(Integer from, Integer to);
   // List<StudentEntity> findAllByCreatedDateBetween(LocalDateTime from, LocalDateTime to);
    List<StudentEntity> findAllByCreatedDateAfter(LocalDateTime date);
    List<StudentEntity> findAllByNameLike(String data);
    Page<StudentEntity> findStudentEntitiesByLevelOrderById(String level, Pageable pageable);
    Page<StudentEntity> findAllByGenderOrderByCreatedDateAsc(String gender, Pageable pageable);
    Page<StudentEntity> findAllByCreatedDateBetweenOrderByCreatedDateAsc(LocalDateTime localDateTime1, LocalDateTime localDateTime2, Pageable pageable);

    //  select * from student where age in (18,20,24)
    List<StudentEntity> findAllByAgeIn(List<Integer> ageList);

    //  select * from student where age in (18,20,24)
//    List<StudentEntity> findAllByVisibleTrue(); // where visible = true
//
//    List<StudentEntity> findAllByVisible(Boolean visible); // where visible = ?

//    StudentEntity findFirstByAgeOrderByCreatedDateDesc(Integer age);
//
//    StudentEntity findTopByAge(Integer age); // where age = ?  limit 1
//
//    List<StudentEntity> findTop3ByAge(Integer age); // where age = ?  limit 3
//    Long countAllByAge(Integer age); // select count(*) from student where age = ?
//    Long countAllBy(); // select count(*) from student

}
