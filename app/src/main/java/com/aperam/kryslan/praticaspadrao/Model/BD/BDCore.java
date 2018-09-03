package com.aperam.kryslan.praticaspadrao.Model.BD;

import android.content.ContentValues;
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
    private static final int VERSAO_BD = 14;

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

        bd.execSQL("CREATE TABLE `tipoLista` (`tipoLista`INTEGER NOT NULL);");
        /*Tipos de lista:
        0 = Lista com imgaens grandes.
        1 = Lista com imagens, mas resumida
        2 = Lista resumida sem imagens.*/

        bd.execSQL("CREATE TABLE `historico` (`_id`INTEGER NOT NULL PRIMARY KEY," +
                "`id_pratica`INTEGER NOT NULL," +
                "FOREIGN KEY(`id_pratica`) REFERENCES `pratica`);");  //Define to tipo de lista em cada Frigamento.

        try {
            PopulaBanco(bd);
        } catch (IOException e) {
            Toast.makeText(c, "Erro ao popular o banco: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

      PreencheColunasTipoListaFrags(bd);  //Ao criar o banco de dados, seta os tipos de listas como 0 (imagem grande e texto).
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        bd.execSQL("DROP TABLE IF EXISTS pratica");
        bd.execSQL("DROP TABLE IF EXISTS areaEmitente");
        bd.execSQL("DROP TABLE IF EXISTS areasRelacionadas");
        bd.execSQL("DROP TABLE IF EXISTS autor");
        bd.execSQL("DROP TABLE IF EXISTS dataPratica");
        bd.execSQL("DROP TABLE IF EXISTS processo");
        bd.execSQL("DROP TABLE IF EXISTS nivel");
        bd.execSQL("DROP TABLE IF EXISTS tipoLista");
        bd.execSQL("DROP TABLE IF EXISTS historico");
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

    private void PreencheColunasTipoListaFrags(SQLiteDatabase bd){
        ContentValues valor = new ContentValues();
        valor.put("tipoLista", 0);
        bd.insert("tipoLista", null, valor);
    }
}
