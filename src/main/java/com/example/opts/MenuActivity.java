package com.example.opts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    Button btnMain, btnCalculadora, btnToast, btnLogout;
    TextView lblUsuario;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);

        context = MenuActivity.this;

        /*btnCalculadora = findViewById(R.id.btnCalculadora_menu);*/
        lblUsuario = findViewById(R.id.lblUsuario_menu);
        btnToast = findViewById(R.id.btnToast_menu);
        btnMain = findViewById(R.id.btnMain_menu);
        btnLogout = findViewById(R.id.btnLogout_menu);

        String nome_usuario = sharedPreferences.getString("usuario", "");
        lblUsuario.setText("Bem vindo " + nome_usuario);

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

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //zerar o cache preferences
                    sharedPreferences.edit().clear().commit();

                    Intent tela = new Intent(context, LogeenActivity.class);
                    startActivity(tela);
                    finish();
                } catch (Exception ex) {
                    exibirToast("Erro" + ex.getMessage());
                }
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