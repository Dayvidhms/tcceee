package br.com.modelo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by dayvid on 11/05/2017.
 */

public class ItemDAO {

    private SQLiteDatabase comandosSQL;
    private BancoDeDados bd;

    public String inserirItem (String nomeItem) {
        ContentValues cv = new ContentValues();

        cv.put(bd.NOME_ITEM, nomeItem);

        comandosSQL = bd.getWritableDatabase();
        Long resultado = comandosSQL.insert(bd.NOME_TABELA_ITEM, null, cv);

        if(resultado == -1) {
            return "Erro";
        } else {
            return "Sucesso";
        }
    }
}
