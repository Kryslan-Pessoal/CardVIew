package com.aperam.kryslan.praticaspadrao.BancoDeDados;

import com.aperam.kryslan.praticaspadrao.domain.ListaPraticas;

import java.util.ArrayList;
import java.util.List;

public class BdPraticasActivity {

    /*public static List<ListaPraticas> GetAreaEmitentePraticasActivity() {
        String[] numero = getNumeroAreaEmitenteBd();
        String[] titulo = getTituloAreaEmitenteBd();
        String[] autor = getAutorAreaEmitenteBd();
        String[] urlDocumento = getUrlDocumentoBd(68, "https://docs.google.com/document/d/1kV00ZQ3EIwhuyDfVKxwaVaBUdq3Vv-3JF9YREqiP-e0");
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
    }*/
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

    /*public static List<ListaPraticas> GetAreasRelacionadasPraticasActivity() {
        String[] numero = getNumeroAreasRelacionadasBd();
        String[] nome = getTituloAreasRelacionadasBd();
        String[] autor = getAutorAreasRelacionadasBd();
        String[] urlDocumento = getUrlDocumentoBd(6, "https://drive.google.com/open?id=14i1SPHMbykOxU4EkXJmGkdOY9KrE2woz7YMRBsJk25o");
        String[] data = getDataAreasRelacionadasBd();
        int[] id = GetId(6);
        List<ListaPraticas> listAux = new ArrayList<>();

        for (int i = 0; i < id.length; i++) {
            ListaPraticas p = new ListaPraticas(
                    numero[i % numero.length],
                    nome[i % nome.length],
                    autor[i % autor.length],
                    urlDocumento[i % urlDocumento.length],
                    data[i % data.length],
                    id[i % id.length]);
            listAux.add(p);
        }
        return listAux;
    }*/
    private static String[] getNumeroAreasRelacionadasBd(){
        return new String[]{
                "PPA01-0031 - v.9" ,
                "PPA01-0023 - v.10" ,
                "PPA01-0026 - v.6" ,
                "PPA01-0032 - v.11" ,
                "PPA09-0159 - v.21" ,
                "PPA09-0162 - v.15"};

    }
    private static String[] getTituloAreasRelacionadasBd(){
        return new String[]{
                "Receber, Conferir e Arquivar Documentos" ,
                "Inspecionar Arquivo de Segurança" ,
                "Conferir Microfilmagem Convencional" ,
                "Atender Solicitações de Consulta" ,
                "Adicionar Carga no Forno" ,
                "Limpar e Reparar o Barrado"};

    }
    private static String[] getAutorAreasRelacionadasBd() {
        return new String[]{
                "MARCUS MICHEL ELIAS" ,
                "ADIR SOARES DE M JUNIOR" ,
                "DOUGLAS FERREIRA DE SOUSA" ,
                "ADIR SOARES DE M JUNIOR" ,
                "DOUGLAS FERREIRA DE SOUSA" ,
                "DOUGLAS FERREIRA DE SOUSA" ,
                "EDNEI ARAUJO CLARA" ,
                "EDNEI ARAUJO CLARA" ,
                "EDNEI ARAUJO CLARA" ,
                "EDNEI ARAUJO CLARA" ,
                "EDNEI ARAUJO CLARA" ,
                "MARCUS MICHEL ELIAS" ,
                "ADIR SOARES DE M JUNIOR" ,
                "Sergio Ricardo Martins" ,
                "DOUGLAS FERREIRA DE SOUSA" ,
                "DOUGLAS FERREIRA DE SOUSA" ,
                "ADIR SOARES DE M JUNIOR" ,
                "ADIR SOARES DE M JUNIOR" ,
                "EDNEI ARAUJO CLARA" ,
                "Sergio Ricardo Martins" ,
                "EDNEI ARAUJO CLARA" ,
                "ADIR SOARES DE M JUNIOR" ,
                "MARCUS MICHEL ELIAS" ,
                "ADIR SOARES DE M JUNIOR" ,
                "Sergio Ricardo Martins" ,
                "MARCUS MICHEL ELIAS" ,
                "Sergio Ricardo Martins" ,
                "MARCUS MICHEL ELIAS" ,
                "Sergio Ricardo Martins" ,
                "MARCUS MICHEL ELIAS" ,
                "Sergio Ricardo Martins" ,
                "Sergio Ricardo Martins" ,
                "MARCUS MICHEL ELIAS" ,
                "DOUGLAS FERREIRA DE SOUSA" ,
                "DOUGLAS FERREIRA DE SOUSA" ,
                "DOUGLAS FERREIRA DE SOUSA" ,
                "ADIR SOARES DE M JUNIOR" ,
                "ADIR SOARES DE M JUNIOR" ,
                "RAVENY CRISTINY ASSIS FRANCISQUINI" ,
                "ADIR SOARES DE M JUNIOR" ,
                "DOUGLAS FERREIRA DE SOUSA" ,
                "GLAUBER F BATISTA" ,
                "ADIR SOARES DE M JUNIOR" ,
                "MARCUS MICHEL ELIAS" ,
                "MARCUS MICHEL ELIAS" ,
                "Sergio Ricardo Martins" ,
                "ADIR SOARES DE M JUNIOR" ,
                "MARCUS MICHEL ELIAS" ,
                "MARCUS MICHEL ELIAS" ,
                "EDNEI ARAUJO CLARA" ,
                "MARCUS MICHEL ELIAS" ,
                "ADIR SOARES DE M JUNIOR" ,
                "MARCUS MICHEL ELIAS" ,
                "ADIR SOARES DE M JUNIOR" ,
                "CLAUDIO SILVA MIRANDA" ,
                "CLAUDIO SILVA MIRANDA" ,
                "Sergio Ricardo Martins" ,
                "ADIR SOARES DE M JUNIOR" ,
                "ADIR SOARES DE M JUNIOR" ,
                "ADIR SOARES DE M JUNIOR" ,
                "EDNEI ARAUJO CLARA" ,
                "EDNEI ARAUJO CLARA" ,
                "ADIR SOARES DE M JUNIOR" ,
                "EDNEI ARAUJO CLARA" ,
                "EDNEI ARAUJO CLARA" ,
                "EDNEI ARAUJO CLARA" ,
                "EDNEI ARAUJO CLARA" ,
                "EDNEI ARAUJO CLARA"};
    }
    private static String[] getDataAreasRelacionadasBd(){
        return new String[]{
                "19/06/2015" ,
                "19/06/2015" ,
                "29/06/2011" ,
                "21/03/2014" ,
                "16/10/2014" ,
                "19/12/2014"};

    }

