package com.project.postllmservice.mapper;

import com.project.postllmservice.entity.Post;

public interface PostMapper {
    int postAdd(Post post, Long userId);
}
