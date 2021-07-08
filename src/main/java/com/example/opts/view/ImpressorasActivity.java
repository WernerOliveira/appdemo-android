package com.example.opts.view;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.opts.R;
import com.example.opts.config.Globais;
import com.example.opts.controller.ImpressoraController;
import com.example.opts.model.Impressora;

public class ImpressorasActivity extends AppCompatActivity {

    Context context;
    TextView lblId;
    EditText txtNome, txtTombo, txtComentario;
    ImpressoraController impressoraController;
    Impressora objImpressora;
    Button btnExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impressoras);

        context = ImpressorasActivity.this;

        lblId = findViewById(R.id.lblId_impressoras);
        txtNome = findViewById(R.id.txtNome_impressoras);
        txtTombo = findViewById(R.id.txtTombo_impressoras);
        txtComentario = findViewById(R.id.txtComentarios_impressoras);

        btnExcluir = findViewById(R.id.btnExcluir_impressoras);

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                impressoraController = new ImpressoraController(context);
                objImpressora = new Impressora();
                objImpressora.setId(Integer.parseInt(lblId.getText().toString()));
                boolean retorno = impressoraController.excluir(objImpressora);
                if (retorno) {
                    Globais.exibirToast(context, "Impressora deletado com sucesso");
                    finish();
                }
            }
        });

        //capturar o id da tela de listagem
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getInt("id") > 0) {

            int codigo = bundle.getInt("id");
            impressoraController = new ImpressoraController(context);
            objImpressora = impressoraController.buscar(codigo);
            if (objImpressora != null) {
                preencheCampos(objImpressora);

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

                objImpressora = new Impressora();

                ImpressoraController objController = new ImpressoraController(context);

                objImpressora.setNome(txtNome.getText().toString());
                objImpressora.setTombo(txtTombo.getText().toString());
                objImpressora.setComentario(txtComentario.getText().toString());

                if (!lblId.getText().equals("ID")) {
                    //alterar
                    objImpressora.setId(Integer.parseInt((lblId.getText().toString())));
                    retorno = objController.alterar(objImpressora);
                    if (retorno) {
                        Globais.exibirToast(context, "Impressora alterado com sucesso");
                    }
                } else {
                    //incluir
                    retorno = objController.incluir(objImpressora);
                    if (retorno) {
                        Globais.exibirToast(context, "Impressora adicionado com sucesso");
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

    private void preencheCampos(Impressora objeto) {
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