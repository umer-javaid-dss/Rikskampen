package com.kampen.riks.app.rikskampen.api.remote_api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Generic_Result<T> {



    @SerializedName("status")
    @Expose
    private
    String  status;


    @SerializedName("message")
    @Expose
    private
    Object msg="";



    @SerializedName("result")
    @Expose
    private T  result;


    @SerializedName("code")
    @Expose
    private
    String code="";


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}