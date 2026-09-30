package com.project.postllmservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor 
@Data @Builder
public class UserResponseDTO {
    private Long id;
    private String userId;
    private String nickname;
    private String email;
}
