package com.project.zpo.RequestsAndResponses;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a request to add a student, including the student's first name, last name, and group ID.
 * Constructor, getters and setters are generated by Lombok annotations.
 */
@AllArgsConstructor
@Getter
@Setter
public class AddStudentRequest {

    private String firstName;
    private String lastName;
    private Long groupId;

}
