package com.example.opts.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.opts.R;
import com.example.opts.consultasapi.ApiTask;

import org.json.JSONException;
import org.json.JSONObject;

public class RastreioActivity extends AppCompatActivity {

    Button btnPesquisar;
    TextView lblResultado;
    EditText txtRastreio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rastreio);

        btnPesquisar = findViewById(R.id.btnConsultaCEP);
        txtRastreio = findViewById(R.id.txtConsulta);
        lblResultado = findViewById(R.id.lblRetorno);

        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String[] args = new String[3];

                    args[0] = "post"; //method
                    args[1] = "https://sinesp.contrateumdev.com.br/api"; //url

                    /*JSONObject body = new JSONObject();
                    body.put("code", txtRastreio.getText());
                    body.put("type","LS" );*/

                    JSONObject body = new JSONObject();
                    body.put("key", "chavedemostracao");
                    body.put("plate",txtRastreio.getText() );

                    args[2] = body.toString(); //body em json

                    ApiTask task = new ApiTask(lblResultado);
                    task.execute(args);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}