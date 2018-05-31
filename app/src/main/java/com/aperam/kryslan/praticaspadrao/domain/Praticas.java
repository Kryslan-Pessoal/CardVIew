package com.aperam.kryslan.praticaspadrao.domain;

public class Praticas {
    //O que vai aparecer no RecyclerView.
    private String titulo;
    private String numeroSetor;
    private String areaEmitente;
    private String autor;
    private String processo;
    private String urlImagem;
    private String urlDocumento;
    private String numeroId;

    //Usado como parâmetros.
    //private String areaEmitente;

    public Praticas(String titulo, String numeroSetor, String areaEmitente, String autor, String processo, String urlImagem, String urlDocumento, String numeroId){
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

    public String getUrlImagem(){
        return urlImagem;
    }
    public void setUrlImagem(String urlImagem){
        this.urlImagem = urlImagem;
    }

    public String getNumeroId(){
        return numeroId;
    }
    public void setNumeroId(String numeroId){
        this.numeroId = numeroId;
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

    public String getUrlDocumento() {
        return urlDocumento;
    }

    public void setUrlDocumento(String urlDocumento) {
        this.urlDocumento = urlDocumento;
    }
}
