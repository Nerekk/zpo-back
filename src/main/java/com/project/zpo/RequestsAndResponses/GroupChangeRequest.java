package com.project.zpo.RequestsAndResponses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroupChangeRequest {

    private Long newGroupId;
    private Long studentAlbum;

}
