package com.example.Library_Project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentBook {
    private String id;
    private Student student;
    private Book book;
    private LocalDate createdDate;
    private LocalDate returnedDate;
    private String duration;
}
