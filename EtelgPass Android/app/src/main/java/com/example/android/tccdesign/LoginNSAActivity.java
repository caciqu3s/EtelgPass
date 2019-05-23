package com.example.android.tccdesign;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;

public class LoginNSAActivity extends AppCompatActivity {

    Classe classe = new Classe();
    Button btn_LogarNSA;
    EditText txt_RA, txt_Senha;
    Intent intent;
    private RequestQueue mQueue;

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_nsa);

        mQueue = Volley.newRequestQueue(this);

        intent = new Intent(getApplicationContext(), MainActivity.class);

        txt_RA = findViewById(R.id.editText_RA);
        txt_Senha = findViewById(R.id.editText_pwd);
        //btn_LogarNSA = (Button) findViewById(R.id.buttonNSA);

        btn_LogarNSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogarNSA(txt_RA.getText().toString(), txt_Senha.getText().toString());
            }
        });
    }

    public void LogarNSA(final String Login, final String Senha) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logando...");
        progressDialog.show();

        String url = "http://nsa.cps.sp.gov.br/wcf/aluno_app.svc/LoginAPP/010/" + Login + "/" + MD5(Senha);

        if (isOnline(getApplicationContext()) == false) {
            progressDialog.dismiss();
            Toast.makeText(this, "Sem conexão com a internet. Tente novamente", Toast.LENGTH_LONG).show();
        } else {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONObject inicio = response.getJSONObject("0");
                                JSONObject jsonObject = inicio.getJSONObject("Aluno");

                                String Nome = jsonObject.getString("Nome");
                                String Sala = jsonObject.getString("Sala");
                                String Curso = jsonObject.getString("Curso");
                                String Validade = jsonObject.getString("Ano");
                                String Número = jsonObject.getString("Número");


                                JSONArray jsonArray = response.getJSONArray("Notas");

                                /*

                                for(i = 0; i < jsonArray.length(); i++)
                                {
                                    JSONObject Notas = jsonArray.getJSONObject(i);

                                    Disciplina[i] = Notas.getString("disciplina");
                                    Professor[i] = Notas.getString("professor");
                                    Conceito1[i] = Notas.getString("conceito1");
                                    Conceito2[i] = Notas.getString("conceito2");
                                    Conceito3[i] = Notas.getString("conceito3");
                                    Conceito4[i] = Notas.getString("conceito4");
                                    ConceitoFinal[i] = Notas.getString("conceito_final");
                                    PorcentagemFaltas[i] = Notas.getString("porcentagem");
                                }
                                */

                                if (Nome != "") {
                                    SharedPreferences.Editor editor = getSharedPreferences(Classe.NOME_PREFERENCE, MODE_PRIVATE).edit();

                                    editor.putString("RA", Login);
                                    editor.putString("Senha", MD5(Senha));
                                    editor.commit();

                                    Bundle bundle = new Bundle();
                                    bundle.putString("RA", Login);
                                    bundle.putString("Token", Nome);
                                    bundle.putString("Sala", Sala);
                                    bundle.putString("Curso", Curso);
                                    bundle.putString("Validade", Validade);
                                    bundle.putString("Número", Número);
                                    /*
                                    bundle.putStringArray("Disciplina", Disciplina);
                                    bundle.putStringArray("Professor", Professor);
                                    bundle.putStringArray("Conceito1", Conceito1);
                                    bundle.putStringArray("Conceito2", Conceito2);
                                    bundle.putStringArray("Conceito3", Conceito3);
                                    bundle.putStringArray("Conceito4", Conceito4);
                                    bundle.putStringArray("ConceitoFinal", ConceitoFinal);
                                    bundle.putStringArray("PorcentagemFaltas", PorcentagemFaltas);
                                    */
                                    intent.putExtras(bundle);
                                    progressDialog.dismiss();
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                } else {
                                    progressDialog.setMessage("RM Inválido");
                                    Toast.makeText(getApplicationContext(), "RM Inválido", Toast.LENGTH_LONG).show();
                                    txt_RA.setText("");
                                    progressDialog.dismiss();
                                }

                            } catch (JSONException e) {
                                progressDialog.setMessage(e.getMessage());
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                                txt_RA.setText("");
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    progressDialog.setMessage(error.getMessage());
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    txt_RA.setText("");
                    progressDialog.dismiss();
                }
            });
            mQueue.add(request);
        }
    }

    public String MD5(String Senha) {
        String code = null;

        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(Senha.getBytes(), 0, Senha.length());
            code = new BigInteger(1, m.digest()).toString(16);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            txt_Senha.setText("");
        }

        return code;
    }
}

