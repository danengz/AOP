package com.yu.aopdemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        getSharedPreferences("sp", MODE_PRIVATE).edit().putBoolean("login", true).commit();
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        this.finish();
    }
}
