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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.postllmservice.dto.request.CreatePostRequestDTO;
import com.project.postllmservice.dto.request.UpdatePostRequestDTO;
import com.project.postllmservice.dto.response.PostDetailResponseDTO;
import com.project.postllmservice.dto.response.PostListResponseDTO;
import com.project.postllmservice.service.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    
    @GetMapping("/{id}")
    public ResponseEntity<PostDetailResponseDTO> detailPost(@PathVariable("id") Long id) {
        return ResponseEntity.ok(postService.detailPost(id));
    }

    @GetMapping({"/", "/list"})
    public ResponseEntity<List<PostListResponseDTO>> findAllPosts() {
        return ResponseEntity.ok(postService.findAllPosts());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPost(@RequestBody CreatePostRequestDTO reqDTO) {
        postService.addPost(reqDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("생성 완료");
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDetailResponseDTO> updatePost(@PathVariable Long id, @RequestBody @Valid UpdatePostRequestDTO reqDTO) {
        return ResponseEntity.ok(postService.updatePost(id, reqDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
    }
}
