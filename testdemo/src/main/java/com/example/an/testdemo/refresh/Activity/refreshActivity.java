package com.example.an.testdemo.refresh.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.an.testdemo.R;
import com.example.an.testdemo.refresh.Adapter.refreshAdapter;
import com.example.an.testdemo.refresh.Adapter.refreshAdapter.OnItemClickLitener;
import com.example.an.testdemo.refresh.itemDecoration.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 刷新数据
 * @author: WDSG
 * @date: 2016/4/13
 */
public class refreshActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private refreshAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recyclerview);

        initData();

        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mAdapter = new refreshAdapter(this, mDatas);

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        initEvent();
    }

    //自定义标题栏
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //标题栏的点击
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_action_add:
                mAdapter.addData(1);
                break;
            case R.id.id_action_delete:
                mAdapter.removeData(1);
                break;
            case R.id.id_action_gridview:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4)); //增删的数据不再当前位置时动画仍旧存在
                break;
            case R.id.id_action_listview:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); //增删的数据只有在当前位置时动画存在,不再当前位置只有增删数据,位置调转到增删的指定位置时仍旧没有动画
                break;
            case R.id.id_action_horizontalGridView:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                        StaggeredGridLayoutManager.VERTICAL));  ////增删的数据只有在当前位置时动画存在,不再当前位置只有增删数据,位置调转到增删的指定位置时恢复动画
                break;
        }
        return true;
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    private void initEvent() {
        mAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(refreshActivity.this, "我在第" + position + "个位置," + "我被点中了,快来救我!",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(refreshActivity.this, "我在第" + position + "个位置," + "我要被杀死了,快来救我!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
