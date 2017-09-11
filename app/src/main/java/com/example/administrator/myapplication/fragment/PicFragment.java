package com.example.administrator.myapplication.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.PicAdapter;
import com.example.administrator.myapplication.entity.Pic;
import com.example.administrator.myapplication.module.Callback;
import com.example.administrator.myapplication.module.PicModule;
import com.example.administrator.myapplication.util.Const;
import com.example.administrator.myapplication.util.Util;

import java.util.ArrayList;
import java.util.List;

public class PicFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView lv;
    private List<Pic> data;
    private PicAdapter adapter;
    private SwipeRefreshLayout refreshLayout;

    public PicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pic, container, false);
        data = new ArrayList<Pic>();
        initView(view);
        initData();

        lv.setOnItemClickListener(this);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });

        return view;
    }

    private void initData() {
        refreshLayout.setRefreshing(true);
        PicModule.getPics(new Callback() {
            @Override
            public void onSuccess(Object obj) {
                data.clear();
                data.addAll((List<Pic>) obj);
                show();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    private void show() {
        if (adapter==null){
            adapter = new PicAdapter(data, getActivity());
            lv.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }
    }



    private void initView(View view) {
        lv = (ListView) view.findViewById(R.id.lv_pic);
        refreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_pic_layout);
        refreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Pic clickPic = data.get(i);
        Util.showPicInActivity(getActivity(), clickPic, Const.PIC_listview_tiao_zhuan);
    }



}
