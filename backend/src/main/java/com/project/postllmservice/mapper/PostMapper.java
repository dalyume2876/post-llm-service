package com.project.postllmservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

import com.project.postllmservice.dto.request.UpdatePostRequestDTO;
import com.project.postllmservice.dto.response.PostDetailResponseDTO;
import com.project.postllmservice.dto.response.PostListResponseDTO;
import com.project.postllmservice.entity.Post;

@Mapper 
public interface PostMapper {
    Optional<PostDetailResponseDTO> findDetailById(@Param("id") Long id);
    List<PostListResponseDTO> findAllPosts();
    int addPost(Post post);
    int updatePost(@Param("id") Long id, @Param("request") UpdatePostRequestDTO dto);
    int deletePost(Long id);
}
