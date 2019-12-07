package com.example.digitalevidence.activities;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.example.digitalevidence.adapters.TabsAdapter;
import com.example.digitalevidence.helpers.DynamoHelper;
import com.example.digitalevidence.adapters.DetailedFragmentAdapter;
import com.example.digitalevidence.models.MODEL_TYPE;
import com.example.digitalevidence.models.MobileDO;
import com.example.digitalevidence.models.Model;
import com.example.digitalevidence.R;
import com.google.android.material.tabs.TabLayout;

import java.util.List;
import java.util.Queue;

public class MobileActivity extends BaseActivity {
    private DynamoHelper dynamoHelper;
    private List<Model> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);

        // Toolbar
        TextView textView = findViewById(R.id.toolbar_title);
        textView.setText(R.string.title_mobile);

        // Tabs
        TabsAdapter tabsPagerAdapter = new TabsAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(tabsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabLayout);
        tabs.setupWithViewPager(viewPager);

        // Utilize Items Labeled Mobile from DynamoDB
        this.dynamoHelper = new DynamoHelper(this, MODEL_TYPE.MOBILE, MobileDO.TABLE_NAME);
    }

    public void loadAndSet(int item_to_load){
        Thread getAll = dynamoHelper.getNItems(item_to_load);
        Thread doAll = addDataToList();
        try {
            getAll.start();
            getAll.join();
            doAll.start();
            doAll.join();
        }
        catch (Exception e) {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch(item.getItemId()) {
            case R.id.profile:
                i = new Intent(this, ProfileActivity.class);
                startActivity(i);
                return(true);
            case R.id.help:
                i = new Intent(this, HelpMobileActivity.class);
                startActivity(i);
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }


    public void setModels(List<Model> models){
        this.models = models;
    }
}