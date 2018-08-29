package com.aperam.kryslan.praticaspadrao.Model.BancoProvisorioFalso;

import com.aperam.kryslan.praticaspadrao.Model.Domain.DataDeVigenciaDia;
import com.aperam.kryslan.praticaspadrao.Model.Domain.DataDeVigenciaMes;
import com.aperam.kryslan.praticaspadrao.Model.Domain.TelaInicialCards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BdExpandableList {
    //CONSTRUTOR MÊS/DIA
    public static List<DataDeVigenciaMes> CriaMesExpansivel() {
        return Arrays.asList(
                CategoriaJaneiro(),
                CategoriaFevereiro(),
                CategoriaMaco(),
                CategoriaAbril(),
                CategoriaMaio(),
                CategoriaJunho(),
                CategoriaJulho(),
                CategoriaAgosto(),
                CategoriaSetembro(),
                CategoriaOutubro(),
                CategoriaNovembro(),
                CategoriaDezembro());
    }
    private static DataDeVigenciaMes CategoriaJaneiro() {
        return new DataDeVigenciaMes("Janeiro", CriaDias());
    }
    private static DataDeVigenciaMes CategoriaFevereiro() {
        return new DataDeVigenciaMes("Fevereiro", CriaDias());
    }
    private static DataDeVigenciaMes CategoriaMaco() {
        return new DataDeVigenciaMes("Março", CriaDias());
    }
    private static DataDeVigenciaMes CategoriaAbril() {
        return new DataDeVigenciaMes("Abril", CriaDias());
    }
    private static DataDeVigenciaMes CategoriaMaio() {
        return new DataDeVigenciaMes("Maio", CriaDias());
    }
    private static DataDeVigenciaMes CategoriaJunho() {
        return new DataDeVigenciaMes("Junho", CriaDias());
    }
    private static DataDeVigenciaMes CategoriaJulho() {
        return new DataDeVigenciaMes("Julho", CriaDias());
    }
    private static DataDeVigenciaMes CategoriaAgosto() {
        return new DataDeVigenciaMes("Agosto", CriaDias());
    }
    private static DataDeVigenciaMes CategoriaSetembro() {
        return new DataDeVigenciaMes("Setembro", CriaDias());
    }
    private static DataDeVigenciaMes CategoriaOutubro() {
        return new DataDeVigenciaMes("Outubro", CriaDias());
    }
    private static DataDeVigenciaMes CategoriaNovembro() {
        return new DataDeVigenciaMes("Novembro", CriaDias());
    }
    private static DataDeVigenciaMes CategoriaDezembro() {
        return new DataDeVigenciaMes("Dezembro", CriaDias());
    }
    private static List<DataDeVigenciaDia> CriaDias() {  //FAZER LISTA MAIS PRÁTICA.
        DataDeVigenciaDia um = new DataDeVigenciaDia("1"); DataDeVigenciaDia dois = new DataDeVigenciaDia("2"); DataDeVigenciaDia tres = new DataDeVigenciaDia("3"); DataDeVigenciaDia quatro = new DataDeVigenciaDia("4");
        DataDeVigenciaDia cinco = new DataDeVigenciaDia("5"); DataDeVigenciaDia seis = new DataDeVigenciaDia("6"); DataDeVigenciaDia sete = new DataDeVigenciaDia("7"); DataDeVigenciaDia oito = new DataDeVigenciaDia("8");
        DataDeVigenciaDia nove = new DataDeVigenciaDia("9"); DataDeVigenciaDia dez = new DataDeVigenciaDia("10"); DataDeVigenciaDia onze = new DataDeVigenciaDia("11"); DataDeVigenciaDia doze = new DataDeVigenciaDia("12");

        return Arrays.asList(um, dois, tres, quatro, cinco, seis, sete, oito, nove, dez, onze, doze);
    }

    public static List<TelaInicialCards> GetDataDeVigenciaMainActivity() {  //PEGAR DEPOIS NO DOCNIX.
        int[] id = GetTipo(2, 12);  //Futuramente criar clase para cada um em domain.
        String[] foto = GetVoidString(12);
        String[] titulo = GetTituloDataDeVigencia();
        String[] grupo = GetGrupo("Data de Vigência", 12);
        List<TelaInicialCards> listAux = new ArrayList<>();

        for (int i = 0; i < id.length; i++) {
            TelaInicialCards p = new TelaInicialCards(
                    id[i % id.length],
                    foto[i % foto.length],
                    titulo[i % titulo.length],
                    grupo[i % grupo.length]);
            //link[i % link.length]);
            listAux.add(p);
        }
        return listAux;
    }

    //UTILS
    private static int[] GetTipo(int valor, int qtd){
        int[] qtdVoid = new int[qtd];
        for (int i = 0; i < qtd; i++) {
            qtdVoid[i] = valor;
        }
        return qtdVoid;
    }
    private static String[] GetGrupo(String grupo, int qtdId){
        String[] qtd = new String[qtdId];
        for (int i = 0; i < qtdId; i++) {
            qtd[i] = grupo;
        }
        return qtd;
    }
    private static String[] GetVoidString(int qtdId){
        String[] qtd = new String[qtdId];
        for (int i = 0; i < qtdId; i++) {
            qtd[i] = "" + i;
        }
        return qtd;
    }
    private static String[] GetTituloDataDeVigencia(){
        return new String[]{
                "2017",
                "2016",
                "2015",
                "2014",
                "2013",
                "2012",
                "2011",
                "2010",
                "2009",
                "2008",
                "2007",
                "2006", "2018"};
    }
}
