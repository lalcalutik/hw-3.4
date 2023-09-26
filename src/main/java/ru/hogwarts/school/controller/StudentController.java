package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
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


    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createStudent = studentService.createStudent(student);
        if (createStudent == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createStudent);
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getStudents(@RequestParam(required = false) Integer minAge,
                                                          @RequestParam(required = false) Integer maxAge) {
        if (minAge != null && maxAge != null) {
            return ResponseEntity.ok(studentService.getStudentsByAgeBetween(minAge, maxAge));
        }
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @GetMapping("{id}/faculty")
    public ResponseEntity<Faculty> getStudentsFaculty(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getFacultyById(id));
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student editStudent = studentService.editStudent(student);
        if (editStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> removeStudent(@PathVariable Long id) {
        studentService.removeStudent(id);
        return ResponseEntity.ok().build();
    }
}
