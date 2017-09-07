package com.example.administrator.myapplication.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.myapplication.LoginActivity;
import com.example.administrator.myapplication.MyApp;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.entity.User;
import com.example.administrator.myapplication.util.Const;
import com.example.administrator.myapplication.util.ToastUtil;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/9/2.
 */

public class MeFragment extends Fragment implements View.OnClickListener{
    private String TAG = "mixinan";
    private User user;
    private TextView tv_nick;
    private TextView tv_name;
    private Button bt_exit;
    private String nickname;
    private String name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me,container,false);

        initView(view);
        setListener();
        return view;
    }

    private void setListener() {
        bt_exit.setOnClickListener(this);
    }

    private void initView(View view) {
        tv_nick = (TextView) view.findViewById(R.id.tv_nickname);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        bt_exit = (Button)view.findViewById(R.id.bt_exit);

        user = getUser();
        //Log.i(TAG, "fragmentMe user -- "+user);

        //把user信息放在全局application类里
        MyApp.setUser(user);

        nickname = user.getMi_user_nickname();
        name = user.getMi_user_name();

        tv_nick.setText(nickname);
        tv_name.setText(name);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_exit:
                ToastUtil.showToast(getActivity(),"退出");
                //返回登录页，并清除 偏好设置
                exit();
                break;
        }
    }

    /**
     *点击“退出”按钮， 返回登录页，并清除 偏好设置
     */
    private void exit() {
        String name = "user-info";
        int mode = MODE_PRIVATE;
        SharedPreferences sp = getActivity().getSharedPreferences(name,mode);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }



    /**
     * 从偏好设置得到用户数据
     */
    public User getUser(){
        SharedPreferences sp = getActivity().getSharedPreferences(Const.LOGIN_USER_FILE_NAME, MODE_PRIVATE);
        User user = new User();
        int userId = sp.getInt("_userId",0);
        String name = sp.getString("_userName","9527");
        String password = sp.getString("_userPassword",null);
        String nickname = sp.getString("_userNickname","游客");
        String gender = sp.getString("_userGender","1");
        String createTime = sp.getString("_userCreateTime","1997-08-08");
        user.setMi_user_id(userId);
        user.setMi_user_name(name);
        user.setMi_user_password(password);
        user.setMi_user_nickname(nickname);
        user.setMi_user_create_time(createTime);
        user.setMi_user_gender(gender);

        return user;
    }
}
