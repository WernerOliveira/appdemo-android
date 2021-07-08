package com.example.opts.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.opts.R;
import com.example.opts.adapter.ComputadorAdapter;
import com.example.opts.config.Globais;
import com.example.opts.controller.ComputadorController;
import com.example.opts.model.Computador;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListaComputadoresActivity extends AppCompatActivity {

    ListView ltvComputadores;
    FloatingActionButton btnAddComputador;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_computadores);

        ltvComputadores = findViewById(R.id.ltvComputadores_listaComputadores);
        btnAddComputador = findViewById(R.id.btnAddComputador_lista_Computador);
        context = ListaComputadoresActivity.this;

        btnAddComputador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globais.abrirActivity(context, ComputadoresActivity.class, false);
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
            ComputadorController controller = new ComputadorController(context);
            ArrayList<Computador> lista = controller.lista();
            if (lista != null) {
                ComputadorAdapter adapter = new ComputadorAdapter(context, lista);

                ltvComputadores.setAdapter(adapter);
            }
        } catch (Exception ex){

        }
    }
}