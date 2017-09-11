package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.entity.Pic;
import com.example.administrator.myapplication.util.Const;

/**
 * Created by Administrator on 2017/9/11.
 */

public class PicDetailActivity extends Activity{
    ImageView iv_pic;
    TextView tv_pic_desc;
    int from;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_detail);

        Intent intent = getIntent();
        Pic pic = (Pic) intent.getSerializableExtra("clickPic");
        from = intent.getIntExtra("from",-1);

        iv_pic = (ImageView)findViewById(R.id.iv_pic_detail);
        tv_pic_desc = (TextView)findViewById(R.id.tv_pic_detail_desc);
        tv_pic_desc.setText(pic.getMi_pic_desc());
        Glide.with(this).load(pic.getMi_pic_url()).into(iv_pic);
    }


    /**
     * 监听Back键按下事件
     * 如果是从welcome页跳转而来，则返回首页
     * 否则，直接退出该页
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (from == Const.PIC_welcome_tiao_zhuan){
                startActivity(new Intent(this, MainActivity.class));
            }
            finish();
            return true;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }

}
