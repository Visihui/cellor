import React,{ FC, useEffect, useState } from 'react';
import { Menu } from 'antd';
import getMenu, { MenuItemType } from '../../api/MenuApi';
import { UserOutlined, SettingOutlined, AppstoreOutlined } from '@ant-design/icons';
import { Link } from 'react-router-dom';

const getIcon = (icon: string) => {
    switch (icon) {
        case 'user':
            return <UserOutlined />;
        case 'setting':
            return <SettingOutlined />;
        case 'appstore':
            return <AppstoreOutlined />;
        default:
            return;
    }
}

const parseMenu = (menu: MenuItemType[]) => {
    return menu.map(item => {
        if(item.children && item.children.length > 0) {
            return(
                <Menu.SubMenu key={item.id} title={item.name} icon={getIcon(item.icon)}>
                    {parseMenu(item.children)}
                </Menu.SubMenu>
            );
        } else {
            return (
                <Menu.Item key={item.id} icon={getIcon(item.icon)}>
                    <Link to={item.path} key={item.id}>
                        {item.name}
                    </Link>
                </Menu.Item>
            );
        }
    });
};

const AppMenu: FC = () => {

    const [menu, setMenu] = useState<MenuItemType[]>([]);

    useEffect(() => {
        getMenu()
        .then(res => {
            if(res && res.code === 200) {
                setMenu(res.data);
            }
        }).catch(err => {
            return err;
        });
    },[]);

    return(
        <Menu mode="inline">
            {parseMenu(menu)}
        </Menu>
    );
}


export default AppMenu;