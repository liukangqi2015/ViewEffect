package com.liu.translucentsystembar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 设置不同版本的Style方式实现沉浸式状态栏
 * Created by liu on 2016/11/28.
 */
public class DiffStyleActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style);
    }
}
