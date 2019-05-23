package com.example.android.tccdesign;

public class Aluno {
    private static String RM, Nome, Sala, Curso, Numero, Validade, PorcentagemGeral;

    private static int contador;

    private static String[] Disciplina = new String[30];
    private static String[] Professor = new String[30];
    private static String[] Conceito1 = new String[30];
    private static String[] Conceito2 = new String[30];
    private static String[] Conceito3 = new String[30];
    private static String[] Conceito4 = new String[30];
    private static String[] ConceitoFinal = new String[30];
    private static String[] PorcentagemFaltas = new String[30];

    public static String getRM() {
        return RM;
    }

    public static void setRM(String RM) {
        Aluno.RM = RM;
    }

    public static String getNome() {
        return Nome;
    }

    public static void setNome(String nome) {
        Nome = nome;
    }

    public static String getSala() {
        return Sala;
    }

    public static void setSala(String sala) {
        Sala = sala;
    }

    public static String getCurso() {
        return Curso;
    }

    public static void setCurso(String curso) {
        Curso = curso;
    }

    public static String getNumero() {
        return Numero;
    }

    public static void setNumero(String numero) {
        Numero = numero;
    }

    public static String getValidade() {
        return Validade;
    }

    public static void setValidade(String validade) {
        Validade = validade;
    }

    public static String getPorcentagemGeral() {
        return PorcentagemGeral;
    }

    public static void setPorcentagemGeral(String porcentagemGeral) {
        PorcentagemGeral = porcentagemGeral;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int c) {
        Aluno.contador = c;
    }

    public static String[] getDisciplina() {
        return Disciplina;
    }

    public static void setDisciplina(String[] disciplina) {
        Disciplina = disciplina;
    }

    public static String[] getProfessor() {
        return Professor;
    }

    public static void setProfessor(String[] professor) {
        Professor = professor;
    }

    public static String[] getConceito1() {
        return Conceito1;
    }

    public static void setConceito1(String[] conceito1) {
        Conceito1 = conceito1;
    }

    public static String[] getConceito2() {
        return Conceito2;
    }

    public static void setConceito2(String[] conceito2) {
        Conceito2 = conceito2;
    }

    public static String[] getConceito3() {
        return Conceito3;
    }

    public static void setConceito3(String[] conceito3) {
        Conceito3 = conceito3;
    }

    public static String[] getConceito4() {
        return Conceito4;
    }

    public static void setConceito4(String[] conceito4) {
        Conceito4 = conceito4;
    }

    public static String[] getConceitoFinal() {
        return ConceitoFinal;
    }

    public static void setConceitoFinal(String[] conceitoFinal) {
        ConceitoFinal = conceitoFinal;
    }

    public static String[] getPorcentagemFaltas() {
        return PorcentagemFaltas;
    }

    public static void setPorcentagemFaltas(String[] porcentagemFaltas) {
        PorcentagemFaltas = porcentagemFaltas;
    }
}
