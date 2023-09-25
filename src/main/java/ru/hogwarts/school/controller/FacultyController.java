package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }


    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty createFaculty = facultyService.createFaculty(faculty);
        if (createFaculty == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createFaculty);
    }

    @GetMapping
    public ResponseEntity getFaculty(@RequestParam(required = false) Long id,
                                     @RequestParam(required = false) String color) {
        if (id != null) {
            return ResponseEntity.ok(facultyService.getFaculty(id));
        }
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.getFacultiesByColor(color));
        }
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty editFaculty = facultyService.editFaculty(faculty);
        if (editFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> removeFaculty(@PathVariable Long id) {
        facultyService.removeFaculty(id);
        return ResponseEntity.ok().build();
    }
}
