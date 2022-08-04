package com.demo.code.common.advice;

import com.demo.code.common.utils.GlobalException;
import com.demo.code.common.utils.Result;
import com.demo.code.common.utils.ResultUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@org.springframework.web.bind.annotation.ControllerAdvice(basePackages = {
    "com.demo.code.modules.*.controller.*"}, annotations = {
    Controller.class, RestController.class})
public class ExceptionAdvice {

  private Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

  @ExceptionHandler({GlobalException.class, Exception.class})
  @ResponseBody
  public ResponseEntity handle(Exception e) {
    logger.error(ExceptionUtils.getStackTrace(e));
    Result result;
    if (e instanceof GlobalException) {
      GlobalException globalException = (GlobalException) e;
      result = ResultUtil.getError(globalException.getCode(), globalException.getMessage());
    } else if (e instanceof MethodArgumentNotValidException) {
      MethodArgumentNotValidException methodArgumentNotValidException =(MethodArgumentNotValidException) e;
      result = ResultUtil.getError(HttpStatus.SC_BAD_REQUEST,
          methodArgumentNotValidException.getBindingResult().getFieldError().getDefaultMessage());
    } else {
      result = ResultUtil.getError(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
    }
    logger.error(result.getMsg());
    return new ResponseEntity<>(result, org.springframework.http.HttpStatus.OK);
  }

}
