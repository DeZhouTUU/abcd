package com.dezhoutuu.mytuu.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;

import com.dezhoutuu.mytuu.Application.BaseActivity;
import com.dezhoutuu.mytuu.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fatJiang on 2018/4/26.
 */

public class ProductListActivity extends BaseActivity {
    @BindView(R.id.rv_product)
    RecyclerView rvProduct;
    @BindView(R.id.sr_product)
    SmartRefreshLayout srProduct;


    public static void goProductListActivity(Context context){
        Intent mIntent = new Intent(context,ProductListActivity.class);
        context.startActivity(mIntent);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_product_list;
    }

    @Override
    public void initView() {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
