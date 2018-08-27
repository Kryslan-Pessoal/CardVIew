package com.aperam.kryslan.praticaspadrao.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDCore extends SQLiteOpenHelper {
    private static final String NOME_BD = "bancoApp";
    private static final int VERSAO_BD = 5;

    Context c;

    //Essa chamada faz: Se o banco existir, ele chama ele, se não existir, ele irá criar um novo, se tiver uma varsão nova do BdLite
    //Ele vai destruir e reconstruir o banco
    public BDCore(Context c){
        super(c, NOME_BD, null, VERSAO_BD);
        this.c = c;
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("CREATE TABLE `categoria` (`_id`INTEGER NOT NULL,`nome`TEXT NOT NULL,PRIMARY KEY(`_id`));");
        bd.execSQL("CREATE TABLE `historico` (`_id`INTEGER NOT NULL PRIMARY KEY,`pratica_id`INTEGER NOT NULL,FOREIGN KEY(`pratica_id`) REFERENCES `pratica`);");  //Define to tipo de lista em cada Frigamento.
        bd.execSQL("CREATE TABLE `pratica` (`_id`INTEGER NOT NULL PRIMARY KEY,`numero`TEXT NOT NULL,`nome`TEXT NOT NULL,`linkDocumento`TEXT,`faseDoDocumento`INTEGER,`etapaDoProcesso`TEXT,`dataDaVersao`TEXT,`necessidadeDeTreinamento`INTEGER,`revisorTecnico`TEXT,`revisorSstMa`TEXT,`aprovador`TEXT);");
        bd.execSQL("CREATE TABLE `subCategoria` (`_id`INTEGER NOT NULL PRIMARY KEY,`nome`TEXT NOT NULL,`imagem`TEXT,`categoria_id`INTEGER NOT NULL,FOREIGN KEY(`categoria_id`) REFERENCES `categoria`(`_id`));");
        bd.execSQL("CREATE TABLE `subCategoria_pratica` (`pratica_id`INTEGER NOT NULL,`subCategoria_id`INTEGER NOT NULL,FOREIGN KEY(`subCategoria_id`) REFERENCES `subCategoria`(`_id`),FOREIGN KEY(`pratica_id`) REFERENCES `pratica`(`_id`));");
        bd.execSQL("CREATE TABLE `tipoLista` (`_id`INTEGER NOT NULL,`tipoLista`INTEGER NOT NULL,`categorias_id`INTEGER NOT NULL,FOREIGN KEY(`categorias_id`) REFERENCES `categoria`(`_id`),PRIMARY KEY(`_id`));");
        /*Tipos de lista:
        0 = Lista com imgaens grandes.
        1 = Lista com imagens, mas resumida
        2 = Lista resumida sem imagens.*/
//        PreencheColunasTipoListaFrags(bd);



    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        bd.execSQL("drop table categoria");
        bd.execSQL("drop table historico");
        bd.execSQL("drop table pratica");
        bd.execSQL("drop table subCategoria");
        bd.execSQL("drop table subCategoria_pratica");
        bd.execSQL("drop table tipoLista");
        onCreate(bd);
    }

    /*private void PreencheColunasTipoListaFrags(SQLiteDatabase bd){
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
    }*/
}
