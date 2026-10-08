package com.project.postllmservice.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor 
@Data @Builder 
public class UpdatePostRequestDTO {

    @NotBlank(message = "게시글 제목을 입력해주세요.")
    private String postName;
    private String postContent;
    private LocalDateTime updatedAt;
}
