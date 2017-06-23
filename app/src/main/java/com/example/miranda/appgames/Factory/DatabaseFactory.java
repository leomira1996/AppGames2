package com.example.miranda.appgames.Factory;

/**
 * Created by Miranda on 11/06/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.miranda.appgames.Util.BancoUtil;

public class DatabaseFactory extends SQLiteOpenHelper {

    public DatabaseFactory(Context context){
        super(context, BancoUtil.NOME_BANCO,null,BancoUtil.VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String  sql = "CREATE TABLE "+BancoUtil.TABELA_CATEGORIA+"("
                + BancoUtil.ID_CATEGORIA + " integer primary key autoincrement,"
                + BancoUtil.TITULO_CATEGORIA + " text"
                +")";
        db.execSQL(sql);

        sql = "CREATE TABLE "+BancoUtil.TABELA_GAME+"("
                + BancoUtil.ID_GAME + " integer primary key autoincrement,"
                + BancoUtil.TITULO_GAME + " text,"
                + BancoUtil.NOTA_GAME + " integer,"
                + BancoUtil.DESCRICAO_GAME + " text,"
                + BancoUtil.IMAGEM_GAME + " text,"
                + BancoUtil.AVALIACAO_GAME + " double,"
                + BancoUtil.CATEGORIA_GAME + " integer,"
                + " FOREIGN KEY (" + BancoUtil.CATEGORIA_GAME + ") REFERENCES " + BancoUtil.TABELA_CATEGORIA + "(" + BancoUtil.ID_CATEGORIA + ")"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_CATEGORIA);
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_GAME);

        onCreate(db);
    }
}
