package com.example.opts.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.opts.R;
import com.example.opts.config.Globais;
import com.example.opts.controller.UsuarioController;
import com.example.opts.model.TipoUsuario;
import com.example.opts.model.Usuario;

import java.util.ArrayList;

public class UsuarioActivity extends AppCompatActivity {

    Context context;
    //Usuario objeto;
    TextView lblId;
    EditText txtLogin, txtSenha, txtEmail, txtTelefone;
    UsuarioController usuarioController;
    Usuario objUsuario;
    Button btnExcluir;
    Spinner spiTipoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        context = UsuarioActivity.this;

        lblId = findViewById(R.id.lblId_usuario);
        txtLogin = findViewById(R.id.txtLogin_usuario);
        txtSenha = findViewById(R.id.txtSenha_usuario);
        txtEmail = findViewById(R.id.txtEmail_usuario);
        txtTelefone = findViewById(R.id.txtTelefone_usuario);
        spiTipoUsuario = findViewById(R.id.spiTipo_usuario);

        btnExcluir = findViewById(R.id.btnExcluir_usuario);

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarioController = new UsuarioController(context);
                objUsuario = new Usuario();
                objUsuario.setId(Integer.parseInt(lblId.getText().toString()));
                boolean retorno = usuarioController.excluir(objUsuario);
                if (retorno) {
                    Globais.exibirToast(context, "Usuário deletado com sucesso");
                    finish();
                }
            }
        });

        //capturar o id da tela de listagem
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getInt("id") > 0) {

            int codigo = bundle.getInt("id");
            usuarioController = new UsuarioController(context);
            objUsuario = usuarioController.buscar(codigo);
            if (objUsuario != null) {
                preencheCampos(objUsuario);

            }

            ArrayList<TipoUsuario> array_tipos = new ArrayList<>();

            array_tipos.add(new TipoUsuario(1, "Administrador"));
            array_tipos.add(new TipoUsuario(2, "Usuário"));

            ArrayAdapter<TipoUsuario> adapter = new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, array_tipos);

            spiTipoUsuario.setAdapter(adapter);

            //objeto = busca do controller

            /*int codigo = bundle.getInt("id");
            usuarioController = new UsuarioController(context);
            objUsuario = usuarioController.buscar(codigo);
            if (objUsuario != null) {
                lblId.setText(String.valueOf(codigo));
                txtLogin.setText(objUsuario.getLogin());
                txtLogin.setEnabled(false);*/

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

                objUsuario = new Usuario();

                UsuarioController objController = new UsuarioController(context);

                TipoUsuario tipoUsuario = (TipoUsuario) spiTipoUsuario.getSelectedItem();

                /*if(tipoUsuario.getId() == 0){
                    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                }*/

                objUsuario.setLogin(txtLogin.getText().toString());
                objUsuario.setEmail(txtEmail.getText().toString());
                objUsuario.setTelefone(txtTelefone.getText().toString());
                objUsuario.setTipo(tipoUsuario.getId());

                String senhaMd5 = Globais.md5(txtSenha.getText().toString());
                objUsuario.setSenha(senhaMd5);

                if (!lblId.getText().equals("ID")) {
                    //alterar
                    objUsuario.setId(Integer.parseInt((lblId.getText().toString())));
                    retorno = objController.alterar(objUsuario);
                    if (retorno) {
                        Globais.exibirToast(context, "Usuário alterado com sucesso");
                    }
                } else {
                    //incluir
                    retorno = objController.incluir(objUsuario);
                    if (retorno) {
                        Globais.exibirToast(context, "Usuário adicionado com sucesso");
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

    private void preencheCampos(Usuario objeto) {
        try {
            lblId.setText(String.valueOf(objeto.getId()));
            txtLogin.setText(objeto.getLogin());
            txtLogin.setEnabled(false);

            txtTelefone.setText(objeto.getTelefone());
            txtEmail.setText(objeto.getEmail());
        } catch (Exception ex) {
            Globais.exibirToast(context, ex.getMessage());
        }
    }
}