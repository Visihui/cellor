package com.demo.code.modules.sysInfo.controller;

import com.demo.code.common.utils.FileUtils;
import com.demo.code.common.utils.Result;
import com.demo.code.modules.sysInfo.entity.SysUser;
import com.demo.code.modules.sysInfo.service.ISysUserService;
import com.demo.code.modules.sysInfo.vo.LoginVO;
import com.demo.code.modules.sysInfo.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author bing.he
 * @since 2022-05-19
 */
@Api(tags = {"用户信息表"})
@RestController
@RequestMapping("/")
public class LoginController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ISysUserService sysUserService;

    @Value("${upload.path}")
    private String uploadPath;


    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping("login")
    public Result<UserVO> appLogin(@RequestBody LoginVO loginVO, ServletResponse servletResponse) {
        return new Result<UserVO>(sysUserService.login(loginVO,servletResponse));
    }

}
