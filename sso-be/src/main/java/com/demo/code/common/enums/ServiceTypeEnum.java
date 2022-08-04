package com.demo.code.common.enums;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ServiceTypeEnum {
    WE_CHAT("1", "weChat"),
    ANDROID("2", "android"),
    IOS("3", "ios"),
    WEB("4", "web");

    private String code;
    private String name;

    ServiceTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


    //讲枚举转换成list格式，这样前台遍历的时候比较容易，列如 下拉框 后台调用toList方法，你就可以得到code 和name了
    public static List toList() {
        List list = Lists.newArrayList();//Lists.newArrayList()其实和new ArrayList()几乎一模
        //  一样, 唯一它帮你做的(其实是javac帮你做的), 就是自动推导(不是"倒")尖括号里的数据类型.

        for (ServiceTypeEnum serviceTypeEnum : ServiceTypeEnum.values()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", serviceTypeEnum.getCode());
            map.put("name", serviceTypeEnum.getName());
            list.add(map);
        }
        return list;
    }

}
