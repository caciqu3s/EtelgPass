package com.example.android.tccdesign;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CadActivity extends AppCompatActivity {
    EditText txt_RM;
    Button btn_verifica;
    RequestQueue mQueue;
    Intent intentETEC;
    Intent intentNSA;
    Intent intentLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad);

        mQueue = Volley.newRequestQueue(getApplicationContext());

        intentETEC = new Intent(getApplicationContext(), CadEteActivity.class);
        intentNSA = new Intent(getApplicationContext(), CadNsaActivity.class);
        intentLogin = new Intent(getApplicationContext(), LoginActivity.class);

        txt_RM = (EditText) findViewById(R.id.editText_RM);
        btn_verifica = (Button) findViewById(R.id.buttonVerifica);

        btn_verifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerificaLogin(txt_RM.getText().toString());
            }
        });
    }

    public void VerificaLogin(final String RM)
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logando...");
        progressDialog.show();
        String url = "https://etelg-pass.000webhostapp.com/API-NOTAS/API-ETE/login.php?rm=" + RM;

        if(isOnline(getApplicationContext()) == false)
        {
            progressDialog.dismiss();
            Toast.makeText(this, "Sem conexão com a internet. Tente novamente", Toast.LENGTH_LONG).show();
        }

        else
        {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                JSONObject jsonObject = response.getJSONObject("Aluno");

                                String nome = jsonObject.getString("Nome");
                                String tipo = jsonObject.getString("tipo");
                                String senha = jsonObject.getString("senha");

                                if(senha != "null")
                                {
                                    Toast.makeText(getApplicationContext(), "Esse RM já possui cadastro", Toast.LENGTH_LONG).show();
                                }

                                else if(tipo.equals("ETEC"))
                                {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("RM", RM);
                                    bundle.putString("Nome", nome);
                                    intentETEC.putExtras(bundle);
                                    startActivity(intentETEC);
                                }

                                else if(tipo.equals("NSA"))
                                {
                                    Toast.makeText(getApplicationContext(), "O aplicativo ainda não possui aos alunos cadastrados ao NSA. Sinto muito.", Toast.LENGTH_LONG).show();
                                    txt_RM.setText("");
                               	/*
                               		Bundle bundle = new Bundle();
                                    bundle.putString("RM", RM);
                                    bundle.putString("Nome", nome);
                                    intentNSA.putExtras(bundle);
                               		startActivity(intentNSA);
                               		*/
                                }

                                else
                                {
                                    Toast.makeText(getApplicationContext(), "RM / RA incorreto, tente novamente", Toast.LENGTH_LONG).show();
                                    txt_RM.setText("");
                                }

                            }

                            catch (JSONException e) {
                                progressDialog.setMessage(e.getMessage());
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                                txt_RM.setText("");
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    progressDialog.setMessage(error.getMessage());
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    txt_RM.setText("");
                    progressDialog.dismiss();
                }
            });
            mQueue.add(request);
        }
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected())
            return true;
        else
            return false;
    }
}
