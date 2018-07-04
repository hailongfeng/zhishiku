package com.zsy.coordinatorlayout;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.fhl.materialdesign.R;
import com.zsy.coordinatorlayout.helper.ItemDragHelperCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    AppBarLayout mAppBarAyout;
    TextView txt1;
    Toolbar toolbar;
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //沉浸式状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //给页面设置工具栏
        mAppBarAyout = (AppBarLayout) findViewById(R.id.appbar);
        txt1 = (TextView) findViewById(R.id.txt1);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        if (collapsingToolbar != null) {
            //设置隐藏图片时候ToolBar的颜色
            collapsingToolbar.setContentScrimColor(Color.parseColor("#11B7F3"));
            //设置工具栏标题
//            collapsingToolbar.setTitle("编程是一种信仰");
            collapsingToolbar.setTitle("");
        }
        List<CellData> list = initData();
        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
            LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(manager);
            // 长按拖拽打开
            ItemDragHelperCallback callback = new ItemDragHelperCallback() {
                @Override
                public boolean isLongPressDragEnabled() {
                    return true;
                }
            };
            ItemTouchHelper helper = new ItemTouchHelper(callback);
            helper.attachToRecyclerView(mRecyclerView);
            //设置adapter
            MyAdapter mAdapter = new MyAdapter(list);
            mRecyclerView.setAdapter(mAdapter);
            //设置RecyclerView的每一项的点击事件
            mAdapter.setRecyclerViewOnItemClickListener(new MyAdapter.RecyclerViewOnItemClickListener() {
                @Override
                public void onItemClickListener(View view, int position) {
                    Snackbar.make(view, "点击了：" + position, Snackbar.LENGTH_SHORT).show();
                }
            });
            //设置RecyclerView的每一项的长按事件
            mAdapter.setOnItemLongClickListener(new MyAdapter.RecyclerViewOnItemLongClickListener() {
                @Override
                public boolean onItemLongClickListener(View view, int position) {
                    Snackbar.make(view, "长按了：" + position, Snackbar.LENGTH_SHORT).show();
                    return true;
                }
            });

        }


        /**
         * 监听滑动状态
         */
        mAppBarAyout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener()
        {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset)
            {
//              float tmp=  ( float) Math.abs(verticalOffset) / appBarLayout.getTotalScrollRange();
//                Log.d("fhl",tmp+"");
//              txt1.setAlpha(tmp);
//              toolbar.setAlpha(1-tmp);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //点击了返回箭头
                break;
            case R.id.about:
                new AlertDialog.Builder(this).setMessage("作者:阿钟\ncsdn地址:http://blog.csdn.net/a_zhon/").show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<CellData> initData() {
        List<CellData> list = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            CellData cellData = new CellData("阿钟程序员 +" + i, "不想当项目经理的程序员不是个好程序员" + i);
            list.add(cellData);
        }
        return list;
    }

}
