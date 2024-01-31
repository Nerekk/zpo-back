package com.project.zpo.Attendance.Requests;

import com.project.zpo.Attendance.Utils.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StudentAttendanceData {

    private Long studentAlbum;
    private AttendanceStatus attendanceStatus;

}
