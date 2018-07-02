package com.aperam.kryslan.praticaspadrao.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDCore extends SQLiteOpenHelper {
    private static final String NOME_BD = "teste";
    private static final int VERSAO_BD = 6;

    //Essa chamada faz: Se o banco existir, ele chama ele, se não existir, ele irá criar um novo, se tiver uma varsão nova do BdLite
    //Ele vai destruir e reconstruir o banco
    public BDCore(Context c){
        super(c, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("create table pratica(_id integer primary key autoincrement, nome text not null, numero text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        bd.execSQL("drop table pratica");
        onCreate(bd);
    }
}
