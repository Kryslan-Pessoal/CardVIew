package com.aperam.kryslan.praticaspadrao.Model.Domain;

import android.os.Parcel;
import android.os.Parcelable;

public class TelaInicialCards implements Parcelable{

    private int id;
    private String fotoUrl;
    private String textoPrincipal;
    private String grupo;
    //private String link;

    public TelaInicialCards(){}
    public TelaInicialCards(int id, String fotoUrl, String textoPrincipal, String grupo){
        this.id = id;
        this.fotoUrl = fotoUrl;
        this.textoPrincipal = textoPrincipal;
        this.grupo = grupo;
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

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    // PARCELABLE
    @Override
    public int describeContents() {
        return 0;
    }

    public TelaInicialCards(Parcel parcel){
        setId(parcel.readInt());
        setFotoUrl(parcel.readString());
        setTextoPrincipal(parcel.readString());
        setGrupo(parcel.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {  //Vai escrever os dados nos pacotes para transportar.
        dest.writeInt( getId() );
        dest.writeString( getFotoUrl() );
        dest.writeString( getTextoPrincipal() );
        dest.writeString( getGrupo() );
    }

    public static final Parcelable.Creator<TelaInicialCards> CREATOR = new Parcelable.Creator<TelaInicialCards>(){
        @Override
        public TelaInicialCards createFromParcel(Parcel source) {  //Pega os dados de volta.
            return new TelaInicialCards(source);
        }
        @Override
        public TelaInicialCards[] newArray(int size) {
            return new TelaInicialCards[size];
        }
    };

    /*public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }*/
}
