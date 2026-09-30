package com.project.postllmservice.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.postllmservice.dto.request.RegisterUserRequestDTO;
import com.project.postllmservice.dto.response.UserResponseDTO;
import com.project.postllmservice.entity.User;
import com.project.postllmservice.exception.UserException;
import com.project.postllmservice.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UserService {
    private final UserMapper userMapper;

    public UserResponseDTO getUserById(Long id) {
        
        User user = userMapper.findById(id).orElseThrow(() -> new UserException("회원을 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        return UserResponseDTO.builder()
            .id(id)
            .userId(user.getUserId())
            .nickname(user.getNickname())
            .email(user.getEmail())
            .build();
    }

    public int addUser(RegisterUserRequestDTO dto) {
        User user = dto.toEntity();

        // Todo 이미 로그인 된 email이 있으면 막는 방어적 코드 필요. (이메일 조회 만든 후)
        if (userMapper.findByEmail(dto.getEmail()).isPresent()) {
            throw new UserException("이미 사용중인 이메일입니다.", HttpStatus.CONFLICT);
        }

        return userMapper.addUser(user);
    }

}
