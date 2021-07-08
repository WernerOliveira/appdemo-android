package com.example.opts.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.opts.R;
import com.example.opts.adapter.UsuarioAdapter;
import com.example.opts.config.Globais;
import com.example.opts.controller.UsuarioController;
import com.example.opts.model.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListaUsuariosActivity extends AppCompatActivity {

    ListView ltvUsuarios;
    FloatingActionButton btnAddUsuario;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        ltvUsuarios = findViewById(R.id.ltvUsuarios_listaUsuarios);
        btnAddUsuario = findViewById(R.id.btnAddUsuario_lista_Usuario);
        context = ListaUsuariosActivity.this;

        btnAddUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globais.abrirActivity(context, UsuarioActivity.class, false);
            }
        });

        atualizarLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }

    private void atualizarLista () {
        try {
            //BUSCAR TODOS OS USER E PREENCHER EM UM LIST
            UsuarioController controller = new UsuarioController(context);
            ArrayList<Usuario> lista = controller.lista();
            if (lista != null) {
                UsuarioAdapter adapter = new UsuarioAdapter(context, lista);

                ltvUsuarios.setAdapter(adapter);
            }
        } catch (Exception ex){

        }
    }
}