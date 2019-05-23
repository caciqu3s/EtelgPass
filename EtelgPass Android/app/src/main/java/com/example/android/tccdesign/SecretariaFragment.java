package com.example.android.tccdesign;


import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.DOWNLOAD_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecretariaFragment extends Fragment {

    TextView txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9, txt10;
    Classe classe = new Classe();
    boolean UpdateMobile;
    ProgressDialog progressDialog;
    DownloadManager downloadManager;
    Intent abrir;

    public SecretariaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View viewroot = inflater.inflate(R.layout.fragment_secretaria, container, false);
        UpdateMobile = false;

        txt1 = (TextView) viewroot.findViewById(R.id.txt1);
        txt2 = (TextView) viewroot.findViewById(R.id.txt2);
        txt3 = (TextView) viewroot.findViewById(R.id.txt3);
        txt4 = (TextView) viewroot.findViewById(R.id.txt4);
        txt5 = (TextView) viewroot.findViewById(R.id.txt5);
        txt6 = (TextView) viewroot.findViewById(R.id.txt6);
        txt7 = (TextView) viewroot.findViewById(R.id.txt7);
        txt8 = (TextView) viewroot.findViewById(R.id.txt8);
        txt9 = (TextView) viewroot.findViewById(R.id.txt9);
        txt10 = (TextView) viewroot.findViewById(R.id.txt10);

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classe.openURL("http://www.etelg.com.br/secretaria/FICHA_DE_REGISTRO_modelo.doc", abrir, getContext());

            }
        });

        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classe.openURL("http://www.etelg.com.br/secretaria/Aproveitamento_de_Estudos.xls", abrir, getContext());
            }
        });

        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classe.openURL("http://www.etelg.com.br/secretaria/Requerimento_de_Trancamento_de_Matr%C3%ADcula.doc", abrir, getContext());
            }
        });

        txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classe.openURL("http://www.etelg.com.br/secretaria/Requerimento_Educa%C3%A7%C3%A3o_F%C3%ADsica.doc", abrir, getContext());
            }
        });

        txt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classe.openURL("http://www.etelg.com.br/secretaria/Requerimento_Transfer%C3%AAncia_de_Per%C3%ADodo_com_timbre_novo.doc", abrir, getContext());
            }
        });

        txt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classe.openURL("http://www.etelg.com.br/secretaria/doc38_req_certifi_modulo.doc", abrir, getContext());
            }
        });

        txt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classe.openURL("http://www.etelg.com.br/secretaria/doc64_requerimento_transf_de_escola.doc", abrir, getContext());
            }
        });

        txt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classe.openURL("http://www.etelg.com.br/secretaria/Doc_36_Requerimento_de__Reconsidera%C3%A7%C3%A3o.doc", abrir, getContext());
            }
        });

        txt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classe.openURL("http://www.etelg.com.br/secretaria/Doc_32_Requerimento_de__Reclassifica%C3%A7%C3%A3o.doc", abrir, getContext());
            }
        });

        txt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classe.openURL("http://www.etelg.com.br/secretaria/transferencia.doc", abrir, getContext());
            }
        });


        return viewroot;
    }
}
