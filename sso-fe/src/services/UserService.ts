import { addAvatar, addUser,updateUser,deleteUser,selectUser } from '../api/UserApi';
import i18n from '../i18n/config';
import { message } from 'antd';
const { t } = i18n;

interface UserInfoType{
     id: bigint;
     name: string; 
    // phone: string;
    // createTime: string;
    // updateTime: string;
    age: string,
    avatar: string,
    chineseName: string,
    email: string,
    englishName: string,
    gender: string,
    loginName: string,
    password: string,
    role: string
}

/**
 * 添加头像 
 * @returns {Promise<ResponseData<string>|undefined>}
 */
 const createAvatar = async (avatarStr:string) => {
    const result = await addAvatar(avatarStr);    
    return result;
}
/**
 * 添加用户
//  * @param {string} age
//  * @param {string} avatar
//  * @param {string} chineseName
//  * @param {string} email
//  * @param {string} englishName
//  * @param {string} gender
//  * @param {string} loginName
//  * @param {string} password
//  * @param {string} role
 * @returns {Promise<ResponseData<UserInfoType>|undefined>}
 */
 
const createUser = async (form: UserInfoType) => {
    const result = await addUser(form);
        if(result?.code===200)
        {
            message.success(t('userinfo.returnMessage.success').toString());
        } 
        else if(result?.code===400) 
        {
            message.warning(t('userinfo.returnMessage.failed').toString());
        }   
        else{
            message.error(t('userinfo.returnMessage.error').toString());
        }
    }
// 修改用户
// @returns {Promise<ResponseData<UserInfoType>|undefined>}
const editUser = async (form: UserInfoType) => {
    const result = await updateUser(form);
        if(result?.code===200)
        {
            message.success(t('userinfo.updateMessage.success').toString());
        } 
        else if(result?.code===405) 
        {
            message.warning(t('userinfo.updateMessage.failed').toString());
        }       
        else{
            message.error(t('userinfo.updateMessage.error').toString());
        }
    }
// *删除用户
//  @returns {Promise<ResponseData<BigInt>>}
const dropUser = async (id: bigint) => {
    const result = await deleteUser(id);
        if(result?.code===200)
        {
            message.success(t('userinfo.deleteMessage.success').toString());
        }
       
        else{
            message.error(t('userinfo.deleteMessage.failed').toString());
        }
    }
    // *查询用户
//  @returns {Promise<ResponseData<UserInfoType>>}
const searchUser = async (id: bigint|number) => {
    const result = await selectUser(id);
        if(result?.code===200)
        {
            return result.data;
        }
       return undefined;       
    }
export{
    createAvatar,
    createUser,    
    editUser,
    dropUser,
    searchUser
}



