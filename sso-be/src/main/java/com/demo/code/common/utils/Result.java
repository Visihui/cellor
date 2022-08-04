package com.demo.code.common.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.http.HttpStatus;

import java.io.Serializable;

/**
 * @author: bing.he
 * @date 2022-02-23 14:22
 */
@ApiModel(value = "返回类")
public class Result<T> implements Serializable {

    @JsonIgnore
    private final String success = "ok";

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "描述")
    private String msg;

    @ApiModelProperty(value = "对象")
    private T data;


    public Result() {
        super();
    }

    public Result(T data, String message, Integer status) {
        this.data = data;
        this.msg = message;
        this.code = status;
    }

    public Result(T data) {
        this.data = data;
        this.msg = success;
        this.code = HttpStatus.SC_OK;
    }

    public String getSuccess() {
        return success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
