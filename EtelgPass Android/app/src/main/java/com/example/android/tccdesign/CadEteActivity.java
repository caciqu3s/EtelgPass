package com.example.android.tccdesign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CadEteActivity extends AppCompatActivity {

    Bundle dados;
    Intent intent;
    TextView BemVindo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_ete);
        intent = getIntent();
        dados = intent.getExtras();
        BemVindo = (TextView)findViewById(R.id.textview_nome);
        BemVindo.setText("Bem vindo " + dados.getString("Nome"));
    }
}
