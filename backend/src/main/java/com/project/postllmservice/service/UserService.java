package com.project.postllmservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.postllmservice.common.exception.BusinessException;
import com.project.postllmservice.common.exception.ErrorCode;
import com.project.postllmservice.dto.request.RegisterUserRequestDTO;
import com.project.postllmservice.dto.request.UpdateUserInfoRequestDTO;
import com.project.postllmservice.dto.response.UserResponseDTO;
import com.project.postllmservice.entity.User;
import com.project.postllmservice.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
@Transactional(readOnly = true)
public class UserService {
    private final UserMapper userMapper;

    private User findUserOrThrow(Long id) {
        return userMapper.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    public UserResponseDTO getUserById(Long id) {
        return UserResponseDTO.from(findUserOrThrow(id));
    }

    @Transactional(rollbackFor = Exception.class)
    public void addUser(RegisterUserRequestDTO requestDTO) {
        User user = requestDTO.toEntity();

        // 이메일 검사
        userMapper.findByEmail(user.getEmail()).ifPresent(u -> {
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
        });      

        // todo int타입으로 mapper가 변환한 행개수로 방어코드 작성
        userMapper.addUser(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public UserResponseDTO updateUser(Long id, UpdateUserInfoRequestDTO requestDTO) {
        
        // 이메일 검사. 바꿀 계정의 id와 이메일이 등록된 id가 같으면 중복처리를 배제함.
        userMapper.findByEmail(requestDTO.getEmail()).ifPresent(user -> {
            if (!user.getId().equals(id)) {
                throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
            }
        });      

        // mapper에 sql요청을 보내서 몇개의 행이 수정됐는지 카운트
        int rows = userMapper.updateUser(id, requestDTO);

        if (rows <= 0) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        return getUserById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Long id) {
        int rows = userMapper.deleteUser(id);

        if (rows <= 0) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }
    }

}
