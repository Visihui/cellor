package com.demo.code.modules.sysInfo.vo;

import com.demo.code.modules.sysInfo.entity.SysUser;
import com.demo.code.modules.sysInfo.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "登录模型对象", description = "登录模型对象")
public class UserVO {

    private String ticket;

    private SysUser user;
}
