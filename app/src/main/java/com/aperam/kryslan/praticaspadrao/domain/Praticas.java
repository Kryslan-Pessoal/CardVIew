package com.aperam.kryslan.praticaspadrao.domain;

import android.os.Parcel;

public class Praticas{
    //O que vai aparecer no RecyclerView.
    private String titulo;
    private String numeroSetor;
    private String areaEmitente;
    private String autor;
    private String processo;
    private String urlImagem;
    private String urlDocumento;
    private int numeroId;

    //Usado como parâmetros.
    //private String areaEmitente;

    public Praticas(String titulo, String numeroSetor, String areaEmitente, String autor, String processo, String urlImagem, String urlDocumento, int numeroId){
        this.titulo = titulo;
        this.numeroSetor = numeroSetor;
        this.areaEmitente = areaEmitente;
        this.autor = autor;
        this.processo = processo;
        this.urlImagem = urlImagem;
        this.urlDocumento = urlDocumento;
        this.numeroId = numeroId;
    }

    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){  //Toda vez que eu der 'setTitulo' no código, ele irá pegar a String passada e salvar.
        this.titulo = titulo;
    }

    public String getNumeroSetor(){
        return numeroSetor;
    }
    public void setNumeroSetor(String numeroSetor){
        this.numeroSetor = numeroSetor;
    }

    public String getAreaEmitente() {
        return areaEmitente;
    }
    public void setAreaEmitente(String areaEmitente) {
        this.areaEmitente = areaEmitente;
    }

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getProcesso() {
        return processo;
    }
    public void setProcesso(String processo) {
        this.processo = processo;
    }

    public String getUrlImagem(){
        return urlImagem;
    }
    public void setUrlImagem(String urlImagem){
        this.urlImagem = urlImagem;
    }

    public String getUrlDocumento() {
        return urlDocumento;
    }
    public void setUrlDocumento(String urlDocumento) {
        this.urlDocumento = urlDocumento;
    }

    public int getNumeroId(){
        return numeroId;
    }
    public void setNumeroId(int numeroId){
        this.numeroId = numeroId;
    }

    
    /*@Override
    public int describeContents() {
        return 0;
    }

    //PARCELABLE
    public Praticas(Parcel parcel){  //Só está escrevendo os objetos.
        setTitulo(parcel.readString());
        setNumeroSetor(parcel.readString());
        setAreaEmitente(parcel.readString());
        setAutor(parcel.readString());
        setProcesso(parcel.readString());
        setUrlImagem(parcel.readString());
        setUrlDocumento(parcel.readString());
        setNumeroId(parcel.readInt());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {  //Só está guardando os objetos.
        dest.writeString(getTitulo());
        dest.writeString(getNumeroSetor());
        dest.writeString(getAreaEmitente());
        dest.writeString(getAutor());
        dest.writeString(getProcesso());
        dest.writeString(getUrlImagem());
        dest.writeString(getUrlDocumento());
        dest.writeInt(getNumeroId());
    }
    public static final Parcelable.Creator<Praticas> CREATOR = new Parcelable.Creator<Praticas>(){
        @Override
        public Praticas createFromParcel(Parcel source) {  //Chamar esta entidade para pegar os dados.
            return new Praticas(source);
        }

        @Override
        public Praticas[] newArray(int size) {
            return new Praticas[size];
        }
    };*/
}
