package com.project.zpo.Controllers;

import com.project.zpo.RequestsAndResponses.GroupAttendanceResponse;
import com.project.zpo.RequestsAndResponses.WholeGroupAttendanceRequest;
import com.project.zpo.Services.AttendanceService;
import com.project.zpo.RequestsAndResponses.AttendanceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


/**
 * Controller class responsible for handling attendance-related HTTP requests.
 * This controller is mapped to the "/attendances" endpoint.
 * <p>
 * The controller allows setting attendance for a group of students via PUT request and retrieving attendance data
 * for a group on a specific date via GET request.
 * <p>
 * It utilizes the {@link AttendanceService} layer to handle business logic related to attendance management.
 */
@RestController
@RequestMapping(path = "attendances")
@CrossOrigin
public class AttendanceController {
    /**
     * Service responsible for handling attendance-related business logic.
     */
    private final AttendanceService attendanceService;

    /**
     * Automatic constructor for the AttendanceController class.
     *
     * @param attendanceService The AttendanceService instance to be used by the controller.
     */
    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    /**
     * Endpoint for setting attendance for a group of students.
     *
     * @param request The request object containing attendance data for the group.
     * @return ResponseEntity representing the HTTP response.
     * @see ResponseEntity
     */
    @PutMapping("")
    public ResponseEntity<String> setGroupOfStudentsAttendances(@RequestBody AttendanceRequest request) {
        return attendanceService.setAttendance(request);
    }

    /**
     * Endpoint for retrieving attendance data for a group on a specific date.
     *
     * @param id   The ID of the group for which attendance data is requested.
     * @param date The date for which attendance data is requested.
     * @return ResponseEntity containing the attendance data for the group on the specified date.
     * @see ResponseEntity
     */
    @GetMapping(path = "group/{id}/{date}")
    public ResponseEntity<GroupAttendanceResponse> getGroupAttendanceData(@PathVariable("id") Long id, @PathVariable("date") LocalDate date) {
        WholeGroupAttendanceRequest attendanceRequest = new WholeGroupAttendanceRequest(id, date);
        return attendanceService.getGroupAttendanceData(attendanceRequest);
    }

}
