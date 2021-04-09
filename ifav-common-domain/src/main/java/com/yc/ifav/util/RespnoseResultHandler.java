package com.yc.ifav.util;


import com.yc.ifav.entity.ResponseResult;
import com.yc.ifav.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class RespnoseResultHandler implements ResponseBodyAdvice<Object> {

    //标记名称
    public static String RESPONSE_RESULT_ERFENG="RESPONSE_RESULT_ERFENG";

    //是否请求，包含了包装注解 标记，没有就直接返回，不需要重写返回体
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ServletRequestAttributes sra=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        HttpServletRequest request=sra.getRequest();
        //判断请求，是否有包装标记
        ResponseResult responseResultErFeng= (ResponseResult) request.getAttribute(RESPONSE_RESULT_ERFENG);
        return responseResultErFeng==null?false:true;
    }

    //异常处理
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

//        //判断是否为异常类
//        if(o instanceof ErrorResult){
//            ErrorResult errorResult= (ErrorResult) o;
//            return  Result.failure(errorResult.getCode(),errorResult.getMessage(),errorResult.getErrors());
//        }

        return Result.success(o);
    }
}
