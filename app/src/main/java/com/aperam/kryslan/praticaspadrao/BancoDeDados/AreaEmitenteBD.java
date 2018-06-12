package com.aperam.kryslan.praticaspadrao.BancoDeDados;

import android.content.Context;

import com.aperam.kryslan.praticaspadrao.domain.IndiceRecycleView;

import java.util.ArrayList;
import java.util.List;

import static com.aperam.kryslan.praticaspadrao.BancoDeDados.BD.GetVoidInt;

public class AreaEmitenteBD {

    public static List<IndiceRecycleView> GetAreaEmitenteBd(Context c) {  //PEGAR DEPOIS NO DOCNIX.
        int[] id = GetId(27);
        String[] foto = GetFotosAreasEminetes();
        String[] areaEmitente = GetAreasEminetes();
        List<IndiceRecycleView> listAux = new ArrayList<>();

        for (int i = 0; i < id.length; i++) {
            IndiceRecycleView p = new IndiceRecycleView(
                    id[i % id.length],
                    foto[i % foto.length],
                    areaEmitente[i % areaEmitente.length]);
            //link[i % link.length]);
            listAux.add(p);
        }
        return listAux;
    }

    public static int[] GetId(int qtdId) {
        int[] qtd = new int[qtdId];
        for (int i = 0; i < qtdId; i++) {
            qtd[i] = i;
        }
        return qtd;
    }

