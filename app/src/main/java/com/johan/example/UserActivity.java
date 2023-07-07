package com.johan.example;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author : FengYiHuan
 * @Description : ClickInterceptor
 * @Company : 深圳市爱聊科技有限公司
 * @vesion : v
 * @Create Date : 2022/8/29 15:18
 */
public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        findViewById(R.id.main_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(getClass().getName(), "main button is click!!");
                finish();
            }
        });
        findViewById(R.id.me_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(getClass().getName(), "me button is click!!");
                finish();
            }
        });
    }

}
