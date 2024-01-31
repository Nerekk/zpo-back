package com.project.zpo.Requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AddStudentRequest {

    private String firstName;
    private String lastName;
    private Long groupId;

}
