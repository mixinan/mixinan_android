package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.entity.Blog;
import com.example.administrator.myapplication.entity.Pic;
import com.example.administrator.myapplication.util.Const;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.administrator.myapplication.R.anim.animation_text;

/**
 * Created by Administrator on 2017/8/30.
 */

public class WelActivity extends Activity implements View.OnClickListener {
    private TextView tv;
    private ImageView iv;
    private Animation anim;
    private int count = 4;
    private String picUrl;
    private String userName;

    private Handler handler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                tv.setBackground(getResources().getDrawable(R.drawable.border, null));
                tv.setText("跳过 " + getCount());
                handler.sendEmptyMessageDelayed(0, 1000);
                anim.reset();

                tv.startAnimation(anim);
            }
        }
    };

    private int getCount() {
        count--;
        if (count == 0) {
            goOtherActivity();
        }
        return count;
    }

    private void goOtherActivity() {
        if (userName == null) {
            goLoginActivity();
        } else {
            goMainActivity();
        }
        finish();
    }

    private void goMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void goLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel);

        tv = (TextView) findViewById(R.id.tv_time);
        iv = (ImageView) findViewById(R.id.iv_wel);
        anim = AnimationUtils.loadAnimation(this, animation_text);
        picUrl = Const.getLastPic;


        //判断有没有登录信息
        int mode = MODE_PRIVATE;
        SharedPreferences sp = getSharedPreferences("user-info", mode);
        userName = sp.getString("_userName", null);

        getPicture();

        tv.setOnClickListener(this);
    }

    private void getPicture() {
        StringRequest request = new StringRequest(Request.Method.GET, picUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Pic p = new Pic();
                Gson gson = new Gson();
                try {
                    JSONObject obj = new JSONObject(response);
                    String picJson = obj.getString("data");
                    Log.i("mixinan", picJson);
                    p = gson.fromJson(picJson, Pic.class);
                    //使用Glide，加载网络图片（通过pic地址）
                    Glide.with(WelActivity.this)
                            .load(p.getMi_pic_url())
                            .into(iv);

                    //图片渐变出现
                    AlphaAnimation alpha = new AlphaAnimation(0.1f,1.0f);
                    alpha.setDuration(1200);
                    iv.startAnimation(alpha);

                    //开始倒计时
                    handler.sendEmptyMessageDelayed(0, 1000);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MyApp.getRequestQueue().add(request);
    }

    @Override
    public void onClick(View view) {
        goOtherActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
