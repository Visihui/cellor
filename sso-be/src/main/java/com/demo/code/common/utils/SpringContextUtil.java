package com.demo.code.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: bing.he
 * @date 2022-02-23 14:22
 */
@Component
public class SpringContextUtil implements ApplicationContextAware
{
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws
            BeansException
    {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * @Description: 获取spring容器中的bean,通过bean名称获取
     * @param beanName bean名称
     * @return: Object 返回Object,需要做强制类型转换
     */
    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }

    /**
     * @Description: 获取spring容器中的bean, 通过bean类型获取
     * @param beanClass bean 类型
     * @return: T 返回指定类型的bean实例
     */
    public static <T> T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }

    /**
     * @Description: 获取spring容器中的bean, 通过bean名称和bean类型精确获取
     * @param beanName bean 名称
     * @param beanClass bean 类型
     * @return: T 返回指定类型的bean实例
     */
    public static <T> T getBean(String beanName, Class<T> beanClass){
        return applicationContext.getBean(beanName,beanClass);
    }

}
