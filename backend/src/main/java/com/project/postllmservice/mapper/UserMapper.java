package com.project.postllmservice.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.postllmservice.dto.request.UpdateUserInfoRequestDTO;
import com.project.postllmservice.entity.User;

@Mapper 
public interface UserMapper {
    int addUser(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    int updateUser(@Param("id") Long id, @Param("request") UpdateUserInfoRequestDTO dto);
    int deleteUser(Long id);
}
