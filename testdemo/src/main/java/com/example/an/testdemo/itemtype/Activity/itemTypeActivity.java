package com.example.an.testdemo.itemtype.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.an.testdemo.R;
import com.example.an.testdemo.itemtype.Adapter.HomeAdapter;
import com.example.an.testdemo.itemtype.itemDecoration.LinearLayoutItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: item展示不同样式数据
 * @author: WDSG
 * @date: 2016/4/13
 */
public class itemTypeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recyclerview);

        initData();

        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter(this, mDatas));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new LinearLayoutItemDecoration(this, LinearLayoutItemDecoration.VERTICAL_LIST));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i <= 'Z'; i++) {
            mDatas.add("" + (char) i);
        }
    }
}
