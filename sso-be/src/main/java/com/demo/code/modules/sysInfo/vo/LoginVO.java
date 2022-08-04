package com.demo.code.modules.sysInfo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "登录模型对象", description = "登录模型对象")
public class LoginVO {

    @ApiModelProperty(value = "登录账号")
    private String loginName;

    @ApiModelProperty(value = "密码(登录口令)")
    private String password;
}
