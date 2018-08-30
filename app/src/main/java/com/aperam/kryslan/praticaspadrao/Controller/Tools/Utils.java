package com.aperam.kryslan.praticaspadrao.Controller.Tools;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;

public class Utils {
    public static int AlturaFabCorrigida(Context c){  //Corrige a posição do FAB, pois com o "layout_scrollFlags", ele fica em posição errada, abaixo do que deveria.
        //CORRIGE ALTURA DO FAB
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
        if (windowmanager != null) {  //Para evitar erros, redimenciona a imagem apenas se o windowmanager exista.
            windowmanager.getDefaultDisplay().getMetrics(displayMetrics);  //Pega o tamanho da tela do celular.
        }
        int alturaDispositivo = displayMetrics.heightPixels;
        Double alturaFabAux = (alturaDispositivo * (-0.05));  //Cálculo baseado na porcentagem, pois assim, independente do tamanho da tela, ou resolução, a nova posição ficará no local correto relativo ao tamanho da tela.
        return alturaFabAux.intValue();
    }

    public static void RodaFab(RapidFloatingActionButton fab){
        //RODA O FAB
        if (fab.getRotation() != 0) {  //Se ele já estiver virado.
            fab.animate().rotation(0).start();
        }else { //Se ele estiver normal.
            fab.animate().rotation(180).start();
        }
    }
}
