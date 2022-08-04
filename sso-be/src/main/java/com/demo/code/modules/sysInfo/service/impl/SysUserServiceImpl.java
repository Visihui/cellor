package com.demo.code.modules.sysInfo.service.impl;

import com.demo.code.common.utils.GlobalException;
import com.demo.code.common.utils.UserUtil;
import com.demo.code.constants.RedisConstants;
import com.demo.code.modules.sysInfo.entity.SysUser;
import com.demo.code.modules.sysInfo.dao.SysUserMapper;
import com.demo.code.modules.sysInfo.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.code.modules.sysInfo.vo.LoginVO;
import com.demo.code.modules.sysInfo.vo.UserConditionVO;
import com.demo.code.modules.sysInfo.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author bing.he
 * @since 2022-05-19
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private SysUserMapper sysUserMapper;
    @Override
    public UserVO login(LoginVO loginVO, ServletResponse servletResponse) {
        UserVO userVO = new UserVO();
        String loginName = loginVO.getLoginName();
        SysUser where = new SysUser();
        where.setLoginName(loginName);
        where.setDeleted(false);
        List<SysUser> sysUserList = sysUserMapper.selectList(Wrappers.query(where));

        if(sysUserList.size()>0 && StringUtils.equals(sysUserList.get(0).getPassword(),DigestUtils.md5DigestAsHex((loginVO.getPassword()).getBytes()))){
            System.out.println("=======================================================");
            System.out.println((loginVO.getLoginName()+loginVO.getPassword()).getBytes());

            String ticket = DigestUtils.md5DigestAsHex((loginVO.getLoginName()+loginVO.getPassword()).getBytes());
            //存到redis里面
            redisTemplate.opsForValue().set(ticket,(RedisConstants.TOKEN_PREFIX+"_"+loginName),RedisConstants.TOKEN_EXPIRE, TimeUnit.SECONDS);
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            resp.setCharacterEncoding("utf-8");
            //打入到cookie里面
            resp.addCookie(new Cookie(RedisConstants.TOKEN_PREFIX+"_"+loginName,ticket));
            userVO.setTicket(ticket);
        }else{
            if(sysUserList.size()<1 ){
                throw new GlobalException(500, "该用户不存在");
            }
            if(!StringUtils.equals(sysUserList.get(0).getPassword(),DigestUtils.md5DigestAsHex((loginVO.getPassword()).getBytes()))){
                throw new GlobalException(400, "用户名密码错误");
            }
        }

        return userVO;
    }

    @Override
    @Transactional
    public int add(SysUser sysUser){
        //获取更新人或者添加人的中文名
        String loginName = UserUtil.getUser();
        SysUser loginNameWhere = new SysUser();
        loginNameWhere.setLoginName(loginName);

        SysUser loginNameUser = baseMapper.selectOne(Wrappers.query(loginNameWhere));

        String chineseName = loginNameUser.getChineseName();
        sysUser.setPassword( DigestUtils.md5DigestAsHex((sysUser.getPassword()).getBytes()));
        //查询数据库里LoginName是否有重复
        SysUser where = new SysUser();
        where.setLoginName(sysUser.getLoginName());
        List<SysUser> list = baseMapper.selectList(Wrappers.query(where));

        if(null!=sysUser.getId() && sysUser.getId()!=0){
            SysUser sysUserCheck= baseMapper.selectById(sysUser.getId());
            if(null == sysUserCheck){
                throw new GlobalException(405, "该用户已删除");
            }

            if(list.size()>0&&!StringUtils.equals(list.get(0).getLoginName(),
                    sysUserCheck.getLoginName())){
                throw new GlobalException(400, "该账号名已存在");
            }
            sysUser.setUpdateName(chineseName);
            return baseMapper.updateById(sysUser);
        }else{
            //账号名loginName不能重复

            if(list.size()>0){
                throw new GlobalException(400, "该账号名已存在");
            }
            sysUser.setCreateName(chineseName);
            return baseMapper.insert(sysUser);

        }
    }

    @Override
    public  IPage<SysUser> findListByPage(UserConditionVO userConditionVO,Integer page, Integer pageCount){
        IPage<SysUser> wherePage = new Page<>(page, pageCount);
        SysUser where = new SysUser();
        if(!StringUtils.isEmpty(userConditionVO.getChineseName())){
            where.setChineseName(userConditionVO.getChineseName());
        }
        if(!StringUtils.isEmpty(userConditionVO.getEnglishName())){
            where.setEnglishName(userConditionVO.getEnglishName());
        }
        if(!StringUtils.isEmpty(userConditionVO.getAge())){
            where.setAge(userConditionVO.getAge());
        }
        if(!StringUtils.isEmpty(userConditionVO.getEmail())){
            where.setEmail(userConditionVO.getEmail());
        }
        if(!StringUtils.isEmpty(userConditionVO.getGender())){
            where.setGender(userConditionVO.getGender());
        }
        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    @Transactional
    public int delete(Long id) {
        baseMapper.deleteById(id);
        return baseMapper.deleteById(id);
    }

    @Override
    public SysUser findById(Long id){
        return  baseMapper.selectById(id);
    }


}
