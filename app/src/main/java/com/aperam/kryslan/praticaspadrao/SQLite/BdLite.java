package com.aperam.kryslan.praticaspadrao.SQLite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.aperam.kryslan.praticaspadrao.domain.ListaPraticas;
import com.aperam.kryslan.praticaspadrao.domain.TelaInicialCards;

import java.util.ArrayList;
import java.util.List;

public class BdLite {
    private static SQLiteDatabase bd;

    public BdLite(Context c){
        BDCore auxBd = new BDCore(c);
        bd = auxBd.getWritableDatabase();
    }

    public void inserir(ListaPraticas pratica){
        ContentValues valores = new ContentValues();
        valores.put("nome", pratica.getTitulo());
        valores.put("numero", pratica.getNumero());
        valores.put("autor", pratica.getNumero());
        valores.put("data", pratica.getNumero());

        bd.insert("pratica", null, valores);
    }

    public static void inserirHistoricoPesquisa(TelaInicialCards historicoSearch){
        ContentValues valores = new ContentValues();
        valores.put("texto", historicoSearch.getTextoPrincipal());

        bd.insert("historicoPesquisa", null, valores);
    }

    public static List<TelaInicialCards> buscarHistoricoPesquisa(){
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

    public void deletarHistoricoPesquisa(TelaInicialCards historicoSearch){
        bd.delete("historicoPesquisa", "_id = ? " , new String[]{"" + historicoSearch.getId()});  //A "?" do segundo parâmetro será substituído pelo terceiro parâmetro.
    }
    public void deletarTudoHistoricoPesquisa(){
        bd.execSQL("delete from "+ "historicoPesquisa");
    }


    public void deletar(ListaPraticas pratica){
        bd.delete("pratica", "_id = ? " , new String[]{"" + pratica.getNumeroId()});  //A "?" do segundo parâmetro será substituído pelo terceiro parâmetro.
    }
    public void deletarTudo(){
        bd.execSQL("delete from "+ "pratica");
    }

    public List<ListaPraticas> buscar(){
        List<ListaPraticas> list = new ArrayList<ListaPraticas>();
        String[] colunas = new String[]{"_id", "nome", "numero", "autor", "data"};
        @SuppressLint("Recycle") Cursor cursor = bd.query("pratica", colunas , null, null, null, null, "_id DESC");  //A "?" do segundo parâmetro será substituído pelo terceiro parâmetro.

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{
                ListaPraticas p = new ListaPraticas();
                p.setNumeroId(cursor.getInt(0));
                p.setTitulo(cursor.getString(1));
                p.setNumero(cursor.getString(2));
                list.add(p);
            }while(cursor.moveToNext());
        }

        return(list);
    }

    public static void atualizaTipoLista(int idFragment, int tipoLista){
        /*Tipos de lista (Id Fragment):
        0 = Lista com imgaens grandes.
        1 = Lista com imagens, mas resumida
        2 = Lista resumida sem imagens.*/

        ContentValues valores = new ContentValues();

        valores.put("tipoLista", tipoLista);

        bd.update("tipoListaFrags", valores, "_id = ?",  new String[]{""+idFragment});
    }
    public static int buscaTipoLista(int idFrag){
        @SuppressLint("Recycle") Cursor cursor = bd.rawQuery("SELECT tipoLista FROM tipoListaFrags WHERE _id=?", new String[] {idFrag + ""});
        int idTipoLista = 0;
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            idTipoLista = cursor.getInt(cursor.getColumnIndex("tipoLista"));
        }

        return idTipoLista;
    }
}
