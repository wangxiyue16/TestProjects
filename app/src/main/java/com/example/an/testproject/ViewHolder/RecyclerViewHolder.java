package com.example.an.testproject.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.example.an.testproject.R;

import java.util.List;

/**
 * @description:
 * @author: WDSG
 * @date: 2016/3/29
 */
public class RecyclerViewHolder extends BaseViewHolder {
    public TextView tv;
    private List<String> mDatas;

    public RecyclerViewHolder(View view,  List<String> datas) {
        super(view);
        mDatas = datas;
        tv = (TextView) view.findViewById(R.id.id_num);
    }

    @Override
    public void setDataOnView(int position) {
        this.tv.setText(mDatas.get(position));
    }
}
