package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "marks")
@Setter
@Getter
public class StudentMarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="studentId", nullable = false)
    private StudentEntity studentId;
    @ManyToOne
    @JoinColumn(name="courseId", nullable=false)
    private CourseEntity courseId;
    @Column(name = "mark")
    private Double mark;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
