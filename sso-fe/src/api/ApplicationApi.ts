import { ResponseData } from './api';
import RequestUtils from "../utils/RequestUtils";


interface ApplicationType{ 
    id: bigint|number;  
    typeCode: string;
    typeName: string;
}

interface ApplicationTypeList{   
    records: ApplicationType[];
}

interface ApplicationInfoType{
    id: bigint|number;
    applicationName: string;
    typeCode: string;
    typeName: string;      
    applicationDescribe: string|undefined;
    appKey: string|undefined;  
}

interface ApplicationItemDataType{
    id: bigint|number;
    applicationName: string;
    typeCode: string;
    typeName: string;      
    applicationDescribe: string|undefined;
    appKey: string|undefined;   
    createTime: string|undefined;
    updateTime: string|undefined;
    createName: string|undefined;
    updateName: string|undefined;
}

interface ApplicationListType{
    total: number; 
    records: ApplicationItemDataType[];
    size: number;
    current:number;  
}

const getApplicationTypeList = async (): Promise<ResponseData<ApplicationTypeList>|undefined> => {
    return await RequestUtils.getFetch<ResponseData<ApplicationTypeList>|undefined>('/account/init', {       
    });
}

const getApplicationList = async ( params: any ): Promise<ResponseData<ApplicationListType>|undefined> => {
    return await RequestUtils.postFetch<ResponseData<ApplicationListType>|undefined>('/account/list', params);
}

const addApplication = async (from: ApplicationInfoType): Promise<ResponseData<ApplicationInfoType>|undefined> => {
    return await RequestUtils.postFetch<ResponseData<ApplicationInfoType>|undefined>('/account/add', {
        applicationName: from.applicationName,
        typeCode: from.typeCode,               
        applicationDescribe: from.applicationDescribe        
    });
}

const updateApplication = async (from: ApplicationInfoType): Promise<ResponseData<ApplicationInfoType>|undefined> => {
    return await RequestUtils.postFetch<ResponseData<ApplicationInfoType>|undefined>('/account/add', {
        id:from.id,
        applicationName: from.applicationName,
        typeCode: from.typeCode,
        applicationDescribe: from.applicationDescribe    
    });
}
const deleteApplication = async ( id: bigint|number ): Promise<ResponseData<bigint>|undefined> => {
    return await RequestUtils.deleteFetch<ResponseData<bigint>|undefined>('/account/delete/'+id, {});
}

const selectApplication = async (id: bigint|number ): Promise<ResponseData<ApplicationInfoType>|undefined> => {
    return await RequestUtils.getFetch<ResponseData<ApplicationInfoType>|undefined>('/account/find/'+id, {       
    });
}
export {
    getApplicationList,
    addApplication,
    updateApplication,
    deleteApplication,
    selectApplication,
    getApplicationTypeList
} ;

export type {
    ApplicationItemDataType,
    ApplicationListType,    
    ApplicationInfoType,
    ApplicationType,
    ApplicationTypeList
};