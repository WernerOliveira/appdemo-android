package com.example.opts.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opts.R;
import com.example.opts.model.Pix;

public class PixActivity extends AppCompatActivity {

    Button btnPagar, btnReceber;
    EditText txtValor;
    TextView lblChave, lblSaldo;
    Pix contaPix;
    SharedPreferences sharedPreferences;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pix);

        //preenche o contexto da tela
        context = PixActivity.this;

        sharedPreferences = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);

        contaPix = new Pix();
        contaPix.setChave(sharedPreferences.getString("chavePix", ""));
        float saldo = sharedPreferences.getFloat("valor", 0);
        contaPix.setSaldo(saldo);
        contaPix.setCheque_especial(200);

        //vincular objetos da tela
        txtValor = findViewById(R.id.txtValor_pix);
        btnPagar = findViewById(R.id.btnPagar_pix);
        btnReceber = findViewById(R.id.btnReceber_pix);
        lblChave = findViewById(R.id.lblChave_pix);
        lblSaldo = findViewById(R.id.lblSaldo_pix);

        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pagar um valor
                if (txtValor.getText().toString().equals("") || txtValor.getText().toString().equals("0")) {
                    exibirToast("Por favor, insira um valor");
                } else {
                        float valor = Float.parseFloat(txtValor.getText().toString());
                        float saldo = contaPix.getSaldo() - valor;
                        contaPix.setSaldo(saldo);
                }

                esconderTeclado();
                atualizarDados();
            }
        });

        btnReceber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //receber um valor
                if (txtValor.getText().toString().equals("") || txtValor.getText().toString().equals("0")) {
                    exibirToast("Por favor, insira um valor");
                } else {
                    float valor = Float.parseFloat(txtValor.getText().toString());
                    float saldo = contaPix.getSaldo() + valor;
                    contaPix.setSaldo(saldo);
                }

                esconderTeclado();
                atualizarDados();
            }
        });

        atualizarDados();
    }

    private void atualizarDados() {
        try {
            sharedPreferences = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);

            lblSaldo.setText(String.valueOf(contaPix.getSaldo()));
            lblChave.setText(contaPix.getChave());
            txtValor.setText("");
        } catch (Exception ex) {

        }
    }

    private void esconderTeclado(){
        try{
            //cria um objeto do teclado do emulador/celular
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            //metodo que esconde o teclado da tela
            imm.hideSoftInputFromWindow(btnPagar.getWindowToken(), 0);
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