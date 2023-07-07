package com.johan.example;

import com.johan.click.interceptor.library.ClickInterceptorInfo;

/**
 * @author : FengYiHuan
 * @Description : ClickInterceptor
 * @Company : 深圳市爱聊科技有限公司
 * @vesion : v
 * @Create Date : 2022/8/29 15:16
 */
public class InterceptorInfo implements ClickInterceptorInfo {

    private String viewIdName;
    private int viewLevel;
    private int viewVersion;

    public InterceptorInfo(String viewIdName, int viewLevel, int viewVersion) {
        this.viewIdName = viewIdName;
        this.viewLevel = viewLevel;
        this.viewVersion = viewVersion;
    }

    @Override
    public String getInterceptViewIdName() {
        return viewIdName;
    }

    @Override
    public int getInterceptViewLevel() {
        return viewLevel;
    }

    @Override
    public int getInterceptViewVersion() {
        return viewVersion;
    }

}
