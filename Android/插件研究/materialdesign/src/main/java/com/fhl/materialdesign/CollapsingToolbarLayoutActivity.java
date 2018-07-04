package com.fhl.materialdesign;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;

public class CollapsingToolbarLayoutActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.main);
        CollapsingToolbarLayout ctlTitle = (CollapsingToolbarLayout) findViewById(R.id.ctl_title);
        toolbar.setTitle("");
//        setSupportActionBar(toolbar);//设置toolbar
//        ctlTitle.setCollapsedTitleGravity(Gravity.CENTER);//设置收缩后标题的位置
//        ctlTitle.setExpandedTitleGravity(Gravity.CENTER);////设置展开后标题的位置
//        ctlTitle.setTitle("你好程序猿");//设置标题的名字
//        ctlTitle.setExpandedTitleColor(Color.WHITE);//设置展开后标题的颜色
//        ctlTitle.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后标题的颜色
    }
}
