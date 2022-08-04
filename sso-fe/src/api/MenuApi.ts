//import '@/mock/ApiData';
import { ResponseData } from './api';
import RequestUtils from "../utils/RequestUtils";

interface MenuItemType{
    id: string;
    icon: string;
    name: string;
    path: string;
    children: MenuItemType[];
}

const getMenu = async (): Promise<ResponseData<MenuItemType[]>|undefined> => {
    return await RequestUtils.getFetch<ResponseData<MenuItemType[]>|undefined>('/menu/tree', {});
}

export default getMenu;

export type {
    MenuItemType
};