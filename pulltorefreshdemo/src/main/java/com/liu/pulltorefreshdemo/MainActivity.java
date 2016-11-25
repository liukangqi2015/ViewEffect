package com.liu.pulltorefreshdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 *
 * Created by liu on 2016/11/24.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button pull_to_refresh_btn,ultra_pull_to_refresh_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
    }

    private void initView() {
        pull_to_refresh_btn= (Button) findViewById(R.id.pull_to_refresh_btn);
        ultra_pull_to_refresh_btn= (Button) findViewById(R.id.ultra_pull_to_refresh_btn);
    }

    private void setListener() {
        pull_to_refresh_btn.setOnClickListener(this);
        ultra_pull_to_refresh_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pull_to_refresh_btn:
                startActivity(new Intent(MainActivity.this,PullToRefreshActivity.class));
                break;
            case R.id.ultra_pull_to_refresh_btn:
                startActivity(new Intent(MainActivity.this,UltraPullToRefreshActivity.class));
                break;
            default:
                break;
        }
    }
}
