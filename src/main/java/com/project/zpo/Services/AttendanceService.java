package com.project.zpo.Services;

import com.project.zpo.Tables.Attendance;
import com.project.zpo.Repositories.AttendanceRepository;
import com.project.zpo.Requests.AttendanceRequest;
import com.project.zpo.Requests.StudentAttendanceData;
import com.project.zpo.Tables.Student;
import com.project.zpo.Tables.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.project.zpo.Messages.AttendanceMessages.ATTENDANCE_DATA_CORRUPTED;
import static com.project.zpo.Messages.AttendanceMessages.ATTENDANCE_OK;
import static com.project.zpo.Messages.StudentMessages.STUDENT_NOT_FOUND_MESSAGE;
import static com.project.zpo.Messages.TermMessages.TERM_ERROR;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }


    private Term getOrCreateTerm(LocalDate date) {
        Term term = TermService.getTerm(date);
        if (term != null)
            return term;

        boolean status = TermService.addTerm(date);
        if (!status)
            return null;

        term = TermService.getTerm(date);

        return term;
    }

    private Student getStudent(Long album) {
        Optional<Student> studentOptional = StudentService.getStudent(album);
        return studentOptional.orElse(null);
    }


    public ResponseEntity<String> setAttendance(AttendanceRequest request) {

        Term term = getOrCreateTerm(request.getDate());
        if (term == null)
            return new ResponseEntity<>(TERM_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);


        List<StudentAttendanceData> attendanceData = request.getAttendanceData();


        for (StudentAttendanceData data : attendanceData) {
            if (data.getStudentAlbum() == null || data.getAttendanceStatus() == null)
                return new ResponseEntity<>(ATTENDANCE_DATA_CORRUPTED, HttpStatus.BAD_REQUEST);
        }


        for (StudentAttendanceData data : attendanceData) {

            Student student = getStudent(data.getStudentAlbum());
            if (student == null)
                return new ResponseEntity<>(STUDENT_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);

            Attendance attendance = new Attendance(student, term, data.getAttendanceStatus().toString());
            attendanceRepository.save(attendance);

        }

        return new ResponseEntity<>(ATTENDANCE_OK, HttpStatus.OK);
    }

}