package com.aperam.kryslan.praticaspadrao.domain;

public class PraticasCards {
    private String titulo;
    private String numeroSetor;
    private String areaEmitente;
    private String autor;
    private String processo;
    private String urlImagem;
    private String urlDocumento;
    private int numeroId;


    public PraticasCards(String titulo, String numeroSetor, String areaEmitente, String autor, String processo, String urlImagem, String urlDocumento, int numeroId){
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
    public PraticasCards(Parcel parcel){  //Só está escrevendo os objetos.
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
