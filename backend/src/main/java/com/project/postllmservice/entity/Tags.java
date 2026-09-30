package com.project.postllmservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor 
@Data @Builder 
public class Tags {
    private int id;
    private String tagName;
}
