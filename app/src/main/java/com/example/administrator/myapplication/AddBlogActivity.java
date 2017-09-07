package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.myapplication.entity.User;
import com.example.administrator.myapplication.util.Const;
import com.example.administrator.myapplication.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2017/8/30.
 */

public class AddBlogActivity extends Activity implements View.OnClickListener{
    private Button bt_esc, bt_send;
    private EditText et;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_blog);

        intView();

        //从application类里获取user信息
        user = MyApp.getUser();
        Log.i("mixinan","Add blog : user == "+user);

        bt_esc.setOnClickListener(this);
        bt_send.setOnClickListener(this);


    }

    private void intView() {
        bt_esc = (Button) findViewById(R.id.bt_esc);
        bt_send = (Button) findViewById(R.id.bt_send);
        et = (EditText) findViewById(R.id.et_add_blog);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_esc:
                finish();
                break;
            case R.id.bt_send:
                sendBlog();
                break;
        }
    }

    private void sendBlog() {
        String blog = et.getText().toString();
        if (blog==""||blog.length()<1){
            ToastUtil.showToast(this,"别闹！");
            return;
        }

        //添加一条blog
        //String addBlog = BASE_URL + "/blog/addblog.do?userId=3&blogText=";

        String url = Const.addBlog + user.getMi_user_id() + "&blogText=" + blog;

        Log.i("mixinan","add blog url -- "+url);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("mixinan",response);
                try {
                    JSONObject obj = new JSONObject(response);
                    int status = obj.getInt("status");
                    if (status==0){
                        finish();
                    }
                    ToastUtil.showToast(getApplicationContext(),obj.getString("msg"));

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
}
