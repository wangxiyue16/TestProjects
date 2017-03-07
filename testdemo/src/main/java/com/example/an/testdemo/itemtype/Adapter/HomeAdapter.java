package com.example.an.testdemo.itemtype.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.an.testdemo.R;
import com.example.an.testdemo.itemtype.viewHolder.BaseViewHolder;
import com.example.an.testdemo.itemtype.viewHolder.RecycleViewHolderthree;
import com.example.an.testdemo.itemtype.viewHolder.RecycleViewHoldertwo;
import com.example.an.testdemo.itemtype.viewHolder.RecyclerViewHolder;

import java.util.List;

/**
 * @description:
 * @author: WDSG
 * @date: 2016/3/29
 */
public class HomeAdapter extends RecyclerView.Adapter<BaseViewHolder>  {
    private List<String> mDatas;
    private LayoutInflater mInflater;

    public HomeAdapter(Context context, List<String> datas)
    {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 1;
        if (position == 0) viewType = 2;
        if (position == 3) viewType = 3;
        return viewType;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 2){
            RecycleViewHoldertwo holdertwo = new RecycleViewHoldertwo(mInflater.inflate(R.layout.recycleitemtwo,parent,false), mDatas);
            return holdertwo;
        } else if(viewType == 3) {
            RecycleViewHolderthree holder = new RecycleViewHolderthree(mInflater.inflate(
                    R.layout.recycleitemthree, parent, false), mDatas);
            return holder;
        } else {
            RecyclerViewHolder holder = new RecyclerViewHolder(mInflater.inflate(
                    R.layout.recycleritem, parent, false), mDatas);
            return holder;
        }

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setDataOnView(position);
//        holder.tv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


}
