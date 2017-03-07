package com.example.an.testdemo.itemtype.viewHolder;


import android.view.View;
import android.widget.TextView;

import com.example.an.testdemo.R;

import java.util.List;

/**
 * @description:
 * @author: WDSG
 * @date: 2016/4/6
 */
public class RecycleViewHoldertwo extends BaseViewHolder {
    public TextView tv1,tv2;
    private List<String> mDatas;

    public RecycleViewHoldertwo(View view, List<String> datas) {
        super(view);
        mDatas = datas;
        tv1 = (TextView) view.findViewById(R.id.id_num1);
        tv2 = (TextView) view.findViewById(R.id.id_num2);
    }

    @Override
    public void setDataOnView(int position) {
        this.tv1.setText(mDatas.get(position));
        this.tv2.setText("我是大帅哥");
    }

}
