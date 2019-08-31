package com.atguigu.advice;

import com.atguigu.entity.CommonResult;
import com.atguigu.exception.BusinessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(BusinessException.class)
    public CommonResult handleBusinessException(BusinessException be){
        return CommonResult.errorResult(be.getErrorCode(), be.getErrorMsg());
    }

}
