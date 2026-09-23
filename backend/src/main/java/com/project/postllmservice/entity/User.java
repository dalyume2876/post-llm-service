package com.project.postllmservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor 
@Data @Builder
public class User {
    private Long id;
    private String userId;
    private String password;
    private String nickname;
    private String profile;
}
