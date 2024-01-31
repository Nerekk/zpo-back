package com.project.zpo.Attendance;

import com.project.zpo.Attendance.Requests.SetAttendanceRequest;
import com.project.zpo.Students.Student;
import com.project.zpo.Students.StudentService;
import com.project.zpo.Term.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.project.zpo.Attendance.Utils.AttendanceMessages.ATTENDANCE_DATA_CORRUPTED;
import static com.project.zpo.Attendance.Utils.AttendanceMessages.ATTENDANCE_OK;
import static com.project.zpo.Students.Utils.StudentMessages.STUDENT_NOT_FOUND_MESSAGE;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }


    public ResponseEntity<String> setAttendance(SetAttendanceRequest setAttendanceRequest) {

        if (setAttendanceRequest == null ||
                setAttendanceRequest.getDate() == null ||
                setAttendanceRequest.getStudentAlbum() == null ||
                setAttendanceRequest.getAttendanceStatus() == null
        )
            return new ResponseEntity<>(ATTENDANCE_DATA_CORRUPTED, HttpStatus.BAD_REQUEST);

        Optional<Student> studentOptional = StudentService.getStudent(setAttendanceRequest.getStudentAlbum());
        if (studentOptional.isEmpty())
            return new ResponseEntity<>(STUDENT_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);

        Student student = studentOptional.get();

        Term term = new Term();
        term.setDate(setAttendanceRequest.getDate());

        // todo: get term

        Attendance attendance = new Attendance(student, term, setAttendanceRequest.getAttendanceStatus().toString());

        attendanceRepository.save(attendance);

        return new ResponseEntity<>(ATTENDANCE_OK, HttpStatus.OK);
    }


}
