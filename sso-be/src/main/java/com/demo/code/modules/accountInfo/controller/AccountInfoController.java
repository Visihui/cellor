package com.demo.code.modules.accountInfo.controller;

import com.demo.code.common.enums.ServiceTypeEnum;
import com.demo.code.common.utils.GlobalException;
import com.demo.code.common.utils.Result;
import com.demo.code.modules.accountInfo.vo.AccountInfoVo;
import com.demo.code.modules.sysInfo.entity.SysUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.demo.code.modules.accountInfo.service.IAccountInfoService;
import com.demo.code.modules.accountInfo.entity.AccountInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 账户信息 前端控制器
 * </p>
 *
 * @author bing.he
 * @since 2022-06-09
 */
@Api(tags = {"账户信息"})
@RestController
@RequestMapping("/account")
public class AccountInfoController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IAccountInfoService accountInfoService;


    @ApiOperation(value = "新增/更新账户信息")
    @PostMapping("/add")
    public Result<Integer> add(@RequestBody @Validated AccountInfo accountInfo){
        return new Result<Integer>( accountInfoService.add(accountInfo));

    }

    @ApiOperation(value = "删除账户信息")
    @DeleteMapping("/delete/{id}")
    public Result<Integer>  delete(@PathVariable("id") Long id){
        if(null == accountInfoService.findById(id)){
            throw new GlobalException(400, "该应用不存在");
        }
        return new Result<Integer>(accountInfoService.delete(id));

    }

    @ApiOperation(value = "查询账户信息分页数据")
    @PostMapping("/list")
    public Result<IPage<AccountInfo>> findListByPage(@RequestBody AccountInfoVo accountInfoVo){
        return new Result<IPage<AccountInfo>>( accountInfoService.findListByPage(accountInfoVo,accountInfoVo.getCurrent(), accountInfoVo.getPageSize()));
    }

    @ApiOperation(value = "id查询账户信息")
    @GetMapping("/find/{id}")
    public Result<AccountInfo>  findById(@PathVariable Long id){
        if(null == accountInfoService.findById(id)){
            throw new GlobalException(400, "该应用不存在");
        }
        return new Result<AccountInfo>(accountInfoService.findById(id));
    }


    @ApiOperation(value = "初始化")
    @GetMapping("/init")
    public Result<List>  init(){
        return new Result<List>(ServiceTypeEnum.toList());
    }

}
