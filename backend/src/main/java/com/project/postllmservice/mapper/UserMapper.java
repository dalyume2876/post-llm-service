package com.project.postllmservice.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.project.postllmservice.entity.User;

@Mapper 
public interface UserMapper {
    int addUser(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    int updateUser(User user);
    int deleteUser(Long id);
}