    /*public static List<ListaPraticas> GetAutorPraticasActivity() {
        String[] numero = getNumeroAutorBd();
        String[] titulo = getTituloAutorBd();
        String[] autor = getAutorAutorBd();
        String[] urlDocumento = getUrlDocumentoBd(6, "https://docs.google.com/document/d/1kV00ZQ3EIwhuyDfVKxwaVaBUdq3Vv-3JF9YREqiP-e0");
        String[] data = getDataAutorBd();
        int[] id = GetId(6);
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
    }  //REVISAR NO DOCNIX!!!!*/
    private static String[] getNumeroAutorBd(){
        return new String[]{
                "PPA01-0031 - v.9" ,
                "PPA01-0023 - v.10" ,
                "PPA01-0026 - v.6" ,
                "PPA01-0032 - v.11" ,
                "PPA09-0159 - v.21" ,
                "PPA09-0162 - v.15"};

    }
    private static String[] getTituloAutorBd(){
        return new String[]{
                "Receber, Conferir e Arquivar Documentos" ,
                "Inspecionar Arquivo de Segurança" ,
                "Conferir Microfilmagem Convencional" ,
                "Atender Solicitações de Consulta" ,
                "Adicionar Carga no Forno" ,
                "Limpar e Reparar o Barrado"};

    }
    private static String[] getAutorAutorBd() {
        String[] url = new String[6];
        for (int i = 0; i < 6; i++) {
            url[i] = "https://drive.google.com/open?id=14i1SPHMbykOxU4EkXJmGkdOY9KrE2woz7YMRBsJk25o";
        }
        return url;
    }
    private static String[] getDataAutorBd(){
        return new String[]{
                "19/06/2015" ,
                "19/06/2015" ,
                "29/06/2011" ,
                "21/03/2014" ,
                "16/10/2014" ,
                "19/12/2014"};

    }

    /*public static List<ListaPraticas> GetDataPraticasActivity() {
        String[] numero = getNumeroDataBd();
        String[] titulo = getTituloDataBd();
        String[] autor = getAutorDataBd();
        String[] urlDocumento = getUrlDocumentoBd(6, "https://docs.google.com/document/d/1kV00ZQ3EIwhuyDfVKxwaVaBUdq3Vv-3JF9YREqiP-e0");
        String[] data = getDataDataBd();
        int[] id = GetId(6);
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
    }  //REVISAR LISTA DUPLA*/
    private static String[] getNumeroDataBd(){
        return new String[]{
                "PPA01-0031 - v.9" ,
                "PPA01-0023 - v.10" ,
                "PPA01-0026 - v.6" ,
                "PPA01-0032 - v.11" ,
                "PPA09-0159 - v.21" ,
                "PPA09-0162 - v.15"};

    }
    private static String[] getTituloDataBd(){
        return new String[]{
                "Receber, Conferir e Arquivar Documentos" ,
                "Inspecionar Arquivo de Segurança" ,
                "Conferir Microfilmagem Convencional" ,
                "Atender Solicitações de Consulta" ,
                "Adicionar Carga no Forno" ,
                "Limpar e Reparar o Barrado"};

    }
    private static String[] getAutorDataBd() {
        String[] url = new String[6];
        for (int i = 0; i < 6; i++) {
            url[i] = "https://drive.google.com/open?id=14i1SPHMbykOxU4EkXJmGkdOY9KrE2woz7YMRBsJk25o";
        }
        return url;
    }
    private static String[] getDataDataBd(){
        return new String[]{
                "19/06/2015" ,
                "19/06/2015" ,
                "29/06/2011" ,
                "21/03/2014" ,
                "16/10/2014" ,
                "19/12/2014"};

    }

