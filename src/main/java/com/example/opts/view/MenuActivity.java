package com.example.opts.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opts.R;

public class MenuActivity extends AppCompatActivity {

    Button btnMain, btnCalculadora, btnPix, btnToast, btnListaUsuarios, btnLogout, btnUsuarios, btnAtivos, btnApi;
    TextView lblUsuario;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);

        context = MenuActivity.this;

        /*btnCalculadora = findViewById(R.id.btnCalculadora_menu);
        btnToast = findViewById(R.id.btnToast_menu);
        btnMain = findViewById(R.id.btnMain_menu);
        btnPix = findViewById(R.id.btnPix_menu);*/
        btnUsuarios = findViewById(R.id.btnUsuarios_menu);
        lblUsuario = findViewById(R.id.lblUsuario_menu);
        btnLogout = findViewById(R.id.btnLogout_menu);
        btnAtivos = findViewById(R.id.btnAtivos_menu);
        btnApi = findViewById(R.id.btnApi_menu);

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

        /*btnPix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //atraves da tela atual, abre a tela Calculadora
                Intent tela = new Intent(context, PixActivity.class);
                startActivity(tela);
                //finish(); //fecha a tela atual
            }
        });*/

        btnUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //atraves da tela atual, abre a tela Calculadora
                Intent tela = new Intent(context, ListaUsuariosActivity.class);
                //Intent tela = new Intent(context, UsuarioActivity.class);
                startActivity(tela);
                //finish(); //fecha a tela atual
            }
        });

        /*btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exibirToast("ARA ARA");
            }
        });*/

        /*btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //atraves da tela atual, abre a tela Main
                Intent tela = new Intent(context, MainActivity.class);
                startActivity(tela);
                //finish(); //fecha a tela atual
            }
        });*/

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

        btnAtivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //atraves da tela atual, abre a tela Calculadora
                Intent tela = new Intent(context, AtivosActivity.class);
                startActivity(tela);
                //finish(); //fecha a tela atual
            }
        });

        btnApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //atraves da tela atual, abre a tela Calculadora
                Intent tela = new Intent(context, RastreioActivity.class);
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