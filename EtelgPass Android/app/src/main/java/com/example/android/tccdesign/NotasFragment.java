package com.example.android.tccdesign;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotasFragment extends Fragment {
    Aluno aluno = new Aluno();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notas, container, false);

        final ArrayList<Word> words = new ArrayList<Word>();
        for (int j = 0; j < aluno.getContador(); j++)
        {
            words.add(new Word(aluno.getDisciplina()[j], aluno.getConceito1()[j], aluno.getConceito2()[j], aluno.getConceito3()[j], aluno.getConceito4()[j], aluno.getPorcentagemFaltas()[j] + "%"));
        }
        /*
        words.add(new Word("Matemática", "R", "MB", "B", "B"));
        words.add(new Word("Português", "R", "I", "B", "B"));
        words.add(new Word("Geografia", "R", "B", "B", "B"));
        words.add(new Word("Física", "R", "R", "B", "B"));
        words.add(new Word("Sociologia", "R", "MB", "B", "R"));
        words.add(new Word("Filosofia", "I", "MB", "B", "B"));
        words.add(new Word("História", "R", "MB", "B", "B"));
        words.add(new Word("Química", "R", "MB", "B", "B"));
        */

        WordAdapter adapter = new WordAdapter(getActivity(), words);
        ListView nota = rootView.findViewById(R.id.notas);
        nota.setAdapter(adapter);
        return rootView;
    }

}
