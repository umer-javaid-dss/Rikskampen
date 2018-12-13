package com.kampen.riks.app.rikskampen.api.remote_api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Generic_Result<T> {

    @SerializedName("status")
    @Expose
    private
    int  status=0;
    @SerializedName("msg")
    @Expose
    private
    String msg="";

    @SerializedName("data")
    @Expose
    private T  dataObject;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public T getDataObject() {
        return dataObject;
    }

    public void setDataObject(T dataObject) {
        this.dataObject = dataObject;
    }
}