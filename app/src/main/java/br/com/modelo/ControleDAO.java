package br.com.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.modelo.BancoDeDados;

public class ControleDAO {

    private SQLiteDatabase comandosSQL;
    private BancoDeDados bd;

    public ControleDAO (Context contexto) {
        bd = new BancoDeDados(contexto);
    }

    public String newRegister (String emailEmpresa, String senhaEmpresa, String cnpjEmpresa) {
        ContentValues cv = new ContentValues();

        cv.put(bd.EMAIL_EMPRESA, emailEmpresa);
        cv.put(bd.SENHA_EMPRESA, senhaEmpresa);
        cv.put(bd.CNPJ_EMPRESA, cnpjEmpresa);

        comandosSQL = bd.getWritableDatabase();
        Long resultado = comandosSQL.insert(bd.NOME_TABELA_EMPRESA, null, cv);

        if(resultado == -1) {
            return "Erro";
        } else {
            return "Sucesso";
        }
    }

    public Cursor validateRegister (String emailEmpresa, String senhaEmpresa) {
        String[] campos = {bd.EMAIL_EMPRESA, bd.SENHA_EMPRESA};

        comandosSQL = bd.getReadableDatabase();

        Cursor cursor;

        String where = bd.EMAIL_EMPRESA + "= '"  + emailEmpresa + "' and " +  bd.SENHA_EMPRESA + " = '" + senhaEmpresa + "'";

        cursor = comandosSQL.query(bd.NOME_TABELA_EMPRESA, campos, where, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }
}
