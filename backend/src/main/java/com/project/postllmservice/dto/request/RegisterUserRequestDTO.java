package com.project.postllmservice.dto.request;

import com.project.postllmservice.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor 
@Data
public class RegisterUserRequestDTO {
    
    @NotBlank(message = "아이디를 입력하세요.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$")
    private String password;

    @NotBlank(message = "이름을 입력하세요.")
    private String nickname;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식으로 작성해주세요.")
    private String email;

    private String profile; 

    public User toEntity() {
        return User.builder()
            .userId(userId)
            .password(password)
            .nickname(nickname)
            .email(email)
            .profile(profile)
            .build();
    }
}
