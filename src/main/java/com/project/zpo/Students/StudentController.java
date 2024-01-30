package com.project.zpo.Students;

import com.project.zpo.Students.Requests.AddStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("add")
    public ResponseEntity<String> addStudent(@RequestBody AddStudentRequest addStudentRequest) {
        return studentService.addStudent(addStudentRequest);
    }

    @PostMapping("remove/{album}")
    public ResponseEntity<String> removeStudent(@PathVariable("album") Long albumToRemove) {
        return studentService.removeStudent(albumToRemove);
    }

}
