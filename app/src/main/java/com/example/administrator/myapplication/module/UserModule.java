package com.example.administrator.myapplication.module;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.myapplication.MyApp;
import com.example.administrator.myapplication.util.Const;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/8.
 */

public class UserModule {

    /**
     * 检查name是否已占用
     * @param name
     * @return
     */
    public static void checkUsernameUsed(final String name, final VolleyCallBack callback){

        //查看 name 有没有被占用
        //public static String checkNameUsed = BASE_URL + "/user/checkNameUsed.do?name=mixinan";
        //public static String checkNameUsed = BASE_URL + "/user/checkNameUsed.do";

        String url = Const.checkNameUsed;

        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    int status = obj.getInt("status");
                    callback.onSuccess(status);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String, String>();
                map.put("name",name);
                return map;
            }
        };

        req.setTag("post");
        MyApp.getRequestQueue().add(req);
    }



    public interface VolleyCallBack{
        void onSuccess(int status);
    }

}
