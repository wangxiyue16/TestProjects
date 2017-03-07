package com.example.an.testproject.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.example.an.testproject.R;

import java.util.List;

/**
 * @description:
 * @author: WDSG
 * @date: 2016/4/6
 */
public class RecycleViewHolderthree extends BaseViewHolder {
    public TextView tv1,tv2,tv3;
    private List<String> mDatas;

    public RecycleViewHolderthree(View view, List<String> datas) {
        super(view);
        mDatas = datas;
        tv1 = (TextView) view.findViewById(R.id.id_num3);
        tv2 = (TextView) view.findViewById(R.id.id_num4);
        tv3 = (TextView) view.findViewById(R.id.id_num5);
    }

    @Override
    public void setDataOnView(int position) {
        this.tv1.setText(mDatas.get(position));
        this.tv2.setText("hello");
        this.tv3.setText("world");
    }
}
