package com.project.postllmservice.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.postllmservice.dto.request.RegisterUserRequestDTO;
import com.project.postllmservice.dto.request.UpdateUserInfoRequestDTO;
import com.project.postllmservice.dto.response.UserResponseDTO;
import com.project.postllmservice.entity.User;
import com.project.postllmservice.exception.UserException;
import com.project.postllmservice.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UserService {
    private final UserMapper userMapper;

    private User findUserOrThrow(Long id) {
        return userMapper.findById(id).orElseThrow(() -> new UserException("해당 유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));
    }

    public UserResponseDTO getUserById(Long id) {
        return UserResponseDTO.from(findUserOrThrow(id));
    }

    @Transactional(rollbackFor = Exception.class)
    public void addUser(RegisterUserRequestDTO dto) {
        User user = dto.toEntity();

        if (userMapper.findByEmail(dto.getEmail()).isPresent()) {
            throw new UserException("이미 사용중인 이메일입니다.", HttpStatus.CONFLICT);
        }

         userMapper.addUser(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public UserResponseDTO updateUser(Long id, UpdateUserInfoRequestDTO request) {
        User currentUser = findUserOrThrow(id);
        
        currentUser.setEmail(request.getEmail());
        currentUser.setNickname(request.getNickname());
        currentUser.setProfile(request.getProfile());

        userMapper.updateUser(currentUser);

        return getUserById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Long id) {
        int rows = userMapper.deleteUser(id);

        if (rows <= 0) {
            throw new UserException("해당 유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

}
