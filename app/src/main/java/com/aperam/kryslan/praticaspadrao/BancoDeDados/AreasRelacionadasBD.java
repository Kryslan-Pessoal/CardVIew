package com.aperam.kryslan.praticaspadrao.BancoDeDados;

import android.content.Context;

import com.aperam.kryslan.praticaspadrao.domain.TelaInicialCards;

import java.util.ArrayList;
import java.util.List;

import static com.aperam.kryslan.praticaspadrao.BancoDeDados.AreaEmitenteBD.GetId;
import static com.aperam.kryslan.praticaspadrao.BancoDeDados.BD.GetTipo;

public class AreasRelacionadasBD {

    public static List<TelaInicialCards> GetAreasRelacionadasBd(Context c){  //PEGAR DEPOIS NO DOCNIX.
        int[] id = GetTipo(0, 29);
        String[] foto = GetFotosAreasRelacionadas();
        String[] areasRelacionadas = GetAreasRelacionadas();
        //String[] link = new String[]{"https://drive.google.com/uc?id=1k3XiOU8sOtuHO_ainqMOHZbZW6oOVnXf", "https://drive.google.com/uc?id=142z4b4FK-NDu7fnh48DPTVMqbcgaHXeJ"};
        List<TelaInicialCards> listAux = new ArrayList<>();

        for(int i=0; i<id.length; i++){
            TelaInicialCards p = new TelaInicialCards(
                    id[i % id.length],
                    foto[i % foto.length],
                    areasRelacionadas[i % areasRelacionadas.length]);
            listAux.add(p);
        }
        return listAux;
    }

    public static String[] GetAreasRelacionadas(){
        return new String[]{"Núcleo 01 - Arquivo Central",
                "Núcleo 02 - Gestão Ambiental",
                "Núcleo 03 - Tecnologia da Informação",
                "Núcleo 04 - Metalurgia Integrada",
                "Núcleo 05 - Infraestrutura",
                "Núcleo 07 - Fundação Aperam Acesita",
                "Núcleo 09 - Aciaria",
                "Núcleo 10 - Centro de Pesquisa",
                "Núcleo 11 - Redução",
                "Núcleo 12 - Laboratório Químico",
                "Núcleo 13 - Manutenção da Aciaria",
                "Núcleo 15 - Metrologia",
                "Núcleo 17 - Laminação a Frio de Inoxidáveis",
                "Núcleo 19 - Laminação a Quente",
                "Núcleo 20 - Laminação a Frio de Aços Elétricos",
                "Núcleo 22 - Relações Trabalhistas",
                "Núcleo 23 - Suprimentos",
                "Núcleo 24 - Gestão da Saúde e Segurança",
                "Núcleo 25 - Gestão da Excelência Operacional",
                "Núcleo 26 - Desenvolvimento e Treinamento",
                "Núcleo 27 - Logística Integrada",
                "Núcleo 28 - Vendas",
                "Núcleo 31 - Gestão Financeira",
                "Núcleo 32 - Engenharia de Projetos",
                "Núcleo 37 - Planejamento Estratégico",
                "Núcleo 38 - Manutenção Central",
                "Núcleo 39 - Comunicação Empresarial",
                "Núcleo 40 - Acabamento a Frio de Aços Inoxidáveis",
                "Núcleo 43 - Automação e Otimização de Processos"};
    }

    public static String[] GetFotosAreasRelacionadas(){
        return new String[]{"https://drive.google.com/uc?id=1o4Esm_RLPzVqd-jR_s8eEJmEcyx1m3cO",
                "https://drive.google.com/uc?id=19JVF6z5DJQQLeFcsJIQn9xZ8B4hfWtCq",
                "https://drive.google.com/uc?id=1kS4OxjShvwPqpNAOgs1InQBEcLydGCd4",
                "https://drive.google.com/uc?id=11E1UetF4nPm2e9Cea9SYv-pF4TOm8u7j",
                "https://drive.google.com/uc?id=15_D4krC5qoaL5gZXTpzXBahY43LXo-F4",
                "https://drive.google.com/uc?id=1yRxKfdFrQR2G0rMbJPOi0TOtO5u7GCyJ",
                "https://drive.google.com/uc?id=1JpzW9FHqZ8hxncIjV5UQCjuiMo0It8DO",
                "https://drive.google.com/uc?id=1FMC1XqTsDl2ZE8EB5B3LXfro7q9jivFp",
                "https://drive.google.com/uc?id=13J6ovGF9JM5-a5HsyVHQyjez9wEU8XrK",
                "https://drive.google.com/uc?id=1grv5QVOo9Nlga70_x4PMv-ul7E6bLZyw",
                "https://drive.google.com/uc?id=11ZAOFMwRRLMDCQoqVfsEbWUp5jY5mCy8",
                "https://drive.google.com/uc?id=1F_gn6E_DjHqz6MyV2XIxjAjo53Xe0qDx",
                "https://drive.google.com/uc?id=185TkmfFvLEaRizSvg51fWgVLUL2knkUa",
                "https://drive.google.com/uc?id=16anwxKguK8JdhvtHrk2bt_u6HAtkSH7G",
                "https://drive.google.com/uc?id=1oF7rt_xPXElOdFyt5I6kpMzUNP8o4bGK",
                "https://drive.google.com/uc?id=1FJHhcDgzedDIk6KCV0qSRjtGQK3ku5FQ",
                "https://drive.google.com/uc?id=1Sa9WtkOL5TNhx8w15-hBcOwuYYLnt44T",
                "https://drive.google.com/uc?id=1uNKB0mgig4hzLDDius_9bvvZXW2Z_IUV",
                "https://drive.google.com/uc?id=12GEPI8CG3QzOIlxDaCqEWT4t4PQbAAQg",
                "https://drive.google.com/uc?id=1yoIUz1qmJF89hclVy4MCy2MjLoajzx10",
                "https://drive.google.com/uc?id=1xlXnPfiyLLUMHoTvqSzMhUOmstqIMRve",
                "https://drive.google.com/uc?id=1YELai99TmDzf-pmWaj0He5e2EmMktY2j",
                "https://drive.google.com/uc?id=1DvL5GJJxPhriljK4C9dEdDkp3HE8lb-C",
                "https://drive.google.com/uc?id=1ghzl5M1Iv4S-EUVvy2ifi1Au3MmwI29e",
                "https://drive.google.com/uc?id=1hCSyOjBk5m-i1UlReYpvVjNq7c5t2Oep",
                "https://drive.google.com/uc?id=1tueQb91_k5Q3bHEkalwACZfpqkIVIF95",
                "https://drive.google.com/uc?id=1Lg-PoI0ZWl_VmqvrpvqrhbO1mfBgJIj4",
                "https://drive.google.com/uc?id=1Vmq2Fip0oOMOgke-3dAgI5di35xRMJ9D",
                "https://drive.google.com/uc?id=1ajHrjGgzBWSZeasmAoz3x2DMpQUdYB3v"};
    }

