package br.com.modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDeDados extends SQLiteOpenHelper{

    public static final String NOME_BANCO = "bancoEmpresa.db";

    public static final String NOME_TABELA_EMPRESA = "empresa";

    public static final String ID_EMPRESA = "_id";
    public static final String EMAIL_EMPRESA = "email_empresa";
    public static final String SENHA_EMPRESA = "senha_empresa";
    public static final String CNPJ_EMPRESA = "cnpj_empresa";

    public static final String NOME_TABELA_ITEM = "item";
    public static final String ID_ITEM = "_1d";
    public static final String NOME_ITEM = "nome";

    public static int VERSAO = 1;

    public BancoDeDados (Context contexto) {
        super(contexto, NOME_BANCO, null, VERSAO);
    }

    public void onCreate (SQLiteDatabase db) {
        String sqlCreate = "create table " + NOME_TABELA_EMPRESA
                + "("
                + ID_EMPRESA + " integer primary key autoincrement, "
                + EMAIL_EMPRESA  + " text not null, "
                + SENHA_EMPRESA + " text not null, "
                + CNPJ_EMPRESA + " text not null);";

        db.execSQL(sqlCreate);

        sqlCreate = "create table " + NOME_TABELA_ITEM +
                "("
                + ID_ITEM + " integer primary key autoincrement, "
                + NOME_ITEM + " text not null);";

        db.execSQL(sqlCreate);
    }

    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + NOME_TABELA_EMPRESA);
        onCreate(db);
    }
}
