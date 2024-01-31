package com.project.zpo.Requests;

import com.project.zpo.Tables.Attendance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TermRequest {
    private Long id;
    private LocalDate date;
    private List<Attendance> attendances;

    @Override
    public String toString() {
        return "TermRequest{" +
                "id=" + id +
                ", date=" + date +
                ", attendances=" + attendances +
                '}';
    }
}
