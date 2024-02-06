package com.project.zpo.Services;

import com.project.zpo.Enumerations.AttendanceStatus;
import com.project.zpo.RequestsAndResponses.*;
import com.project.zpo.Tables.Attendance;
import com.project.zpo.Repositories.AttendanceRepository;
import com.project.zpo.Tables.Group;
import com.project.zpo.Tables.Student;
import com.project.zpo.Tables.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.project.zpo.Messages.AttendanceMessages.ATTENDANCE_DATA_CORRUPTED;
import static com.project.zpo.Messages.AttendanceMessages.ATTENDANCE_OK;
import static com.project.zpo.Messages.StudentMessages.STUDENT_NOT_FOUND_MESSAGE;
import static com.project.zpo.Messages.TermMessages.TERM_ERROR;

/**
 * Service class responsible for handling attendance-related business logic.
 * It interacts with the database via the {@link AttendanceRepository} and utilizes other services such as {@link TermService} and {@link StudentService}.
 */
@Service
public class AttendanceService {

    /**
     * Repository for managing Attendance entities in the database.
     */
    private final AttendanceRepository attendanceRepository;

    /**
     * Automatic constructor for the AttendanceService class.
     *
     * @param attendanceRepository The AttendanceRepository instance to be used by the service.
     */
    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }


    /**
     * Private method to get or create (if not exists) a term based on the provided date.
     *
     * @param date The date for which the term is needed.
     * @return The Term object representing the term for the given date or null.
     */
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

    /**
     * Private method to retrieve a student based on the provided album number.
     *
     * @param album The album number of the student.
     * @return The Student object representing the student with the provided album number or null.
     */
    private Student getStudent(Long album) {
        Optional<Student> studentOptional = StudentService.getStudent(album);
        return studentOptional.orElse(null);
    }


    /**
     * Method for setting attendance based on the provided request.
     *
     * @param request The AttendanceRequest containing data for setting attendance.
     * @return ResponseEntity representing the HTTP response indicating the status of the operation.
     * @see ResponseEntity
     */
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

    /**
     * Method for retrieving attendance data for a group on a specific date.
     *
     * @param request The WholeGroupAttendanceRequest containing data for retrieving group attendance.
     * @return ResponseEntity containing the GroupAttendanceResponse with attendance data for the group.
     * @see ResponseEntity
     */
    public ResponseEntity<GroupAttendanceResponse> getGroupAttendanceData(WholeGroupAttendanceRequest request) {

        LocalDate date = request.getDate();
        Long groupId = request.getGroupId();

        Optional<Group> groupOpt = GroupService.getGroup(groupId);
        if (groupOpt.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        Group group = groupOpt.get();

        Term term = TermService.getTerm(date);
        if (term == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        List<StudentResponse> studentResponseList = new ArrayList<>(group.getStudents().size());

        for (Student student : group.getStudents()) {
            StudentResponse studentResponse = new StudentResponse();

            studentResponse.setAlbum(student.getAlbum());
            studentResponse.setFirstName(student.getFirstName());
            studentResponse.setLastName(student.getLastName());

            Attendance attendance = attendanceRepository.findByTerm(term, student);
            if (attendance == null)
                studentResponse.setAttendanceStatus(AttendanceStatus.NOT_SET);
            else
                studentResponse.setAttendanceStatus(AttendanceStatus.valueOf(attendance.getStatus()));

            studentResponseList.add(studentResponse);
        }

        GroupAttendanceResponse response = new GroupAttendanceResponse(studentResponseList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
