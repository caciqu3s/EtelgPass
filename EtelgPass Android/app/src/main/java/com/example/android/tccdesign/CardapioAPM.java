package com.example.android.tccdesign;

public class CardapioAPM {
    private static String[] Dia = new String[5];
    private static String[] pBasico = new String[5];
    private static String[] Salada = new String[5];
    private static String[] pPrincipal = new String[5];
    private static String[] Guarnicao = new String[5];
    private static String[] Sobremesa = new String[5];
    private static int contador;

    public String[] getDia() {
        return Dia;
    }

    public void setDia(String[] dia) {
        Dia = dia;
    }

    public String[] getpBasico() {
        return pBasico;
    }

    public void setpBasico(String[] pBasico) {
        this.pBasico = pBasico;
    }

    public String[] getSalada() {
        return Salada;
    }

    public void setSalada(String[] salada) {
        Salada = salada;
    }

    public String[] getpPrincipal() {
        return pPrincipal;
    }

    public void setpPrincipal(String[] pPrincipal) {
        this.pPrincipal = pPrincipal;
    }

    public String[] getGuarnicao() {
        return Guarnicao;
    }

    public void setGuarnicao(String[] guarnicao) {
        Guarnicao = guarnicao;
    }

    public String[] getSobremesa() {
        return Sobremesa;
    }

    public void setSobremesa(String[] sobremesa) {
        Sobremesa = sobremesa;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
}
