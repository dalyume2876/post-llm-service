package com.project.postllmservice.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.project.postllmservice.common.exception.BusinessException;
import com.project.postllmservice.common.exception.ErrorCode;
import com.project.postllmservice.dto.request.CreatePostRequestDTO;
import com.project.postllmservice.dto.request.UpdatePostRequestDTO;
import com.project.postllmservice.dto.response.PostDetailResponseDTO;
import com.project.postllmservice.dto.response.PostListResponseDTO;
import com.project.postllmservice.entity.Post;
import com.project.postllmservice.mapper.PostMapper;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
@Transactional (readOnly = true) 
public class PostService {

    private final PostMapper postMapper;
    private Long tempUserId = 1L; // 회원가입 구현이 안되었기에, 임시로 사용

    public PostDetailResponseDTO detailPost(Long id) {
        return postMapper.findDetailById(id)
            .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));
    }

    public List<PostListResponseDTO> findAllPosts() {
        return postMapper.findAllPosts();
    }


    // todo 시큐리티 제작 후 id받기
    @Transactional(rollbackFor = Exception.class)
    public void addPost(CreatePostRequestDTO dto) {
        Post post = dto.toEntity(tempUserId);

        int rows = postMapper.addPost(post);

        if(rows <= 0) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public PostDetailResponseDTO updatePost(Long id, UpdatePostRequestDTO request) {
        int rows = postMapper.updatePost(id, request);

        if (rows <= 0) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        return detailPost(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletePost(Long id) {

        int rows = postMapper.deletePost(id);

        if (rows == 0) {
            throw new BusinessException(ErrorCode.POST_NOT_FOUND);
        }
    }

    
}
