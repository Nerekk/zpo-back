package com.project.zpo.Controllers;

import com.project.zpo.RequestsAndResponses.AddStudentRequest;
import com.project.zpo.RequestsAndResponses.GroupChangeRequest;
import com.project.zpo.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class responsible for handling student-related HTTP requests.
 * This controller is mapped to the "/students" endpoint.
 * <p>
 * The controller provides endpoints for adding, removing, and changing student groups.
 * It utilizes the {@link StudentService} layer to handle business logic related to student management.
 */
@RestController
@RequestMapping(path = "students")
@CrossOrigin
public class StudentController {

    /**
     * Service responsible for handling student-related business logic.
     */
    private final StudentService studentService;

    /**
     * Automatic constructor for the StudentController class.
     *
     * @param studentService The StudentService instance to be used by the controller.
     */
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    /**
     * Endpoint for adding a new student.
     *
     * @param addStudentRequest The request object containing information about the student to be added.
     * @return ResponseEntity representing the HTTP response.
     * @see ResponseEntity
     */
    @PostMapping()
    public ResponseEntity<String> addStudent(@RequestBody AddStudentRequest addStudentRequest) {
        return studentService.addStudent(addStudentRequest);
    }

    /**
     * Endpoint for removing an existing student.
     *
     * @param albumToRemove The album number of the student to be removed.
     * @return ResponseEntity representing the HTTP response.
     * @see ResponseEntity
     */
    @DeleteMapping("{album}")
    public ResponseEntity<String> removeStudent(@PathVariable("album") Long albumToRemove) {
        return studentService.removeStudent(albumToRemove);
    }

    /**
     * Endpoint for changing the group of a student.
     *
     * @param changeRequest The request object containing information about the group change.
     * @return ResponseEntity representing the HTTP response.
     * @see ResponseEntity
     */
    @PutMapping("group/change")
    public ResponseEntity<String> changeStudentGroup(@RequestBody GroupChangeRequest changeRequest) {
        return studentService.changeGroup(changeRequest);
    }


}
