package com.project.postllmservice.dto.response;

import com.project.postllmservice.entity.User;

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
    private String profile;

    public static UserResponseDTO from(User user) {
        return UserResponseDTO.builder()
        .id(user.getId())
        .userId(user.getUserId())
        .nickname(user.getNickname())
        .email(user.getEmail())
        .profile(user.getProfile())
        .build();
    }
}
