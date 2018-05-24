package com.aperam.kryslan.praticaspadrao.domain;

public class Praticas {
    //O que vai aparecer no RecyclerView.
    private String nome;
    private String numero;
    private String urlImagem;
    private String link;

    //Usado como parâmetros.
    //private String areaEmitente;

    public Praticas(String nome, String numero, String urlImagem, String link){
        this.nome = nome;
        this.numero = numero;
        this.urlImagem = urlImagem;
        this.link = link;
    }

    public String getNome(){
        return nome;
    }
    public void setNome(String nome){  //Toda vez que eu der 'setNome' no código, ele irá pegar a String passada e salvar.
        this.nome = nome;
    }

    public String getNumero(){
        return numero;
    }
    public void setNumero(String numero){
        this.numero = numero;
    }

    public String getUrlImagem(){
        return urlImagem;
    }
    public void setUrlImagem(String urlImagem){
        this.urlImagem = urlImagem;
    }

    public String getLink(){
        return link;
    }
    public void setLink(String link){
        this.link = link;
    }
}
