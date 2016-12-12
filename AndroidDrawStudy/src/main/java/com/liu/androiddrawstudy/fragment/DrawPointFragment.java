package com.liu.androiddrawstudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liu.androiddrawstudy.R;

/**
 * 画点的Fragment
 * Created by liu on 2016/12/12.
 */

public class DrawPointFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.frag_draw_point,container,false);
        return rootView;
    }
}
