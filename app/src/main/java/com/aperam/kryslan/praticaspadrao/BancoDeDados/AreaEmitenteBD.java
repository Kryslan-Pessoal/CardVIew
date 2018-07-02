package com.aperam.kryslan.praticaspadrao.BancoDeDados;

import android.content.Context;

import com.aperam.kryslan.praticaspadrao.domain.ListaPraticas;
import com.aperam.kryslan.praticaspadrao.domain.TelaInicialCards;

import java.util.ArrayList;
import java.util.List;

import static com.aperam.kryslan.praticaspadrao.BancoDeDados.BD.GetTipo;

public class AreaEmitenteBD {

    public static List<TelaInicialCards> GetAreaEmitenteBd(Context c) {  //PEGAR DEPOIS NO DOCNIX.
        int[] id = GetTipo(0, 27);
        String[] foto = GetFotosAreasEminetes();
        String[] areaEmitente = GetAreasEminetes();
        List<TelaInicialCards> listAux = new ArrayList<>();

        for (int i = 0; i < id.length; i++) {
            TelaInicialCards p = new TelaInicialCards(
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


    //BdLite LISTA SIMPLES DAS PRATICAS
    public static List<ListaPraticas> GetAreaEmitenteBdLista() {  //PEGAR DEPOIS NO DOCNIX.
        String[] numero = getNumeroAreaEmitenteBd();
//        String[] areaEmitente = GetAreasEminetes();
        String[] titulo = getTituloAreaEmitenteBd();
        String[] autor = getAutorAreaEmitenteBd();
        String[] urlDocumento = getUrlDocumentoAreaEmitenteBd(68);
        String[] data = getDataAreaEmitenteBd();
        int[] id = GetId(68);
        List<ListaPraticas> listAux = new ArrayList<>();

        for (int i = 0; i < id.length; i++) {
            ListaPraticas p = new ListaPraticas(
                numero[i % numero.length],
                titulo[i % titulo.length],
                autor[i % autor.length],
                urlDocumento[i % urlDocumento.length],
                data[i % data.length],
                id[i % id.length]);
                //link[i % link.length]);
            listAux.add(p);
        }
        return listAux;
    }

    private static String[] getNumeroAreaEmitenteBd() {
        return new String[]{
                "PPA40-0096 - v.2" ,
                "PPA40-0097 - v.17" ,
                "PPA40-0098 - v.8" ,
                "PPA40-0099 - v.10" ,
                "PPA40-0100 - v.3" ,
                "PPA40-0101 - v.4" ,
                "PPA40-0102 - v.18" ,
                "PPA40-0103 - v.4" ,
                "PPA40-0104 - v.14" ,
                "PPA40-0105 - v.9" ,
                "PPA40-0106 - v.2" ,
                "PPA40-0107 - v.1" ,
                "PPA40-0108 - v.22" ,
                "PPA40-0109 - v.5" ,
                "PPA40-0110 - v.9" ,
                "PPA40-0111 - v.11" ,
                "PPA40-0112 - v.18" ,
                "PPA40-0113 - v.22" ,
                "PPA40-0114 - v.7" ,
                "PPA40-0115 - v.3" ,
                "PPA40-0116 - v.12" ,
                "PPA40-0117 - v.14" ,
                "PPA40-0118 - v.1" ,
                "PPA40-0119 - v.8" ,
                "PPA40-0120 - v.4" ,
                "PPA40-0121 - v.3" ,
                "PPA40-0122 - v.8" ,
                "PPA40-0123 - v.2" ,
                "PPA40-0124 - v.5" ,
                "PPA40-0125 - v.3" ,
                "PPA40-0126 - v.5" ,
                "PPA40-0127 - v.3" ,
                "PPA40-0128 - v.1" ,
                "PPA40-0129 - v.5" ,
                "PPA40-0130 - v.3" ,
                "PPA40-0131 - v.3" ,
                "PPA40-0132 - v.1" ,
                "PPA40-0133 - v.1" ,
                "PPA40-0134 - v.9" ,
                "PPA40-0135 - v.9" ,
                "PPA40-0136 - v.11" ,
                "PPA40-0145 - v.3" ,
                "PPA40-0146 - v.14" ,
                "PPA40-0148 - v.2" ,
                "PPA40-0149 - v.1" ,
                "PPA40-0150 - v.5" ,
                "PPA40-0151 - v.3" ,
                "PPA40-0152 - v.1" ,
                "PPA40-0154 - v.2" ,
                "PPA40-0156 - v.2" ,
                "PPA40-0157 - v.6" ,
                "PPA40-0158 - v.4" ,
                "PPA40-0159 - v.2" ,
                "PPA40-0163 - v.1" ,
                "PPA40-0169 - v.1" ,
                "PPA40-0170 - v.1" ,
                "PPA40-0171 - v.0" ,
                "PPA40-0172 - v.2" ,
                "PPA40-0173 - v.1" ,
                "PPA40-0174 - v.1" ,
                "PPA40-0175 - v.0" ,
                "PPA40-0176 - v.1" ,
                "PPA40-0179 - v.0" ,
                "PPA40-0180 - v.0" ,
                "PPA40-0181 - v.2" ,
                "PPA40-0182 - v.2" ,
                "PPA40-0183 - v.0" ,
                "PPA40-0184 - v.0"};
    }

    public static String[] getTituloAreaEmitenteBd() {
        return new String[]{
                "Monitorar Sistema de Detecção e Combate à Incêndio - TL9" ,
                "Cortar Bobinas e Tiras - TL1" ,
                "Aplicar Polietileno - TL1" ,
                "Expedir Bobinas e Tiras - TL1" ,
                "Receber Bobinas e Tiras - TL1" ,
                "Montar Ferramentais de Corte - TL1" ,
                "Cortar Bobina, Inspecionar e Amostrar - TL5" ,
                "Localizar Bobinas - Acabamento Final" ,
                "Retirar bobinas,coletar e Montar luvas de papelão - TL5" ,
                "Preparar e Aplicar Polietileno - TL5" ,
                "Montar Árvore de Corte - TL5" ,
                "Cortar Tiras para a CETUBOS - TL6" ,
                "Cortar Bobinas e Tiras - TL6" ,
                "Receber Bobinas e Tiras - TL6" ,
                "Montar Ferramental de Corte - TL6" ,
                "Aplicar Polietileno - TL6" ,
                "Expedir Bobinas e Tiras - TL6" ,
                "Cortar Bobinas e Tiras - TL9" ,
                "Receber Bobinas e Tiras - TL9" ,
                "Montar Ferramental de Corte - TL9" ,
                "Aplicar Polietileno - TL9" ,
                "Expedir Bobinas e Tiras - TL9" ,
                "Cortar Tiras para a CETUBOS - TL9" ,
                "Cortar Chapas e Blanks - TT1" ,
                "Aplicar Polietileno - TT1" ,
                "Receber Bobinas e Tiras - TT1" ,
                "Empilhar e Expedir Chapas e Blanks - TT1" ,
                "Corrigir Forma do Produto - TT1" ,
                "Cortar Chapas e Blanks - TT4" ,
                "Receber Bobinas e Tiras - TT4" ,
                "Empilhar e Expedir Chapas e Blanks - TT4" ,
                "Aplicar Polietileno - TT4" ,
                "Corrigir Forma do Produto - TT4" ,
                "Receber Bobinas - AP2" ,
                "Preparar Bobinas - AP2" ,
                "Soldar Bobinas - AP2" ,
                "Controlar Tração - AP2" ,
                "Rebobinar Bobinas - AP2" ,
                "Expedir Bobinas - AP2" ,
                "Realizar Acabamento Buffing Bright - AP2" ,
                "Realizar Acabamento Satin Finish - AP2" ,
                "Realizar Embalagem Mecanizada em Bobinas e Tiras" ,
                "Operar Ponte Rolante - Acabamento Final" ,
                "Organizar e Limpar a Área" ,
                "Realizar Atividades de Controle de Processo" ,
                "Gerir Ferramental do Corte Longitudinal" ,
                "Gerir Ferramental de Corte Transversal" ,
                "Gerir Retífica de Billy Rolls" ,
                "Efetuar Corte em Luva de Papelão" ,
                "Cortar e Entregar Polietileno" ,
                "Processar Aparas" ,
                "Expedir Produto Sucatado" ,
                "Utilizar Caminhonete - F1000" ,
                "Realizar Verificação da Calibração do Espectrofotometro" ,
                "Operar Pórtico - OAFF" ,
                "Operar Talhas - OAFF" ,
                "Receber Bobinas e Tiras - TLE" ,
                "Preparar o equipamento para processo - TLE" ,
                "Cortar Bobinas e Tiras - TLE" ,
                "Expedir Bobinas e Tiras - TLE" ,
                "Gerir Ferramental de Corte Transversal - PBs" ,
                "Receber/Estocar insumos - Acabamento Final" ,
                "Processo de corte Duplex - Acabamento Final" ,
                "Preparar e Introduzir Bobinas - TL5" ,
                "Descartar Pontas - TL5" ,
                "Processar e Expedir aparas - TL5" ,
                "Manusear papel e plástico - TL5" ,
                "Trocar diâmetro na bobinadeira da saída - TL5"};
    }

    private static String[] getAutorAreaEmitenteBd() {
        return new String[]{"MARCUS MICHEL ELIAS",
                "ADIR SOARES DE M JUNIOR",
                "DOUGLAS FERREIRA DE SOUSA",
                "ADIR SOARES DE M JUNIOR",
                "DOUGLAS FERREIRA DE SOUSA",
                "DOUGLAS FERREIRA DE SOUSA",
                "EDNEI ARAUJO CLARA",
                "EDNEI ARAUJO CLARA",
                "EDNEI ARAUJO CLARA",
                "EDNEI ARAUJO CLARA",
                "EDNEI ARAUJO CLARA",
                "MARCUS MICHEL ELIAS",
                "ADIR SOARES DE M JUNIOR",
                "Sergio Ricardo Martins",
                "DOUGLAS FERREIRA DE SOUSA",
                "DOUGLAS FERREIRA DE SOUSA",
                "ADIR SOARES DE M JUNIOR",
                "ADIR SOARES DE M JUNIOR",
                "EDNEI ARAUJO CLARA",
                "Sergio Ricardo Martins",
                "EDNEI ARAUJO CLARA",
                "ADIR SOARES DE M JUNIOR",
                "MARCUS MICHEL ELIAS",
                "ADIR SOARES DE M JUNIOR",
                "Sergio Ricardo Martins",
                "MARCUS MICHEL ELIAS",
                "Sergio Ricardo Martins",
                "MARCUS MICHEL ELIAS",
                "Sergio Ricardo Martins",
                "MARCUS MICHEL ELIAS",
                "Sergio Ricardo Martins",
                "Sergio Ricardo Martins",
                "MARCUS MICHEL ELIAS",
                "DOUGLAS FERREIRA DE SOUSA",
                "DOUGLAS FERREIRA DE SOUSA",
                "DOUGLAS FERREIRA DE SOUSA",
                "ADIR SOARES DE M JUNIOR",
                "ADIR SOARES DE M JUNIOR",
                "RAVENY CRISTINY ASSIS FRANCISQUINI",
                "ADIR SOARES DE M JUNIOR",
                "DOUGLAS FERREIRA DE SOUSA",
                "GLAUBER F BATISTA",
                "ADIR SOARES DE M JUNIOR",
                "MARCUS MICHEL ELIAS",
                "MARCUS MICHEL ELIAS",
                "Sergio Ricardo Martins",
                "ADIR SOARES DE M JUNIOR",
                "MARCUS MICHEL ELIAS",
                "MARCUS MICHEL ELIAS",
                "EDNEI ARAUJO CLARA",
                "MARCUS MICHEL ELIAS",
                "ADIR SOARES DE M JUNIOR",
                "MARCUS MICHEL ELIAS",
                "ADIR SOARES DE M JUNIOR",
                "CLAUDIO SILVA MIRANDA",
                "CLAUDIO SILVA MIRANDA",
                "Sergio Ricardo Martins",
                "ADIR SOARES DE M JUNIOR",
                "ADIR SOARES DE M JUNIOR",
                "ADIR SOARES DE M JUNIOR",
                "EDNEI ARAUJO CLARA",
                "EDNEI ARAUJO CLARA",
                "ADIR SOARES DE M JUNIOR",
                "EDNEI ARAUJO CLARA",
                "EDNEI ARAUJO CLARA",
                "EDNEI ARAUJO CLARA",
                "EDNEI ARAUJO CLARA",
                "EDNEI ARAUJO CLARA"};
    }

    private static String[] getUrlDocumentoAreaEmitenteBd(int qtd) {
        String[] url = new String[qtd];
        for (int i = 0; i < qtd; i++) {
            url[i] = "https://docs.google.com/document/d/1kV00ZQ3EIwhuyDfVKxwaVaBUdq3Vv-3JF9YREqiP-e0";
        }
        return url;
    }

    private static String[] getDataAreaEmitenteBd() {
        return new String[]{"30/05/2012",
                "14/03/2018",
                "30/05/2016",
                "07/08/2017",
                "31/08/2015",
                "25/07/2017",
                "08/05/2018",
                "26/07/2017",
                "29/05/2018",
                "30/01/2018",
                "17/02/2017",
                "24/02/2011",
                "05/06/2018",
                "19/04/2016",
                "22/05/2016",
                "31/07/2017",
                "28/05/2018",
                "05/06/2018",
                "30/06/2017",
                "13/12/2011",
                "06/11/2017",
                "02/04/2018",
                "10/03/2011",
                "21/11/2017",
                "08/09/2015",
                "11/09/2015",
                "17/09/2016",
                "18/05/2011",
                "17/06/2015",
                "02/01/2015",
                "02/07/2015",
                "15/04/2014",
                "03/03/2011",
                "17/01/2017",
                "15/04/2011",
                "14/11/2012",
                "02/03/2011",
                "02/03/2011",
                "10/07/2017",
                "14/07/2017",
                "30/05/2016",
                "06/10/2011",
                "02/03/2018",
                "10/06/2011",
                "11/03/2011",
                "17/10/2017",
                "15/12/2014",
                "14/03/2011",
                "26/04/2011",
                "23/11/2012",
                "30/05/2013",
                "18/10/2016",
                "06/06/2011",
                "14/03/2011",
                "03/02/2018",
                "03/02/2018",
                "18/01/2017",
                "18/04/2018",
                "09/05/2017",
                "01/02/2018",
                "02/02/2017",
                "06/02/2018",
                "13/03/2018",
                "15/03/2018",
                "29/05/2018",
                "29/05/2018",
                "29/03/2018",
                "29/03/2018"};
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