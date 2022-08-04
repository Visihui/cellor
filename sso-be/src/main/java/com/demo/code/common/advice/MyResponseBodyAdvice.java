//package com.demo.code.common.advice;
//
//import cn.hutool.core.util.StrUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.http.server.ServletServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
//import javax.servlet.http.HttpServletResponse;
//
//@Slf4j
//@Component
//@org.springframework.web.bind.annotation.ControllerAdvice(basePackages = {
//        "com.demo.code.**"}, annotations = {
//        Controller.class, RestController.class})
//public class MyResponseBodyAdvice implements ResponseBodyAdvice {
//    @Override
//    public boolean supports(MethodParameter returnType, Class converterType) {
//        return true;
//    }
//
//    @Override
//    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType selectedContentType,
//                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse serverHttpResponse) {
//
//        //添加响应头
//        HttpServletResponse response = ((ServletServerHttpResponse) serverHttpResponse).getServletResponse();
//
//        //允许跨域的请求方式
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT");
//        //预检请求的间隔时间
//        response.setHeader("Access-Control-Max-Age", "3600");
//        //允许跨域请求携带的请求头
//        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token,Access-Control-Allow-Headers");
//        //若要返回cookie、携带seesion等信息则将此项设置我true
//        response.setHeader("Access-Control-Allow-Credentials","false");
//        //简称为HSTS。它允许一个HTTPS网站，要求浏览器总是通过HTTPS来访问它
//        response.setHeader("strict-transport-security","max-age=16070400; includeSubDomains");
//        response.setHeader("X-Frame-Options","SAMEORIGIN");
//        //这个响应头主要是用来定义页面可以加载哪些资源，减少XSS的发生
//        response.setHeader("Content-Security-Policy", "default-src 'self'; script-src 'self'; frame-ancestors 'self'; object-src 'none'");
//        //互联网上的资源有各种类型，通常浏览器会根据响应头的Content-Type字段来分辨它们的类型。通过这个响应头可以禁用浏览器的类型猜测行为
//        response.setHeader("X-Content-Type-Options","nosniff");
//        //1; mode=block：启用XSS保护，并在检查到XSS攻击时，停止渲染页面
//        response.setHeader("X-XSS-Protection","1; mode=block");
//        response.setHeader("Access-Control-Allow-Origin","*");
//
//
//        // 过滤掉swagger相关的url
//        if (StrUtil.containsAny(request.getURI().getPath(), "/electric/swagger", "/electric/v2")) {
//            return body;
//        }
//
//
//        return body;
//    }
//}
