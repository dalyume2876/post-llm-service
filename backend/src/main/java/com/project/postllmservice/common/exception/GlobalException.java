package com.project.postllmservice.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.postllmservice.common.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice 
@Slf4j 
public class GlobalException {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidException(
        MethodArgumentNotValidException exception
    ) {
        String message = exception.getBindingResult() // 검증결과 가져오기
                    .getFieldErrors() // 필드별 오류 목록 가져오기
                    .get(0) // 첫 번째 오류 선택
                    .getDefaultMessage(); //검증 어노테이션에 설정한 메세지 가져오기

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(false, message, null));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException exception) {

        ErrorCode errorCode = exception.getErrorCode();

        ApiResponse<Void> response = new ApiResponse<Void>(false, exception.getMessage(), null);

        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception exception) {
        log.warn("서버 내부 오류 발생", exception);

        ApiResponse<Void> response = new ApiResponse<Void>(false, "서버 내부 오류가 발생했습니다.", null);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
