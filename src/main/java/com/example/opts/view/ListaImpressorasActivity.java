package com.example.opts.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.opts.R;
import com.example.opts.adapter.ImpressoraAdapter;
import com.example.opts.config.Globais;
import com.example.opts.controller.ImpressoraController;
import com.example.opts.model.Impressora;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListaImpressorasActivity extends AppCompatActivity {

    ListView ltvImpressoras;
    FloatingActionButton btnAddImpressora;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_impressoras);

        ltvImpressoras = findViewById(R.id.ltvImpressoras_listaImpressoras);
        btnAddImpressora = findViewById(R.id.btnAddImpressora_lista_Impressora);
        context = ListaImpressorasActivity.this;

        btnAddImpressora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globais.abrirActivity(context, ImpressorasActivity.class, false);
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
            ImpressoraController controller = new ImpressoraController(context);
            ArrayList<Impressora> lista = controller.lista();
            if (lista != null) {
                ImpressoraAdapter adapter = new ImpressoraAdapter(context, lista);

                ltvImpressoras.setAdapter(adapter);
            }
        } catch (Exception ex){

        }
    }
}