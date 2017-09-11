package com.example.administrator.myapplication.util;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import com.example.administrator.myapplication.PicDetailActivity;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.WeiboAdapter;
import com.example.administrator.myapplication.entity.Pic;
import com.example.administrator.myapplication.entity.WeiboData;
import com.example.administrator.myapplication.module.BlogModule;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


/**
 * Created by Administrator on 2017/9/6.
 */

public class Util {

    /**
     * 显示对话框，提示用户要删除联系人
     * @param context
     * @param blog
     * @param adapter
     */
    public static void showDeleteBlogDialog(Context context, final WeiboData blog, final WeiboAdapter adapter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.bloged);
        builder.setTitle("系统提示");
        builder.setMessage("确定要删除这条微博？");
        builder.setPositiveButton("再想想",null);
        builder.setNegativeButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                BlogModule.deleteBlogById(blog);
                adapter.removeData(blog);
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }


    /**
     * 分享字符串
     * @param context
     * @param shareString
     * @param title
     */
    public static void shareBlog(Context context, String shareString, String title){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, shareString);
        context.startActivity(Intent.createChooser(intent, title));
    }


    /**
     * 复制到剪贴板
     * @param context
     * @param copyString
     */
    public static void copyToClipboard(Context context, String copyString){
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newPlainText("copyString",copyString));
        ToastUtil.showToast(context,"已复制到剪贴板");
    }


    /**
     * 把字符串转换为UTF8 编码
     * @param string
     * @return
     */
    public static String UTF8Decoder(String string){
        String res = null;
        try {
            res = URLDecoder.decode(string,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return res;
    }


    /**
     * 打印到控制台
     * @param logString
     */
    public static void log(String logString){
        boolean log_open = true;
        if (log_open){
            Log.i("mixinan", logString);
        }
    }


    /**
     * 显示大图
     * @param context
     * @param pic
     * @param from
     */
    public static void showPicInActivity(Context context, Pic pic, int from){
        Intent intent = new Intent(context, PicDetailActivity.class);
        intent.putExtra("clickPic",pic);
        intent.putExtra("from",from);
        context.startActivity(intent);
    }
}
