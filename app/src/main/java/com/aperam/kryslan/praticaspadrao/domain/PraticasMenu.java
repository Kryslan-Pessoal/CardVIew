package com.aperam.kryslan.praticaspadrao.domain;

public class PraticasMenu {

    private int id;
    private String areaEmitente;
    private String areasRelacionadas;
    private String autor;
    private String dataDeVigencia;  //USAR TIPO DATE
    private String Nivel;
    private String processo;
    private String restrito;

    public PraticasMenu(int id, String areaEmitente, String areasRelacionadas, String autor, String dataDeVigencia, String Nivel, String processo, String restrito){
        this.id = id;
        this.areaEmitente = areaEmitente;
        this.areasRelacionadas = areasRelacionadas;
        this.autor = autor;
        this.dataDeVigencia = dataDeVigencia;
        this.Nivel = Nivel;
        this.processo = processo;
        this.restrito = restrito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAreaEmitente() {
        return areaEmitente;
    }

    public void setAreaEmitente(String areaEmitente) {
        this.areaEmitente = areaEmitente;
    }

    public String getAreasRelacionadas() {
        return areasRelacionadas;
    }

    public void setAreasRelacionadas(String areasRelacionadas) {
        this.areasRelacionadas = areasRelacionadas;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDataDeVigencia() {
        return dataDeVigencia;
    }

    public void setDataDeVigencia(String dataDeVigencia) {
        this.dataDeVigencia = dataDeVigencia;
    }

    public String getNivel() {
        return Nivel;
    }

    public void setNivel(String nivel) {
        Nivel = nivel;
    }

    public String getProcesso() {
        return processo;
    }

    public void setProcesso(String processo) {
        this.processo = processo;
    }

    public String getRestrito() {
        return restrito;
    }

    public void setRestrito(String restrito) {
        this.restrito = restrito;
    }



}
