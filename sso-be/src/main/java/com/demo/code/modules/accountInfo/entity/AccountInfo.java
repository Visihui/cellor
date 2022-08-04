package com.demo.code.modules.accountInfo.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.demo.code.common.utils.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 账户信息
 * </p>
 *
 * @author bing.he
 * @since 2022-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AccountInfo对象", description="账户信息")
public class AccountInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.INPUT)
    @TableField(fill = FieldFill.INSERT)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;


    @Length(max=50)
    @ApiModelProperty(value = "应用名称")
    private String applicationName;


    @Length(max=5)
    @ApiModelProperty(value = "类型code")
    private String typeCode;

    @Length(max=50)
    @ApiModelProperty(value = "类型名字")
    private String typeName;

    @Length(max=255)
    @ApiModelProperty(value = "描述")
    private String applicationDescribe;

    @Length(max=50)
    @ApiModelProperty(value = "appkey")
    private String appKey;


    @Length(max=50)
    private String createName;

    @Length(max=50)
    private String updateName;
}
