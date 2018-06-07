package com.aperam.kryslan.praticaspadrao.domain;

import android.content.Context;

import com.aperam.kryslan.praticaspadrao.R;

import java.util.ArrayList;
import java.util.List;

public class BD {

    //Menus (sumários)
    public static String[] GetTabsBd(Context c){
        return new String[]{
                c.getResources().getString(R.string.favoritos),
                c.getResources().getString(R.string.areaEminente),
                c.getResources().getString(R.string.areasRelacionadas),
                c.getResources().getString(R.string.autor),
                c.getResources().getString(R.string.dataDeVigencia),
                c.getResources().getString(R.string.nivel),
                c.getResources().getString(R.string.processo),
                c.getResources().getString(R.string.restrito)};
    }

    public static List<PraticasCards> GetAreaEmitenteBd(Context c){  //PEGAR DEPOIS NO DOCNIX.
        int[] id = new int[]{0, 1, 2, 3, 4, 5, 6};
        String[] areaEmitente = GetTabsBd(c);
        String[] autor = new String[]{"CRISTINA BORGES P. M. ALTO", "CRISTINA BORGES P. M. ALTO"};
        String[] processo = new String[]{"Logística de Transporte", "Logística de Transporte"};
        String[] urlImagem = new String[]{"https://drive.google.com/uc?id=1k3XiOU8sOtuHO_ainqMOHZbZW6oOVnXf", "https://drive.google.com/uc?id=142z4b4FK-NDu7fnh48DPTVMqbcgaHXeJ"};
        String[] urlDocumento = new String[]{"https://drive.google.com/open?id=1IK_eTLIkkuC30U7lUwbUFHEUjLYThT7_7D4_NCWLhZE", "https://drive.google.com/open?id=1x2XoDPLHRAyH9vej5gCen53YFUL_BncmPGLQ6bp5igY"};
        int[] numeroId = new int[]{270008, 270007};
        List<PraticasCards> listAux = null;

        return new ArrayList<>();
    }
}
