package com.example.dto;

import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import com.example.entity.StudentMarkEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class StudentMarkDTO {
   private Integer id;
   private StudentEntity studentId;
   private CourseEntity courseId;
   private Double mark;
   private LocalDateTime createdDate;
}
