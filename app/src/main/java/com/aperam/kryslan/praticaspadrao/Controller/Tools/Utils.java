package com.aperam.kryslan.praticaspadrao.Controller.Tools;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class Utils {
    public static int AlturaFabCorrigida(Context c){  //Corrige a posição do FAB, pois com o "layout_scrollFlags", ele fica em posição errada, abaixo do que deveria.
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
        if (windowmanager != null) {  //Para evitar erros, redimenciona a imagem apenas se o windowmanager exista.
            windowmanager.getDefaultDisplay().getMetrics(displayMetrics);  //Pega o tamanho da tela do celular.
        }
        int alturaDispositivo = displayMetrics.heightPixels;
        Double alturaFabAux = (alturaDispositivo * (-0.05));  //Cálculo baseado na porcentagem, pois assim, independente do tamanho da tela, ou resolução, a nova posição ficará no local correto relativo ao tamanho da tela.
        return alturaFabAux.intValue();
    }
}
