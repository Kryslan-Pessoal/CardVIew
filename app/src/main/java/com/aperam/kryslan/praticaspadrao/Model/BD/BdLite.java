package com.aperam.kryslan.praticaspadrao.Model.BD;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aperam.kryslan.praticaspadrao.Model.Domain.ListaPraticas;
import com.aperam.kryslan.praticaspadrao.Model.Domain.TelaInicialCards;

import java.util.ArrayList;
import java.util.List;

public class BdLite {
    private static SQLiteDatabase bd;

    public BdLite(Context c){
        BDCore auxBd = new BDCore(c);
        bd = auxBd.getWritableDatabase();
    }

    //region SELECT
    //CATEGORIAS
    public static List<TelaInicialCards> SelectSubCategoria(String categoria){
        return SelectSubCategoria(categoria, true);
    }
    @SuppressLint("Recycle")
    public static List<TelaInicialCards> SelectSubCategoria(String categoria, boolean ordenar){
        List<TelaInicialCards> list = new ArrayList<>();
        String[] colunas;
        @SuppressLint("Recycle") Cursor cursor;

        String orderBy;
        if(ordenar) orderBy = "nome ASC";
        else orderBy = " nome DESC";

        if(categoria.equals("areaEmitente") || categoria.equals("areasRelacionadas") || categoria.equals("nivel") ||
                categoria.equals("processo")){  //Se for essas categorias é porque tem imagem.
            colunas = new String[]{"_id", "nome", "imagem"};
            cursor = bd.query(categoria, colunas , "_id != ?", new String[]{"1"}, null, null, orderBy);  //A "?" do segundo parâmetro será substituído pelo terceiro parâmetro.
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    TelaInicialCards p = new TelaInicialCards();
                    p.setId(cursor.getInt(0));
                    p.setTextoPrincipal(cursor.getString(1));
                    p.setFotoUrl(cursor.getString(2));
                    p.setGrupo(categoria);
                    list.add(p);
                } while (cursor.moveToNext());
            }
        }else{
            colunas = new String[]{"_id", "nome"};
            cursor = bd.query(categoria, colunas , "_id != ?", new String[]{"1"}, null, null, orderBy);  //A "?" do segundo parâmetro será substituído pelo terceiro parâmetro.
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    TelaInicialCards p = new TelaInicialCards();
                    p.setId(cursor.getInt(0));
                    p.setTextoPrincipal(cursor.getString(1));
                    p.setGrupo(categoria);
                    list.add(p);
                } while (cursor.moveToNext());
            }
        }
        return(list);
    }

    //LISTA DE PRÁTICAS
    public static List<ListaPraticas> SelectListPraticas(String categoria, int idCategoria) {
        return SelectListPraticas(categoria, idCategoria, " nome ASC ");
    }
    @SuppressLint("Recycle")
    public static List<ListaPraticas> SelectListPraticas(String categoria, int idCategoria, String ordenar) {
        List<ListaPraticas> list = new ArrayList<>();
        String[] colunas = new String[]{"_id", "nome", "numero", "linkDocumento", "faseDocumento", "dataVersao", "treinamento", "revisorTecnico",
                "revisorSstMa", "aprovador"};

        @SuppressLint("Recycle") Cursor cursor = bd.query("pratica", colunas , categoria + " == ?", new String[]{"" + idCategoria}, null, null, ordenar);  //A "?" do segundo parâmetro será substituído pelo terceiro parâmetro.
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                ListaPraticas p = new ListaPraticas();

                String treinamento;  //Converte treinamento de inteiro para String.
                if(cursor.getString(6).equals("1"))
                    treinamento = "Não";
                else treinamento = "Sim";

                p.setId(cursor.getInt(0));
                p.setNome(cursor.getString(1));
                p.setNumero(cursor.getString(2));
                p.setLinkDocumento(cursor.getString(3));
                p.setFaseDocumento(cursor.getString(4));
                p.setDataVersao(cursor.getString(5));
                p.setTreinamento(treinamento);
                p.setRevisorTecnico(cursor.getString(7));
                p.setRevisorSstMa(cursor.getString(8));
                p.setAprovador(cursor.getString(9));
                list.add(p);
            }while(cursor.moveToNext());
        }
        return(list);
    }

    //TODAS AS PRÁTICAS
    public static List<ListaPraticas> SelectTodasAsPraticas() {
        List<ListaPraticas> list = new ArrayList<>();
        String[] colunas = new String[]{"_id", "nome", "numero", "linkDocumento", "faseDocumento", "dataVersao", "treinamento", "revisorTecnico",
                "revisorSstMa", "aprovador"};
        @SuppressLint("Recycle") Cursor cursor = bd.query("pratica", colunas , null, null, null, null, null);  //A "?" do segundo parâmetro será substituído pelo terceiro parâmetro.
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                ListaPraticas p = new ListaPraticas();
                String treinamento;  //Converte treinamento de inteiro para String.
                if(cursor.getString(6).equals("1"))
                    treinamento = "Não";
                else treinamento = "Sim";
                p.setId(cursor.getInt(0));
                p.setNome(cursor.getString(1));
                p.setNumero(cursor.getString(2));
                p.setLinkDocumento(cursor.getString(3));
                p.setFaseDocumento(cursor.getString(4));
                p.setDataVersao(cursor.getString(5));
                p.setTreinamento(treinamento);
                p.setRevisorTecnico(cursor.getString(7));
                p.setRevisorSstMa(cursor.getString(8));
                p.setAprovador(cursor.getString(9));
                list.add(p);
            }while(cursor.moveToNext());
        }
        return(list);
    }

    //HISTÓRICO
    @SuppressLint("Recycle")
    public static List<ListaPraticas> SelectHistorico() {
        List<ListaPraticas> list = new ArrayList<>();
        String query = "select p._id, nome, numero, linkDocumento, faseDocumento, dataVersao, treinamento, revisorTecnico," +
                " revisorSstMa, aprovador from pratica p join historico h on p._id = h.id_pratica;";
        @SuppressLint("Recycle") Cursor cursor = bd.rawQuery(query, null);  //A "?" do segundo parâmetro será substituído pelo terceiro parâmetro.
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                ListaPraticas p = new ListaPraticas();

                String treinamento;  //Converte treinamento de inteiro para String.
                if(cursor.getString(6).equals("1"))
                    treinamento = "Não";
                else treinamento = "Sim";
                p.setId(cursor.getInt(0));
                p.setNome(cursor.getString(1));
                p.setNumero(cursor.getString(2));
                p.setLinkDocumento(cursor.getString(3));
                p.setFaseDocumento(cursor.getString(4));
                p.setDataVersao(cursor.getString(5));
                p.setTreinamento(treinamento);
                p.setRevisorTecnico(cursor.getString(7));
                p.setRevisorSstMa(cursor.getString(8));
                p.setAprovador(cursor.getString(9));
                list.add(p);
            }while(cursor.moveToNext());
        }
        return(list);
    }


    //HISTÓRICO DE PESQUISA
    public static List<TelaInicialCards> SelectHistoricoPesquisa(){
        List<TelaInicialCards> list = new ArrayList<>();
        String[] colunas = new String[]{"_id", "texto"};
        @SuppressLint("Recycle") Cursor cursor = bd.query("historicoPesquisa", colunas , null, null, null, null, "_id DESC");  //A "?" do segundo parâmetro será substituído pelo terceiro parâmetro.
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                TelaInicialCards p = new TelaInicialCards();
                p.setId(cursor.getInt(0));
                p.setTextoPrincipal(cursor.getString(1));
                list.add(p);
            }while(cursor.moveToNext());
        }
        return(list);
    }

    //TIPO LISTA
    public static int SelectTipoLista(){
        @SuppressLint("Recycle") Cursor cursor = bd.rawQuery("SELECT tipoLista FROM tipoLista", null);
        int tipoLista = 0;
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            tipoLista = cursor.getInt(cursor.getColumnIndex("tipoLista"));
        }
        return tipoLista;
    }

    //endregion

    //region INSERT
    public void InsertHistorico(int idPratica){
        ContentValues valores = new ContentValues();
        valores.put("id_pratica", idPratica);

        bd.insert("historico", null, valores);
    }

    public static void inserirHistoricoPesquisa(TelaInicialCards historicoSearch){
        ContentValues valores = new ContentValues();
        valores.put("texto", historicoSearch.getTextoPrincipal());

        bd.insert("historicoPesquisa", null, valores);
    }
    //endregion

    //region DELETA
    public void deletarHistoricoPesquisa(TelaInicialCards historicoSearch){
        bd.delete("historicoPesquisa", "_id = ? " , new String[]{"" + historicoSearch.getId()});  //A "?" do segundo parâmetro será substituído pelo terceiro parâmetro.
    }
    public void deletarTudoHistoricoPesquisa(){
        bd.execSQL("delete from "+ "historicoPesquisa");
    }


    public void DeletaPratica(ListaPraticas pratica){
        bd.delete("pratica", "_id = ? " , new String[]{"" + pratica.getId()});  //A "?" do segundo parâmetro será substituído pelo terceiro parâmetro.
    }
    public void DeletaHistorico(){
        bd.execSQL("delete from "+ "historico");
    }
    //endregion

    //region UPDATE
    public static void atualizaTipoLista(int tipoLista){
        /*Tipos de lista (Id Fragment):
        0 = Lista com imgaens grandes.
        1 = Lista com imagens, mas resumida
        2 = Lista resumida sem imagens.*/

        ContentValues valores = new ContentValues();

        valores.put("tipoLista", tipoLista);

        bd.update("tipoLista", valores, null,  null);
    }
    //endregion
}
