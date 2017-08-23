package com.minoapp.base;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Devin on 2017/6/14.
 */

public class BaseResponse<T> implements Serializable {
    public static final int SUCCESS=1;
    @SerializedName("code")
    private int code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    private T data;

    public boolean success(){
        return code==SUCCESS;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {

        return "code"+getCode()+"||msg"+getMsg()+"dataSize"+getData().toString();
    }
}
