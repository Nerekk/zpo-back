package com.project.zpo.RequestsAndResponses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WholeGroupAttendanceRequest {

    private Long groupId;
    private LocalDate date;

}
