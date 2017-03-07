package com.example.an.itemdecorationdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * @description:
 * @author: WDSG
 * @date: 2016/4/7
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView tv;

    public ViewHolder(View view) {
        super(view);
        tv = (TextView) view.findViewById(R.id.id_num);
    }

}