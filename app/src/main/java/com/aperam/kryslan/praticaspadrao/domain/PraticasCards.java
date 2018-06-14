package com.aperam.kryslan.praticaspadrao.domain;

public class PraticasCards {
    private String numero;
    private String areaEmitente;
    private String titulo;
    private String autor;
    private String processo;
    private String urlImagem;
    private String urlDocumento;
    private int numeroId;


    public PraticasCards(String numero, String areaEmitente, String titulo, String autor, String processo, String urlImagem, String urlDocumento, int numeroId){
        this.numero = numero;
        this.areaEmitente = areaEmitente;
        this.titulo = titulo;
        this.autor = autor;
        this.processo = processo;
        this.urlImagem = urlImagem;
        this.urlDocumento = urlDocumento;
        this.numeroId = numeroId;
    }

    public String getNumero(){
        return numero;
    }
    public void setNumero(String numero){
        this.numero = numero;
    }

    public String getAreaEmitente() {
        return areaEmitente;
    }
    public void setAreaEmitente(String areaEmitente) {
        this.areaEmitente = areaEmitente;
    }

    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){  //Toda vez que eu der 'setTitulo' no código, ele irá pegar a String passada e salvar.
        this.titulo = titulo;
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
    public PraticasCards(Parcel parcel){  //Só está escrevendo os objetos.
        setTitulo(parcel.readString());
        setNumero(parcel.readString());
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
        dest.writeString(getNumero());
        dest.writeString(getAreaEmitente());
        dest.writeString(getAutor());
        dest.writeString(getProcesso());
        dest.writeString(getUrlImagem());
        dest.writeString(getUrlDocumento());
        dest.writeInt(getNumeroId());
    }
    public static final Parcelable.Creator<PraticasCards> CREATOR = new Parcelable.Creator<PraticasCards>(){
        @Override
        public PraticasCards createFromParcel(Parcel source) {  //Chamar esta entidade para pegar os dados.
            return new PraticasCards(source);
        }

        @Override
        public PraticasCards[] newArray(int size) {
            return new PraticasCards[size];
        }
    };*/
}
