package com.example.administrator.myapplication.module;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.myapplication.MyApp;
import com.example.administrator.myapplication.entity.WeiboData;
import com.example.administrator.myapplication.util.Const;
import com.example.administrator.myapplication.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/9/6.
 */

public class BlogModule {

    /**
     * 根据blogId删除blog
     * @param weibo
     */
    public static void deleteBlogById(WeiboData weibo) {
        //通过blogId删除blog
        //public static String deleteBlog = BASE_URL + "/blog/delete.do?blogId=30";
        //public static String deleteBlog = BASE_URL + "/blog/delete.do?blogId=";
        String url = Const.deleteBlog + weibo.getBlog().getMi_blog_id();

        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ToastUtil.showToast(MyApp.getInstance(),"删除成功");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        MyApp.getRequestQueue().add(req);
    }


    //获取blog页数，并保存在MyApp全局
    //public static String getBlogPages = BASE_URL+"/blog/getblogpages.do";

    public static void getPages(){
        String url = Const.getBlogPages;
        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            int pages = obj.getInt("data");
                            System.out.println("mixinan  blogmodule(getPages) = "+ pages);
                            MyApp.setBlogPages(pages);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MyApp.getRequestQueue().add(req);
    }
}
