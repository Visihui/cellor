package com.demo.code.modules.sysInfo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.demo.code.common.utils.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="User对象", description="用户信息表")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "中文名")
    private String chineseName;

    @ApiModelProperty(value = "英文名")
    private String englishName;

    @ApiModelProperty(value = "头像")
    private String  avatar;

    @ApiModelProperty(value = "邮箱")
    private String  email;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "相别）")
    private String role;

}
