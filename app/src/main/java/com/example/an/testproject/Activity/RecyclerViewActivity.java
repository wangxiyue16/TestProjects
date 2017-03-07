package com.example.an.testproject.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.an.testproject.Adapter.HomeAdapter;
import com.example.an.testproject.ItemDecoration.LinearLayoutItemDecoration;
import com.example.an.testproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView
 */

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;
//    private StaggeredGridAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recyclerview);

        initData();

/*************************************************************/
//        mRecyclerView = findView(R.id.id_recyclerview);
//        mRecyclerView.setAdapter(adapter)  // 设置adapter

        //下面俩方法是配套使用的
//        mRecyclerView.setLayoutManager(layout);  //设置布局管理器
//        mRecyclerView.addItemDecoration(new LinearLayoutItemDecoration(getActivity(), LinearLayoutItemDecoration.HORIZONTAL_LIST)); //添加分割线

//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());  // 设置Item增加、移除动画

//        你想要控制其显示的方式，请通过布局管理器LayoutManager
//        你想要控制Item间的间隔（可绘制），请通过ItemDecoration
//        你想要控制Item增删的动画，请通过ItemAnimator
//        你想要控制点击、长按事件，请自己写
/*************************************************************/

        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter(this, mDatas));
//        mRecyclerView.setAdapter(mAdapter = new StaggeredGridAdapter(this, mDatas));

        //(1) 画横竖线
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); //线性管理器，支持横向、纵向
        mRecyclerView.addItemDecoration(new LinearLayoutItemDecoration(this, LinearLayoutItemDecoration.VERTICAL_LIST));

        //(2) 画Grid样式
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4)); //网格布局管理器,第二个参数用于设置网格中的列数(即每行数量)
//        mRecyclerView.addItemDecoration(new GridLayoutItemDecoration(this));

//        //(3) 画瀑布流样式
        //第一个参数用于设置网格中的列数或者行数;第二个参数传一个orientation，如果传入的是StaggeredGridLayoutManager.VERTICAL代表有多少列；
            // 那么传入的如果是StaggeredGridLayoutManager.HORIZONTAL就代表有多少行
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)); //瀑布流式布局管理器
//        mRecyclerView.addItemDecoration(new StaggeredGridItemDecoration(this));

        //添加动画效果
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i <= 'Z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

}
