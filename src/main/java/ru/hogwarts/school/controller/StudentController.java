package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping // POST http://localhost:8080/student
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        Student addedStudent = studentService.addStudent(student);
        return ResponseEntity.ok(addedStudent);
    }

    @GetMapping("{studentId}") // GET http://localhost:8080/student/1
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId){
        Student student = studentService.getStudentById(studentId);
        if (student == null){
            System.out.println("null");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/all") // GET http://localhost:8080/student/all
    public ResponseEntity<Collection<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PutMapping // PUT http://localhost:8080/student
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
        Student updatedStudent = studentService.updateStudent(student);
        if (updatedStudent == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("{studentId}") // DELETE http://localhost:8080/student/1
    public ResponseEntity deleteStudentById(@PathVariable Long studentId){
        studentService.deleteStudentById(studentId);
        return ResponseEntity.ok().build();
    }

}