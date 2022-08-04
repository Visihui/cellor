package com.demo.code.modules.lobInfo.controller;

import com.demo.code.common.utils.Result;
import com.demo.code.modules.sysInfo.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"测试"})
@RestController
@RequestMapping("test")
@Slf4j
public class TestController {
	
	/**
	 * 测试get方法
	 * @return
	 */
	@ApiOperation(value = "创建数据集")
	@GetMapping("/testGet")
	public Result<Boolean> testGet(){
		System.out.println("get使用参数：");
		return new Result<Boolean>(true);
	}
	
	/**
	 * 测试post方法
	 * @param userName
	 * @return
	 */
	@ApiOperation(value = "创建数据集")
	@PostMapping("testPost")
	public String testPost(String userName){
		System.out.println("post使用参数："+userName);
		return userName;
	}
}