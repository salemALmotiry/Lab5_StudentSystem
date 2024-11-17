package com.example.lab5_studentsystem.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    int ID ;
    String name ;
    int age;
    String degree;
    double GPA;
}
