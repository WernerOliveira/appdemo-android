package com.example.opts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    Button btnMain, btnCalculadora, btnToast;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        context = MenuActivity.this;

        /*btnCalculadora = findViewById(R.id.btnCalculadora_menu);*/
        btnToast = findViewById(R.id.btnToast_menu);
        btnMain = findViewById(R.id.btnMain_menu);

        /*btnCalculadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //atraves da tela atual, abre a tela Calculadora
                Intent tela = new Intent(context, CalculadoraActivity.class);
                startActivity(tela);
                //finish(); //fecha a tela atual
            }
        });*/

        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exibirToast("ARA ARA");
            }
        });

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //atraves da tela atual, abre a tela Main
                Intent tela = new Intent(context, MainActivity.class);
                startActivity(tela);
                //finish(); //fecha a tela atual
            }
        });

    }

    private void exibirToast(String texto){
        try{
            Toast.makeText(context, texto, Toast.LENGTH_LONG).show();
        }catch (Exception ex){

        }
    }
}