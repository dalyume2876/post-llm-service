package com.project.postllmservice.dto.request;

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
}
