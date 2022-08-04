package com.demo.code.common.utils;

import com.demo.code.common.Servlet.Filter.SSOFilter;
import com.demo.code.modules.sysInfo.entity.SysUser;

public class UserUtil {
    public static String  getUser() {

        return SSOFilter.loginName;
    }
}
