package com.aperam.kryslan.praticaspadrao.BancoDeDados;

import android.content.Context;

import com.aperam.kryslan.praticaspadrao.domain.AreaEmitente;

import java.util.ArrayList;
import java.util.List;

import static com.aperam.kryslan.praticaspadrao.BancoDeDados.BD.GetTabsBd;
import static com.aperam.kryslan.praticaspadrao.BancoDeDados.BD.GetVoidInt;
import static com.aperam.kryslan.praticaspadrao.BancoDeDados.BD.GetVoidString;

public class areaEmitenteBD {

    public static List<AreaEmitente> GetAreaEmitenteBd(Context c) {  //PEGAR DEPOIS NO DOCNIX.
        int[] id = GetId(27);
        String[] foto = GetVoidString(27);
        String[] areaEmitente = GetAreasEminetes();
        int[] numero = GetVoidInt(27);
        //String[] link = new String[]{"https://drive.google.com/uc?id=1k3XiOU8sOtuHO_ainqMOHZbZW6oOVnXf", "https://drive.google.com/uc?id=142z4b4FK-NDu7fnh48DPTVMqbcgaHXeJ"};
        List<AreaEmitente> listAux = new ArrayList<>();

        for (int i = 0; i < id.length; i++) {
            AreaEmitente p = new AreaEmitente(
                    id[i % id.length],
                    areaEmitente[i % areaEmitente.length],
                    foto[i % foto.length],
                    numero[i % numero.length]);
            //link[i % link.length]);
            listAux.add(p);
        }
        return listAux;
    }

    public static int[] GetFotos(Context c) {

        return null;
    }

    public static int[] GetId(int qtdId) {
        int[] qtd = new int[qtdId];
        for (int i = 0; i < qtdId; i++) {
            qtd[i] = i;
        }
        return qtd;
    }

    public static String[] GetAreasEminetes() {
        String[] areasEminentes = {"Acabamento a Frio de Aços Inoxidáveis",
                "Acabamento a Quente de Aços Inoxidáveis",
                "Acabamento e Reprocessamento de Aços Inoxidáveis",
                "Aciaria",
                "Automação e Otimização de Processos",
                "Centro de Pesquisa",
                "Comunicação Empresarial",
                "Engenharia de Projetos",
                "Gestão Ambiental",
                "Gestão da Excelência Operacional",
                "Gestão da Saúde e Segurança",
                "Gestão de Recursos Humanos",
                "Gestão Financeira",
                "Infraestrutura",
                "Laminação a Frio de Aços Elétricos",
                "Laminação a Frio de Aços Inoxidáveis",
                "Laminação de Tiras a Quente",
                "Logística Integrada",
                "Manutenção Central",
                "Metalurgia",
                "Metalurgia de Aços Elétricos",
                "Metalurgia Integrada",
                "Planejamento Estratégico",
                "Redução",
                "Suprimentos",
                "Tecnologia da Informação",
                "Vendas"};
        return areasEminentes;
    }
}