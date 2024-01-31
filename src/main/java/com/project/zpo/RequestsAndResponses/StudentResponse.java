package com.project.zpo.RequestsAndResponses;

import com.project.zpo.Enumerations.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentResponse {

    private Long album;
    private String firstName;
    private String lastName;
    private AttendanceStatus attendanceStatus;

}
