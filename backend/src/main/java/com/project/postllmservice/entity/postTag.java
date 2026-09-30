package com.project.postllmservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor 
@Data @Builder 
public class postTag {
    private Long id;
    private Long postId;
    private int tagId;
}
