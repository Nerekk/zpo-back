package com.project.zpo.Controllers;

import com.project.zpo.RequestsAndResponses.GroupAttendanceResponse;
import com.project.zpo.RequestsAndResponses.WholeGroupAttendanceRequest;
import com.project.zpo.Services.AttendanceService;
import com.project.zpo.RequestsAndResponses.AttendanceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "attendances")
@CrossOrigin
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

    @GetMapping(path = "group/{id}/{date}")
    public ResponseEntity<GroupAttendanceResponse> getGroupAttendanceData(@PathVariable("id") Long id, @PathVariable("date") LocalDate date) {
        WholeGroupAttendanceRequest attendanceRequest = new WholeGroupAttendanceRequest(id, date);
        return attendanceService.getGroupAttendanceData(attendanceRequest);
    }

}
