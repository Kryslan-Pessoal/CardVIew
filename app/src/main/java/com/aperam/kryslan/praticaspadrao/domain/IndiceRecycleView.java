package com.aperam.kryslan.praticaspadrao.domain;

public class IndiceRecycleView {

    private int id;
    private String fotoUrl;
    private String textoPrincipal;
    //private String link;

    public IndiceRecycleView(int id, String fotoUrl, String textoPrincipal){
        this.id = id;
        this.fotoUrl = fotoUrl;
        this.textoPrincipal = textoPrincipal;
        //this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getTextoPrincipal() {
        return textoPrincipal;
    }

    public void setTextoPrincipal(String textoPrincipal) {
        this.textoPrincipal = textoPrincipal;
    }

    /*public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }*/
}
