package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.student.StudentService;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/sayHello")
    public ResponseEntity<String> greeting(){
        return ResponseEntity.ok("Hello world!");
    }

    @PostMapping("/student")
    public ResponseEntity<?> addStudent(@RequestBody Student student)
    {
        System.out.println("THIS IS WHERE IT PRINTS");
        return ResponseEntity.ok("ok");
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

}
