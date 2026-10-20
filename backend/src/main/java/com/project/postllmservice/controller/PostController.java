package com.project.postllmservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.postllmservice.common.response.ApiResponse;
import com.project.postllmservice.dto.request.CreatePostRequestDTO;
import com.project.postllmservice.dto.request.UpdatePostRequestDTO;
import com.project.postllmservice.dto.response.PostDetailResponseDTO;
import com.project.postllmservice.dto.response.PostListResponseDTO;
import com.project.postllmservice.service.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/test-error")
    public void testError() {
        throw new RuntimeException("테스트용 서버 오류");
    }

    @GetMapping("post/{id}")
    public ResponseEntity<ApiResponse<PostDetailResponseDTO>> detailPost(@PathVariable("id") Long id) {

        PostDetailResponseDTO post = postService.detailPost(id);

        ApiResponse<PostDetailResponseDTO> response = new ApiResponse<PostDetailResponseDTO>(true, "게시글 조회 성공", post);

        return ResponseEntity.ok(response);
    }

    @GetMapping({ "/", "post/list" })
    public ResponseEntity<ApiResponse<List<PostListResponseDTO>>> findAllPosts() {

        List<PostListResponseDTO> post = postService.findAllPosts();
        ApiResponse<List<PostListResponseDTO>> response = new ApiResponse<List<PostListResponseDTO>>(true, "게시글 조회 성공",
                post);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Void>> addPost(@Valid @RequestBody CreatePostRequestDTO reqDTO) {
        postService.addPost(reqDTO);

        ApiResponse<Void> response = new ApiResponse<>(true, "생성 완료", null);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("post/{id}")
    public ResponseEntity<ApiResponse<PostDetailResponseDTO>> updatePost(@PathVariable Long id,
            @RequestBody @Valid UpdatePostRequestDTO reqDTO) {
        PostDetailResponseDTO post = postService.updatePost(id, reqDTO);

        ApiResponse<PostDetailResponseDTO> response = new ApiResponse<PostDetailResponseDTO>(true, "게시글 수정 완료", post);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("post/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
