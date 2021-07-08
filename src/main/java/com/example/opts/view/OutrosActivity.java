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
import com.example.opts.controller.OutroController;
import com.example.opts.model.Outro;

public class OutrosActivity extends AppCompatActivity {

    Context context;
    TextView lblId;
    EditText txtNome, txtTombo, txtComentario;
    OutroController outroController;
    Outro objOutro;
    Button btnExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outros);

        context = OutrosActivity.this;

        lblId = findViewById(R.id.lblId_outros);
        txtNome = findViewById(R.id.txtNome_outros);
        txtTombo = findViewById(R.id.txtTombo_outros);
        txtComentario = findViewById(R.id.txtComentarios_outros);

        btnExcluir = findViewById(R.id.btnExcluir_outros);

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outroController = new OutroController(context);
                objOutro = new Outro();
                objOutro.setId(Integer.parseInt(lblId.getText().toString()));
                boolean retorno = outroController.excluir(objOutro);
                if (retorno) {
                    Globais.exibirToast(context, "Item deletado com sucesso");
                    finish();
                }
            }
        });

        //capturar o id da tela de listagem
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getInt("id") > 0) {

            int codigo = bundle.getInt("id");
            outroController = new OutroController(context);
            objOutro = outroController.buscar(codigo);
            if (objOutro != null) {
                preencheCampos(objOutro);

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

                objOutro = new Outro();

                OutroController objController = new OutroController(context);

                objOutro.setNome(txtNome.getText().toString());
                objOutro.setTombo(txtTombo.getText().toString());
                objOutro.setComentario(txtComentario.getText().toString());

                if (!lblId.getText().equals("ID")) {
                    //alterar
                    objOutro.setId(Integer.parseInt((lblId.getText().toString())));
                    retorno = objController.alterar(objOutro);
                    if (retorno) {
                        Globais.exibirToast(context, "Item alterado com sucesso");
                    }
                } else {
                    //incluir
                    retorno = objController.incluir(objOutro);
                    if (retorno) {
                        Globais.exibirToast(context, "Item adicionado com sucesso");
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

    private void preencheCampos(Outro objeto) {
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