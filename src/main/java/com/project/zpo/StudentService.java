package com.project.zpo;

import com.project.zpo.Groups.Group;
import com.project.zpo.Students.Requests.AddStudentRequest;
import com.project.zpo.Students.Student;
import com.project.zpo.Students.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.project.zpo.Students.Utils.StudentMessages.*;


@Service
@Transactional
public class StudentService {

    @Autowired
    public StudentRepository studentRepository;


    public ResponseEntity<String> addStudent(AddStudentRequest addStudentRequest) {

        Student student = new Student();
        student.setFirstName(addStudentRequest.getFirstName());
        student.setLastName(addStudentRequest.getLastName());

        Group group = new Group();
        group.setId(addStudentRequest.getGroup_id());

//         todo: group searching
//        student.setStudentGroup();
//        return new ResponseEntity<>(STUDENT_INSERTION_ERROR_MESSAGE, HttpStatus.OK);

        studentRepository.save(student);


        return new ResponseEntity<>(STUDENT_INSERTED_MESSAGE, HttpStatus.OK);
    }

    public ResponseEntity<String> removeStudent(Long albumToRemove) {
        Optional<Student> student = studentRepository.findById(albumToRemove);

        if (student.isEmpty())
            return new ResponseEntity<>(STUDENT_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);

        studentRepository.deleteById(albumToRemove);

        return new ResponseEntity<>(STUDENT_REMOVED_MESSAGE, HttpStatus.OK);
    }

}
