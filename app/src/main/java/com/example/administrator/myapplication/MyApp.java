package com.example.administrator.myapplication;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.myapplication.entity.User;
import com.example.administrator.myapplication.module.BlogModule;

/**
 * Created by Administrator on 2017/8/30.
 */

public class MyApp extends Application {
    private static MyApp singleton;
    private static RequestQueue queue;
    private static User user;
    private static int blogPages;

    public static MyApp getInstance() {
        return singleton;
    }

    public static RequestQueue getRequestQueue() {
        return queue;
    }

    @Override
    public final void onCreate() {
        super.onCreate();
        singleton = this;
        queue = Volley.newRequestQueue(getApplicationContext());
        BlogModule.getPages();
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User loginUser) {
        user = loginUser;
    }

    public static void setBlogPages(int pages){
        blogPages = pages;
        System.out.println("mixinan  myApp pages = "+blogPages);
    }

    public static int getBlogPages(){
        return blogPages;
    }

}
