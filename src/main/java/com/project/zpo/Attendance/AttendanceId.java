package com.project.zpo.Attendance;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class AttendanceId implements Serializable {
    private Long student;
    private Long term;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttendanceId that)) return false;
        return Objects.equals(getStudent(), that.getStudent()) && Objects.equals(getTerm(), that.getTerm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudent(), getTerm());
    }
}
