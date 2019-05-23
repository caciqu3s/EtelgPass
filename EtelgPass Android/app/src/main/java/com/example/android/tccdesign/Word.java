package com.example.android.tccdesign;

public class Word {
    private String mMateria;
    private String mNota1;
    private String mNota2;
    private String mNota3;
    private String mNota4;
    private String mFalta;
    private String mDia;
    private String mAula1, mAula2, mAula3, mAula4, mAula5, mAula6, mAula7, mAula8, mAula9;
    private String mProf1, mProf2, mProf3, mProf4, mProf5, mProf6, mProf7, mProf8, mProf9;
    private String mSala1, mSala2, mSala3, mSala4, mSala5, mSala6, mSala7, mSala8, mSala9;
    private String mDia_semana,mSobremesa,mPratobasico1,mPratobasico2,mPratobasicoAPM,mSalada,mSaladaAPM,mGuarnicao,mGuarnicaoAPM,mPratoprincipal,mPratoprincipalAPM;

    public Word(String materia, String nota1, String nota2, String nota3, String nota4, String falta) {
        mMateria = materia;
        mNota1 = nota1;
        mNota2 = nota2;
        mNota3 = nota3;
        mNota4 = nota4;
        mFalta = falta;
    }
    public Word(String dia, String aula1, String prof1, String sala1, String aula2, String prof2, String sala2,
    String aula3, String prof3, String sala3, String aula4, String prof4, String sala4, String aula5, String prof5, String sala5,
    String aula6, String prof6, String sala6, String aula7, String prof7, String sala7, String aula8, String prof8, String sala8,
                String aula9, String prof9, String sala9){
        mDia = dia;
        mAula1 = aula1; mAula2 = aula2; mAula3 = aula3; mAula4 = aula4; mAula5 = aula5; mAula6 = aula6; mAula7 = aula7; mAula8 = aula8; mAula9 = aula9;
        mProf1 = prof1; mProf2 = prof2; mProf3 = prof3; mProf4 = prof4; mProf5 = prof5; mProf6 = prof6; mProf7 = prof7; mProf8 = prof8; mProf9 = prof9;
        mSala1 = sala1; mSala2 = sala2; mSala3 = sala3; mSala4 = sala4; mSala5 = sala5; mSala6 = sala6; mSala7 = sala7; mSala8 = sala8; mSala9 = sala9;
    }
    public Word(String dia_semana,String pratobasico1, String pratobasico2, String pratobasicoAPM, String pratoprincipal, String pratoprincipalAPM,
                String guarnicao, String guarnicaoAPM, String salada, String saladaAPM, String sobremesa){
        mDia_semana = dia_semana;mPratobasico1 = pratobasico1;mPratobasico2 = pratobasico2;mPratobasicoAPM = pratobasicoAPM;mPratoprincipal = pratoprincipal;
        mPratoprincipalAPM = pratoprincipalAPM;mGuarnicao = guarnicao;mGuarnicaoAPM = guarnicaoAPM;mSalada = salada;mSaladaAPM = saladaAPM;mSobremesa = sobremesa;
    }

    public String getMateria() {
        return mMateria;
    }

    public String getDia_semana(){return mDia_semana;}

    public boolean hasMateria() {
        return mMateria != null;
    }

    public boolean hasDia_semana(){return  mDia_semana != null;}

    public String getNota1() {
        return mNota1;
    }

    public String getNota2() {
        return mNota2;
    }

    public String getNota3() {
        return mNota3;
    }

    public String getNota4() {
        return mNota4;
    }

    public String getFalta() {
        return mFalta;
    }

    /*public String getDia() {return mDia;}

    public String getAula1() {return mAula1;}   public String getProf1() {return mProf1;}   public String getSala1() {return mSala1;}

    public String getAula2() {return mAula2;}   public String getProf2() {return mProf2;}   public String getSala2() {return mSala2;}

    public String getAula3() {return mAula3;}   public String getProf3() {return mProf3;}   public String getSala3() {return mSala3;}

    public String getAula4() {return mAula4;}   public String getProf4() {return mProf4;}   public String getSala4() {return mSala4;}

    public String getAula5() {return mAula5;}   public String getProf5() {return mProf5;}   public String getSala5() {return mSala5;}

    public String getAula6() {return mAula6;}   public String getProf6() {return mProf6;}   public String getSala6() {return mSala6;}

    public String getAula7() {return mAula7;}   public String getProf7() {return mProf7;}   public String getSala7() {return mSala7;}

    public String getAula8() {return mAula8;}   public String getProf8() {return mProf8;}   public String getSala8() {return mSala8;}

    public String getAula9() {return mAula9;}   public String getProf9() {return mProf9;}   public String getSala9() {return mSala9;}*/

    public String getPratobasico1(){return mPratobasico1;}  public String getPratobasico2(){return mPratobasico2;} public String getPratobasicoAPM(){return mPratobasicoAPM;}
    public String getPratoprincipal(){return mPratoprincipal;} public String getPratoprincipalAPM(){return mPratoprincipalAPM;}  public String getGuarnicao(){return mGuarnicao;}
    public String getGuarnicaoAPM(){return mGuarnicaoAPM;} public String getSalada(){return mSalada;} public String getSaladaAPM(){return mSaladaAPM;}   public String getSobremesa(){return mSobremesa;}
}