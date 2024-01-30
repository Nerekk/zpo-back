package com.project.zpo.Groups;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.zpo.Students.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "student_group")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Group implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @OneToMany(targetEntity = Student.class, mappedBy = "studentGroup")
    @JsonIgnore
    private List<Student> students;

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
