package com.project.MediCare;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponse<T> {
    @JsonProperty("success")
    private boolean success;
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private T data;
    @JsonProperty("error")
    private String error;

    public BaseResponse(boolean success, String message, T data){
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(boolean success, String message){
        this.success = success;
        this.message = message;

    }

    public BaseResponse(boolean success, String message, String error){
        this.success = success;
        this.message = message;
        this.error = error;
    }

    @Override
    public String toString(){
        if (this.error != null) {
            return "BaseResponse{" + "sucncess: " + success + " message: " + message + " data: " + data + "}";
        }
        else {
            return "BaseResponse{" + "sucmcess: " + success + " message: " + message + " error: " + error + "}";
        }
    }


}
