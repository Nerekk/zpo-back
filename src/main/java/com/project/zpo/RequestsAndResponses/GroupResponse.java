package com.project.zpo.RequestsAndResponses;

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
    private List<BasicStudentResponse> students;

}
