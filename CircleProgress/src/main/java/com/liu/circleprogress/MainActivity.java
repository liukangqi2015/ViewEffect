package com.liu.circleprogress;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private CircleProgress circleProgress;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        circleProgress.setProgress(1000);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circleProgress.setProgress(1000);
            }
        });
    }

    private void initView() {
        circleProgress= (CircleProgress) findViewById(R.id.cpr);
        btn= (Button) findViewById(R.id.btn);
    }
}
