package com.example.repository;

import com.example.entity.StudentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;


public interface StudentRepository extends CrudRepository<StudentEntity, Integer>, PagingAndSortingRepository<StudentEntity, Integer> {
//    @Modifying
//    @Query("UPDATE StudentEntity as s SET s.id = ?1, s.name = ?2, s.surname = ?3, s.age = ?4, " +
//            "s.created_date = ?5, s.gender = ?6, s.level = ?7 WHERE s.id = ?8")
//    Optional<StudentEntity> update(Integer id, String name, String surname, Integer age, LocalDateTime createdDate, String gender, String level, Integer old_id );

    @Transactional
    @Modifying
    @Query("UPDATE StudentEntity e SET e = :newEntity WHERE e.id = :entityId")
    int updateAttribute(@Param("entityId") Integer entityId, @Param("newEntity") StudentEntity newEntity);
    @Transactional
    @Modifying
    @Query("select new StudentEntity (s.id, s.name, s.surname) from StudentEntity as s where s.name =:name  order by s.createdDate limit 1")
    List<StudentEntity> getStudentShortDetailLimit(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(" from StudentEntity as s where s.name =:name")
    List<StudentEntity> findAllByName(@Param("name") String name);
    @Transactional
    @Modifying
    @Query(" from StudentEntity as s where s.surname =:surname")
    List<StudentEntity> findAllBySurname(@Param("surname") String surname);

    @Transactional
    @Modifying
    @Query(" from StudentEntity as s where s.level =:level")
    List<StudentEntity> findAllByLevel(@Param("level") String level);

    @Transactional
    @Modifying
    @Query(" from StudentEntity as s where s.age =:age")
    List<StudentEntity> findAllByAge(@Param("age") Integer age);

    @Transactional
    @Modifying
    @Query(" from StudentEntity as s where s.gender =:gender")
    List<StudentEntity> findAllByGender(@Param("gender") String gender);

    @Transactional
    @Modifying
    @Query(" from StudentEntity as s where TO_CHAR(s.createdDate, 'YYYY-MM-DD HH24:MI:SS') like :data")
    List<StudentEntity> findAllByCreatedDateStartsWith(@Param("date") String data);

    @Transactional
    @Modifying
    @Query("from StudentEntity as s where s.createdDate between :date1 and :date2")
    List<StudentEntity> findAllByCreatedDateBetween(@Param("date1") LocalDateTime date1, @Param("date1") LocalDateTime date2);

    @Transactional
    @Modifying
    @Query("from StudentEntity as s where s.level = :level")
    Page<StudentEntity> findStudentEntitiesByLevelOrderById(@Param("level") String level, Pageable pageable);

    @Transactional
    @Modifying
    @Query("from StudentEntity as s where s.gender = :gender order by s.createdDate ASC ")
    Page<StudentEntity> findAllByGenderOrderByCreatedDateAsc(@Param("gender") String gender, Pageable pageable);

    @Transactional
    @Modifying
    @Query("from StudentEntity as s where s.createdDate between :date1 and :date2 order by s.createdDate ASC ")
    Page<StudentEntity> findAllByCreatedDateBetweenOrderByCreatedDateAsc(@Param("date1") LocalDateTime date1, @Param("date2") LocalDateTime date2, Pageable pageable);

    //    List<StudentEntity> findAllByName(String name);
//
//    List<StudentEntity> findAllBySurname(String surname);
//    List<StudentEntity> findAllByLevel(String level);
//    List<StudentEntity> findAllByAge(Integer age);
//    List<StudentEntity> findAllByGender(String gender);
//    List<StudentEntity> findAllByCreatedDateStartsWith(String date);
//    List<StudentEntity> findAllByCreatedDateBetween(LocalDateTime date1, LocalDateTime date2);
  //  Page<StudentEntity> findStudentEntitiesByLevelOrderById(String level, Pageable pageable);
  //  Page<StudentEntity> findAllByGenderOrderByCreatedDateAsc(String gender, Pageable pageable);
   // Page<StudentEntity> findAllByCreatedDateBetweenOrderByCreatedDateAsc(LocalDateTime localDateTime1, LocalDateTime localDateTime2, Pageable pageable);






    //*********************************    Another   ***************************************
//    List<StudentEntity> findAllByAgeBetween(Integer from, Integer to);
//   // List<StudentEntity> findAllByCreatedDateBetween(LocalDateTime from, LocalDateTime to);
//    List<StudentEntity> findAllByCreatedDateAfter(LocalDateTime date);
//    List<StudentEntity> findAllByNameLike(String data);

    //  select * from student where age in (18,20,24)
  //  List<StudentEntity> findAllByAgeIn(List<Integer> ageList);

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
