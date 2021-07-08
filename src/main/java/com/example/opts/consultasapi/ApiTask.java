package com.example.opts.consultasapi;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiTask extends AsyncTask<String, Void, String> {

    private TextView textView;

    public ApiTask(TextView text) {
        this.textView = text;
    }

    @Override
    protected String doInBackground(String... params) {
        Api api = new Api();
        String retorno = new String("");
        String body_json = new String("");

        String method = params[0].trim();

        String url_api = params[1].replaceAll(" ", "%20");
        if(params .length > 2) {
            body_json = params[2];
        }

        if(method.equals("post")){
            retorno = api.post(url_api, body_json);
        } else if(method.equals("get")) {
            retorno = api.get(url_api);
        }

        return retorno;
    }

    @Override
    protected void onPostExecute(String s) {
        /*try {
            JSONObject jsonRequest = new JSONObject(s);

            JSONArray objeto = jsonRequest.getJSONArray("data");

            StringBuilder retorno = new StringBuilder("");
            for(int i = 0; i < objeto.length(); i++){
                JSONObject obj = objeto.getJSONObject(i);
                retorno.append("Categoria: " + obj.getString("modelo") + "\n");

                if(obj.has("data")){
                    JSONArray eventos = obj.getJSONArray("data");
                    for(int ie = 0 ; ie< eventos.length(); ie++){
                        JSONObject event = eventos.getJSONObject(ie);

                        retorno.append("Teste: " + event.getString("cor") + "\n");
                        retorno.append(event.getString("ano") + "\n\n");
                    }
                }
            }
            textView.setText(retorno.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        try {
            JSONObject jsonRequest = new JSONObject(s);

            JSONObject objeto = jsonRequest.getJSONObject("data");

            StringBuilder retorno = new StringBuilder("");

            retorno.append("Ano: " + objeto.getString("ano") + "\n\n");
            retorno.append("Cor: " + objeto.getString("cor") + "\n\n");
            retorno.append("Modelo: " + objeto.getString("modelo") + "\n\n");
            retorno.append("Estado: " + objeto.getString("uf") + "\n\n");
            retorno.append("Município: " + objeto.getString("municipio") + "\n\n");
            retorno.append("Situação: " + objeto.getString("situacao") + "\n\n");
            retorno.append("Placa: " + objeto.getString("placa") + "\n\n");

            textView.setText(retorno.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonRequest = new JSONObject(s);

            JSONObject objeta = jsonRequest.getJSONObject("error");

            StringBuilder retorne = new StringBuilder("");

            retorne.append("Por favor, espere 1 minuto antes de fazer outra busca" + objeta.getString("error") + "\n\n");
            retorne.append("Placa: " + objeta.getString("plate") + "\n\n");

            textView.setText(retorne.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
