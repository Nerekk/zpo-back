package com.project.zpo.Attendance;

import com.project.zpo.Attendance.Requests.SetAttendanceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "attendances")
public class AttendanceController {
    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    public ResponseEntity<String> setStudentAttendance(@RequestBody SetAttendanceRequest setAttendanceRequest) {
        return attendanceService.setAttendance(setAttendanceRequest);
    }

}
