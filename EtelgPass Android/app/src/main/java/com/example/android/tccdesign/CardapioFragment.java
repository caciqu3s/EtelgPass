package com.example.android.tccdesign;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CardapioFragment extends Fragment {

    CardapioAPM apm = new CardapioAPM();
    CardapioGov gov = new CardapioGov();
    String dia_semana;

    public CardapioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cardapio, container, false);

        final ArrayList<Word> words = new ArrayList<Word>();

        for(int i = 0; i < 5; i++)
        {
            if(i == 0)
            {
                dia_semana = "Segunda-Feira";
            }

            else if(i == 1)
            {
                dia_semana = "Terça-Feira";
            }

            else if(i == 2)
            {
                dia_semana = "Quarta-Feira";
            }

            else if(i == 3)
            {
                dia_semana = "Quinta-Feira";
            }

            else if(i == 4)
            {
                dia_semana = "Sexta-Feira";
            }

            words.add(new Word(dia_semana, gov.getOp1()[i], gov.getOp2()[i], apm.getpBasico()[i], gov.getOp3()[i], apm.getpPrincipal()[i], gov.getOp4()[i],
                    apm.getGuarnicao()[i], gov.getOp5()[i], apm.getSalada()[i], apm.getSobremesa()[i]));
        }


        WordAdapter adapter = new WordAdapter(getActivity(), words);
        ListView cardapio = rootView.findViewById(R.id.cardapio);
        cardapio.setAdapter(adapter);
        return rootView;
    }

}