    /*public static List<ListaPraticas> GetNivelPraticasActivity() {
        String[] numero = getNumeroNivelBd();
        String[] titulo = getTituloNivelBd();
        String[] autor = getAutorNivelBd();
        String[] urlDocumento = getUrlDocumentoBd(6, "https://docs.google.com/document/d/1kV00ZQ3EIwhuyDfVKxwaVaBUdq3Vv-3JF9YREqiP-e0");
        String[] data = getDataNivelBd();
        int[] id = GetId(6);
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
    }  //REVISAR NO DOCNIX!!!!*/
    private static String[] getNumeroNivelBd(){
        return new String[]{
                "PPA01-0031 - v.9" ,
                "PPA01-0023 - v.10" ,
                "PPA01-0026 - v.6" ,
                "PPA01-0032 - v.11" ,
                "PPA09-0159 - v.21" ,
                "PPA09-0162 - v.15"};

    }
    private static String[] getTituloNivelBd(){
        return new String[]{
                "Receber, Conferir e Arquivar Documentos" ,
                "Inspecionar Arquivo de Segurança" ,
                "Conferir Microfilmagem Convencional" ,
                "Atender Solicitações de Consulta" ,
                "Adicionar Carga no Forno" ,
                "Limpar e Reparar o Barrado"};

    }
    private static String[] getAutorNivelBd() {  //REVISAR
        return new String[]{"Receber, Conferir e Arquivar Documentos" ,
                "Inspecionar Arquivo de Segurança" ,
                "Conferir Microfilmagem Convencional" ,
                "Atender Solicitações de Consulta" ,
                "Adicionar Carga no Forno" ,
                "Limpar e Reparar o Barrado"};
    }
    private static String[] getDataNivelBd(){
        return new String[]{
                "19/06/2015" ,
                "19/06/2015" ,
                "29/06/2011" ,
                "21/03/2014" ,
                "16/10/2014" ,
                "19/12/2014"};

    }

