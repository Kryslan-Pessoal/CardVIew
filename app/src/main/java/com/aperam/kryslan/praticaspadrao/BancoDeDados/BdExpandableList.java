package com.aperam.kryslan.praticaspadrao.BancoDeDados;

import com.aperam.kryslan.praticaspadrao.domain.DataDeVigenciaDia;
import com.aperam.kryslan.praticaspadrao.domain.DataDeVigenciaMes;

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
}
