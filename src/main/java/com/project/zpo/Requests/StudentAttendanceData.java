package com.project.zpo.Requests;

import com.project.zpo.Enumerations.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StudentAttendanceData {

    private Long studentAlbum;
    private AttendanceStatus attendanceStatus;

}
