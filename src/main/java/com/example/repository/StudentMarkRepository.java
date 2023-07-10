package com.example.repository;


import com.example.entity.StudentMarkEntity;
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

public interface StudentMarkRepository extends CrudRepository<StudentMarkEntity, Integer> , PagingAndSortingRepository<StudentMarkEntity, Integer> {

   @Transactional
    @Modifying
    @Query(" update StudentMarkEntity as s  set s.studentId = :studentId, s.courseId = :courseId, s.mark = :mark" +
            " where s.id =:id")
    int update(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId, @Param("mark") Double mark, @Param("id") Integer id);


    @Transactional
    @Modifying
    @Query("from StudentMarkEntity as s where s.id = :id")
    List<StudentMarkEntity> getStudentById(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("select s.id, s.studentId.id, s.studentId.name, s.studentId.surname, s.courseId.id, s.courseId.name, s.mark, s.createdDate from StudentMarkEntity as s where s.id = :id")
    List<StudentMarkEntity> getStudentWithDetailById(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("delete StudentMarkEntity as s where s.id = :id")
    int deleteStudentById(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(" from StudentMarkEntity")
    List<StudentMarkEntity> getStudentList();

    @Transactional
    @Modifying
    @Query("select s.mark from StudentMarkEntity as s where s.studentId.id = :id and s.createdDate between :date and  :date2")
    List<StudentMarkEntity> getStudentMarkByDate(@Param("id") Integer id, @Param("date") LocalDateTime localDateTime,  @Param("date2") LocalDateTime localDateTime2);
    @Transactional
    @Modifying
    @Query("select s.mark from StudentMarkEntity as s where s.id = :id and s.createdDate between :date1 and :date2")
    List<StudentMarkEntity> getStudentMarkByBetweenDates( @Param("id") Integer id, @Param("date1") LocalDateTime localDateTime1, @Param("date2") LocalDateTime localDateTime2);

    @Transactional
    @Modifying
    @Query("select s.mark from StudentMarkEntity as s where s.id = :id order by s.createdDate desc ")
    List<Double> getStudentMarkByCreatedDate(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("select mark from StudentMarkEntity order by createdDate desc ")
    List<Double> getAllStudentMarkByCreatedDate();

     @Transactional
     @Modifying
     @Query("from StudentMarkEntity as s where s.id = :id order by s.createdDate desc ")
     List<StudentMarkEntity> getStudentLastMark(@Param("id") Integer id);

 @Transactional
 @Modifying
 @Query("from StudentMarkEntity  order by mark desc ")
 List<StudentMarkEntity> getTop3StudentByMark();

 @Transactional
 @Modifying
 @Query("from StudentMarkEntity as s where s.id = :id order by s.createdDate asc ")
 List<StudentMarkEntity> getStudentFirstMark(@Param("id") Integer id);

 @Transactional
 @Modifying
 @Query("from StudentMarkEntity as s where s.courseId.name = :name order by s.createdDate asc ")
 List<StudentMarkEntity> getStudentFirstMarkByCourse(@Param("name") String name);

 @Transactional
 @Modifying
 @Query("from StudentMarkEntity as s where s.id = :id and s.courseId.name = :name order by s.mark desc ")
 List<StudentMarkEntity> getStudentBestMarkByCourse(@Param("id") Integer id, @Param("name") String name);

 @Transactional
 @Modifying
 @Query("select  sum(s.mark)/count(*) from StudentMarkEntity as s where s.id = :id group by s.id ")
 double getStudentMediumMark(@Param("id") Integer id);

 @Transactional
 @Modifying
 @Query("select  sum(s.mark)/count(*) from StudentMarkEntity as s where s.id = :id  and s.courseId.name = :name group by s.id ")
 double getStudentMediumMarkByCourse(@Param("id") Integer id,  @Param("name") String name);

 @Transactional
 @Modifying
 @Query("select count(*) from StudentMarkEntity as s where s.id = :id  and s.mark>:mark ")
 int getStudentBigMark(@Param("id") Integer id,  @Param("mark") double mark);


 @Transactional
 @Modifying
 @Query("from StudentMarkEntity")
 Page<StudentMarkEntity> studentMarkPagination(Pageable pageable);

 @Transactional
 @Modifying
 @Query("from StudentMarkEntity as s where s.studentId.id = :id order by s.createdDate")
 Page<StudentMarkEntity> findStudentMarkPaginationByStudentId(@Param("id") Integer id, Pageable pageable);

 @Transactional
 @Modifying
 @Query("from StudentMarkEntity as s where s.courseId.id = :id order by s.createdDate")
 Page<StudentMarkEntity> findStudentMarkPaginationByCourseId(@Param("id") Integer id, Pageable pageable);


}
