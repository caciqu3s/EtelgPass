package com.example.android.tccdesign;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
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

import java.io.File;

public class splash_screen extends AppCompatActivity {

    public String[] Disciplina = new String[20];
    public String[] Professor = new String[20];
    public String[] Conceito1 = new String[20];
    public String[] Conceito2 = new String[20];
    public String[] Conceito3 = new String[20];
    public String[] Conceito4 = new String[20];
    public String[] ConceitoFinal = new String[20];
    public String[] PorcentagemFaltas = new String[20];
    public String[] DiaGov = new String[5];
    public String[] Op1 = new String[5];
    public String[] Op2 = new String[5];
    public String[] Op3 = new String[5];
    public String[] Op4 = new String[5];
    public String[] Op5 = new String[5];
    public String[] DiaApm = new String[5];
    public String[] pBasico = new String[5];
    public String[] Salada = new String[5];
    public String[] pPrincipal = new String[5];
    public String[] Guarnicao = new String[5];
    public String[] Sobremesa = new String[5];
    Intent intent1;
    Intent intent;
    Intent intent_classe;
    Classe classe = new Classe();
    Aluno aluno = new Aluno();
    CardapioGov gov = new CardapioGov();
    CardapioAPM apm = new CardapioAPM();
    String rm, nome, sala, curso, validade, numero;
    boolean UpdateMobile;
    DownloadManager downloadManager;
    AlertDialog.Builder builder;
    ProgressDialog progressDialog;
    final BroadcastReceiver onComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {
                progressDialog.dismiss();
                openFile();
            }
        }
    };
    int i;
    private RequestQueue mQueue;

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences prefs = getSharedPreferences(Classe.NOME_PREFERENCE, MODE_PRIVATE);
        rm = prefs.getString("RM", "");
        UpdateMobile = prefs.getBoolean("UpdateMobile", true);


        progressDialog = new ProgressDialog(this);
        int REQUEST_CODE = 1;

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, REQUEST_CODE);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.INSTALL_PACKAGES
        }, REQUEST_CODE);

        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        // registramos nosso BroadcastReceiver
        registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        mQueue = Volley.newRequestQueue(this);

        intent1 = new Intent(getApplicationContext(), LoginActivity.class);
        intent = new Intent(getApplicationContext(), MainActivity.class);
        intent_classe = new Intent(Intent.ACTION_VIEW);

        VerificaLogin();
    }

    public void PerguntarAtualizacao() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(splash_screen.this);
        } else {
            builder = new AlertDialog.Builder(splash_screen.this);
        }
        builder.setTitle("Atualização Disponível")
                .setMessage("Deseja Atualizar?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        classe.openURL("https://drive.google.com/uc?authuser=0&id=1oSn9-30J2yzueNj85W95UjgbG292P2dK&export=download", intent_classe, getApplicationContext());
                        finishAffinity();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                        Toast.makeText(getApplicationContext(), "Atualização cancelada", Toast.LENGTH_LONG).show();
                    }
                }).setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void openFile() {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(path, "app_debug.apk");
        Uri uri = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", file);


        Intent install = new Intent(Intent.ACTION_VIEW);
        install.setDataAndType(uri, "application/vnd.android.package-archive");
        install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(install);
        finishAffinity();
    }

    public void iniciarDownload() {
        Uri uri = Uri.parse("http://etelgpass.ga/APK/app-debug.apk");
        progressDialog.setMessage("Baixando...");
        progressDialog.show();

        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .mkdirs();

        downloadManager.enqueue(new DownloadManager.Request(uri)
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle("EtelgPass")
                .setDescription("Realizando o download.")
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                        "app_debug.apk"));
    }

    public void Atualizar() {
        if (Classe.getNetworkClass(getApplicationContext()) == "2G" || Classe.getNetworkClass(getApplicationContext()) == "3G" || Classe.getNetworkClass(getApplicationContext()) == "4G") {
            if (UpdateMobile == true) {
                iniciarDownload();
            } else {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(splash_screen.this);
                } else {
                    builder = new AlertDialog.Builder(splash_screen.this);
                }
                builder.setTitle("Atualização com dados móveis desabilitada")
                        .setMessage("Deseja continuar?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                iniciarDownload();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                                Toast.makeText(getApplicationContext(), "Atualização cancelada", Toast.LENGTH_LONG).show();
                            }
                        }).setCancelable(false)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        } else {
            iniciarDownload();
        }
    }

    public void VerificaLogin() {

        if (rm != null) {
            Logar(rm);

        } else {
            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
            finish();
        }

    }

    public void Logar(final String RM) {
        String url = "https://etelg-pass.000webhostapp.com/API-NOTAS/API-ETE/resultado.php?rm=" + RM;

        if (isOnline(getApplicationContext()) == false) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
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
                                String Versao = jsonObject.getString("Versão");
                                String PorcentagemGeral = jsonObject.getString("Porcentagem Geral");

                                aluno.setRM(RM);
                                aluno.setNome(Nome);
                                aluno.setSala(Sala);
                                aluno.setCurso(Curso);
                                aluno.setValidade(Validade);
                                aluno.setNumero(Número);
                                aluno.setPorcentagemGeral(PorcentagemGeral);


                                JSONArray jsonArray = response.getJSONArray("Notas");

                                for (i = 0; i < jsonArray.length(); i++) {
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

                                aluno.setDisciplina(Disciplina);
                                aluno.setProfessor(Professor);
                                aluno.setConceito1(Conceito1);
                                aluno.setConceito2(Conceito2);
                                aluno.setConceito3(Conceito3);
                                aluno.setConceito4(Conceito4);
                                aluno.setConceitoFinal(ConceitoFinal);
                                aluno.setPorcentagemFaltas(PorcentagemFaltas);
                                aluno.setContador(i);

                                JSONArray json_gov = response.getJSONArray("Gov");

                                for (i = 0; i < json_gov.length(); i++) {
                                    JSONObject CardapioGov = json_gov.getJSONObject(i);

                                    DiaGov[i] = CardapioGov.getString("Dia");
                                    Op1[i] = CardapioGov.getString("Opção 1");
                                    Op2[i] = CardapioGov.getString("Opção 2");
                                    Op3[i] = CardapioGov.getString("Opção 3");
                                    Op4[i] = CardapioGov.getString("Opção 4");
                                    Op5[i] = CardapioGov.getString("Opção 5");
                                }

                                gov.setDia(DiaGov);
                                gov.setOp1(Op1);
                                gov.setOp2(Op2);
                                gov.setOp3(Op3);
                                gov.setOp4(Op4);
                                gov.setOp5(Op5);
                                gov.setContador(i);

                                JSONArray json_apm = response.getJSONArray("APM");

                                for (i = 0; i < json_apm.length(); i++) {
                                    JSONObject CardapioAPM = json_apm.getJSONObject(i);

                                    DiaApm[i] = CardapioAPM.getString("Dia");
                                    pBasico[i] = CardapioAPM.getString("Prato Básico");
                                    Salada[i] = CardapioAPM.getString("Salada");
                                    pPrincipal[i] = CardapioAPM.getString("Prato Principal");
                                    Guarnicao[i] = CardapioAPM.getString("Guarnição");
                                    Sobremesa[i] = CardapioAPM.getString("Sobremesa");
                                }

                                apm.setDia(DiaApm);
                                apm.setpBasico(pBasico);
                                apm.setSalada(Salada);
                                apm.setpPrincipal(pPrincipal);
                                apm.setGuarnicao(Guarnicao);
                                apm.setSobremesa(Sobremesa);
                                apm.setContador(i);

                                if (Nome != "") {
                                    SharedPreferences.Editor editor = getSharedPreferences(Classe.NOME_PREFERENCE, MODE_PRIVATE).edit();

                                    editor.putString("RM", RM);
                                    editor.commit();

                                    if (!Versao.equals(Classe.Versao_app)) {
                                        PerguntarAtualizacao();
                                    } else {
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                } else {
                                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent1);
                                    finish();
                                }

                            } catch (JSONException e) {
                                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent1);
                                finish();
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent1);
                    finish();
                }
            });
            mQueue.add(request);
        }
    }

    public void onDestroy() {

        super.onDestroy();
        unregisterReceiver(onComplete);
    }

}
