package com.liu.pulltorefreshdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * ListView的Adapter
 * Created by liu on 2016/11/23.
 */
public class ListAdapter extends BaseAdapter{
    private List<String> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;

    public ListAdapter(List<String> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        mInflater=LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView=mInflater.inflate(R.layout.list_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.textView= (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(mDatas.get(position));
        return convertView;
    }

    static class ViewHolder{
        private TextView textView;
    }
}
