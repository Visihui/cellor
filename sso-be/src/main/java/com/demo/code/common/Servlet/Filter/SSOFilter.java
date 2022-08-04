package com.demo.code.common.Servlet.Filter;

import com.demo.code.constants.RedisConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



//作用范围
@Order(2)
@WebFilter(urlPatterns = {"/test/*","/sysUser/*","/menu/*","/account/*"}, filterName = "apiFilter")
public class SSOFilter implements Filter {
    private Logger log = LoggerFactory.getLogger(getClass());

//    @Value("${filter.uris}")
//    private String anonUris;

    public  static  String  loginName;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("已进入到拦截器拦截器");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        //解决请求和响应的中文乱码
        req.setCharacterEncoding("utf-8");
        //Cookie，服务端从客户端获取cookie
        //这里返回数组，说明Cookie可能存在多个
        Cookie[] cookies = req.getCookies();
        //判断从客户端获取的Cookie是否存在
        Boolean flag = new Boolean(false);

        if (cookies != null){
            //如果存在Cookie
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                //获取cookie的名字
                //
                //if (cookie.getName().equals(RedisConstants.TOKEN_PREFIX)){
                if (redisTemplate.hasKey(cookie.getValue())){
                    //获取cookie中的值
                    String strTicket = cookie.getName();
                    String redisTicket = String.valueOf( redisTemplate.opsForValue().get(cookie.getValue()));
                    if(null != redisTicket&&StringUtils.equals(strTicket,redisTicket)){
                        loginName=redisTicket.replace(RedisConstants.TOKEN_PREFIX+"_","");
                       log.info(redisTicket.replace(RedisConstants.TOKEN_PREFIX+"_","")+" 正在登录！");
                        flag = true;
                        break;
                    }
                }
            }
            if(!flag){
                //验证不通过
                HttpServletResponse resp = (HttpServletResponse) servletResponse;
                resp.setCharacterEncoding("utf-8");
                PrintWriter out = resp.getWriter();
                out.write("401");
            }else{
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }else{
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            resp.setCharacterEncoding("utf-8");
            PrintWriter out = resp.getWriter();
            out.write("401");
        }
    }
    @Override
    public void destroy() {

    }
}
