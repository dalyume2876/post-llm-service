package com.project.postllmservice.common.response;

// ApiResponse로 응답형식 변경
// 제너릭 사용
//  T -> 나중에 사용할 데이터 타입 여기에 DTO를 담을 수 있음.
public class ApiResponse<T> {
    private final boolean success;
    private final String message;
    private final T data;
    
    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    
}
