package com.project.zpo.RequestsAndResponses;

import com.project.zpo.Tables.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class GroupResponse {
    private Long id;
    private String name;
//    private List<Student> students;

//    @Override
//    public String toString() {
//        return "GroupResponse{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", students=" + students +
//                '}';
//    }
}
