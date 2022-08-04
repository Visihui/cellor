package com.demo.code.common.utils;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 基础实体类
 * @author: bing.he
 * @create: 2022-02-22 11:31
 */
@Data
public class BaseEntity implements Serializable {

  @TableField(fill = FieldFill.INSERT)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  protected Date createTime;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @TableField(fill = FieldFill.UPDATE)
  protected Date updateTime;

  @TableLogic(value = "0", delval = "1")
  @TableField(fill = FieldFill.INSERT)
  protected Boolean deleted;

}
