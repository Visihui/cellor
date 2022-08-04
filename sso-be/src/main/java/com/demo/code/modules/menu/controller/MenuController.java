package com.demo.code.modules.menu.controller;

import com.demo.code.common.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.demo.code.modules.menu.service.IMenuService;
import com.demo.code.modules.menu.entity.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * <p>
 * 外网样本信息 前端控制器
 * </p>
 *
 * @author bing.he
 * @since 2022-05-31
 */
@RestController
@RequestMapping("/menu")
@ApiOperation(value = "菜单信息")
public class MenuController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IMenuService menuService;

    @GetMapping("/tree")
    public Result<List<Menu>> tree(){
        return  new Result<List<Menu>>(menuService.getMenuTree());
    }

}
