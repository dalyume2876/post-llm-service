package com.project.postllmservice.service;

import com.project.postllmservice.dto.request.CreatePostRequestDTO;
import com.project.postllmservice.entity.Post;
import com.project.postllmservice.mapper.PostMapper;

public class PostService {

    private PostMapper postMapper;

    public int addPost(CreatePostRequestDTO dto, Long userId) {
        Post post = Post.builder()
        .postName(dto.getPostName())
        .postContent(dto.getPostContent())
        .userId(userId)
        .build();

        return postMapper.postAdd(post, userId);
    }
}
