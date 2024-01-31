package com.project.zpo.RequestsAndResponses;

import com.project.zpo.Enumerations.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StudentAttendanceData {

    private Long studentAlbum;
    private AttendanceStatus attendanceStatus;

}
