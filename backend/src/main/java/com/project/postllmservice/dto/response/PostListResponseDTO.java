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
public class PostListResponseDTO {
    private String postName;
    private String postContent;
    private LocalDateTime uploadedAt;
    private LocalDateTime updatedAt;

    private String nickname;

    public static PostListResponseDTO from(Post post, User user) {
        return PostListResponseDTO.builder()
            .postName(post.getPostName())
            .postContent(post.getPostContent())
            .uploadedAt(post.getUploadedAt())
            .updatedAt(post.getUpdatedAt())
            .nickname(user.getNickname())
            .build();
    }
}
