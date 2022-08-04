import React,{ FC } from 'react';
import { useTranslation } from 'react-i18next';
import { Card, Form, Input, Button } from 'antd';
import { UserOutlined, LockOutlined } from '@ant-design/icons';
import './LoginPage.less';
import Logo from '@/assets/logo.svg';
import { signIn } from '../../services/LoginService';

const formlayout = {
    labelCol: { span: 6 },
    wrapperCol: { span: 18 },
};

const LoginTitle: FC = () => {
    const { t } = useTranslation();
    return(
        <div className="login-title">
            <img src={Logo} alt="logo" />
            <span>{t('login.Title')}</span>
        </div>
    );
}

const LoginPage: FC = () => {
    const { t } = useTranslation();
    const [loginForm] = Form.useForm();
    return(
        <div className={'login-container'}>
            <Card title={ <LoginTitle/> } className={'login-card'}>
                <Form {...formlayout} 
                    initialValues={{ remember: true }} 
                    form = {loginForm} 
                    onFinish = {async ( value )=> { 
                        await signIn(value);
                         window.location.reload();
                     }}>
                    <Form.Item 
                        label={t('login.UsernameLabel')} 
                        name="username"
                        rules={[{required:true, message: t('login.UsernamePlaceholder')}]}>
                        <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder={t('login.UsernamePlaceholder')} />
                    </Form.Item>
                    <Form.Item 
                        label={t('login.PasswordLabel')} 
                        name="password"
                        rules={[{required:true, message: t('login.PasswordPlaceholder')}]}>
                        <Input.Password prefix={<LockOutlined className="site-form-item-icon" />} placeholder={t('login.PasswordPlaceholder')} />
                    </Form.Item>
                    <Form.Item
                        wrapperCol={{ span: 18, offset: 6 }}>
                        <Button type="primary" htmlType="submit">{t('login.LoginButton')}</Button> 
                        <a href="/forgotpwd" className='form-login-forgot'>{t('login.ForgotPasswordTips')}</a>
                    </Form.Item>
                </Form>
            </Card>
        </div>
    );
}

export default LoginPage;