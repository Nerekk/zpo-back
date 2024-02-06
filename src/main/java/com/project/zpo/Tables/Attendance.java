package com.project.zpo.Tables;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@IdClass(AttendanceId.class)
public class Attendance implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "album", referencedColumnName = "album")
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "term_id", referencedColumnName = "id")
    private Term term;

    @Column(nullable = false)
    private String status;

    @Override
    public String toString() {
        return "Attendance{" +
                "student=" + student +
                ", term=" + term +
                ", status='" + status + '\'' +
                '}';
    }
}
