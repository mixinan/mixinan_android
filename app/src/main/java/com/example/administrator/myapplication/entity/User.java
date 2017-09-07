package com.example.administrator.myapplication.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/30.
 */

public class User implements Serializable {
    private int mi_user_id;
    private String mi_user_name;
    private String mi_user_password;
    private String mi_user_gender;
    private String mi_user_nickname;
    private String mi_user_create_time;

    public User() {
    }

    public User(int mi_user_id, String mi_user_name, String mi_user_password, String mi_user_gender, String mi_user_nickname, String mi_user_create_time) {
        this.mi_user_id = mi_user_id;
        this.mi_user_name = mi_user_name;
        this.mi_user_password = mi_user_password;
        this.mi_user_gender = mi_user_gender;
        this.mi_user_nickname = mi_user_nickname;
        this.mi_user_create_time = mi_user_create_time;
    }

    public int getMi_user_id() {
        return mi_user_id;
    }

    public void setMi_user_id(int mi_user_id) {
        this.mi_user_id = mi_user_id;
    }

    public String getMi_user_name() {
        return mi_user_name;
    }

    public void setMi_user_name(String mi_user_name) {
        this.mi_user_name = mi_user_name;
    }

    public String getMi_user_password() {
        return mi_user_password;
    }

    public void setMi_user_password(String mi_user_password) {
        this.mi_user_password = mi_user_password;
    }

    public String getMi_user_gender() {
        return mi_user_gender;
    }

    public void setMi_user_gender(String mi_user_gender) {
        this.mi_user_gender = mi_user_gender;
    }

    public String getMi_user_nickname() {
        return mi_user_nickname;
    }

    public void setMi_user_nickname(String mi_user_nickname) {
        this.mi_user_nickname = mi_user_nickname;
    }

    public String getMi_user_create_time() {
        return mi_user_create_time;
    }

    public void setMi_user_create_time(String mi_user_create_time) {
        this.mi_user_create_time = mi_user_create_time;
    }


    @Override
    public String toString() {
        return "User{" +
                "mi_user_id=" + mi_user_id +
                ", mi_user_name='" + mi_user_name + '\'' +
                ", mi_user_password='" + mi_user_password + '\'' +
                ", mi_user_gender='" + mi_user_gender + '\'' +
                ", mi_user_nickname='" + mi_user_nickname + '\'' +
                ", mi_user_create_time='" + mi_user_create_time + '\'' +
                '}';
    }
}
