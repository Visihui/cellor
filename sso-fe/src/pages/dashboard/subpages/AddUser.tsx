import React,{ useState, useEffect } from "react";
import { useTranslation } from "react-i18next";
import { Input, Select, Avatar, Button, Form, InputNumber } from "antd";
import { UserOutlined,LockOutlined } from "@ant-design/icons";
import PubSub from 'pubsub-js' ;
import { createUser,editUser } from '../../../services/UserService';
import AvatarLoad from './Avatar'
import { UserInfoType } from "../../../api/UserApi";

const { Option } = Select;
const formlayout = {
    labelCol: { span: 6 },
    wrapperCol: { span: 18 },
};

interface userPropsType{
    userData: UserInfoType|undefined 
  }
// const AddUser: FC = (props:userPropsType) => {
    const AddUser= (props:userPropsType) => {
    const { t } = useTranslation();
    const avatarInit =  undefined;
    const [ avatar,setAvatar ] = useState<string|undefined>(avatarInit);
    // const [avatar, setAvatar] = useState<UploadFile<any>|undefined>(avatarInit);
  
    const [userForm] = Form.useForm(); 
    const handleChange = (value: string) => {
        // console.log(`selected ${value}`);
    };
    // const avatarhandleChange = (value: UploadFile<any>|undefined) => { 
    const avatarhandleChange = (value: string|undefined) => { 
        if(value)
        {            
             setAvatar(value);            
        }
       return value;      
    };
   
    useEffect(() => {
            userForm.resetFields(undefined);
            userForm.setFieldsValue(props.userData); 
            setAvatar(props.userData?.avatar);   
           
        }, [props.userData])
    return(
        <div> 
        <Form {...formlayout} 
                    initialValues={{ remember: true }} 
                    form = {userForm} 
                    onFinish = {async ( value )=> { 
                         value.avatar = avatar;
                        value.id=props.userData?.id;
                         if(props.userData?.id)
                         {
                            await editUser(value);         
                         }                          
                        else{
                            await createUser(value); 
                         }                   
                        //window.location.reload();
                        
                        PubSub.publish('refresh', 'refreshData');  
                     }}>
                      <Form.Item                                   
                        name="id" 
                        hidden={true}                       
                       >    
                          <Input hidden={true}  />
                    </Form.Item>
                    <Form.Item 
                        label={t('userinfo.avatar')}                        
                        name="avatar"
                        // rules={[{required:true, message: t('userinfo.avatarPlaceholder')}]}
                        >  
                         <Avatar shape="square" size={64} icon={<UserOutlined />} src={avatar} />                                                         
                         <AvatarLoad avatarChange={(p)=> avatarhandleChange(p)  } ></AvatarLoad> 
                    </Form.Item>
                    {/* <Form.Item 
                        label={t('userinfo.username')}                      
                        name="username"
                        rules={[{required:true, message: t('userinfo.usernamePlaceholder')}]}>
                        <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder={t('userinfo.usernamePlaceholder')} maxLength={20}/>
                    </Form.Item> */}
                     <Form.Item 
                        label={t('userinfo.loginName')}                        
                        name="loginName"
                        rules={[{required:true, message: t('userinfo.loginNamePlaceholder')}]}>
                        <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder={t('userinfo.loginNamePlaceholder')} maxLength={20}/>
                    </Form.Item>
                    <Form.Item 
                        label={t('userinfo.password')}                       
                        name="password"
                        rules={[{required:true, message: t('userinfo.passwordPlaceholder')}]}>
                        <Input.Password prefix={<LockOutlined className="site-form-item-icon" />} placeholder={t('userinfo.passwordPlaceholder')} maxLength={20}/>
                    </Form.Item>
                    <Form.Item 
                        label={t('userinfo.chineseName')}                        
                        name="chineseName"
                        rules={[{required:true, message: t('userinfo.chineseNamePlaceholder')}]}>
                        <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder={t('userinfo.chineseNamePlaceholder')} maxLength={20}/>
                    </Form.Item>

                    <Form.Item 
                        label={t('userinfo.englishName')}                      
                        name="englishName"
                        rules={[{required:true, message: t('userinfo.englishNamePlaceholder')}]}>
                        <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder={t('userinfo.englishNamePlaceholder')} maxLength={20}/>
                    </Form.Item>

                   
                    <Form.Item 
                        label={t('userinfo.gender')}                         
                        name="gender"
                        rules={[{required:true, message: t('userinfo.genderPlaceholder')}]}>
                        {/* <Input  placeholder={t('userinfo.genderPlaceholder')} /> */}
                        <Select  style={{ width: 120 }} onChange={handleChange} placeholder={t('userinfo.genderPlaceholder')} >                           
                            <Option value="0">{t('userinfo.sex.male')} </Option>
                            <Option value="1">{t('userinfo.sex.female')} </Option>
                        </Select>
                    </Form.Item>
                    <Form.Item 
                        label={t('userinfo.age')}                       
                        name="age"
                        rules={[{required:true, message: t('userinfo.agePlaceholder')}]}>
                        <InputNumber min="0"  placeholder={t('userinfo.agePlaceholder')} />
                    </Form.Item>
                    <Form.Item 
                        label={t('userinfo.role')}                        
                        name="role"
                        rules={[{required:true, message: t('userinfo.rolePlaceholder')}]}>
                        <Input  placeholder={t('userinfo.rolePlaceholder')} maxLength={50}/>
                    </Form.Item>
                    <Form.Item 
                        label={t('userinfo.email')}                       
                        name="email"
                        rules={[{required:true, message: t('userinfo.emailPlaceholder')}]} >
                        <Input  placeholder={t('userinfo.emailPlaceholder')} maxLength={50} />
                    </Form.Item>                   
                    <Form.Item
                        wrapperCol={{ span: 18, offset: 6 }}>
                        <Button type="primary" htmlType="submit">{t('common.okText')}</Button>                         
                    </Form.Item>
                </Form>
        </div>
 
    );
}
export default AddUser