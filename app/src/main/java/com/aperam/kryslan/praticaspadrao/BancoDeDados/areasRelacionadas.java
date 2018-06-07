package com.aperam.kryslan.praticaspadrao.BancoDeDados;

import android.content.Context;

import com.aperam.kryslan.praticaspadrao.domain.AreaEmitente;

import java.util.ArrayList;
import java.util.List;

import static com.aperam.kryslan.praticaspadrao.BancoDeDados.BD.GetVoidInt;
import static com.aperam.kryslan.praticaspadrao.BancoDeDados.areaEmitenteBD.GetId;
import static com.aperam.kryslan.praticaspadrao.BancoDeDados.BD.GetVoidString;

public class areasRelacionadas {

    public static List<AreaEmitente> GetAreaEmitenteBd(Context c){  //PEGAR DEPOIS NO DOCNIX.
        int[] id = GetId(27);
        String[] foto = GetVoidString(27);
        String[] areaEmitente = GetAreasEminetesNucle();
        int[] numero = GetVoidInt(27);
        //String[] link = new String[]{"https://drive.google.com/uc?id=1k3XiOU8sOtuHO_ainqMOHZbZW6oOVnXf", "https://drive.google.com/uc?id=142z4b4FK-NDu7fnh48DPTVMqbcgaHXeJ"};
        List<AreaEmitente> listAux = new ArrayList<>();

        for(int i=0; i<id.length; i++){
            AreaEmitente p = new AreaEmitente(
                    id[i % id.length],
                    foto[i % foto.length],
                    areaEmitente[i % areaEmitente.length],
                    numero[i % numero.length]);
            //link[i % link.length]);
            listAux.add(p);
        }
        return new ArrayList<>();
    }

    public static String[] GetAreasEminetesNucle(){
        return new String[]{"Núcleo 01 - Arquivo Central"+
                "Núcleo 02 - Gestão Ambiental"+
                "Núcleo 03 - Tecnologia da Informação"+
                "Núcleo 04 - Metalurgia Integrada"+
                "Núcleo 05 - Infraestrutura"+
                "Núcleo 07 - Fundação Aperam Acesita"+
                "Núcleo 09 - Aciaria"+
                "Núcleo 10 - Centro de Pesquisa"+
                "Núcleo 11 - Redução"+
                "Núcleo 12 - Laboratório Químico"+
                "Núcleo 13 - Manutenção da Aciaria"+
                "Núcleo 15 - Metrologia"+
                "Núcleo 17 - Laminação a Frio de Inoxidáveis"+
                "Núcleo 19 - Laminação a Quente"+
                "Núcleo 20 - Laminação a Frio de Aços Elétricos"+
                "Núcleo 22 - Relações Trabalhistas"+
                "Núcleo 23 - Suprimentos"+
                "Núcleo 24 - Gestão da Saúde e Segurança"+
                "Núcleo 25 - Gestão da Excelência Operacional"+
                "Núcleo 26 - Desenvolvimento e Treinamento"+
                "Núcleo 27 - Logística Integrada"+
                "Núcleo 28 - Vendas"+
                "Núcleo 31 - Gestão Financeira"+
                "Núcleo 32 - Engenharia de Projetos"+
                "Núcleo 37 - Planejamento Estratégico"+
                "Núcleo 38 - Manutenção Central"+
                "Núcleo 39 - Comunicação Empresarial"+
                "Núcleo 40 - Acabamento a Frio de Aços Inoxidáveis"+
                "Núcleo 43 - Automação e Otimização de Processos"};
    }
}
