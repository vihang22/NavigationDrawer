package com.ckeeda.navigationdrawer.Activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import com.ckeeda.navigationdrawer.FlowerAdapter;
import com.ckeeda.navigationdrawer.FlowerPresenter;
import com.ckeeda.navigationdrawer.Model.Flower;
import com.ckeeda.navigationdrawer.R;

import java.util.ArrayList;
import java.util.List;

public class FlowerListActivity extends AppCompatActivity implements FlowerPresenter.FlowerCallbackListener {

    RecyclerView rv;
    SwipeRefreshLayout swipelayout;


    FlowerPresenter mFlowerPresenter;
    FlowerAdapter adapter;
    List<Flower> flowerlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower_list);
        setTitle(getIntent().getStringExtra("Title"));
        //getActionBar().setTitle(getIntent().getStringExtra("title"));
        mFlowerPresenter = new FlowerPresenter(this);
        configureUI();
        mFlowerPresenter.fetch();
    }

    void configureUI(){

        rv = (RecyclerView) findViewById(R.id.recyclerview);
        swipelayout = (SwipeRefreshLayout) findViewById(R.id.swipe);

        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FlowerAdapter(this,flowerlist);
        rv.setAdapter(adapter);
        swipelayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mFlowerPresenter.fetch();
            }
        });

    }

    @Override
    public void onFetchStart() {
          swipelayout.setRefreshing(true);

        }

    }

    @Override
    public void onFetchProgress(List<Flower> flowerlist) {
         if(swipelayout.isRefreshing())
           swipelayout.setRefreshing(false);

         this.flowerlist.clear();
         this.flowerlist.addAll(flowerlist);
         Log.v("FlowerListActivity", String.valueOf(this.flowerlist.size()));
         adapter.notifyDataSetChanged();


    }

    @Override
    public void OnFetchComplete() {
        if(swipelayout.isRefreshing())
           swipelayout.setRefreshing(false);

    }
}
