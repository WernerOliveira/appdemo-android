package com.example.opts.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opts.R;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText txtUser, txtPass;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = LoginActivity.this;

        btnLogin = findViewById(R.id.btnLogin_login);

        txtUser = findViewById(R.id.txtNome_login);
        txtPass = findViewById(R.id.txtSenha_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String user = txtUser.getText().toString();
                    String pass = txtPass.getText().toString();
                    if (user.equals("androido") && pass.equals("androido")){
                        //atraves da tela atual, abre a tela Calculadora
                        Intent tela = new Intent(context, MenuActivity.class);
                        startActivity(tela);
                        //finish(); //fecha a tela atual
                    } else {
                        exibirToast("Usuário ou senha inválido(s)");
                    }
                } catch (Exception ex) {
                }
            }
        });
    }

    private void esconderTeclado(){
        try{
            //cria um objeto do teclado do emulador/celular
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            //metodo que esconde o teclado da tela
            imm.hideSoftInputFromWindow(btnLogin.getWindowToken(), 0);
        }catch (Exception ex){

        }
    }

    private void exibirToast(String texto){
        try{
            Toast.makeText(context, texto, Toast.LENGTH_LONG).show();
        }catch (Exception ex){

        }
    }
}