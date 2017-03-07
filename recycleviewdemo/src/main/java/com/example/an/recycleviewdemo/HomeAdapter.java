package com.example.an.recycleviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private List<String> mDatas;
    private LayoutInflater mInflater;
    private int i=0;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public HomeAdapter(Context context, List<String> datas) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                R.layout.item_home, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setText(mDatas.get(position));

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    removeData(pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addData(int position) {
        mDatas.add(position, "我复活了" + (i++));
        notifyItemInserted(position); //有动画效果
//        notifyDataSetChanged(); //没有动画效果
//        notifyItemChanged(position); //没有动画效果,数据是插入了,但是因为只刷新当前position位置,所以插入多条只能看到最新添加的数据
//        notifyItemRangeChanged(position,mDatas.size()); //没有动画效果,可以刷新从positionStart开始itemCount数量的item数据（这里的刷新指回调onBindViewHolder()方法）,只能看到position位置往后的数据闪一下
//    notifyItemRangeInserted(position,2); //有动画,插入的数据不只一条,而是算是position位置应该插入的这条数据加上position位置往后一共itemCount(第二个参数个数)条数据的数据量
    }


    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position); //有动画效果
//        notifyDataSetChanged();
//        notifyItemRangeRemoved(position, 2);//有动画,删除的数据不只一条,而是算是position位置应该删除的这条数据加上position位置往后一共itemCount(第二个参数个数)条数据的数据量
    }

    class MyViewHolder extends ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
        }
    }
}