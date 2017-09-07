package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.myapplication.entity.User;
import com.example.administrator.myapplication.util.Const;
import com.example.administrator.myapplication.util.ToastUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends Activity {
    private EditText et_name, et_pwd;
    private Button bt_login;
    private Button bt_regist;
    private String name;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        bt_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegistActivity.class));
            }
        });
    }

    private void login() {
        String url = Const.checkUser;
        name = et_name.getText().toString();
        pwd = et_pwd.getText().toString();
        //public static String checkUser = BASE_URL + "/user/login.do?name=125&password=125";
        url += name + "&password=" + pwd;
        System.out.println("url---" + url);
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        User user = new Gson().fromJson(obj.getString("data"),User.class);
                        Log.i("mixinan","login user --- "+user.toString());

                        //在偏好设置里，保存user信息
                        saveCookie(user);

                        ToastUtil.showToast(LoginActivity.this, obj.getString("msg"));
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                    ToastUtil.showToast(LoginActivity.this, obj.getString("msg"));
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

    /**
     * 偏好设置，保存登录的用户信息
     */
    private void saveCookie(User user) {
        String name = "user-info";
        int mode = MODE_PRIVATE;
        SharedPreferences sp = getSharedPreferences(name,mode);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("_userId",user.getMi_user_id());
        editor.putString("_userName",user.getMi_user_name());
        editor.putString("_userPassword",user.getMi_user_password());
        editor.putString("_userNickname",user.getMi_user_nickname());
        editor.putString("_userGender",user.getMi_user_gender());
        editor.putString("_userCreateTime",user.getMi_user_create_time());
        editor.commit();
    }


    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_regist = (Button) findViewById(R.id.bt_regist);
    }
}
