package com.project.postllmservice.dto.response;

import java.time.LocalDateTime;

import com.project.postllmservice.entity.Post;
import com.project.postllmservice.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor 
@Data @Builder 
public class PostDetailResponseDTO {
    private String postName;
    private String postContent;
    private LocalDateTime uploadedAt;
    private LocalDateTime updatedAt;

    private Long userId;
    private String nickname;
    private String profile;
    
    public static PostDetailResponseDTO from(Post post, User user) {
        return PostDetailResponseDTO.builder()
            .postName(post.getPostName())
            .postContent(post.getPostContent())
            .uploadedAt(post.getUploadedAt())
            .updatedAt(post.getUpdatedAt())
            .userId(post.getId())
            .nickname(user.getNickname())
            .profile(user.getProfile())
            .build();
    }
}