    /*public static List<ListaPraticas> GetProcessoPraticasActivity() {
        String[] numero = getNumeroProcessoBd();
        String[] titulo = getTituloProcessoBd();
        String[] autor = getAutorProcessoBd();
        String[] urlDocumento = getUrlDocumentoBd(6, "https://drive.google.com/open?id=12Ir3h3G-PN2yE43gr6u5pI_Y14uassvbU0KxpidsbHo");
        String[] data = getDataProcessoBd();
        int[] id = GetId(6);
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
    }*/
    private static String[] getNumeroProcessoBd(){
        return new String[]{
                "PPA19-0465 - v.6" ,
                "PPA19-0467 - v.3" ,
                "PPA19-0470 - v.1" ,
                "PPA19-0473 - v.1" ,
                "PPA19-0502 - v.0" ,
                "PPA40-0083 - v.0" ,
                "PPA40-0084 - v.0" ,
                "PPA40-0085 - v.0" ,
                "PPA40-0086 - v.0" ,
                "PPA40-0103 - v.4" ,
                "PPA40-0129 - v.5" ,
                "PPA40-0130 - v.3" ,
                "PPA40-0131 - v.3" ,
                "PPA40-0132 - v.1" ,
                "PPA40-0133 - v.1" ,
                "PPA40-0134 - v.9" ,
                "PPA40-0135 - v.9" ,
                "PPA40-0136 - v.11" ,
                "PPA40-0152 - v.1" ,
                "PPA40-0163 - v.1" ,
                "PPA40-0164 - v.0" ,
                "PPA40-0169 - v.1" ,
                "PPA40-0170 - v.1" ,
                "PPA40-0171 - v.0" ,
                "PPA40-0172 - v.2" ,
                "PPA40-0173 - v.1" ,
                "PPA40-0174 - v.1" ,
                "PPA40-0175 - v.0" ,
                "PPA40-0176 - v.1" ,
                "PPA40-0182 - v.2"};

    }
    private static String[] getTituloProcessoBd(){
        return new String[]{
                "Cortar Chapas/Blanks e embalar materiais na TG2" ,
                "Operar ponte rolante por controle remoto" ,
                "Operar ponte rolante L80" ,
                "Preparação e Corte de Chapas Inox e Carbono - TT5" ,
                "Operar carros de transferência no Acabamento a Quente" ,
                "Aplicar Filme Protetivo ? Buterfly" ,
                "Empilhar e Expedir Chapas - Buterfly" ,
                "Realizar Acabamento - Buterfly" ,
                "Receber Chapas - Buterfly" ,
                "Localizar Bobinas - Acabamento Final" ,
                "Receber Bobinas - AP2" ,
                "Preparar Bobinas - AP2" ,
                "Soldar Bobinas - AP2" ,
                "Controlar Tração - AP2" ,
                "Rebobinar Bobinas - AP2" ,
                "Expedir Bobinas - AP2" ,
                "Realizar Acabamento Buffing Bright - AP2" ,
                "Realizar Acabamento Satin Finish - AP2" ,
                "Gerir Retífica de Billy Rolls" ,
                "Realizar Verificação da Calibração do Espectrofotometro" ,
                "Transporte Interno de Materiais" ,
                "Operar Pórtico - OAFF" ,
                "Operar Talhas - OAFF" ,
                "Receber Bobinas e Tiras - TLE" ,
                "Preparar o equipamento para processo - TLE" ,
                "Cortar Bobinas e Tiras - TLE" ,
                "Expedir Bobinas e Tiras - TLE" ,
                "Gerir Ferramental de Corte Transversal - PBs" ,
                "Receber/Estocar insumos - Acabamento Final" ,
                "Processar e Expedir aparas - TL5"};

    }
    private static String[] getAutorProcessoBd() {
        return new String[]{
                "LINCOLN DOS S RODRIGUES" ,
                "LINCOLN DOS S RODRIGUES" ,
                "VIVALDO GERALDO DINIZ" ,
                "JOSE CARLOS ROSA DA SILVA" ,
                "LINCOLN DOS S RODRIGUES" ,
                "Nucleo Acabamento e Servicos em Inox" ,
                "Nucleo Acabamento e Servicos em Inox" ,
                "Nucleo Acabamento e Servicos em Inox" ,
                "Nucleo Acabamento e Servicos em Inox" ,
                "EDNEI ARAUJO CLARA" ,
                "DOUGLAS FERREIRA DE SOUSA" ,
                "DOUGLAS FERREIRA DE SOUSA" ,
                "DOUGLAS FERREIRA DE SOUSA" ,
                "ADIR SOARES DE M JUNIOR" ,
                "ADIR SOARES DE M JUNIOR" ,
                "RAVENY CRISTINY ASSIS FRANCISQUINI" ,
                "ADIR SOARES DE M JUNIOR" ,
                "DOUGLAS FERREIRA DE SOUSA" ,
                "MARCUS MICHEL ELIAS" ,
                "ADIR SOARES DE M JUNIOR" ,
                "SANDEY NOVY ARAUJO ROCHA" ,
                "CLAUDIO SILVA MIRANDA" ,
                "CLAUDIO SILVA MIRANDA" ,
                "Sergio Ricardo Martins" ,
                "ADIR SOARES DE M JUNIOR" ,
                "ADIR SOARES DE M JUNIOR" ,
                "ADIR SOARES DE M JUNIOR" ,
                "EDNEI ARAUJO CLARA" ,
                "EDNEI ARAUJO CLARA" ,
                "EDNEI ARAUJO CLARA"};
    }
    private static String[] getDataProcessoBd(){
        return new String[]{
                "19/10/2017" ,
                "10/07/2014" ,
                "13/07/2012" ,
                "15/07/2015" ,
                "24/10/2012" ,
                "08/01/2010" ,
                "08/01/2010" ,
                "08/01/2010" ,
                "08/01/2010" ,
                "26/07/2017" ,
                "17/01/2017" ,
                "15/04/2011" ,
                "14/11/2012" ,
                "02/03/2011" ,
                "02/03/2011" ,
                "10/07/2017" ,
                "14/07/2017" ,
                "30/05/2016" ,
                "14/03/2011" ,
                "14/03/2011" ,
                "20/12/2010" ,
                "03/02/2018" ,
                "03/02/2018" ,
                "18/01/2017" ,
                "18/04/2018" ,
                "09/05/2017" ,
                "01/02/2018" ,
                "02/02/2017" ,
                "06/02/2018" ,
                "29/05/2018"};

    }

    private static String[] getUrlDocumentoBd(int qtd, String link) {
        String[] url = new String[qtd];
        for (int i = 0; i < qtd; i++) {
            url[i] = link;
        }
        return url;
    }
    private static int[] GetId(int qtdId) {
        int[] qtd = new int[qtdId];
        for (int i = 0; i < qtdId; i++) {
            qtd[i] = i;
        }
        return qtd;
    }
}