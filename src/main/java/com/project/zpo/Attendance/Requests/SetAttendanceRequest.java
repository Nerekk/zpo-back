package com.project.zpo.Attendance.Requests;

import com.project.zpo.Attendance.Utils.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
public class SetAttendanceRequest {

    private Long studentAlbum;
    private LocalDate date;
    private AttendanceStatus attendanceStatus;

    @Override
    public String toString() {
        return "SetAttendanceRequest{" +
                "student_album=" + studentAlbum +
                ", date=" + date +
                ", attendanceStatus=" + attendanceStatus +
                '}';
    }

}
