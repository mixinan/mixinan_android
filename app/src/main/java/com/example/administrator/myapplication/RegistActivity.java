package com.example.administrator.myapplication;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.myapplication.util.Const;
import com.example.administrator.myapplication.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistActivity extends Activity {
    private EditText et_name, et_pwd, et_pwd_final, et_nickname;
    private RadioGroup rg;
    private Button bt_regist;
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
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        et_pwd_final = (EditText) findViewById(R.id.et_pwd_final);
        et_nickname = (EditText) findViewById(R.id.et_nickname);
        rg = (RadioGroup) findViewById(R.id.rg_gender);
        bt_regist = (Button) findViewById(R.id.bt_regist);
    }

    private void regist() {
        name = et_name.getText().toString().trim();
        pwd = et_pwd.getText().toString().trim();
        pwd_final = et_pwd_final.getText().toString().trim();
        nickname = et_nickname.getText().toString().trim();

        RadioButton rb = findViewById(rg.getCheckedRadioButtonId());
        gender = rb.getText().toString().trim();

        Log.i("mixinan", "regist信息: "+name+pwd+pwd_final+nickname+gender);

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
