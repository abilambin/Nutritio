package com.example.abilambin.nutritio.utils;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bellamy on 16/02/18.
 */

public class BackgroundRestCaller extends AsyncTask<Request, Integer, String> {
    private int responseCode;

    @Override
    protected String doInBackground(Request... requests) {
        OkHttpClient client = new OkHttpClient();

        Response response = null;

        try {
            response = client.newCall(requests[0]).execute();


            String res = response.body().string();
            this.responseCode = response.code();

            return res;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getResponseCode() {
        return responseCode;
    }
}
