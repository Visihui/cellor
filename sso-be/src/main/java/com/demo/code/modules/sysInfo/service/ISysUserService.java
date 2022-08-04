package com.demo.code.modules.sysInfo.service;

import com.demo.code.modules.sysInfo.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.code.modules.sysInfo.vo.LoginVO;
import com.demo.code.modules.sysInfo.vo.UserConditionVO;
import com.demo.code.modules.sysInfo.vo.UserVO;

import javax.servlet.ServletResponse;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author bing.he
 * @since 2022-05-19
 */
public interface ISysUserService extends IService<SysUser> {

   /**
     * 查询用户信息表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<SysUser>
     */
    IPage<SysUser> findListByPage(UserConditionVO userConditionVO,Integer page, Integer pageCount);


   /**
     * 删除用户信息表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * id查询数据
     *
     * @param id id
     * @return SysUser
     */
    SysUser findById(Long id);

    /**
     * 登录
     * @param loginVO
     * @return
     */
    UserVO login(LoginVO loginVO, ServletResponse servletResponse);

    /**
     * 添加用户信息表
     *
     * @param sysUser 用户信息表
     * @return int
     */
    int add(SysUser sysUser);

}
