package com.demo.code.modules.sysInfo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.code.common.utils.FileUtils;
import com.demo.code.common.utils.GlobalException;
import com.demo.code.common.utils.Result;
import com.demo.code.modules.sysInfo.vo.LoginVO;
import com.demo.code.modules.sysInfo.vo.UserConditionVO;
import com.demo.code.modules.sysInfo.vo.UserVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.demo.code.modules.sysInfo.service.ISysUserService;
import com.demo.code.modules.sysInfo.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping("/sysUser")
public class SysUserController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ISysUserService sysUserService;

    @Value("${upload.path}")
    private String uploadPath;


    @ApiOperation(value = "头像处理")
    @PostMapping("/avatar")
    public Result<String> avatar(@RequestParam("file") MultipartFile file) throws IOException {
        String dir =System.getProperty("user.dir");
         String avatar="";
        if (!file.isEmpty())
        {
             avatar = FileUtils.uploadOne(file,dir+uploadPath);
        }
        return new Result<String>(avatar);
    }

    @ApiOperation(value = "新增用户信息表")
    @PostMapping("/add")
    public Result<Integer>  add(@RequestBody @Validated SysUser sysUser) {
        return new Result<Integer>(sysUserService.add(sysUser));

    }

    @ApiOperation(value = "查询用户信息表分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @PostMapping("/list")
    public Result<IPage<SysUser>>   findListByPage(@RequestBody UserConditionVO userConditionVO){
        // @RequestParam Integer current,
        return new Result<IPage<SysUser>>(sysUserService.findListByPage(userConditionVO,userConditionVO.getCurrent(), userConditionVO.getPageSize()));

    }


    @ApiOperation(value = "id查询用户信息表")
    @GetMapping("/find/{id}")
    public  Result<SysUser>  findById(@PathVariable Long id){
        if(null == sysUserService.findById(id)){
            throw new GlobalException(400, "该用户不存在");
        }
        return new Result<SysUser>(sysUserService.findById(id));

    }

    @ApiOperation(value = "删除用户信息表")
    @DeleteMapping("/delete/{id}")
    public Result<Integer>  delete(@PathVariable("id") Long id){
        if(null == sysUserService.findById(id)){
            throw new GlobalException(400, "该用户不存在");
        }
        return new Result<Integer>(sysUserService.delete(id));
    }


}
