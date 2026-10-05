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

import com.project.postllmservice.dto.request.RegisterUserRequestDTO;
import com.project.postllmservice.dto.request.UpdateUserInfoRequestDTO;
import com.project.postllmservice.dto.response.UserResponseDTO;
import com.project.postllmservice.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController 
@RequiredArgsConstructor 
@RequestMapping("/user")
@Slf4j 
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody @Valid RegisterUserRequestDTO dto) {   
        userService.addUser(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 완료");
    }

    @PutMapping("/profile")
    public ResponseEntity<UserResponseDTO> updateUser(
        @RequestParam("id") Long id,
        @RequestBody @Valid UpdateUserInfoRequestDTO request
    ) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
        @PathVariable Long id) {
            userService.deleteUser(id);

            return ResponseEntity.noContent().build();
    }
}
