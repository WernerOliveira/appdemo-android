package com.example.opts.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.opts.R;

public class AtivosActivity extends AppCompatActivity {

    Button btnComputadores, btnImpressoras, btnOutros;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ativos);

        context = AtivosActivity.this;

        btnComputadores = findViewById(R.id.btnComputadores_ativos);
        btnImpressoras = findViewById(R.id.btnImpressoras_ativos);
        btnOutros = findViewById(R.id.btnOutros_ativos);

        btnComputadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tela = new Intent(context, ListaComputadoresActivity.class);
                startActivity(tela);
                //finish(); //fecha a tela atual
            }
        });

        btnImpressoras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tela = new Intent(context, ListaImpressorasActivity.class);
                startActivity(tela);
                //finish(); //fecha a tela atual
            }
        });

        btnOutros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tela = new Intent(context, ListaOutrosActivity.class);
                startActivity(tela);
                //finish(); //fecha a tela atual
            }
        });
    }
}