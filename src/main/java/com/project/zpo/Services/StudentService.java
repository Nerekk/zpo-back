package com.project.zpo.Services;

import com.project.zpo.Repositories.StudentRepository;
import com.project.zpo.RequestsAndResponses.AddStudentRequest;
import com.project.zpo.RequestsAndResponses.GroupChangeRequest;
import com.project.zpo.Tables.Group;
import com.project.zpo.Tables.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.project.zpo.Messages.GroupMessages.*;
import static com.project.zpo.Messages.StudentMessages.*;

/**
 * Service class responsible for handling student-related business logic.
 * It interacts with the database via the {@link StudentRepository} and provides various operations related to students.
 */
@Service
@Transactional
public class StudentService {
    /**
     * Repository for managing Student entities in the database.
     */
    private static StudentRepository studentRepository;

    /**
     * Automatic constructor for the StudentService class.
     *
     * @param studentRepository The StudentRepository instance to be used by the service.
     */
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        StudentService.studentRepository = studentRepository;
    }

    /**
     * Method to check if a student with the given album number exists.
     *
     * @param album The album number of the student to check.
     * @return True if the student exists, false otherwise.
     */
    public static boolean doesStudentExists(Long album) {
        return studentRepository.findById(album).isPresent();
    }

    /**
     * Method to retrieve a student by their album number.
     *
     * @param album The album number of the student to retrieve.
     * @return Optional containing the Student object if found, or empty if not found.
     * @see Optional
     */
    public static Optional<Student> getStudent(Long album) {
        return studentRepository.findById(album);
    }


    /**
     * Private method to populate the name fields of a student.
     *
     * @param student           The Student object to populate.
     * @param addStudentRequest The AddStudentRequest containing the student's name.
     */
    private void fillName(Student student, AddStudentRequest addStudentRequest) {
        student.setFirstName(addStudentRequest.getFirstName());
        student.setLastName(addStudentRequest.getLastName());
    }


    /**
     * Method for adding a new student.
     *
     * @param addStudentRequest The AddStudentRequest containing data for the new student.
     * @return ResponseEntity representing the HTTP response indicating the status of the operation.
     * @see ResponseEntity
     */
    public ResponseEntity<String> addStudent(AddStudentRequest addStudentRequest) {

        Student student = new Student();
        fillName(student, addStudentRequest);

        Optional<Group> group = GroupService.getGroup(addStudentRequest.getGroupId());
        if (group.isEmpty())
            return new ResponseEntity<>(GROUP_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);

        student.setStudentGroup(group.get());

        studentRepository.save(student);

        return new ResponseEntity<>(STUDENT_INSERTED_MESSAGE, HttpStatus.OK);
    }

    /**
     * Method for removing an existing student.
     *
     * @param albumToRemove The album number of the student to remove.
     * @return ResponseEntity representing the HTTP response indicating the status of the operation.
     * @see ResponseEntity
     */
    public ResponseEntity<String> removeStudent(Long albumToRemove) {

        if (!doesStudentExists(albumToRemove))
            return new ResponseEntity<>(STUDENT_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);

        studentRepository.deleteById(albumToRemove);

        return new ResponseEntity<>(STUDENT_REMOVED_MESSAGE, HttpStatus.OK);
    }

    /**
     * Method for changing the group of a student.
     *
     * @param changeRequest The GroupChangeRequest containing data for the group change.
     * @return ResponseEntity representing the HTTP response indicating the status of the operation.
     * @see ResponseEntity
     */
    public ResponseEntity<String> changeGroup(GroupChangeRequest changeRequest) {

        if (changeRequest == null || changeRequest.getNewGroupId() == null || changeRequest.getStudentAlbum() == null)
            return new ResponseEntity<>(GROUP_WRONG_DATA_MESSAGE, HttpStatus.BAD_REQUEST);

        Optional<Student> studentOpt = StudentService.getStudent(changeRequest.getStudentAlbum());
        if (studentOpt.isEmpty())
            return new ResponseEntity<>(STUDENT_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);

        Optional<Group> groupOpt = GroupService.getGroup(changeRequest.getNewGroupId());
        if (groupOpt.isEmpty())
            return new ResponseEntity<>(GROUP_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);

        Group group = groupOpt.get();
        Student student = studentOpt.get();

        if (group.getId() == student.getStudentGroup().getId())
            return new ResponseEntity<>(GROUP_ATTENDANCE_CONFLICT_MESSAGE, HttpStatus.CONFLICT);
        
        student.setStudentGroup(group);
        studentRepository.save(student);

        return new ResponseEntity<>(GROUP_CHANGED_MESSAGE, HttpStatus.OK);
    }

}
