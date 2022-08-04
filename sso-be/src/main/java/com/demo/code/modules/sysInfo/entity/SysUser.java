package com.demo.code.modules.sysInfo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.demo.code.common.utils.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author bing.he
 * @since 2022-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysUser对象", description="用户信息表")
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.INPUT)
    @TableField(fill = FieldFill.INSERT)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    @Length(max=25)
    @ApiModelProperty(value = "姓名")
    private String chineseName;

    @Length(max=25)
    @ApiModelProperty(value = "英文名")
    private String englishName;

    @Length(max=25)
//    @NotNull(message= "登录名不能为空")
    @ApiModelProperty(value = "登录名称")
    private String loginName;


    @ApiModelProperty(value = "登录名称")
    private String avatar;

    @Length(max=200)
    @ApiModelProperty(value = "email")
    private String email;

    @Length(max=150)
    @ApiModelProperty(value = "密码")
    private String password;

    @Length(max=30)
    @ApiModelProperty(value = "年龄")
    private String age;

    @Length(max=5)
    @ApiModelProperty(value = "用户性别（0男 1女 2未知）")
    private String gender;

    @Length(max=30)
    @ApiModelProperty(value = "角色")
    private String role;

    @Length(max=25)
    @ApiModelProperty(value = "创建人")
    private String createName;

    @Length(max=25)
    @ApiModelProperty(value = "更新人")
    private String updateName;

}