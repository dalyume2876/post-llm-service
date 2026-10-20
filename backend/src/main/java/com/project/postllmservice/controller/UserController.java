package com.project.postllmservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.postllmservice.common.response.ApiResponse;
import com.project.postllmservice.dto.request.RegisterUserRequestDTO;
import com.project.postllmservice.dto.request.UpdateUserInfoRequestDTO;
import com.project.postllmservice.dto.response.UserResponseDTO;
import com.project.postllmservice.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController 
@RequiredArgsConstructor
@Slf4j 
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUserById(@PathVariable Long id) {

        UserResponseDTO user = userService.getUserById(id);

        ApiResponse<UserResponseDTO> response = new ApiResponse<UserResponseDTO>(true, "조회 성공", user);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<Void>> signUp(@RequestBody @Valid RegisterUserRequestDTO dto) {   
        userService.addUser(dto);

        ApiResponse<Void> response = new ApiResponse<>(true, "회원가입 성공", null);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/profile")
    public ResponseEntity<ApiResponse<UserResponseDTO>> updateUser(
        @RequestParam("id") Long id,
        @RequestBody @Valid UpdateUserInfoRequestDTO request
    ) {
        UserResponseDTO userResponseDTO = userService.updateUser(id, request);
        ApiResponse<UserResponseDTO> response = new ApiResponse<UserResponseDTO>(true, "프로필 조회 성공", userResponseDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
        @PathVariable Long id) {
            userService.deleteUser(id);

            return ResponseEntity.noContent().build();
    }
}
