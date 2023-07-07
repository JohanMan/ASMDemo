package com.johan.click.interceptor.library;

import android.view.View;

/**
 * @author : FengYiHuan
 * @Description : ClickInterceptor
 * @Company : 深圳市爱聊科技有限公司
 * @vesion : v
 * @Create Date : 2022/8/29 09:07
 */
public interface ClickInterceptor {

    /**
     * 是否拦截
     *
     * @param clickView 点击的View
     * @return 结果
     */
    boolean onIntercept(View clickView);

}
