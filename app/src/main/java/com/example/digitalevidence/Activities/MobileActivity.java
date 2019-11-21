package com.example.digitalevidence.Activities;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.digitalevidence.Adapters.TabsAdapter;
import com.example.digitalevidence.Helpers.DynamoHelper;
import com.example.digitalevidence.Adapters.DetailedFragmentAdapter;
import com.example.digitalevidence.Models.MODEL_TYPE;
import com.example.digitalevidence.Models.MobileDO;
import com.example.digitalevidence.Models.Model;
import com.example.digitalevidence.R;
import com.google.android.material.tabs.TabLayout;

import java.util.List;
import java.util.Queue;

public class MobileActivity extends BaseActivity {

    private DynamoHelper dynamoHelper;
    private List<Model> models;
    private DetailedFragmentAdapter detailedFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TabsAdapter tabsPagerAdapter = new TabsAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(tabsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabLayout);
        tabs.setupWithViewPager(viewPager);
        this.dynamoHelper = new DynamoHelper(this, MODEL_TYPE.MOBILE, MobileDO.TABLE_NAME);
    }

    public void loadAndSet(int item_to_load){
        Thread getAll = dynamoHelper.getNItems(item_to_load);
        Thread doAll =  addDataToList();
        try {
            getAll.start();
            getAll.join();
            doAll.start();
            doAll.join();
        } catch (Exception e) {
            e.getLocalizedMessage();
        }

    }


    private Thread addDataToList(){
        return new Thread(new Runnable() {
            @Override
            public void run() {
                Queue<Model> pending = dynamoHelper.getModelsPending();
                while(pending.size() > 0) {
                    models.add(pending.poll());
                }
            }

        });
    }


    public void setModels(List<Model> models){
        this.models = models;
    }
}