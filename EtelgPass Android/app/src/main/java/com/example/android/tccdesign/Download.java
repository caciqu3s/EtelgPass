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

public class Download {
    public static final String NOME_PREFERENCE = "INFORMACOES_LOGIN_AUTOMATICO";
    public static final String Versao_app = "1.1.1";
    private String url, nome_arquivo, titulo_download, titulo, mensagem;

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

    public void PerguntarDownload(final String url, final String nome_arquivo, final String titulo_download, final String titulo, final String mensagem, final boolean UpdateMobile, final Context context,
                                  final ProgressDialog progressDialog, final DownloadManager downloadManager) {
        // Da valor as variaveis para serem usados em outros lugares do código
        setUrl(url);
        setNome_arquivo(nome_arquivo);
        setTitulo_download(titulo_download);
        setTitulo(titulo);
        setMensagem(mensagem);

        //Criação do AlertDialog
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
                        // Caso tocar em "Sim"
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
                        // Caso tocar em "Não"
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
        //Criação do AlertDialog
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
                        //Caso tocar em "Sim"
                        iniciarDownload(url, nome_arquivo, titulo_download, titulo, mensagem, UpdateMobile, context, progressDialog, downloadManager);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Caso tocar em "Não"
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
                        "EtelgPass.apk"));
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
            // Caso Chrome não estiver instalado, o usuário poderá escolher qual aplicativo usar para abrir o navegador
            intent.setPackage(null);
            context.startActivity(intent);
        }
    }
}
