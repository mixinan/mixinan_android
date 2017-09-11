package com.example.administrator.myapplication.module;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.myapplication.MyApp;
import com.example.administrator.myapplication.entity.Pic;
import com.example.administrator.myapplication.util.Const;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */

public class PicModule {
    public static void getPics(final Callback callback){
        //获取所有图片
        //public static String getPics = BASE_URL + "/pic/getAll.do";
        String url = Const.getPics;

        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    List<Pic> data = new ArrayList<>();
                    JSONObject obj = new JSONObject(response);
                    String objArrayString = obj.getString("data");
                    Gson gson = new Gson();
                    JsonArray array = new JsonParser().parse(objArrayString).getAsJsonArray();
                    for (JsonElement je : array) {
                        Pic pic = gson.fromJson(je,Pic.class);
                        data.add(pic);
                    }

                    //用回调形式发过去
                    callback.onSuccess(data);
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
