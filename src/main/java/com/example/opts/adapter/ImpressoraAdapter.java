package com.example.opts.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.opts.R;
import com.example.opts.config.Globais;
import com.example.opts.model.Impressora;
import com.example.opts.view.ImpressorasActivity;

import java.util.ArrayList;

public class ImpressoraAdapter extends ArrayAdapter<Impressora> {

    private final Context context;
    private final ArrayList<Impressora> elementos;

    public ImpressoraAdapter(Context context, ArrayList<Impressora> elementos) {
        super(context, R.layout.activity_lista_impressoras, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        try {

            Impressora objeto = elementos.get(position);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //toda vez que passa por um item da lista, os elementos são associados
            View rowView = inflater.inflate(R.layout.item_lista_impressoras, parent, false);

            TextView id = rowView.findViewById(R.id.lblId_item_impressoras);
            TextView nome = rowView.findViewById(R.id.lblNome_item_impressoras);
            TextView tombo = rowView.findViewById(R.id.lblTombo_item_imp);

            id.setText(String.valueOf(objeto.getId()));
            nome.setText(objeto.getNome());
            tombo.setText(objeto.getTombo());

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent tela = new Intent (context, ImpressorasActivity.class);
                    tela.putExtra("id", objeto.getId());
                    context.startActivity(tela);
                }
            });

            return rowView;
        } catch (Exception ex) {
            Globais.exibirToast(context, ex.getMessage());
            return null;
        }
    }
}
