package com.example.abilambin.nutritio.restApi;


import okhttp3.MediaType;

public abstract class RestCallerConstant {
    public static final String SERVER_ADDR = "http://37.187.103.85:8080";
    public static final MediaType JSON_MEDIATYPE = MediaType.parse("application/json; charset=utf-8");
}
