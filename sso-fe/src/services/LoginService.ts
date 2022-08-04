import { login, validTicket } from '../api/UserApi';
import { getTicket, setTicket, setUserInfo } from '../utils/LocalStorageUtil';
import i18n from '../i18n/config';
import { message } from 'antd';
const { t } = i18n;

interface LoginFormType{
    username: string,
    password: string
}

/**
 * 验证登录状态
 * @returns {Promise<ResponseData<boolean>|undefined>}
 */
const checkLogin = async (): Promise<boolean> => {
    const ticket = getTicket();
    if(ticket) {
        const result = await validTicket(ticket);
        if(result && result.data) {
            return true;
        }
    }
    return false;
}
/**
 * 登录
 * @param {string} username
 * @param {string} password
 * @returns {Promise<ResponseData<UserInfo>|undefined>}
 */
const signIn = async (form: LoginFormType) => {
    const result = await login(form.username, form.password);
    // if(result && result.data) {
    //     setTicket(result.data.ticket);
    //     setUserInfo(result.data.user);
    // }   
    switch (result?.code) {       
        case 400:            
            message.warning(t('login.LoginMessage.400').toString());            
            break;      
        case 500:
            message.warning(t('login.LoginMessage.500').toString());
            break;
    }
    if(result?.code===200) {
        setTicket(result.data.ticket);
        setUserInfo(result.data.user);
    }
}

export{
    checkLogin,
    signIn
}