    /*public static String[] GetFotosAreasRelacionadas(){
        return new String[]{"https://drive.google.com/uc?id=1PwpDIGEVWlL8_zeJn7-8uITzPFdH_Ck3",
                "https://drive.google.com/uc?id=18Lw7BqFT-hW0_pwjde-M0LpGYfbvdpSC",
                "https://drive.google.com/uc?id=1en4GHyi0PekuS24mX9Y1B2ndkrDVZXs7",
                "https://drive.google.com/uc?id=1A-dF6npaEKRLl9fseoJumRA7k8ZbqFwJ",
                "https://drive.google.com/uc?id=156AcbKtx14Wyf6GhGGQWmnrpgRsT5DDm",
                "https://upload.wikimedia.org/wikipedia/commons/4/4e/Fachada_do_Centro_Cultural_da_Funda%C3%A7%C3%A3o_Aperam-Acesita%2C_Tim%C3%B3teo_MG.JPG",
                "https://drive.google.com/uc?id=1s7NTVC-4NCoDr7DTis4p5b7sbZlrVvoU",
                "https://drive.google.com/uc?id=1Q71dgNZscLuxzrjmprbbfPasf-AFwOtl",
                "https://drive.google.com/uc?id=1wqQ-7kAWGhXB1_3fIfgWRj0dtNbHpX2b",
                "https://drive.google.com/uc?id=15CqOHy6lzVKx6FDtNMG-ULRi3FQW4yyc",
                "https://drive.google.com/uc?id=1UsHiI53pe-_VlBP_nQosRwDPu9nKE0rs",
                "https://drive.google.com/uc?id=1tY-AiZlW-Mnya79UB5f-4ssWlevByD7b",
                "https://drive.google.com/uc?id=14A2LC7QUPfJheQzX5gkiwB9CE05c_xLE",
                "https://drive.google.com/uc?id=1_UkYpJIdUnGMn9tcdFVTUvi14IN4G7ft",
                "https://drive.google.com/uc?id=1kau3bJLunocPzVq6GxD-FyZ3tkUszjBw",
                "https://drive.google.com/uc?id=11oCfxvyQG7E-K9XQLzkJPIfTiKSdAHh6",
                "https://drive.google.com/uc?id=1AtQMgJtS5grwNSmAoi9i7DxaCsLn_FgI",
                "https://drive.google.com/uc?id=18369ZVKu5z-8fyhSRZGSeDwU3eB0yMzK",
                "https://drive.google.com/uc?id=1gM1EU6TVbu6EuiLS_YuTefxjv9-4eEwE",
                "https://drive.google.com/uc?id=1FuvybpmpvsOJT7t5qWoK5PeRc60ZaESP",
                "https://drive.google.com/uc?id=1Rz5Hoxk2eW4sPm_X_MrHgFVi48pOOMOw",
                "https://drive.google.com/uc?id=1ug4G1d3v4i1LBTmJA4nFAxSFsqjKE4X9",
                "https://drive.google.com/uc?id=16vfKpwvbp8tMciofGfy2vTBlwnp5TJGA",
                "https://drive.google.com/uc?id=12KlYeDL7nRb9_r1J8djtp0mAltBqjmSm",
                "https://drive.google.com/uc?id=13TgwheNzuiC0uioeryNmak8Je0mfuOpG",                    Antiga
                "https://drive.google.com/uc?id=1HUAVWwUAhPXd_3jmScd-7rhkF_1nynt6",
                "https://drive.google.com/uc?id=1PZ-WA6iJa-1KOWYqpclzKBOG4Yg03cgz",
                "https://drive.google.com/uc?id=1xuXBhEJJI-4MCUYmOE1f1uOZiE1ySYRj",
                "https://drive.google.com/uc?id=1om9QWV_bx9uNwzlWj0QTQEen87JEjXlP"};
    }*/
}
