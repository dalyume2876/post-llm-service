package com.project.postllmservice.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor 
@Data @Builder 
public class Post {
    private Long id;
    private String postName;
    private String postContent;
    private Long userId;
    private LocalDateTime uploadedAt;
    private LocalDateTime updatedAt;
}
