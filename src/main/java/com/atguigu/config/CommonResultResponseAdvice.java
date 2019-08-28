package com.atguigu.config;


import com.alibaba.fastjson.JSON;
import com.atguigu.entity.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
/*
*
*Springboot使用了ResponseBodyAdvice处理 string类型返回值异常
*
* springmvc内部定义了九个不同的MessageConverter用来处理不同的返回值。
* 在AbstractMessageConverterMethodProcessor类下面的writeWithMessageConverters方法可以看出来，
* 每个MessageConverer是根据返回类型和媒体类型来选择处理的MessageConverter的
*
*
*所以导致这个问题的原因就是，controller层中返回的类型是String，
* 但是在ResponseBodyAdvice实现类中，我们把响应的类型修改成了ResponseResult。
* 这就导致了，上面的这段代码在选择处理MessageConverter的时候，
* 依旧根据之前的String类型选择对应String类型的StringMessageConverter。
* 而在StringMessageConverter类型，他只接受String类型的返回类型，
* 我们在ResponseBodyAdvice中将返回值从String类型改成ResponseResult类型之后，
* 调用StringMessageConverter方法发生类型强转。ReponseResult无法转换成String，发生类型转换异常
*
*
* */
@RestControllerAdvice(basePackages = "com.atguigu.controller")
public class CommonResultResponseAdvice implements ResponseBodyAdvice<Object> {

       private static Logger logger= LoggerFactory.getLogger(CommonResultResponseAdvice.class);

        //返回true，说明会调用下面的方法，对返回值进行包装
        @Override
        public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
            return true;
        }

        @Override
        public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
            logger.info("请求返回数据类型class={}", body.getClass().getName());
            //将返回值统一包装成CommonResult对象
            CommonResult result=null;

            if(body instanceof CommonResult){
                result=(CommonResult)body;
            }else {
                result=new CommonResult(body);
            }
            //debug时打印响应结果
            if (logger.isDebugEnabled()) {
                logger.debug("响应参数：{} ", JSON.toJSONString(result));
            }
            //处理返回值是String的情况
            if (body instanceof String) {
                return JSON.toJSONString(result);
            }

            return result;
        }

}
