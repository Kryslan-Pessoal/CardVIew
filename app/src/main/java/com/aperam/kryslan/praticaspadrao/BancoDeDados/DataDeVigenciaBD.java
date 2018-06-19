package com.aperam.kryslan.praticaspadrao.BancoDeDados;

import android.content.Context;

import com.aperam.kryslan.praticaspadrao.domain.TelaInicialCards;

import java.util.ArrayList;
import java.util.List;

import static com.aperam.kryslan.praticaspadrao.BancoDeDados.AreaEmitenteBD.GetId;
import static com.aperam.kryslan.praticaspadrao.BancoDeDados.BD.GetVoidString;

public class DataDeVigenciaBD {

    public static List<TelaInicialCards> GetDataDeVigenciaBd(Context c) {  //PEGAR DEPOIS NO DOCNIX.
        int[] id = GetId(12);  //Futuramente criar clase para cada um em domain.
        String[] foto = GetVoidString(12);
        String[] textoPrincipal = GetDatasAno();
        List<TelaInicialCards> listAux = new ArrayList<>();

        for (int i = 0; i < id.length; i++) {
            TelaInicialCards p = new TelaInicialCards(
                    id[i % id.length],
                    foto[i % foto.length],
                    textoPrincipal[i % textoPrincipal.length]);
            //link[i % link.length]);
            listAux.add(p);
        }
        return listAux;
    }

    public static String[] GetDatasAno(){
        return new String[]{
                "2006" ,
                "2008" ,
                "2009" ,
                "2010" ,
                "2011" ,
                "2012" ,
                "2013" ,
                "2014" ,
                "2015" ,
                "2016" ,
                "2017" ,
                "2018"};
    }
}
