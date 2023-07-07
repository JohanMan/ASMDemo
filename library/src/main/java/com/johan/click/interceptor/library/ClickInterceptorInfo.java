package com.johan.click.interceptor.library;

/**
 * @author : FengYiHuan
 * @Description : ClickInterceptor
 * @Company : 深圳市爱聊科技有限公司
 * @vesion : v
 * @Create Date : 2022/8/29 09:07
 */
public interface ClickInterceptorInfo {

    /**
     * 获取拦截View的ID字符串
     *
     * @return 拦截View的ID字符串
     */
    String getInterceptViewIdName();

    /**
     * 获取拦截View的层级
     *
     * @return 拦截View的层级
     */
    int getInterceptViewLevel();

    /**
     * 获取拦截View的版本号
     *
     * @return 拦截View的版本号
     */
    int getInterceptViewVersion();

}
