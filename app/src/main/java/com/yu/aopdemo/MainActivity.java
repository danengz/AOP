package com.yu.aopdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yu.aopdemo.db.DBOperation;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void byAspectJ(View view) {
        startActivity(new Intent(this, AspectJActivity.class));
    }

    public void byProxy(View view) {
        startActivity(new Intent(this, DBActivity.class));
    }
}
