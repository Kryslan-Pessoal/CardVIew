package com.aperam.kryslan.praticaspadrao.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDCore extends SQLiteOpenHelper {
    private static final String NOME_BD = "bancoApp";
    private static final int VERSAO_BD = 2;

    //Essa chamada faz: Se o banco existir, ele chama ele, se não existir, ele irá criar um novo, se tiver uma varsão nova do BdLite
    //Ele vai destruir e reconstruir o banco
    public BDCore(Context c){
        super(c, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("create table pratica(_id integer primary key autoincrement, nome text not null, numero text not null, autor text not null, data text not null);");
        bd.execSQL("create table tipoListaFrags(_id integer primary key, fragments text not null, tipoLista integer not null);");
        /*Tipos de lista:
        0 = Lista com imgaens grandes.
        1 = Lista com imagens, mas resumida
        2 = Lista resumida sem imagens.*/

        PreencheColunasTipoListaFrags(bd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        bd.execSQL("drop table pratica");
        bd.execSQL("drop table tipoListaFrags");
        onCreate(bd);
    }

    private void PreencheColunasTipoListaFrags(SQLiteDatabase bd){
        ContentValues valores = new ContentValues();
        valores.put("_id", 0);
        valores.put("fragments", "areaEmitente");
        valores.put("tipoLista", 0);
        bd.insert("tipoListaFrags", null, valores);

        valores.clear();
        valores.put("_id", 1);
        valores.put("fragments", "areasRelacionadas");
        valores.put("tipoLista", 0);
        bd.insert("tipoListaFrags", null, valores);

        valores.clear();
        valores.put("_id", 2);
        valores.put("fragments", "nivel");
        valores.put("tipoLista", 0);
        bd.insert("tipoListaFrags", null, valores);

        valores.clear();
        valores.put("_id", 3);
        valores.put("fragments", "historico");
        valores.put("tipoLista", 0);
        bd.insert("tipoListaFrags", null, valores);
    }
}
