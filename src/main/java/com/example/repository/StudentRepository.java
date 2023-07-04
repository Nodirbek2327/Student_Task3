package com.example.repository;

import com.example.entity.StudentEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {
//    @Modifying
//    @Query("UPDATE StudentEntity as s SET s.id = ?1, s.name = ?2, s.surname = ?3, s.age = ?4, " +
//            "s.created_date = ?5, s.gender = ?6, s.level = ?7 WHERE s.id = ?8")
//    Optional<StudentEntity> update(Integer id, String name, String surname, Integer age, LocalDateTime createdDate, String gender, String level, Integer old_id );
    @Modifying
    @Query("UPDATE StudentEntity e SET e = :newEntity WHERE e.id = :entityId")
    int updateAttribute(@Param("entityId") Integer entityId, @Param("newEntity") StudentEntity newEntity);

    List<StudentEntity> findAllByName(String name);

    List<StudentEntity> findAllBySurname(String surname);
    List<StudentEntity> findAllByLevel(String level);
    List<StudentEntity> findAllByAge(Integer age);
    List<StudentEntity> findAllByGender(String gender);
    List<StudentEntity> findAllByCreatedDate(LocalDate date);

    List<StudentEntity> findAllByCreatedDateBetween(LocalDate date1, LocalDate date2);
}
