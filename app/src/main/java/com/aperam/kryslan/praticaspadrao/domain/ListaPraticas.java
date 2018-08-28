package com.aperam.kryslan.praticaspadrao.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class ListaPraticas implements Parcelable{
    private int id;
    private String nome;
    private String numero;
    private String linkDocumento;
    private String faseDocumento;
    private String treinamento;
    private String revisorTecnico;
    private String revisorSstMa;
    private String aprovador;
    private String dataVersao;
    private int areaEmitente;
    private int areasRelacionadas;
    private int autor;
    private int dataPratica;
    private int nivel;
    private int processo;

    public ListaPraticas(){}

    public ListaPraticas(int id, String nome, String numero, String linkDocumento, String faseDocumento, String treinamento,
                         String revisorTecnico, String revisorSstMa, String aprovador, String dataVersao,
                         int areaEmitente, int areasRelacionadas, int autor, int dataPratica, int nivel, int processo){

        this.id = id;
        this.nome = nome;
        this.numero = numero;
        this.linkDocumento = linkDocumento;
        this.faseDocumento = faseDocumento;
        this.treinamento = treinamento;
        this.revisorTecnico = revisorTecnico;
        this.revisorSstMa = revisorSstMa;
        this.aprovador = aprovador;
        this.dataVersao = dataVersao;
        this.areaEmitente = areaEmitente;
        this.areasRelacionadas = areasRelacionadas;
        this.autor = autor;
        this.dataPratica = dataPratica;
        this.nivel = nivel;
        this.processo = processo;
    }

    //region GETs/SETs
    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public String getNome(){return nome;}
    public void setNome(String nome){this.nome = nome;}

    public String getNumero(){return numero;}
    public void setNumero(String numero){this.numero = numero;}

    public String getLinkDocumento() {return linkDocumento;}
    public void setLinkDocumento(String linkDocumento) {this.linkDocumento = linkDocumento;}

    public String getFaseDocumento() {return faseDocumento;}
    public void setFaseDocumento(String faseDocumento) {this.faseDocumento = faseDocumento;}

    public String getTreinamento() {return treinamento;}
    public void setTreinamento(String treinamento) {this.treinamento = treinamento;}

    public String getRevisorTecnico() {return revisorTecnico;}
    public void setRevisorTecnico(String revisorTecnico) {this.revisorTecnico = revisorTecnico;}

    public String getRevisorSstMa() {return revisorSstMa;}
    public void setRevisorSstMa(String revisorSstMa) {this.revisorSstMa = revisorSstMa;}

    public String getAprovador() {return aprovador;}
    public void setAprovador(String aprovador) {this.aprovador = aprovador;}

    public String getDataVersao() {return dataVersao;}
    public void setDataVersao(String dataVersao) {this.dataVersao = dataVersao;}

    public int getAreaEmitente() {return areaEmitente;}
    public void setAreaEmitente(int areaEmitente) {this.areaEmitente = areaEmitente;}

    public int getAreasRelacionadas() {return areasRelacionadas;}
    public void setAreasRelacionadas(int areasRelacionadas) {this.areasRelacionadas = areasRelacionadas;}

    public int getAutor() {return autor;}
    public void setAutor(int autor) {this.autor = autor;}

    public int getDataPratica() {return dataPratica;}
    public void setDataPratica(int dataPratica) {this.dataPratica = dataPratica;}

    public int getNivel() {return nivel;}
    public void setNivel(int nivel) {this.nivel = nivel;}

    public int getProcesso() {return processo;}
    public void setProcesso(int processo) {this.processo = processo;}
    //endregion

    //PARCELABLE
    @Override
    public int describeContents() {
        return 0;
    }

    public ListaPraticas(Parcel parcel){  //Equivalente ao "Set" normal mas como parcelable.
        setId(parcel.readInt());
        setNome(parcel.readString());
        setNumero(parcel.readString());
        setLinkDocumento(parcel.readString());
        setFaseDocumento(parcel.readString());
        setTreinamento(parcel.readString());
        setRevisorTecnico(parcel.readString());
        setRevisorSstMa(parcel.readString());
        setAprovador(parcel.readString());
        setDataVersao(parcel.readString());
        setAreaEmitente(parcel.readInt());
        setAreasRelacionadas(parcel.readInt());
        setAutor(parcel.readInt());
        setNivel(parcel.readInt());
        setProcesso(parcel.readInt());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {  //Equivalente ao "Get" normal mas como parcelable.
        dest.writeInt(getId());
        dest.writeString(getNome());
        dest.writeString(getNumero());
        dest.writeString(getLinkDocumento());
        dest.writeString(getFaseDocumento());
        dest.writeString(getTreinamento());
        dest.writeString(getRevisorTecnico());
        dest.writeString(getRevisorSstMa());
        dest.writeString(getAprovador());
        dest.writeString(getDataVersao());
        dest.writeInt(getAreaEmitente());
        dest.writeInt(getAreasRelacionadas());
        dest.writeInt(getAutor());
        dest.writeInt(getNivel());
        dest.writeInt(getProcesso());
    }

    public static final Parcelable.Creator<ListaPraticas> CREATOR = new Parcelable.Creator<ListaPraticas>(){
        @Override
        public ListaPraticas createFromParcel(Parcel source) {  //Chamar esta entidade para pegar os dados.
            return new ListaPraticas(source);
        }
        @Override
        public ListaPraticas[] newArray(int size) {
            return new ListaPraticas[size];
        }
    };
}
