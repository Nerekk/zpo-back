package com.project.zpo.Students;

import com.project.zpo.Attendance.Attendance;
import com.project.zpo.Groups.Group;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long album;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group studentGroup;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private List<Attendance> attendances;

}
