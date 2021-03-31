package com.example.opts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView lblTitulo;
    Button btnButao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //arquivo xml do layout da tela;

        //vinculos dos componentes com os componentes xml;
        lblTitulo = findViewById(R.id.lblTitulo);
        btnButao = findViewById(R.id.btnButao);

        btnButao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lblTitulo.setText("abcde");
            }
        });

    }
}