//import '@/mock/ApiData';
import { ResponseData } from './api';
import RequestUtils from "../utils/RequestUtils";

// interface UserInfoType{
//     id: number;
//     name: string;
//     avatar: string;
//     email: string;
//     phone: string;
//     role: number;
//     createTime: string;
//     updateTime: string;
// }

interface UserInfoType{
     id: bigint|number;
    name: string; 
    // phone: string;
    // createTime: string;
    // updateTime: string;
    age: string;
    avatar: string;
    chineseName: string;
    email: string;
    englishName: string;
    gender: string;
    loginName: string;
    password: string;
    role: string;
}
interface UserItemDataType{
    // id: number;
    id:bigint;
    name: string;
    loginName: string;
    chineseName: string|undefined;
    englishName: string|undefined;
    avatar: string|undefined;
    createBy: UserItemDataType|undefined;
    updateBy: UserItemDataType|undefined;
    // createAt: string|undefined;
    // updateAt: string|undefined;
    createTime: string|undefined;
    updateAt: string|undefined;
    description: string|undefined;
}

interface UserListType{
    page: number;
    pageSize: number;
    total: number;
    list: UserItemDataType[];

    records: UserItemDataType[];
    size: number;
    current:number;
}

interface LoginReturnType{
    ticket: string;
    user: UserInfoType;
}


const login = async (username: string, password: string): Promise<ResponseData<LoginReturnType>|undefined> => {
    return await RequestUtils.postFetch<ResponseData<LoginReturnType>|undefined>('/login', {
        loginName: username,
        password: password
    });
}

const validTicket = async (ticket: string): Promise<ResponseData<boolean>|undefined> => {
    return await RequestUtils.getFetch<ResponseData<boolean>|undefined>('/test/testGet', {
        ticket: ticket
    });
}

const fetchUsers = async ( params: any ): Promise<ResponseData<UserListType>|undefined> => {
    return await RequestUtils.postFetch<ResponseData<UserListType>|undefined>('/sysUser/list', params);
}

const addAvatar = async (avatarStr: string): Promise<ResponseData<string>|undefined> => {
    return await RequestUtils.postFetch<ResponseData<string>|undefined>('/avatar', {
        avatar: avatarStr
    });
}
const addUser = async (from: UserInfoType): Promise<ResponseData<UserInfoType>|undefined> => {
    return await RequestUtils.postFetch<ResponseData<UserInfoType>|undefined>('/sysUser/add', {
        age: from.age,
        avatar: from.avatar,
        chineseName: from.chineseName,
        email: from.email,
        englishName: from.englishName,
        gender: from.gender,
        loginName: from.loginName,
        password: from.password,
        role: from.role
    });
}

const updateUser = async (from: UserInfoType): Promise<ResponseData<UserInfoType>|undefined> => {
    return await RequestUtils.postFetch<ResponseData<UserInfoType>|undefined>('/sysUser/add', {
        id:from.id,
        age: from.age,
        avatar: from.avatar,
        chineseName: from.chineseName,
        email: from.email,
        englishName: from.englishName,
        gender: from.gender,
        loginName: from.loginName,
        password: from.password,
        role: from.role
    });
}
const deleteUser = async ( id: bigint ): Promise<ResponseData<bigint>|undefined> => {
    return await RequestUtils.deleteFetch<ResponseData<bigint>|undefined>('/sysUser/delete/'+id, {});
}

const selectUser = async (id: bigint|number ): Promise<ResponseData<UserInfoType>|undefined> => {
    return await RequestUtils.getFetch<ResponseData<UserInfoType>|undefined>('/sysUser/find/'+id, {       
    });
}

export {
    login,
    validTicket,
    fetchUsers,
    addAvatar,
    addUser,
    updateUser,
    deleteUser,
    selectUser
}

export type {
    UserInfoType,
    LoginReturnType,
    UserItemDataType
}