package com.project.postllmservice.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor 
@Data @Builder 
public class postComments {
    private int id;
    private String comment;
    private Long postId;
    private Long userId;
    private LocalDateTime uploadedAt;
    private LocalDateTime updatedAt;
}
