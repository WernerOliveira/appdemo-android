package com.example.opts.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.opts.R;
import com.example.opts.config.Globais;
import com.example.opts.controller.ComputadorController;
import com.example.opts.model.Computador;

public class ComputadoresActivity extends AppCompatActivity {

    Context context;
    TextView lblId;
    EditText txtNome, txtTombo, txtComentario;
    ComputadorController computadorController;
    Computador objComputador;
    Button btnExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computadores);

        context = ComputadoresActivity.this;

        lblId = findViewById(R.id.lblId_computadores);
        txtNome = findViewById(R.id.txtNome_computadores);
        txtTombo = findViewById(R.id.txtTombo_computadores);
        txtComentario = findViewById(R.id.txtComentarios_computadores);

        btnExcluir = findViewById(R.id.btnExcluir_computadores);

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computadorController = new ComputadorController(context);
                objComputador = new Computador();
                objComputador.setId(Integer.parseInt(lblId.getText().toString()));
                boolean retorno = computadorController.excluir(objComputador);
                if (retorno) {
                    Globais.exibirToast(context, "Computador deletado com sucesso");
                    finish();
                }
            }
        });

        //capturar o id da tela de listagem
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getInt("id") > 0) {

            int codigo = bundle.getInt("id");
            computadorController = new ComputadorController(context);
            objComputador = computadorController.buscar(codigo);
            if (objComputador != null) {
                preencheCampos(objComputador);

            }

        } else {
            btnExcluir.setVisibility(View.INVISIBLE);
        }
    }

    //função para inflar menu na tela
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cad, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //Funçao que captura o clique em algum item do menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean retorno;

        switch (id) {
            case R.id.action_ok:

                objComputador = new Computador();

                ComputadorController objController = new ComputadorController(context);

                objComputador.setNome(txtNome.getText().toString());
                objComputador.setTombo(txtTombo.getText().toString());
                objComputador.setComentario(txtComentario.getText().toString());

                if (!lblId.getText().equals("ID")) {
                    //alterar
                    objComputador.setId(Integer.parseInt((lblId.getText().toString())));
                    retorno = objController.alterar(objComputador);
                    if (retorno) {
                        Globais.exibirToast(context, "Computador alterado com sucesso");
                    }
                } else {
                    //incluir
                    retorno = objController.incluir(objComputador);
                    if (retorno) {
                        Globais.exibirToast(context, "Computador adicionado com sucesso");
                    }
                }
                //SALVAR
                //Curso objCurso = new Curso();
                //objCurso.setNome(txtNomeCurso.getText().toString());
                //finish();

            case R.id.action_cancel:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void preencheCampos(Computador objeto) {
        try {
            lblId.setText(String.valueOf(objeto.getId()));
            txtNome.setText(objeto.getNome());
            txtNome.setEnabled(true);

            txtTombo.setText(objeto.getTombo());
            txtComentario.setText(objeto.getComentario());
        } catch (Exception ex) {
            Globais.exibirToast(context, ex.getMessage());
        }
    }
}