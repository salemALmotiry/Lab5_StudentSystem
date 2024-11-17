package com.example.lab5_studentsystem.studentController;

import com.example.lab5_studentsystem.apiResponse.ApiResponse;
import com.example.lab5_studentsystem.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/student-system")
public class StudentController {
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Student> studentsAboveAverage = new ArrayList<>();

    @GetMapping("/show")
    public ArrayList<Student> getStudents() {
        return students;
    }

    @PostMapping("create-student")
    public ApiResponse createStudent(@RequestBody Student student) {
        if (students.contains(student)) {
            return new ApiResponse("Student already exists");
        }


        this.students.add(student);
        return new ApiResponse("Successfully created student");
    }

    @PutMapping("/update-student/{index}")
    public ApiResponse updateStudent(@PathVariable int index,@RequestBody Student student) {
        if(index > students.size() || index < 0) {
            return new ApiResponse("student does not exist");
        }
        this.students.set(index, student);
        return new ApiResponse("Student updated successfully");
    }


    @DeleteMapping("/delete-student/{index}")
    public ApiResponse deleteStudent(@PathVariable int index) {
        if(index > students.size() || index < 0) {
            return new ApiResponse("student does not exist");
        }
        this.students.remove(index);
        return new ApiResponse("Student deleted successfully");
    }

    @GetMapping("/get-above-average")
    public ArrayList<Student> getOverAverage() {
        double average = 0;

        for(Student student : students) {
            average += student.getGPA();
        }
        average /= students.size();

        for(Student student : students) {
            if(student.getGPA() > average) {
                studentsAboveAverage.add(student);
            }
        }
        return studentsAboveAverage;
    }


    @GetMapping("/honor/{id}")
    public Map<String, String> HonorsStudents(@PathVariable int id ) {
        HashMap<String, String> map = new HashMap<>();

        for(Student student : students) {
           if (student.getID() == id) {
               map.put("name",student.getName());
               map.put("Degree",student.getDegree());
               map.put("GPA",String.valueOf(student.getGPA()));

               if (student.getID()>4.75){
                   map.put("honor","First honor degree");
               }else if(student.getID()>4.25){
                   map.put("honor","First honor degree");

               }else {
                   map.put("honor","None");
               }
           }
        }
       return map;
    }





}
