package com.example.administrator.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.myapplication.module.UserModule;
import com.example.administrator.myapplication.util.Const;
import com.example.administrator.myapplication.util.ToastUtil;
import com.example.administrator.myapplication.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistActivity extends Activity {
    private EditText et_name, et_pwd, et_pwd_final, et_nickname;
    private TextView tv_name_log;
    private RadioGroup rg;
    private Button bt_regist;
    private Button bt_back;
    private String name;
    private String pwd;
    private String pwd_final;
    private String nickname;
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        initView();
        bt_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regist();
            }
        });

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        tv_name_log = (TextView) findViewById(R.id.tv_name_log);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        et_pwd_final = (EditText) findViewById(R.id.et_pwd_final);
        et_nickname = (EditText) findViewById(R.id.et_nickname);
        rg = (RadioGroup) findViewById(R.id.rg_gender);
        bt_regist = (Button) findViewById(R.id.bt_regist);
        bt_back = (Button) findViewById(R.id.bt_back);
    }

    private void regist() {
        name = et_name.getText().toString().trim();
        pwd = et_pwd.getText().toString().trim();
        pwd_final = et_pwd_final.getText().toString().trim();
        nickname = et_nickname.getText().toString().trim();

        RadioButton rb = findViewById(rg.getCheckedRadioButtonId());
        gender = rb.getText().toString().trim();

        Log.i("mixinan", "regist信息: "+name+pwd+pwd_final+nickname+gender);


        //name输入框，焦点改变的监听
        et_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus){
                    if (name.equals("")){
                        tv_name_log.setText("不能为空");
                        tv_name_log.setTextColor(Color.RED);
                    }else{
                        //长度小于4位
                        if (name.length()<4){
                            tv_name_log.setText("长度不能小于4位");
                            tv_name_log.setTextColor(Color.RED);
                            return;
                        }else {
                            UserModule.checkUsernameUsed(name, new UserModule.VolleyCallBack() {
                                @Override
                                public void onSuccess(int status) {
                                    if (status == 1) {
                                        tv_name_log.setText("名字已被占用，换一个吧");
                                        tv_name_log.setTextColor(Color.RED);
                                    }
                                }
                            });
                        }
                    }
                }else{
                    //ToastUtil.showToast(RegistActivity.this,"name得到焦点");
                }
            }
        });


        if (name.equals("") || pwd=="" || nickname.equals("")){
            ToastUtil.showToast(this,"不能为空");
            return;
        }

        if(!pwd.equals(pwd_final)){
            ToastUtil.showToast(this,"密码不一致");
            return;
        }




        //注册用户
        //public static String registUser = BASE_URL + "/user/regist.do?name=125&password=125&gender=1";

        String url = Const.registUser;

        url += name + "&password=" + pwd + "&gender=" + gender + "&nickname=" + nickname;
        System.out.println("mixinan  url---" + url);
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        finish();
                    }
                    ToastUtil.showToast(RegistActivity.this, obj.getString("msg"));
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
