import { getApplicationList,selectApplication,addApplication,updateApplication,deleteApplication,getApplicationTypeList } from '../api/ApplicationApi';
import { ApplicationInfoType } from "../api/ApplicationApi";
import i18n from '../i18n/config';
import { message } from 'antd';
const { t } = i18n;

 // *查询应用信息列表
//  @returns {Promise<ResponseData<ApplicationListType>>}
const getApplicationDataList = async (params:any) => {
    const result = await getApplicationList(params);
        if(result?.code===200)
        {
            return result;
        }
    return undefined;       
    }
// 新增应用
// @returns {Promise<ResponseData<ApplicationInfoType>|undefined>}
const createApplication = async (form: ApplicationInfoType) => {
    const result = await addApplication(form);
        if(result?.code===200)
        {
            message.success(t('applicationInfo.returnMessage.success').toString());
        } 
        else if(result?.code===400) 
        {
            message.warning(t('applicationInfo.returnMessage.failed').toString());
        }   
        else{
            message.error(t('applicationInfo.returnMessage.error').toString());
        }
    }
// 修改应用
// @returns {Promise<ResponseData<ApplicationInfoType>|undefined>}
const editApplication = async (form: ApplicationInfoType) => {
    const result = await updateApplication(form);
        if(result?.code===200)
        {
            message.success(t('applicationInfo.updateMessage.success').toString());
        } 
        else if(result?.code===405) 
        {
            message.warning(t('applicationInfo.updateMessage.failed').toString());
        }       
        else{
            message.error(t('applicationInfo.updateMessage.error').toString());
        }
    }

// *删除应用
//  @returns {Promise<ResponseData<BigInt>>}
const dropApplication = async (id: bigint|number) => {
    const result = await deleteApplication(id);
        if(result?.code===200)
        {
            message.success(t('applicationInfo.deleteMessage.success').toString());
        }               
        else{
            message.error(t('applicationInfo.deleteMessage.failed').toString());
        }
    }   

// *查询应用
//  @returns {Promise<ResponseData<UserInfoType>>}
const searchApplication = async (id: bigint|number) => {
    const result = await selectApplication(id);
        if(result?.code===200)
        {
            return result.data;
        }
        return undefined;       
    }   

    // *查询应用
//  @returns {Promise<ResponseData<UserInfoType>>}
const searchApplicationTypeList = async () => {
    const result = await getApplicationTypeList();
        if(result?.code===200)
        {
            return result;
        }
        return undefined;       
    } 
export{       
    getApplicationDataList,       
    createApplication,
    editApplication,
    dropApplication,
    searchApplication,
    searchApplicationTypeList
}