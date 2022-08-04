package com.demo.code.constants;

/**
 * Redis常量
 */
public class RedisConstants {

    /**
     * key值前缀
     */
    public static final String TOKEN_PREFIX = "token_prefix";

//    /**
//     * token的有效期，2个小时
//     */
//    public static final Integer TOKEN_EXPIRE = 60 * 60 * 2;

    /**
     * token的有效期，2分钟
     */
    public static final Integer TOKEN_EXPIRE = 3600;

}
