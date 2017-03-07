package com.example.an.testproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.example.an.testproject.R;
import com.example.an.testproject.ViewHolder.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: WDSG
 * @date: 2016/3/29
 */
public class StaggeredGridAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<String> mDatas;
    private LayoutInflater mInflater;
    private List<Integer> mHeights;

    public StaggeredGridAdapter(Context context, List<String> datas) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;

        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < mDatas.size(); i++) {
            mHeights.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolder holder = new RecyclerViewHolder(mInflater.inflate(
                R.layout.recycleritem, parent, false), mDatas);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        LayoutParams lp = holder.tv.getLayoutParams();
        lp.height = mHeights.get(position);

        holder.tv.setLayoutParams(lp);
        holder.tv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

}
