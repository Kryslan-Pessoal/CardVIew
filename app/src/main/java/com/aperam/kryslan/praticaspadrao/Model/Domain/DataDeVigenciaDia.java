package com.aperam.kryslan.praticaspadrao.Model.Domain;

import android.os.Parcel;
import android.os.Parcelable;

public class DataDeVigenciaDia implements Parcelable {

    private String data;

    public DataDeVigenciaDia(){}
    public DataDeVigenciaDia(String data){
        this.data = data;
    }

    public String getData(){
        return data;
    }
    public void setData(String numero){
        this.data = data;
    }

    //PARCELABLE
    @Override
    public int describeContents() {
        return 0;
    }

    public DataDeVigenciaDia(Parcel parcel){  //Só está escrevendo os objetos.
        setData(parcel.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {  //Só está guardando os objetos.
        dest.writeString(getData());
    }
    public static final Parcelable.Creator<DataDeVigenciaDia> CREATOR = new Parcelable.Creator<DataDeVigenciaDia>(){
        @Override
        public DataDeVigenciaDia createFromParcel(Parcel source) {  //Chamar esta entidade para pegar os dados.
            return new DataDeVigenciaDia(source);
        }
        @Override
        public DataDeVigenciaDia[] newArray(int size) {
            return new DataDeVigenciaDia[size];
        }
    };
}
