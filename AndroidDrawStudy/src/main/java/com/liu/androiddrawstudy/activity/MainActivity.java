package com.liu.androiddrawstudy.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.liu.androiddrawstudy.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button draw_base_graphics_btn,canvas_operation_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
    }

    private void initView() {
        draw_base_graphics_btn= (Button) findViewById(R.id.draw_base_graphics_btn);
        canvas_operation_btn= (Button) findViewById(R.id.canvas_operation_btn);
    }

    private void setListener() {
        draw_base_graphics_btn.setOnClickListener(this);
        canvas_operation_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.draw_base_graphics_btn:
                startActivity(new Intent(MainActivity.this,DrawBaseGraphicsActivity.class));
                break;
            case R.id.canvas_operation_btn:
                startActivity(new Intent(MainActivity.this,CanvasOperationActivity.class));
                break;
            default:
                break;
        }
    }
}
