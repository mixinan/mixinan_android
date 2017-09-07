package com.example.administrator.myapplication.entity;

import java.util.List;

public class WeiboResult {
    private int status;
    private String msg;
    private List<WeiboData> data;

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setData(List<WeiboData> data) {
        this.data = data;
    }

    public List<WeiboData> getData() {
        return this.data;
    }
}




