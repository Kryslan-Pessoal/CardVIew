package com.aperam.kryslan.praticaspadrao.SQLite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aperam.kryslan.praticaspadrao.domain.ListaPraticas;

import java.util.ArrayList;
import java.util.List;

public class BdLite {
    private SQLiteDatabase bd;

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

    public void deletar(ListaPraticas pratica){
        bd.delete("pratica", "_id = ? " , new String[]{"" + pratica.getNumeroId()});  //A "?" do segundo parâmetro será substituído pelo terceiro parâmetro.
    }

    public List<ListaPraticas> buscar(){
        List<ListaPraticas> list = new ArrayList<ListaPraticas>();
        String[] colunas = new String[]{"_id", "nome", "numero", "autor", "data"};
        @SuppressLint("Recycle") Cursor cursor = bd.query("pratica", colunas , null, null, null, null, "_id ASC");  //A "?" do segundo parâmetro será substituído pelo terceiro parâmetro.

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

    /*public void atualizar(ListaPraticas pratica){
        ContentValues valores = new ContentValues();
        valores.put("nome", pratica.getTitulo());
        valores.put("numero", pratica.getNumero());

        bd.update("pratica", valores, "_id = ?",  new String[]{""+pratica.getNumeroId()});
    }*/
}
