package com.johan.click.interceptor.library;

import android.view.View;

/**
 * @author : FengYiHuan
 * @Description : ClickInterceptor
 * @Company : 深圳市爱聊科技有限公司
 * @vesion : v
 * @Create Date : 2022/8/29 09:07
 */
public class ClickInterceptorManager {

    private static volatile ClickInterceptorManager instance;

    public static ClickInterceptorManager getInstance() {
        if (instance == null) {
            synchronized (ClickInterceptorManager.class) {
                if (instance == null) {
                    instance = new ClickInterceptorManager();
                }
            }
        }
        return instance;
    }

    // 版本号
    private int version;
    // 拦截器
    private ClickInterceptor clickInterceptor;

    private ClickInterceptorManager() {

    }

    /**
     * 设置拦截器 初始化使用
     *
     * @param clickInterceptor 拦截器
     */
    private void setClickInterceptor(ClickInterceptor clickInterceptor) {
        this.clickInterceptor = clickInterceptor;
    }

    /**
     * 是否拦截
     *
     * @param clickView 点击View
     * @return 结果
     */
    public boolean isIntercept(View clickView) {
        if (clickInterceptor != null) {
            return clickInterceptor.onIntercept(clickView);
        }
        return false;
    }

    /**
     * 设置版本号
     *
     * @param version 版本号
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * 获取版本号
     *
     * @return 结果
     */
    public int getVersion() {
        return version;
    }

    /**
     * 初始化 设置拦截器
     *
     * @param clickInterceptor 拦截器
     */
    public static void init(ClickInterceptor clickInterceptor) {
        getInstance().setClickInterceptor(clickInterceptor);
    }

}
