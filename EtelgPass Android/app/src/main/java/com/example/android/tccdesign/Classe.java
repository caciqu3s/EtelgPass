package com.example.android.tccdesign;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;

import java.io.File;

public class Classe {
    public static final String NOME_PREFERENCE = "INFORMACOES_LOGIN_AUTOMATICO";
    public static final String Versao_app = "1.1.1";
    private static String RM, Nome, Sala, Curso, Numero, Validade, PorcentagemGeral;
    private static int c;
    private static String[] Disciplina = new String[30];
    private static String[] Professor = new String[30];
    private static String[] Conceito1 = new String[30];
    private static String[] Conceito2 = new String[30];
    private static String[] Conceito3 = new String[30];
    private static String[] Conceito4 = new String[30];
    private static String[] ConceitoFinal = new String[30];
    private static String[] PorcentagemFaltas = new String[30];
    private String url;
    private String nome_arquivo;
    private String titulo_download;
    private String titulo;
    private String mensagem;

    public static String getNetworkClass(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();

        if (info == null || !info.isConnected())
            return "-"; //sem conexão
        if (info.getType() == ConnectivityManager.TYPE_WIFI)
            return "WIFI";
        if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            int networkType = info.getSubtype();
            switch (networkType) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : troque por 11
                    return "2G";
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : troque por 14
                case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : troque por 12
                case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : troque por 15
                    return "3G";
                case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : troque por 13
                    return "4G";
                default:
                    return "?";
            }
        }
        return "?";
    }

    public static int getC() {
        return c;
    }

    public static void setC(int c) {
        Classe.c = c;
    }

    public static String getPorcentagemGeral() {
        return PorcentagemGeral;
    }

    public static void setPorcentagemGeral(String porcentagemGeral) {
        PorcentagemGeral = porcentagemGeral;
    }

    public void PerguntarDownload(final String url, final String nome_arquivo, final String titulo_download, final String titulo, final String mensagem, final boolean UpdateMobile, final Context context,
                                  final ProgressDialog progressDialog, final DownloadManager downloadManager) {
        setUrl(url);
        setNome_arquivo(nome_arquivo);
        setTitulo_download(titulo_download);
        setTitulo(titulo);
        setMensagem(mensagem);

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle(titulo)
                .setMessage(mensagem)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        if (getNetworkClass(context) == "2G" || getNetworkClass(context) == "3G" || getNetworkClass(context) == "4G") {
                            if (UpdateMobile == true) {
                                iniciarDownload(url, nome_arquivo, titulo_download, titulo, mensagem, UpdateMobile, context, progressDialog, downloadManager);
                            } else {
                                DadosMoveisDesabilitado(url, nome_arquivo, titulo_download, titulo, mensagem, UpdateMobile, context, progressDialog, downloadManager);
                            }
                        } else {
                            iniciarDownload(url, nome_arquivo, titulo_download, titulo, mensagem, UpdateMobile, context, progressDialog, downloadManager);
                        }
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        /*
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        ((Activity)context).finish();
                        */
                    }
                }).setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void DadosMoveisDesabilitado(final String url, final String nome_arquivo, final String titulo_download, final String titulo, final String mensagem, final boolean UpdateMobile, final Context context,
                                        final ProgressDialog progressDialog, final DownloadManager downloadManager) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle("Baixar arquivos com dados móveis desabilitada")
                .setMessage("Deseja continuar?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        iniciarDownload(url, nome_arquivo, titulo_download, titulo, mensagem, UpdateMobile, context, progressDialog, downloadManager);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        /*
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        ((Activity)context).finish();
                        */
                    }
                }).setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void iniciarDownload(final String url, final String nome_arquivo, final String titulo_download, final String titulo, final String mensagem, final boolean UpdateMobile, final Context context,
                                final ProgressDialog progressDialog, final DownloadManager downloadManager) {
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

    public void openFile(final String nome_arquivo, Intent install, Context context, String type) {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(path, nome_arquivo);
        //Uri arquivoURI = FileProvider.getUriForFile(splash_screen.this, splash_screen.this.getApplicationContext().getPackageName() + ".my.package.name.provider", file);

        /*
        String type = "";
        String extension = MimeTypeMap.getFileExtensionFromUrl(file.toString());
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        */

        install.setDataAndType(Uri.fromFile(file), type);
        install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(install);
    }

    public void openURL(final String url, Intent intent, Context context) {
        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setPackage("com.android.chrome");

        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            // Chrome browser presumably not installed so allow user to choose instead
            intent.setPackage(null);
            context.startActivity(intent);
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNome_arquivo() {
        return nome_arquivo;
    }

    public void setNome_arquivo(String nome_arquivo) {
        this.nome_arquivo = nome_arquivo;
    }

    public String getTitulo_download() {
        return titulo_download;
    }

    public void setTitulo_download(String titulo_download) {
        this.titulo_download = titulo_download;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getRM() {
        return RM;
    }

    public void setRM(String RM) {
        Classe.RM = RM;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getSala() {
        return Sala;
    }

    public void setSala(String sala) {
        Sala = sala;
    }

    public String getCurso() {
        return Curso;
    }

    public void setCurso(String curso) {
        Curso = curso;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }

    public String getValidade() {
        return Validade;
    }

    public void setValidade(String validade) {
        Validade = validade;
    }

    public String[] getDisciplina() {
        return Disciplina;
    }

    public void setDisciplina(String[] disciplina) {
        Disciplina = disciplina;
    }

    public String[] getProfessor() {
        return Professor;
    }

    public void setProfessor(String[] professor) {
        Professor = professor;
    }

    public String[] getConceito1() {
        return Conceito1;
    }

    public void setConceito1(String[] conceito1) {
        Conceito1 = conceito1;
    }

    public String[] getConceito2() {
        return Conceito2;
    }

    public void setConceito2(String[] conceito2) {
        Conceito2 = conceito2;
    }

    public String[] getConceito3() {
        return Conceito3;
    }

    public void setConceito3(String[] conceito3) {
        Conceito3 = conceito3;
    }

    public String[] getConceito4() {
        return Conceito4;
    }

    public void setConceito4(String[] conceito4) {
        Conceito4 = conceito4;
    }

    public String[] getConceitoFinal() {
        return ConceitoFinal;
    }

    public void setConceitoFinal(String[] conceitoFinal) {
        ConceitoFinal = conceitoFinal;
    }

    public String[] getPorcentagemFaltas() {
        return PorcentagemFaltas;
    }

    public void setPorcentagemFaltas(String[] porcentagemFaltas) {
        PorcentagemFaltas = porcentagemFaltas;
    }
}
