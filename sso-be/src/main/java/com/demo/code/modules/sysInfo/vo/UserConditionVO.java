package com.demo.code.modules.sysInfo.vo;

import com.demo.code.modules.sysInfo.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@ApiModel(value = "登录筛选对象", description = "登录筛选对象")
public class UserConditionVO {

    private String chineseName;

    private String englishName;

    private String email;

    private String age;

    private String gender;

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
