package com.project.zpo.Attendance;

import com.project.zpo.Students.Student;
import com.project.zpo.Term.Term;
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
    @OneToOne
    @MapsId
    @JoinColumn(name = "album", referencedColumnName = "album")
    private Student student;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
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
