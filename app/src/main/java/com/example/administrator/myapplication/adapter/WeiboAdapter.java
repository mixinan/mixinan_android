package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.myapplication.MyApp;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.entity.Blog;
import com.example.administrator.myapplication.entity.User;
import com.example.administrator.myapplication.entity.WeiboData;
import com.example.administrator.myapplication.util.Const;
import com.example.administrator.myapplication.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by Administrator on 2017/9/3.
 */

public class WeiboAdapter extends BaseAdapter{
    private List<WeiboData> data;
    private Context context;
    private LayoutInflater inflater;

    public WeiboAdapter(List<WeiboData> data, Context context) {
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return data==null? 0:data.size();
    }

    @Override
    public WeiboData getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_blog,null);
            holder.tv_author = (TextView) view.findViewById(R.id.tv_author);
            holder.tv_blog = (TextView) view.findViewById(R.id.tv_blog);
            holder.tv_time = (TextView) view.findViewById(R.id.tv_time);
            holder.tv_like = (TextView) view.findViewById(R.id.tv_like);
            holder.like_layout = view.findViewById(R.id.like_layout);
            holder.share_layout = view.findViewById(R.id.bt_blog_share_layout);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        final WeiboData weibo = data.get(i);
        final Blog blog = weibo.getBlog();
        final User user = weibo.getUser();

        final String nickname = user.getMi_user_nickname();
        final String blogText = blog.getMi_blog_text();
        String createTime = blog.getMi_blog_create_time();
        final int likeTime = blog.getMi_blog_like_times();

        holder.tv_author.setText(Util.UTF8Decoder(nickname));
        holder.tv_blog.setText(Util.UTF8Decoder(blogText));
        holder.tv_time.setText(createTime);
        holder.tv_like.setText(likeTime==0 ? "" : likeTime+"");






        //点赞
        final ViewHolder finalHolder = holder;
        holder.like_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = Const.LIKE + blog.getMi_blog_id();
                StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            int likeTimes = obj.getInt("data");
                            finalHolder.tv_like.setText(likeTimes+"");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                MyApp.getRequestQueue().add(req);
            }
        });




        //分享
        holder.share_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.shareBlog(context, Util.UTF8Decoder(blogText+"\n-- "+nickname),"分享微博到:");
            }
        });


        return view;
    }


    class ViewHolder{
        TextView tv_author, tv_blog, tv_time, tv_like;
        View like_layout, share_layout;
    }


    public void removeData(WeiboData blog) {
        data.remove(blog);
        notifyDataSetChanged();
    }

}
