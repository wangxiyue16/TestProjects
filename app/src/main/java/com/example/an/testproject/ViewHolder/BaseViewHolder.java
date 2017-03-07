package com.example.an.testproject.ViewHolder;


import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

public abstract class BaseViewHolder extends ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract  void setDataOnView(int position);

}
