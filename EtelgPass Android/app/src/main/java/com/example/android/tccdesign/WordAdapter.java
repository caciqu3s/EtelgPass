package com.example.android.tccdesign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word>{
        public WordAdapter(Context context, ArrayList<Word> words) {
            super(context,0,words);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View listItemView = convertView;
            Word currentWord = getItem(position);

            if (listItemView == null) {

                if (currentWord.hasMateria()) {
                    listItemView = LayoutInflater.from(getContext()).inflate(
                            R.layout.list_nota, parent, false);
                }
                else {
                    listItemView = LayoutInflater.from(getContext()).inflate(
                            R.layout.list_cardapio, parent, false);
                }
            }

            TextView materiaTextView = listItemView.findViewById(R.id.text_materia);
            TextView nota1TextView = listItemView.findViewById(R.id.text_nota1);
            TextView nota2TextView = listItemView.findViewById(R.id.text_nota2);
            TextView nota3TextView = listItemView.findViewById(R.id.text_nota3);
            TextView nota4TextView = listItemView.findViewById(R.id.text_nota4);
            TextView faltaTextView = listItemView.findViewById(R.id.text_Falta);
            /*TextView diaTextView = listItemView.findViewById(R.id.text_dia);
            TextView aula1TextView = listItemView.findViewById(R.id.text_aula1);
            TextView aula2TextView = listItemView.findViewById(R.id.text_aula2);
            TextView aula3TextView = listItemView.findViewById(R.id.text_aula3);
            TextView aula4TextView = listItemView.findViewById(R.id.text_aula4);
            TextView aula5TextView = listItemView.findViewById(R.id.text_aula5);
            TextView aula6TextView = listItemView.findViewById(R.id.text_aula6);
            TextView aula7TextView = listItemView.findViewById(R.id.text_aula7);
            TextView aula8TextView = listItemView.findViewById(R.id.text_aula8);
            TextView aula9TextView = listItemView.findViewById(R.id.text_aula9);
            TextView prof1TextView = listItemView.findViewById(R.id.text_prof1);
            TextView prof2TextView = listItemView.findViewById(R.id.text_prof2);
            TextView prof3TextView = listItemView.findViewById(R.id.text_prof3);
            TextView prof4TextView = listItemView.findViewById(R.id.text_prof4);
            TextView prof5TextView = listItemView.findViewById(R.id.text_prof5);
            TextView prof6TextView = listItemView.findViewById(R.id.text_prof6);
            TextView prof7TextView = listItemView.findViewById(R.id.text_prof7);
            TextView prof8TextView = listItemView.findViewById(R.id.text_prof8);
            TextView prof9TextView = listItemView.findViewById(R.id.text_prof9);
            TextView sala1TextView = listItemView.findViewById(R.id.text_sala1);
            TextView sala2TextView = listItemView.findViewById(R.id.text_sala2);
            TextView sala3TextView = listItemView.findViewById(R.id.text_sala3);
            TextView sala4TextView = listItemView.findViewById(R.id.text_sala4);
            TextView sala5TextView = listItemView.findViewById(R.id.text_sala5);
            TextView sala6TextView = listItemView.findViewById(R.id.text_sala6);
            TextView sala7TextView = listItemView.findViewById(R.id.text_sala7);
            TextView sala8TextView = listItemView.findViewById(R.id.text_sala8);
            TextView sala9TextView = listItemView.findViewById(R.id.text_sala9);*/
            TextView dia_semana = listItemView.findViewById(R.id.text_dia_semana);
            TextView pratobasico1 = listItemView.findViewById(R.id.text_arroz);
            TextView pratobasico2 = listItemView.findViewById(R.id.text_feijao);
            TextView pratobasicoAPM = listItemView.findViewById(R.id.text_BasicoAPM);
            TextView pratoprincipal = listItemView.findViewById(R.id.text_principal);
            TextView pratoprincipalAPM = listItemView.findViewById(R.id.text_pratoprincipalAPM);
            TextView guarnicao = listItemView.findViewById(R.id.text_guarnicao);
            TextView guarnicaoAPM = listItemView.findViewById(R.id.text_guarnicaoAPM);
            TextView salada = listItemView.findViewById(R.id.text_saladasobrmesa);
            TextView saladaAPM = listItemView.findViewById(R.id.text_saladaAPM);
            TextView sobremesaAPM = listItemView.findViewById(R.id.text_sobremesaAPM);

            if(currentWord.hasMateria()){
                materiaTextView.setText(currentWord.getMateria());
                nota1TextView.setText(currentWord.getNota1());
                nota2TextView.setText(currentWord.getNota2());
                nota3TextView.setText(currentWord.getNota3());
                nota4TextView.setText(currentWord.getNota4());
                faltaTextView.setText(currentWord.getFalta());
            }
            else {
                /*diaTextView.setText(currentWord.getDia());
                aula1TextView.setText(currentWord.getAula1());
                aula2TextView.setText(currentWord.getAula2());
                aula3TextView.setText(currentWord.getAula3());
                aula4TextView.setText(currentWord.getAula4());
                aula5TextView.setText(currentWord.getAula5());
                aula6TextView.setText(currentWord.getAula6());
                aula7TextView.setText(currentWord.getAula7());
                aula8TextView.setText(currentWord.getAula8());
                aula9TextView.setText(currentWord.getAula9());
                prof1TextView.setText(currentWord.getProf1());
                prof2TextView.setText(currentWord.getProf2());
                prof3TextView.setText(currentWord.getProf3());
                prof4TextView.setText(currentWord.getProf4());
                prof5TextView.setText(currentWord.getProf5());
                prof6TextView.setText(currentWord.getProf6());
                prof7TextView.setText(currentWord.getProf7());
                prof8TextView.setText(currentWord.getProf8());
                prof9TextView.setText(currentWord.getProf9());
                sala1TextView.setText(currentWord.getSala1());
                sala2TextView.setText(currentWord.getSala2());
                sala3TextView.setText(currentWord.getSala3());
                sala4TextView.setText(currentWord.getSala4());
                sala5TextView.setText(currentWord.getSala5());
                sala6TextView.setText(currentWord.getSala6());
                sala7TextView.setText(currentWord.getSala7());
                sala8TextView.setText(currentWord.getSala8());
                sala9TextView.setText(currentWord.getSala9());*/
                dia_semana.setText(currentWord.getDia_semana());
                pratobasico1.setText(currentWord.getPratobasico1());
                pratobasico2.setText(currentWord.getPratobasico2());
                pratoprincipal.setText(currentWord.getPratoprincipal());
                guarnicao.setText(currentWord.getGuarnicao());
                salada.setText(currentWord.getSalada());
                pratobasicoAPM.setText(currentWord.getPratobasicoAPM());
                saladaAPM.setText(currentWord.getSaladaAPM());
                pratoprincipalAPM.setText(currentWord.getPratoprincipalAPM());
                guarnicaoAPM.setText(currentWord.getGuarnicaoAPM());
                sobremesaAPM.setText(currentWord.getSobremesa());
            }
            return listItemView;
        }
}
