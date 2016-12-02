package com.liu.androiddrawstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button draw_base_graphics_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
    }

    private void initView() {
        draw_base_graphics_btn= (Button) findViewById(R.id.draw_base_graphics_btn);
    }

    private void setListener() {
        draw_base_graphics_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.draw_base_graphics_btn:
                startActivity(new Intent(MainActivity.this,DrawBaseGraphicsActivity.class));
                break;
            default:
                break;
        }
    }
}