    public static String[] GetAreasEminetes() {
        return new String[]{"Acabamento a Frio de Aços Inoxidáveis",
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
    }

    public static String[] GetFotosAreasEminetes() {
        return new String[]{"https://drive.google.com/uc?id=1Vmq2Fip0oOMOgke-3dAgI5di35xRMJ9D",
                "https://drive.google.com/uc?id=1e5iRGqobvaQLQwvJXsyHlKivBzTKGxDE",
                "https://drive.google.com/uc?id=1ECkekr_tseRV9hqN8ZheXkf6KdEKmZ4R",
                "https://drive.google.com/uc?id=1JpzW9FHqZ8hxncIjV5UQCjuiMo0It8DO",
                "https://drive.google.com/uc?id=1ajHrjGgzBWSZeasmAoz3x2DMpQUdYB3v",
                "https://drive.google.com/uc?id=1FMC1XqTsDl2ZE8EB5B3LXfro7q9jivFp",
                "https://drive.google.com/uc?id=1Lg-PoI0ZWl_VmqvrpvqrhbO1mfBgJIj4",
                "https://drive.google.com/uc?id=1iBpFi9pDiqDP7J3AM2vsPdmvjewB9FAc",
                "https://drive.google.com/uc?id=19JVF6z5DJQQLeFcsJIQn9xZ8B4hfWtCq",
                "https://drive.google.com/uc?id=12GEPI8CG3QzOIlxDaCqEWT4t4PQbAAQg",
                "https://drive.google.com/uc?id=1uNKB0mgig4hzLDDius_9bvvZXW2Z_IUV",
                "https://drive.google.com/uc?id=1EmF52GPiJeQrB9RtZfY_xKvp6Kk4nJ4o",
                "https://drive.google.com/uc?id=1YELai99TmDzf-pmWaj0He5e2EmMktY2j",
                "https://drive.google.com/uc?id=15_D4krC5qoaL5gZXTpzXBahY43LXo-F4",
                "https://drive.google.com/uc?id=1oF7rt_xPXElOdFyt5I6kpMzUNP8o4bGK",
                "https://drive.google.com/uc?id=185TkmfFvLEaRizSvg51fWgVLUL2knkUa",
                "https://drive.google.com/uc?id=1EixpLT90SRtHqO1Ya4a8szZa1Cjrev_E",
                "https://drive.google.com/uc?id=1xlXnPfiyLLUMHoTvqSzMhUOmstqIMRve",
                "https://drive.google.com/uc?id=1tueQb91_k5Q3bHEkalwACZfpqkIVIF95",
                "https://drive.google.com/uc?id=1s9ruZfsjfFxgtHKSuWx6qvP_F-GM8lJe",
                "https://drive.google.com/uc?id=1I64kjuZQP0tvOQFpS-Ek0nv_vso4N8EC",
                "https://drive.google.com/uc?id=11E1UetF4nPm2e9Cea9SYv-pF4TOm8u7j",
                "https://drive.google.com/uc?id=1hCSyOjBk5m-i1UlReYpvVjNq7c5t2Oep",
                "https://drive.google.com/uc?id=13J6ovGF9JM5-a5HsyVHQyjez9wEU8XrK",
                "https://drive.google.com/uc?id=1Sa9WtkOL5TNhx8w15-hBcOwuYYLnt44T",
                "https://drive.google.com/uc?id=1kS4OxjShvwPqpNAOgs1InQBEcLydGCd4",
                "https://drive.google.com/uc?id=1DvL5GJJxPhriljK4C9dEdDkp3HE8lb-C"};
    }

    /*public static String[] GetFotosAreasEminetes() {
        return new String[]{"https://drive.google.com/uc?id=1xuXBhEJJI-4MCUYmOE1f1uOZiE1ySYRj",
                "https://drive.google.com/uc?id=1ZIwGuULsVgTamORTuTFV8TsuG-ilO5aY",
                "https://drive.google.com/uc?id=1xhLAHkKpaBcIr0XU5kfi7F0_STfvnIku",
                "https://drive.google.com/uc?id=1s7NTVC-4NCoDr7DTis4p5b7sbZlrVvoU",
                "https://drive.google.com/uc?id=1om9QWV_bx9uNwzlWj0QTQEen87JEjXlP",
                "https://drive.google.com/uc?id=1Q71dgNZscLuxzrjmprbbfPasf-AFwOtl",
                "https://drive.google.com/uc?id=1PZ-WA6iJa-1KOWYqpclzKBOG4Yg03cgz",
                "https://drive.google.com/uc?id=12KlYeDL7nRb9_r1J8djtp0mAltBqjmSm",
                "https://drive.google.com/uc?id=18Lw7BqFT-hW0_pwjde-M0LpGYfbvdpSC",
                "https://drive.google.com/uc?id=1gM1EU6TVbu6EuiLS_YuTefxjv9-4eEwE",
                "https://drive.google.com/uc?id=18369ZVKu5z-8fyhSRZGSeDwU3eB0yMzK",
                "https://drive.google.com/uc?id=1Xq6HXF5RMRqG-dO9A2SIB40LzMZeEZ_9",
                "https://drive.google.com/uc?id=16vfKpwvbp8tMciofGfy2vTBlwnp5TJGA",
                "https://drive.google.com/uc?id=156AcbKtx14Wyf6GhGGQWmnrpgRsT5DDm",
                "https://drive.google.com/uc?id=1kau3bJLunocPzVq6GxD-FyZ3tkUszjBw",
                "https://drive.google.com/uc?id=14A2LC7QUPfJheQzX5gkiwB9CE05c_xLE",
                "https://drive.google.com/uc?id=1NuT7D300GE11-6jZgbqwsS5tMoMLYN0f",
                "https://drive.google.com/uc?id=1Rz5Hoxk2eW4sPm_X_MrHgFVi48pOOMOw",
                "https://drive.google.com/uc?id=1HUAVWwUAhPXd_3jmScd-7rhkF_1nynt6",
                "https://drive.google.com/uc?id=1AXcRaqSAAFexk980ooyGMEQvFb9bi60b",
                "https://drive.google.com/uc?id=1AAdlbj2aRN3EcUDcoBYNTw-N6LAeR4Ly",
                "https://drive.google.com/uc?id=1A-dF6npaEKRLl9fseoJumRA7k8ZbqFwJ",
                "https://drive.google.com/uc?id=13TgwheNzuiC0uioeryNmak8Je0mfuOpG",
                "https://drive.google.com/uc?id=1wqQ-7kAWGhXB1_3fIfgWRj0dtNbHpX2b",
                "https://drive.google.com/uc?id=1AtQMgJtS5grwNSmAoi9i7DxaCsLn_FgI",
                "https://drive.google.com/uc?id=1en4GHyi0PekuS24mX9Y1B2ndkrDVZXs7",
                "https://drive.google.com/uc?id=1ug4G1d3v4i1LBTmJA4nFAxSFsqjKE4X9"};
    }*/

}