package com.example.opts.consultasapi;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Api {

    public String get (String _url){
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(_url).build();

            Response response = client.newCall(request).execute();

            String resposta = response.body().string();

            return resposta;
        } catch (Exception ex) {
            return "ERRO: " + ex.getMessage();
        }
    }

    public String post (String _url, String json){
        try {
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
            Request.Builder builder = new Request.Builder();

            RequestBody body = RequestBody.create(mediaType, json);
            builder.post(body);

            Request request = new Request.Builder().url(_url).post(body).build();

            Response response = client.newCall(request).execute();

            String resposta = response.body().string();

            return resposta;
        } catch (Exception ex) {
            return "ERRO: " + ex.getMessage();
        }
    }
}
