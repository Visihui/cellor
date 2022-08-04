package com.demo.code.modules.accountInfo.service;

import com.demo.code.modules.accountInfo.entity.AccountInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.code.modules.accountInfo.vo.AccountInfoVo;

/**
 * <p>
 * 账户信息 服务类
 * </p>
 *
 * @author bing.he
 * @since 2022-06-09
 */
public interface IAccountInfoService extends IService<AccountInfo> {

    /**
     * 查询账户信息分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<AccountInfo>
     */
    IPage<AccountInfo> findListByPage(AccountInfoVo accountInfoVo, Integer page, Integer pageCount);

    /**
     * 添加账户信息
     *
     * @param accountInfo 账户信息
     * @return int
     */
    int add(AccountInfo accountInfo);

    /**
     * 删除账户信息
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改账户信息
     *
     * @param accountInfo 账户信息
     * @return int
     */
    int updateData(AccountInfo accountInfo);

    /**
     * id查询数据
     *
     * @param id id
     * @return AccountInfo
     */
    AccountInfo findById(Long id);
}
