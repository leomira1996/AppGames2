package com.example.miranda.appgames.DAO;

/**
 * Created by Miranda on 11/06/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.miranda.appgames.Util.BancoUtil;
import com.example.miranda.appgames.Factory.DatabaseFactory;
import com.example.miranda.appgames.Model.Game;


public class GameDAO {
    private SQLiteDatabase db;
    private DatabaseFactory banco;

    public GameDAO(Context context){
        banco = new DatabaseFactory(context);
    }

    public String insereDado(Game game){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.TITULO_GAME, game.getTitulo());
        valores.put(BancoUtil.NOTA_GAME, game.getNota());
        valores.put(BancoUtil.DESCRICAO_GAME, game.getDescricao());
        valores.put(BancoUtil.IMAGEM_GAME, game.getImagem());

        resultado = db.insert(BancoUtil.TABELA_GAME, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {BancoUtil.ID_GAME,BancoUtil.TITULO_GAME,BancoUtil.NOTA_GAME};
        db = banco.getReadableDatabase();

        cursor = db.query(BancoUtil.TABELA_GAME, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {BancoUtil.ID_GAME,BancoUtil.TITULO_GAME,BancoUtil.NOTA_GAME,BancoUtil.DESCRICAO_GAME,BancoUtil.IMAGEM_GAME};
        String where = BancoUtil.ID_GAME + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(BancoUtil.TABELA_GAME,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alteraRegistro(Game game){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = BancoUtil.ID_GAME + "=" + game.get_ID();

        valores = new ContentValues();
        valores.put(BancoUtil.TITULO_GAME, game.getTitulo());
        valores.put(BancoUtil.NOTA_GAME, game.getNota());
        valores.put(BancoUtil.DESCRICAO_GAME, game.getDescricao());
        valores.put(BancoUtil.IMAGEM_GAME, game.getImagem());

        db.update(BancoUtil.TABELA_GAME,valores,where,null);
        db.close();
    }

    public void deletaRegistro(int id){
        String where = BancoUtil.ID_GAME + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(BancoUtil.TABELA_GAME,where,null);
        db.close();
    }
}
