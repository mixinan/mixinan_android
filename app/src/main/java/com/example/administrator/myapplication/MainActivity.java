package com.example.administrator.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.fragment.BlogFragment;
import com.example.administrator.myapplication.fragment.MeFragment;
import com.example.administrator.myapplication.fragment.PicFragment;
import com.example.administrator.myapplication.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private ViewPager vp;
    private RadioGroup rg;
    private List<Fragment> fs = new ArrayList<Fragment>();
    private Button btAdd;
    private Button btSearch;
    private TextView tvTitle;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        btAdd = (Button)findViewById(R.id.bt_add_blog);
        btSearch = (Button)findViewById(R.id.bt_search);
        tvTitle = (TextView)findViewById(R.id.tv_title);
        vp = (ViewPager) findViewById(R.id.vp);
        rg = (RadioGroup) findViewById(R.id.rg);
        fs.add(new BlogFragment());
        fs.add(new PicFragment());
        fs.add(new MeFragment());

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddBlogActivity.class));
            }
        });

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到搜索页面
                //startActivity(new Intent(MainActivity.this,SearchActivity.class));

                ToastUtil.showToast(context,"coming soon...");

            }
        });

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fs.get(position);
            }

            @Override
            public int getCount() {
                return fs.size();
            }
        };

        vp.setOffscreenPageLimit(3);
        vp.setAdapter(adapter);


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rb_blog:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.rb_radio:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.rb_me:
                        vp.setCurrentItem(2);
                        break;
                }
            }
        });


        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Log.i("mixinan","positionOffset---"+positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton)rg.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    /**
     * 按两次返回键，退出
     */
    private long lastTime = 0;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - lastTime > 2000) {
                    ToastUtil.showToast(MainActivity.this,"再按一次退出");
                    lastTime = secondTime;
                    return true;
                } else {
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }




}
