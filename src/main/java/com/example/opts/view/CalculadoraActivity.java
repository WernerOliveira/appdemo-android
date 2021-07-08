package com.example.opts.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opts.R;

public class CalculadoraActivity extends AppCompatActivity {

    Button btnSomar, btnSubtrai, btnMultiplica, btnDivide, btnLimpa;
    EditText txtNum1, txtNum2;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        //preenche o contexto da tela
        context = CalculadoraActivity.this;

        //declaração dos botoes
        btnSomar = findViewById(R.id.btnSoma_calculadora);
        btnSubtrai = findViewById(R.id.btnSubtrai_calculadora);
        btnMultiplica = findViewById(R.id.btnMultiplica_calculadora);
        btnDivide = findViewById(R.id.btnDivide_calculadora);
        btnLimpa = findViewById(R.id.btnLimpa_calculadora);

        txtNum1 = findViewById(R.id.txtNum1_calculadora);
        txtNum2 = findViewById(R.id.txtNum2_calculadora);

        //SOMA
        btnSomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double num1 = Double.parseDouble(txtNum1.getText().toString());
                    double num2 = Double.parseDouble(txtNum2.getText().toString());

                    double resultado = num1 + num2;

                    exibirToast("Resultado = " + resultado);

                    txtNum1.requestFocus(); //coloca cursor no campo indicado
                    esconderTeclado();

                }catch (Exception ex){

                }
            }
        });

        //SUBTRAI
        btnSubtrai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double num1 = Double.parseDouble(txtNum1.getText().toString());
                    double num2 = Double.parseDouble(txtNum2.getText().toString());

                    double resultado = num1 - num2;

                    exibirToast("Resultado = " + resultado);

                    txtNum1.requestFocus(); //coloca cursor no campo indicado
                    esconderTeclado();

                }catch (Exception ex){

                }
            }
        });

        //MULTIPLICA
        btnMultiplica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double num1 = Double.parseDouble(txtNum1.getText().toString());
                    double num2 = Double.parseDouble(txtNum2.getText().toString());

                    double resultado = num1 * num2;

                    exibirToast("Resultado = " + resultado);

                    txtNum1.requestFocus(); //coloca cursor no campo indicado
                    esconderTeclado();

                }catch (Exception ex){

                }
            }
        });

        //DIVIDE
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double num1 = Double.parseDouble(txtNum1.getText().toString());
                    double num2 = Double.parseDouble(txtNum2.getText().toString());

                    if(num2 > 0) {
                        double resultado = num1 / num2;

                        exibirToast("Resultado = " + resultado);

                        txtNum1.requestFocus(); //coloca cursor no campo indicado
                        esconderTeclado();
                    } else {
                        exibirToast("A divisão não pode ser feita");
                    }

                }catch (Exception ex){

                }
            }
        });

        btnLimpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNum1.setText("");
                txtNum2.setText("");
                esconderTeclado();
            }
        });
    }

    private void esconderTeclado(){
        try{
            //cria um objeto do teclado do emulador/celular
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            //metodo que esconde o teclado da tela
            imm.hideSoftInputFromWindow(btnSomar.getWindowToken(), 0);
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