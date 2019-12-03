package com.example.digitalevidence.fragments;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitalevidence.R;
import com.example.digitalevidence.activities.BaseActivity;
import com.example.digitalevidence.adapters.DetailedFragmentAdapter;
import com.example.digitalevidence.helpers.EndlessRecyclerViewScrollListener;
import com.example.digitalevidence.models.Model;

import java.util.ArrayList;
import java.util.List;

@TargetApi(23)
public class DeviceListFragment extends Fragment {
    private BaseActivity activity;
    private final int COLS = 1;

    public DeviceListFragment() {
        // Required empty public constructor
    }
    public static DeviceListFragment newInstance() {
        return new DeviceListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_devicelist, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.RecyclerView);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), COLS);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        activity = (BaseActivity) getActivity();

        List<Pair<String, List<Model>>> pairList = new ArrayList<>();
        DetailedFragmentAdapter detailedFragmentAdapter = new DetailedFragmentAdapter(pairList);
        recyclerView.setAdapter(detailedFragmentAdapter);

        activity.setModels(pairList);

        activity.loadAndSet(2);
        EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener =  new EndlessRecyclerViewScrollListener(layoutManager){
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                activity.loadAndSet(2);
            }
        };

        recyclerView.addOnScrollListener(endlessRecyclerViewScrollListener);

        layoutManager.setMeasuredDimension(Integer.MAX_VALUE, Integer.MAX_VALUE);

        return root;
    }
}