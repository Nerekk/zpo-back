package com.project.zpo.Controllers;

import com.project.zpo.Services.AttendanceService;
import com.project.zpo.Requests.AttendanceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PutMapping("")
    public ResponseEntity<String> setGroupOfStudentsAttendances(@RequestBody AttendanceRequest request) {
        return attendanceService.setAttendance(request);
    }

}
