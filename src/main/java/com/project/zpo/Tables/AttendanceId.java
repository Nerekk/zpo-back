package com.project.zpo.Tables;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Embeddable class representing the composite primary key for the Attendance class.
 * The composite key consists of two foreign keys: student and term.
 * The class implements the Serializable interface to enable object serialization.
 */
@Embeddable
@Getter
@Setter
public class AttendanceId implements Serializable {
    /**
     * Foreign key representing the student associated with the attendance record.
     */
    private Long student;
    /**
     * Foreign key representing the term associated with the attendance record.
     */
    private Long term;

    /**
     * Overridden equals method, comparing two AttendanceId objects for equality.
     *
     * @param o Object to be compared for equality with this AttendanceId.
     * @return True if the specified object is equal to this AttendanceId, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttendanceId that)) return false;
        return Objects.equals(getStudent(), that.getStudent()) && Objects.equals(getTerm(), that.getTerm());
    }

    /**
     * Overridden hashCode method, generating a hash code value for this AttendanceId.
     *
     * @return A hash code value for this AttendanceId.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getStudent(), getTerm());
    }
}
