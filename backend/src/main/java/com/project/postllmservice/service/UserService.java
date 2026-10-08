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
@Transactional(readOnly = true)
public class UserService {
    private final UserMapper userMapper;

    private User findUserOrThrow(Long id) {
        return userMapper.findById(id).orElseThrow(() -> new UserException("해당 유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));
    }

    // Todo - 이메일 조회 만들기

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
        // Todo 이메일 조회 만들어지면, 중복 이메일 확인하기
        int rows = userMapper.updateUser(id, request);

        if (rows <= 0) {
            throw new IllegalStateException("회원정보 수정에 실패했습니다.");
        }

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
