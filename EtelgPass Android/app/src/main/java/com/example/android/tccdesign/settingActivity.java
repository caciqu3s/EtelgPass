package com.example.android.tccdesign;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

public class settingActivity extends Activity {

    Switch UpdateMobile;
    boolean ativado;
    Classe classe = new Classe();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ImageButton upbutton = findViewById(R.id.upButton);
        UpdateMobile = findViewById(R.id.switchUpdate);

        SharedPreferences prefs = getSharedPreferences(Classe.NOME_PREFERENCE, MODE_PRIVATE);
        ativado = prefs.getBoolean("UpdateMobile", true);

        UpdateMobile.setChecked(ativado);

        UpdateMobile.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = getSharedPreferences(Classe.NOME_PREFERENCE, MODE_PRIVATE).edit();
                editor.putBoolean("UpdateMobile", isChecked);
                editor.commit();
            }
        });

        upbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Voltar();
            }
        });
    }

    public void Sair(View view)
    {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void Voltar() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
