package com.demo.code.modules.accountInfo.service.impl;

import cn.hutool.core.date.DateUtil;
import com.demo.code.common.enums.ServiceTypeEnum;
import com.demo.code.common.utils.GlobalException;
import com.demo.code.common.utils.UserUtil;
import com.demo.code.modules.accountInfo.entity.AccountInfo;
import com.demo.code.modules.accountInfo.dao.AccountInfoMapper;
import com.demo.code.modules.accountInfo.service.IAccountInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.code.modules.accountInfo.vo.AccountInfoVo;
import com.demo.code.modules.sysInfo.dao.SysUserMapper;
import com.demo.code.modules.sysInfo.entity.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.util.DigestUtils;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 账户信息 服务实现类
 * </p>
 *
 * @author bing.he
 * @since 2022-06-09
 */
@Service
public class AccountInfoServiceImpl extends ServiceImpl<AccountInfoMapper, AccountInfo> implements IAccountInfoService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public  IPage<AccountInfo> findListByPage(AccountInfoVo accountInfoVo, Integer page, Integer pageCount){
        IPage<AccountInfo> wherePage = new Page<>(page, pageCount);
        AccountInfo where = new AccountInfo();
        if(!StringUtils.isEmpty(accountInfoVo.getApplicationName())){
            where.setApplicationName(accountInfoVo.getApplicationName());
        }
        if(!StringUtils.isEmpty(accountInfoVo.getTypeCode())){
            where.setTypeCode(accountInfoVo.getTypeCode());
        }
        if(!StringUtils.isEmpty(accountInfoVo.getAppKey())){
            where.setAppKey(accountInfoVo.getAppKey());
        }
        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(AccountInfo accountInfo){
        //获取更新人或者添加人的中文名
        String loginName = UserUtil.getUser();
        SysUser loginNameWhere = new SysUser();
        loginNameWhere.setLoginName(loginName);
        SysUser loginNameUser = sysUserMapper.selectOne(Wrappers.query(loginNameWhere));
        String chineseName = loginNameUser.getChineseName();
        //获取类型名称
/*        for (ServiceTypeEnum obj : ServiceTypeEnum.values()) {
            if(StringUtils.equals(obj.getCode().trim(),accountInfo.getTypeCode().trim())){
                accountInfo.setTypeName(obj.getName());
                break;
            }
        }*/
        AccountInfo where = new AccountInfo();
        where.setApplicationName(accountInfo.getApplicationName());
        List<AccountInfo> list = baseMapper.selectList(Wrappers.query(where));
        if(null!=accountInfo.getId() && accountInfo.getId()!=0){
            AccountInfo accountInfoCheck =baseMapper.selectById(accountInfo.getId());
            if(null == accountInfoCheck){
                throw new GlobalException(405, "该应用已删除");
            }
            if(list.size()>0 && !StringUtils.equals(list.get(0).getApplicationName(),
                    accountInfoCheck.getApplicationName())){
                throw new GlobalException(400, "该应用名已存在");
            }
            accountInfo.setUpdateName(chineseName);
            return baseMapper.updateById(accountInfo);
        }else{
            //账号名loginName不能重复
            if(list.size()>0){
                throw new GlobalException(400, "该应用名已存在");
            }
            //appKey
            String key = DigestUtils.md5DigestAsHex((accountInfo.getApplicationName()+ DateUtil.formatDateTime(new Date())).getBytes());
            accountInfo.setAppKey(key);
            accountInfo.setCreateName(chineseName);
            return baseMapper.insert(accountInfo);

        }
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(AccountInfo accountInfo){
        return baseMapper.updateById(accountInfo);
    }

    @Override
    public AccountInfo findById(Long id){
        return  baseMapper.selectById(id);
    }
}
