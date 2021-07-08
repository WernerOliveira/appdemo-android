package com.example.opts.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.opts.R;
import com.example.opts.adapter.OutroAdapter;
import com.example.opts.config.Globais;
import com.example.opts.controller.OutroController;
import com.example.opts.model.Outro;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListaOutrosActivity extends AppCompatActivity {

    ListView ltvOutro;
    FloatingActionButton btnAddOutro;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_outros);

        ltvOutro = findViewById(R.id.ltvOutros_listaOutros);
        btnAddOutro = findViewById(R.id.btnAddLista_Outro);
        context = ListaOutrosActivity.this;

        btnAddOutro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globais.abrirActivity(context, OutrosActivity.class, false);
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
            OutroController controller = new OutroController(context);
            ArrayList<Outro> lista = controller.lista();
            if (lista != null) {
                OutroAdapter adapter = new OutroAdapter(context, lista);

                ltvOutro.setAdapter(adapter);
            }
        } catch (Exception ex){

        }
    }
}