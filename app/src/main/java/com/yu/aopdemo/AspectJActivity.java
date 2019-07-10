package com.yu.aopdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yu.aopdemo.annotation.ClickBehavior;
import com.yu.aopdemo.annotation.LoginCheck;

public class AspectJActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspectj);
        textView = findViewById(R.id.textView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshLoginState();
    }

    private void refreshLoginState(){
        boolean isLogin = getSharedPreferences("sp", MODE_PRIVATE).getBoolean("login", false);
        textView.setText(isLogin ? "已登录" : "未登录");
    }

    /**
     * 跳转到我的购物车
     * @param view
     */
    @LoginCheck
    @ClickBehavior("我的购物车")
    public void myShoppingCar(View view) {
        Log.i("haha", "点击按钮>>>我的购物车");
        Toast.makeText(this, "假装已经跳转到我的购物车", Toast.LENGTH_SHORT).show();
    }

    /**
     * 跳转到我的收藏
     * @param view
     */
    @LoginCheck
    @ClickBehavior("我的收藏")
    public void myCollection(View view) {
        Log.i("haha", "点击按钮>>>我的收藏");
        Toast.makeText(this, "假装已经跳转到我的收藏", Toast.LENGTH_SHORT).show();
    }

    /**
     * 退出登录
     * @param view
     */
    @ClickBehavior("登出")
    public void logout(View view) {
        getSharedPreferences("sp", MODE_PRIVATE).edit().putBoolean("login", false).commit();
        Toast.makeText(this, "登出成功", Toast.LENGTH_SHORT).show();
        refreshLoginState();
    }
}
