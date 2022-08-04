package com.demo.code.modules.menu.service;

import com.demo.code.modules.menu.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * <p>
 * 外网样本信息 服务类
 * </p>
 *
 * @author bing.he
 * @since 2022-05-31
 */
public interface IMenuService extends IService<Menu> {

    /**
     * menu树信息
     * @return
     */
    List<Menu> getMenuTree();
}
