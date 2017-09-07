package com.example.administrator.myapplication.entity;

/**
 * Created by Administrator on 2017/9/3.
 */
public class WeiboData {
    private Blog blog;
    private User user;

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Blog getBlog() {
        return this.blog;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

}
