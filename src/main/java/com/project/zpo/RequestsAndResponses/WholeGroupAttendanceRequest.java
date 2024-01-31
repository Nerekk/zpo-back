package com.project.zpo.RequestsAndResponses;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class WholeGroupAttendanceRequest {

    private Long groupId;
    private LocalDate date;

}
