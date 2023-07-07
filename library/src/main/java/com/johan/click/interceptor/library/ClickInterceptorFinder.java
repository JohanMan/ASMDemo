package com.johan.click.interceptor.library;

import android.content.Context;
import android.view.View;
import android.view.ViewParent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : FengYiHuan
 * @Description : ClickInterceptor
 * @Company : 深圳市爱聊科技有限公司
 * @vesion : v
 * @Create Date : 2022/8/29 11:04
 */
public class ClickInterceptorFinder {

    private static volatile ClickInterceptorFinder instance;

    public static ClickInterceptorFinder getInstance() {
        if (instance == null) {
            synchronized (ClickInterceptorFinder.class) {
                if (instance == null) {
                    instance = new ClickInterceptorFinder();
                }
            }
        }
        return instance;
    }

    private Map<String, List<? extends ClickInterceptorInfo>> infoMap;

    private ClickInterceptorFinder() {
        infoMap = new HashMap<>();
    }

    /**
     * 重置数据
     */
    public void resetData() {
        infoMap.clear();
    }

    /**
     * 添加数据
     *
     * @param contextName 页面名称
     * @param infoList    拦截View信息列表
     */
    public void addData(String contextName, List<? extends ClickInterceptorInfo> infoList) {
        infoMap.put(contextName, infoList);
    }

    /**
     * 是否拦截View
     *
     * @param clickView 点击View
     * @return 结果
     */
    public boolean findData(View clickView) {
        Context context = clickView.getContext();
        // 判断页面
        String contextName = context.getClass().getName();
        // 存在相同页面
        if (infoMap.containsKey(contextName)) {
            // 判断ID
            List<? extends ClickInterceptorInfo> infoList = infoMap.get(contextName);
            for (ClickInterceptorInfo info : infoList) {
                try {
                    // View ID String 转 View ID
                    int interceptViewId = context.getResources().getIdentifier(info.getInterceptViewIdName(), "id", context.getPackageName());
                    // 相同ID
                    if (clickView.getId() == interceptViewId) {
                        // 判断层级
                        if (info.getInterceptViewLevel() > 0) {
                            // 获取View的层级
                            int viewLevel = getViewLevel(clickView);
                            if (viewLevel != info.getInterceptViewLevel()) {
                                continue;
                            }
                        }
                        // 判断版本号
                        if (info.getInterceptViewVersion() > 0) {
                            if (ClickInterceptorManager.getInstance().getVersion() != info.getInterceptViewVersion()) {
                                continue;
                            }
                        }
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 获取View的层级
     * @param view
     * @return
     */
    public int getViewLevel(View view) {
        int level = 0;
        ViewParent parent = view.getParent();
        while (parent != null) {
            level ++;
            if (parent instanceof View) {
                if (((View) parent).getId() == android.R.id.content) {
                    break;
                }
            }
            parent = parent.getParent();
        }
        return level;
    }

}
