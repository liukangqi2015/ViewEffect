package com.liu.androiddrawstudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment基类
 * Created by 刘康祺 on 2016/12/19 0019.
 */

public abstract class BaseFragment extends Fragment {
    public abstract int getLayout();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(getLayout(),container,false);
        init(rootView);
        return rootView;
    }

    protected abstract void init(View rootView);


}
