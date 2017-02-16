package com.liu.androiddrawstudy.fragment;

import android.view.View;
import android.widget.Button;

import com.liu.androiddrawstudy.R;
import com.liu.androiddrawstudy.view.TouchPathView;

/**
 * Created by liu on 2017/2/16.
 */

public class TouchPathViewFragment extends BaseFragment {
    private Button reset_btn;
    private TouchPathView touch_path_view;

    @Override
    public int getLayout() {
        return R.layout.frag_touch_path_view;
    }

    @Override
    protected void init(View rootView) {
        reset_btn= (Button) rootView.findViewById(R.id.reset_btn);
        touch_path_view= (TouchPathView) rootView.findViewById(R.id.touch_path_view);
        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touch_path_view.reset();
            }
        });
    }
}
