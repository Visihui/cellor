package com.demo.code.common.utils;

/**
 * @author: bing.he
 * @date 2022-02-23 14:22
 */
public class ResultUtil {

  public static Result getOK(Object object) {
    Result result = new Result();
    result.setCode(200);
    result.setMsg("Success");
    result.setData(object);
    return result;
  }

  public static Result getOK() {
    return getOK(null);
  }

  public static Result getError(Integer code, String msg) {
    Result result = new Result();
    result.setCode(code);
    result.setMsg(msg);
    return result;
  }
}
