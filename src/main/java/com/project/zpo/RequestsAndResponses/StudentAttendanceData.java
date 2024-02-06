package com.project.zpo.RequestsAndResponses;

import com.project.zpo.Enumerations.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Data Transfer Object (DTO) representing student attendance data, including the student's album number and their attendance status.
 * Constructor and getters are generated by Lombok annotations.
 */
@AllArgsConstructor
@Getter
public class StudentAttendanceData {

    private Long studentAlbum;
    private AttendanceStatus attendanceStatus;

}
