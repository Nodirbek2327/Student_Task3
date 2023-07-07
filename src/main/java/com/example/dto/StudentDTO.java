package com.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class StudentDTO {
   private Integer id;
   private String name;
   private String surname;
   private String level;
   private Integer age;
   private String gender;
   private LocalDateTime createdDate;
}
