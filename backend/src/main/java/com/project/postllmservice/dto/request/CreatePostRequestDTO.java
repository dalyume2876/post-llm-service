package com.project.postllmservice.dto.request;

import com.project.postllmservice.entity.Post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor 
@Data @Builder 
public class CreatePostRequestDTO {

    @NotBlank
    @Size(max = 50) 
    private String postName;
    private String postContent;
    
    public Post toEntity(Long userId) {
        return Post.builder()
            .postName(postName)
            .postContent(postContent)
            .userId(userId)
            .build();
    }
}
