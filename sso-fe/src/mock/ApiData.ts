import Mock from 'mockjs';

Mock.setup({
    timeout: '200-600'
});

Mock.mock('/api/sso/login', 'post', {
    code: 200,
    msg: '登录成功',
    data: {
        ticket: '@string(32)',
        user: {
            id: '@id',
            name: '@cname',
            chineseName: '@cname',
            englishName: '@cname',
            avatar: '@image(100x100, @color, #fff, avatar)',
            email: '@email',
            phone: '@string(11)',
            role: '@integer(1, 3)',
            createTime: '@datetime',
            updateTime: '@datetime',
        }
    }
});

Mock.mock('/api/sso/validTicket', 'post', (option: any) => {
    const ticket = option.body;
    if(ticket){
        return {
            code: 200,
            msg: '验证成功',
            data: true
        }
    }else{
        return {
            code: 200,
            msg: '验证失败',
            data: false
        }
    }
});

Mock.mock('/api/sso/menu', 'get', {
    code: 200,
    msg: '获取菜单成功',
    data: [
        {
            id: '1',
            icon: 'user',
            name: '用户',
            path: '',
            children: [
                {
                    id: '1-1',
                    icon: '',
                    name: '用户管理',
                    path: '/home/management/users',
                    children: []
                },
            ]
        },
        {
            id: '2',
            icon: 'appstore',
            name: '应用',
            path: '',
            children: [
                {
                    id: '2-1',
                    icon: '',
                    name: '应用管理',
                    path: '/home/management/applications',
                    children: []
                },
            ]
        },
        {
            id: '3',
            icon: 'setting',
            name: '设置',
            path: '',
            children: [
                {
                    id: '3-1',
                    icon: '',
                    name: '系统设置',
                    path: '/home/management/system/setting',
                    children: []
                },
            ]
        }
    ]
});

Mock.mock('/api/sso/user/list', 'get', {
    code: 200,
    msg: '获取用户列表成功',
    data: {
        page: 1,
        pageSize: 10,
        total: 50,
        'list|5-10': [
            {
                id: '@id',
                key: '@id',
                name: '@first',
                chineseName: '@cname',
                englishName: '@name',
                avatar: '@image(100x100, @color, #fff, avatar)',
                createBy: {
                    id: '@id',
                    name: '@first',
                    chineseName: '@cname',
                    englishName: '@name',
                    avatar: '@image(100x100, @color, #fff, avatar)',
                },
                updateBy: {
                    id: '@id',
                    name: '@first',
                    chineseName: '@cname',
                    englishName: '@name',
                    avatar: '@image(100x100, @color, #fff, avatar)',
                },
                createAt: '@datetime',
                updateAt: '@datetime',
                description: '@cparagraph(1, 3)',
            }
        ]
    }
});



