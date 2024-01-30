package com.project.zpo.Groups.Requests;

import com.project.zpo.Students.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class GroupRequest {
    private Long id;
    private String name;
    private List<Student> students;

    @Override
    public String toString() {
        return "GroupRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
