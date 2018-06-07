package com.aperam.kryslan.praticaspadrao.domain;

public class AreaEmitente {

    private int id;
    private String foto;
    private String areaEmitente;
    private int numero;
    //private String link;

    public AreaEmitente(int id, String foto, String areaEmitente, int numero){
        this.id = id;
        this.foto = foto;
        this.areaEmitente = areaEmitente;
        this.numero = numero;
        //this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getAreaEmitente() {
        return areaEmitente;
    }

    public void setAreaEmitente(String areaEmitente) {
        this.areaEmitente = areaEmitente;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    /*public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }*/
}
