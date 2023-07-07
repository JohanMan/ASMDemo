package com.johan.example;

import android.app.Application;
import android.util.Log;
import android.view.View;

import com.johan.click.interceptor.library.ClickInterceptor;
import com.johan.click.interceptor.library.ClickInterceptorFinder;
import com.johan.click.interceptor.library.ClickInterceptorManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : FengYiHuan
 * @Description : ClickInterceptor
 * @Company : 深圳市爱聊科技有限公司
 * @vesion : v
 * @Create Date : 2022/8/29 10:47
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ClickInterceptorManager.init(new ClickInterceptor() {
            @Override
            public boolean onIntercept(View clickView) {
                if (ClickInterceptorFinder.getInstance().findData(clickView)) {
                    Log.e(getClass().getName(), clickView + " is intercept --------------- ");
                    return true;
                }
                return false;
            }
        });
        List<InterceptorInfo> infoList = new ArrayList<>();
        InterceptorInfo interceptorInfo = new InterceptorInfo("me_button", 0, 0);
        infoList.add(interceptorInfo);
        ClickInterceptorFinder.getInstance().addData(MainActivity.class.getName(), infoList);
        ClickInterceptorFinder.getInstance().addData(UserActivity.class.getName(), infoList);
        Log.e(getClass().getName(), "UserActivity.class.getName() : " + UserActivity.class.getName());
    }

}
