import axios from 'axios';
import i18n from '../i18n/config';

import { message } from 'antd';
const { t } = i18n;

/**
 * 请求工具类
 */

const baseUrl = process.env.NODE_ENV === 'development' ? '/api' : '/api/sso';

class RequestUtils {

    static async require<T>(url: string, method: string, params: any|null): Promise<T|undefined>{
        try {
            const { data, status } = await axios.request({
                url: baseUrl + url,               
                method: method,
                timeout: 3000,
                // withCredentials: true,
                data: {...params},
            });           
            switch (status) {
                case 200:
                    return data;
                // case 400:
                //     message.error();
                case 401:
                    message.error(t('common.request.401').toString());
                    break;
                case 403:
                    message.error(t('common.request.403').toString());
                    break;
                case 404:
                    message.error(t('common.request.404').toString());
                    break;
                case 500:
                    message.error(t('common.request.500').toString());
                    break;
            }
        } catch (error) {
            message.error(t('error.request').toString());
        }
    }
    
    static async getFetch<T>(url: string, params: any|null): Promise<T|undefined>{
        return await RequestUtils.require(url, 'GET', params);
    }

    static async postFetch<T>(url: string, params: any|null): Promise<T|undefined>{
        return await RequestUtils.require(url, 'POST', params);
    }

    static async updateFetch<T>(url: string, params: any|null): Promise<T|undefined>{
        return await RequestUtils.require(url, 'PUT', params);
    }

    static async deleteFetch<T>(url: string, params: any|null): Promise<T|undefined>{
        return await RequestUtils.require(url, 'DELETE', params);
    }
}

export default RequestUtils;