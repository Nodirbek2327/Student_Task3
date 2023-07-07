package com.example.dto;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class CourseDTO {
    private Integer id;
    private String name;
    private String price;
    private String duration;
    private LocalDateTime createdDate;
}
