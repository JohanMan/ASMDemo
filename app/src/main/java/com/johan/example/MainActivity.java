package com.johan.example;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author : FengYiHuan
 * @Description : ClickInterceptor
 * @Company : 深圳市爱聊科技有限公司
 * @vesion : v
 * @Create Date : 2022/8/29 09:15
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.user_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(getClass().getName(), "user button is click!!");
                startActivity(new Intent(MainActivity.this, UserActivity.class));
            }
        });
        findViewById(R.id.me_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(getClass().getName(), "me button is click!!");
                startActivity(new Intent(MainActivity.this, UserActivity.class));
            }
        });
    }

}
