package com.example.abilambin.nutritio.utils;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class BackgroundRestCaller extends AsyncTask<Request, Integer, String> {
    private int responseCode;
    private List<Integer> responseCodes;

    private String responseBody;
    private List<String> responseBodies;

    @Override
    protected String doInBackground(Request... requests) {
        OkHttpClient client = new OkHttpClient();

        try {
            if(requests.length == 1){                                   // Une seule requete
                Response response = client.newCall(requests[0]).execute();

                this.responseBody = response.body().string();
                this.responseCode = response.code();

                response.body().close();

                return this.responseBody;
            }else{                                                      // Plusieurs requetes
                this.responseCodes = new ArrayList<>();
                this.responseBodies = new ArrayList<>();

                for (Request r : requests) {
                    Response response = client.newCall(r).execute();


                    responseBodies.add(response.body().string());
                    responseCodes.add(response.code());
                }

                return responseBodies.get(0);
            }



        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getResponseCode() {
        return responseCode;
    }

    public List<Integer> getResponseCodes() {
        return responseCodes;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public List<String> getResponseBodies() {
        return responseBodies;
    }
}
