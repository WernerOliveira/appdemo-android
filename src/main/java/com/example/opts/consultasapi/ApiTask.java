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
        try {
            JSONObject jsonRequest = new JSONObject(s);

            int code = jsonRequest.getInt("code");

            StringBuilder retorno = new StringBuilder("");
            if(code == 200) {
                JSONObject objeto = jsonRequest.getJSONObject("data");

                retorno.append("Ano: " + objeto.getString("ano") + "\n\n");
                retorno.append("Cor: " + objeto.getString("cor") + "\n\n");
                retorno.append("Modelo: " + objeto.getString("modelo") + "\n\n");
                retorno.append("Estado: " + objeto.getString("uf") + "\n\n");
                retorno.append("Município: " + objeto.getString("municipio") + "\n\n");
                retorno.append("Situação: " + objeto.getString("situacao") + "\n\n");
                retorno.append("Placa: " + objeto.getString("placa") + "\n\n");
            } else{
                retorno.append("Aguardo 1 minuto antes de fazer outra requisição");
            }
            textView.setText(retorno.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
