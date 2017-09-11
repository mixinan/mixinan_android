package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.entity.Pic;
import com.example.administrator.myapplication.util.Util;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */

public class PicAdapter extends BaseAdapter{
    private List<Pic> data;
    private Context context;
    private LayoutInflater inflater;


    public PicAdapter(List<Pic> data, Context context) {
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data==null? 0:data.size();
    }

    @Override
    public Pic getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            view = inflater.inflate(R.layout.item_pic,null);
            holder = new ViewHolder();
            holder.iv_pic = (ImageView) view.findViewById(R.id.iv_item_pic);
            holder.tv_name = (TextView) view.findViewById(R.id.tv_item_pic_name);
            holder.tv_desc = (TextView) view.findViewById(R.id.tv_item_pic_desc);
            holder.tv_time = (TextView) view.findViewById(R.id.tv_item_pic_time);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();

            Pic pic = getItem(i);
            Glide.with(context).load(pic.getMi_pic_url()).into(holder.iv_pic);
            holder.tv_name.setText(pic.getMi_pic_name());
            holder.tv_desc.setText(pic.getMi_pic_desc());
            holder.tv_time.setText(pic.getMi_pic_create_time());
        }


        return view;
    }


    class ViewHolder{
        ImageView iv_pic;
        TextView tv_name, tv_desc, tv_time;
    }
}
