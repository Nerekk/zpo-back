package com.project.zpo.Attendance.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;


@Getter
@AllArgsConstructor
public class AttendanceRequest {

    private LocalDate date;
    private List<StudentAttendanceData> attendanceData;

}
