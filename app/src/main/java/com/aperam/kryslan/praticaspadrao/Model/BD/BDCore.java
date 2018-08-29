package com.aperam.kryslan.praticaspadrao.Model.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.aperam.kryslan.praticaspadrao.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BDCore extends SQLiteOpenHelper {
    //GLOBAIS
    private static final String NOME_BD = "bancoApp";
    private static final int VERSAO_BD = 8;

    private Context c;

    //Essa chamada faz: Se o banco existir, ele chama ele, se não existir, ele irá criar um novo, se tiver uma varsão nova do BdLite
    //Ele vai destruir e reconstruir o banco
    public BDCore(Context c){
        super(c, NOME_BD, null, VERSAO_BD);
        this.c = c;
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("CREATE TABLE `pratica` (" +
                "`_id`INTEGER NOT NULL PRIMARY KEY," +
                "`nome`TEXT NOT NULL," +
                "`numero`TEXT NOT NULL," +
                "`linkDocumento`TEXT NOT NULL," +
                "`faseDocumento`TEXT," +
                "`dataVersao`TEXT," +
                "`treinamento`INTEGER," +
                "`revisorTecnico`INTEGER," +
                "`revisorSstMa`TEXT," +
                "`aprovador`TEXT," +
                "`id_areaEmitente`INTEGER," +
                "`id_areasRelacionadas`INTEGER," +
                "`id_autor`INTEGER," +
                "`id_data`INTEGER," +
                "`id_nivel`INTEGER," +
                "`id_processo`INTEGER," +
                "FOREIGN KEY(`id_areaEmitente`) REFERENCES `areaEmitente`(`_id`)," +
                "FOREIGN KEY(`id_areasRelacionadas`) REFERENCES `areasRelacionadas`(`_id`)," +
                "FOREIGN KEY(`id_autor`) REFERENCES `autor`(`_id`)," +
                "FOREIGN KEY(`id_data`) REFERENCES `dataPratica`(`_id`)," +
                "FOREIGN KEY(`id_nivel`) REFERENCES `nivel`(`_id`)," +
                "FOREIGN KEY(`id_processo`) REFERENCES `processo`(`_id`));");

        bd.execSQL("CREATE TABLE `areaEmitente` (" +
                "`_id`INTEGER NOT NULL PRIMARY KEY," +
                "`nome`INTEGER NOT NULL," +
                "`imagem`TEXT);");
        bd.execSQL("CREATE TABLE `areasRelacionadas` (" +
                "`_id`INTEGER NOT NULL PRIMARY KEY," +
                "`nome`INTEGER NOT NULL," +
                "`imagem`TEXT);");
        bd.execSQL("CREATE TABLE `autor` (" +
                "`_id`INTEGER NOT NULL PRIMARY KEY," +
                "`nome`INTEGER NOT NULL);");
        bd.execSQL("CREATE TABLE `dataPratica` (" +
                "`_id`INTEGER NOT NULL PRIMARY KEY," +
                "`nome`INTEGER NOT NULL);");
        bd.execSQL("CREATE TABLE `processo` (" +
                "`_id`INTEGER NOT NULL PRIMARY KEY," +
                "`nome`INTEGER NOT NULL," +
                "`imagem`TEXT);");
        bd.execSQL("CREATE TABLE `nivel` (" +
                "`_id`INTEGER NOT NULL PRIMARY KEY," +
                "`nome`INTEGER NOT NULL," +
                "`imagem`TEXT);");

        bd.execSQL("CREATE TABLE `tipoLista` (`_id`INTEGER NOT NULL," +
                "`tipoLista`INTEGER NOT NULL," +
                "`id_categorias`INTEGER NOT NULL," +  ///CORRIGIR PQ AGR NÃO TEM MAIS "id_categorias"
                "FOREIGN KEY(`id_categorias`) REFERENCES `categoria`(`_id`)," +
                "PRIMARY KEY(`_id`));");

        bd.execSQL("CREATE TABLE `historico` (`_id`INTEGER NOT NULL PRIMARY KEY," +
                "`id_pratica`INTEGER NOT NULL," +
                "FOREIGN KEY(`id_pratica`) REFERENCES `pratica`);");  //Define to tipo de lista em cada Frigamento.

        try {
            PopulaBanco(bd);
        } catch (IOException e) {
            Toast.makeText(c, "Erro ao popular o banco: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        /*Tipos de lista:
        0 = Lista com imgaens grandes.
        1 = Lista com imagens, mas resumida
        2 = Lista resumida sem imagens.*/
//      PreencheColunasTipoListaFrags(bd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        bd.execSQL("drop table pratica");
        bd.execSQL("drop table areaEmitente");
        bd.execSQL("drop table areasRelacionadas");
        bd.execSQL("drop table autor");
        bd.execSQL("drop table dataPratica");
        bd.execSQL("drop table processo");
        bd.execSQL("drop table nivel");
        bd.execSQL("drop table tipoLista");
        bd.execSQL("drop table historico");
        onCreate(bd);
    }

    private void PopulaBanco(SQLiteDatabase bd) throws IOException {  //Pega um arquivo de res (recursos) e excecuta o seu comando SQL.
        //ABRINDO O ARQUIVO
        InputStream insertsStream = c.getResources().openRawResource(R.raw.ppas_bd);
        BufferedReader insertReader = new BufferedReader(new InputStreamReader(insertsStream));

        //Assumindo que o arquivo vai ter cada comando separado por quebras de linha.
        while (insertReader.ready()) {
            String insertStmt = insertReader.readLine();
            bd.execSQL(insertStmt);
        }
        insertReader.close();
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
