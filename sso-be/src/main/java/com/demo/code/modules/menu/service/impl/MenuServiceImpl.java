package com.demo.code.modules.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.code.modules.menu.entity.Menu;
import com.demo.code.modules.menu.dao.MenuMapper;
import com.demo.code.modules.menu.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.code.modules.sysInfo.dao.SysUserMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 外网样本信息 服务实现类
 * </p>
 *
 * @author bing.he
 * @since 2022-05-31
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuTree() {

        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
//        if(null !=treeId){
//            wrapper.eq("parent_id", treeId);
//        }else{
        wrapper.isNull("parent_id");
        //  }
        List<Menu> list = menuMapper.selectList(wrapper);
        Menu info = new Menu();
        info.setChildren(list);
        list.forEach(this::findAllChild);
        return list;
    }


    public void findAllChild(Menu menu) {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", menu.getId());
        // 首次进入这个方法时，查出的是二级节点列表
        // 递归调用时，就能依次查出三、四、五等等级别的节点列表，
        List<Menu> resources = menuMapper.selectList(wrapper);
        menu.setChildren(resources);
        if (resources != null && resources.size() > 0) {
            resources.forEach(this::findAllChild);
        }

    }
}
