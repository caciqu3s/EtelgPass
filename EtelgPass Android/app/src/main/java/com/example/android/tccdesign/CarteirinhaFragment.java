package com.example.android.tccdesign;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarteirinhaFragment extends Fragment {

    TextView txt_RM;
    TextView txt_Nome;
    TextView txt_Sala;
    TextView txt_Curso;
    TextView txt_Validade;
    TextView txt_Numero;
    TextView txt_Porcentagem;

    Aluno aluno = new Aluno();

    public CarteirinhaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carteirinha, container, false);

        txt_RM = view.findViewById(R.id.txt_RM);
        txt_Nome = view.findViewById(R.id.txt_Nome);
        txt_Sala = view.findViewById(R.id.txt_Sala);
        txt_Curso = view.findViewById(R.id.txt_Curso);
        txt_Numero = view.findViewById(R.id.txt_Numero);
        txt_Validade = view.findViewById(R.id.txt_Validade);
        txt_Porcentagem = view.findViewById(R.id.txt_porcentagem);

        txt_RM.setText(aluno.getRM());
        txt_Nome.setText(aluno.getNome());
        txt_Sala.setText(aluno.getSala());
        txt_Curso.setText(aluno.getCurso());
        txt_Numero.setText(aluno.getNumero());
        txt_Validade.setText(aluno.getValidade());
        txt_Porcentagem.setText(aluno.getPorcentagemGeral() + "%");

        txt_RM.setAllCaps(true);
        // Inflate the layout for this fragment
        return view;
    }
}
