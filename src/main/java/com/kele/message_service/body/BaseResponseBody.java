package com.kele.message_service.body;

import com.google.gson.annotations.SerializedName;

public class BaseResponseBody {
    @SerializedName("code")
    private int code = 5001;
    @SerializedName("message")
    private String message = "";


    public void setCode(int code) {
        this.code = code;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "BaseResponseBody{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
