package com.demo.code.modules.accountInfo.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@ApiModel(value = "查询模型对象", description = "查询模型对象")
public class AccountInfoVo {

    private String applicationName;

    private String typeCode;

    private String appKey;

    private Integer current;

    private Integer pageSize;

    private Integer total;

//    public Integer getPageSize() {
//        return pageSize;
//    }
//
//    public Integer getCurrent() {
//        Date currentTime = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateString = sdf.format(currentTime);
//        int i = Integer.parseInt(dateString);
//        return i;
//    }
}

