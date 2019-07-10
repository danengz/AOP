package com.yu.aopdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.yu.aopdemo.db.DBOperation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DBActivity extends AppCompatActivity implements DBOperation, View.OnClickListener {

    DBOperation dbOperation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        findViewById(R.id.insert).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.update).setOnClickListener(this);

//        dbOperation = new DBOperation();
        dbOperation = (DBOperation) Proxy.newProxyInstance(DBOperation.class.getClassLoader(), new Class[]{DBOperation.class}, new DBHandler(this));
    }

    class DBHandler implements InvocationHandler{

        private DBOperation dbOperation;
        DBHandler(DBOperation dbOperation){
            this.dbOperation = dbOperation;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
            if (dbOperation != null) {
                //先进行查询操作
                dbOperation.save();
            }
            return method.invoke(dbOperation, args);
        }
    }


    @Override
    public void insert() {
        Log.i("haha", "数据库操作>>>增");
    }

    @Override
    public void delete() {
        Log.i("haha", "数据库操作>>>删");
    }

    @Override
    public void update() {
        Log.i("haha", "数据库操作>>>改");
    }

    @Override
    public void save() {
        Log.i("haha", "数据库操作>>>备份");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.insert:
                dbOperation.insert();
                break;

            case R.id.delete:
                dbOperation.delete();
                break;

            case R.id.update:
                dbOperation.update();
                break;
        }
    }

}
