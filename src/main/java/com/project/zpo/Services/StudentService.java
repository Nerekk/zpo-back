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


@Service
@Transactional
public class StudentService {
    private static StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        StudentService.studentRepository = studentRepository;
    }

    public static boolean doesStudentExists(Long album) {
        return studentRepository.findById(album).isPresent();
    }

    public static Optional<Student> getStudent(Long album) {
        return studentRepository.findById(album);
    }


    private void fillName(Student student, AddStudentRequest addStudentRequest) {
        student.setFirstName(addStudentRequest.getFirstName());
        student.setLastName(addStudentRequest.getLastName());
    }


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

    public ResponseEntity<String> removeStudent(Long albumToRemove) {

        if (!doesStudentExists(albumToRemove))
            return new ResponseEntity<>(STUDENT_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);

        studentRepository.deleteById(albumToRemove);

        return new ResponseEntity<>(STUDENT_REMOVED_MESSAGE, HttpStatus.OK);
    }

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
