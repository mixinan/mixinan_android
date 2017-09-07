package com.example.administrator.myapplication.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/27.
 */

public class Blog implements Serializable {
    private int mi_blog_id;
    private int mi_user_id;
    private String mi_blog_text;
    private int mi_blog_like_times;
    private String mi_blog_create_time;
    private String mi_blog_last_modify_time;

    public int getMi_blog_id() {
        return mi_blog_id;
    }

    public void setMi_blog_id(int mi_blog_id) {
        this.mi_blog_id = mi_blog_id;
    }

    public int getMi_user_id() {
        return mi_user_id;
    }

    public void setMi_user_id(int mi_user_id) {
        this.mi_user_id = mi_user_id;
    }

    public String getMi_blog_text() {
        return mi_blog_text;
    }

    public void setMi_blog_text(String mi_blog_text) {
        this.mi_blog_text = mi_blog_text;
    }

    public int getMi_blog_like_times() {
        return mi_blog_like_times;
    }

    public void setMi_blog_like_times(int mi_blog_like_times) {
        this.mi_blog_like_times = mi_blog_like_times;
    }

    public String getMi_blog_create_time() {
        return mi_blog_create_time;
    }

    public void setMi_blog_create_time(String mi_blog_create_time) {
        this.mi_blog_create_time = mi_blog_create_time;
    }

    public String getMi_blog_last_modify_time() {
        return mi_blog_last_modify_time;
    }

    public void setMi_blog_last_modify_time(String mi_blog_last_modify_time) {
        this.mi_blog_last_modify_time = mi_blog_last_modify_time;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "mi_blog_id=" + mi_blog_id +
                ", mi_user_id=" + mi_user_id +
                ", mi_blog_text='" + mi_blog_text + '\'' +
                ", mi_blog_like_times=" + mi_blog_like_times +
                ", mi_blog_create_time='" + mi_blog_create_time + '\'' +
                '}';
    }
}
