package com.project.zpo.Controllers;

import com.project.zpo.RequestsAndResponses.AddStudentRequest;
import com.project.zpo.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "students")
@CrossOrigin
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping()
    public ResponseEntity<String> addStudent(@RequestBody AddStudentRequest addStudentRequest) {
        return studentService.addStudent(addStudentRequest);
    }

    @DeleteMapping("{album}")
    public ResponseEntity<String> removeStudent(@PathVariable("album") Long albumToRemove) {
        return studentService.removeStudent(albumToRemove);
    }

}
