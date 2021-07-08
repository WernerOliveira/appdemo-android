package com.example.opts.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opts.R;
import com.example.opts.config.Globais;
import com.example.opts.controller.UsuarioController;
import com.example.opts.model.Usuario;

public class LogeenActivity extends AppCompatActivity {

    Button btnLogin;
    EditText txtUser, txtPass;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logeen);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);

        context = LogeenActivity.this;

        btnLogin = findViewById(R.id.btnLogin_logeen);

        txtUser = findViewById(R.id.txtLogin_usuario_logeen);
        txtPass = findViewById(R.id.txtSenha_usuario_logeen);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioController controller = new UsuarioController(context);
                Usuario user = controller.login(txtUser.getText().toString(), txtPass.getText().toString());

                if (user != null) {
                    Intent tela = new Intent(context, MenuActivity.class);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("usuario", txtUser.getText().toString());
                    editor.putString("chavePix", "12345678910");
                    startActivity(tela);
                    finish();
                } else {
                    Globais.exibirToast(context, "Usuário/senha inválidos");
                }
            }
        });

        /*String nome_usuario = sharedPreferences.getString("usuario", "");
        if (!nome_usuario.equals("")) {
            Intent tela = new Intent(context, MenuActivity.class);
            startActivity(tela);
        }*/
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