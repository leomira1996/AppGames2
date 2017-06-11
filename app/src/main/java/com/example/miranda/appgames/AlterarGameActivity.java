package com.example.miranda.appgames;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import com.example.miranda.appgames.Util.BancoUtil;
import com.example.miranda.appgames.DAO.GameDAO;
import com.example.miranda.appgames.Model.Game;
import com.example.miranda.appgames.R;
import com.example.miranda.appgames.Util.Util;


public class AlterarGameActivity extends Activity {

    EditText game;
    EditText nota;
    EditText descricao;
    ImageView poster;
    Button alterar;
    Button deletar;
    Cursor cursor;
    GameDAO crud;
    String codigo;
    final int ACTIVITY_SELECT_IMAGE = 1234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_game);

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new GameDAO(getBaseContext());

        game = (EditText)findViewById(R.id.editText4);
        nota = (EditText)findViewById(R.id.editText5);
        descricao = (EditText)findViewById(R.id.editText6);
        poster = (ImageView) findViewById(R.id.imageView2);

        alterar = (Button)findViewById(R.id.button2);

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        game.setText(cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.TITULO_GAME)));
        nota.setText(cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NOTA_GAME)));
        descricao.setText(cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.DESCRICAO_GAME)));
        poster.setImageBitmap(Util.Base64toImage(cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.IMAGEM_GAME))));

        poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Selecione a Imagem"),ACTIVITY_SELECT_IMAGE);
            }
        });

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game gameObj = new Game();
                gameObj.set_ID(Integer.parseInt(codigo));
                gameObj.setTitulo(game.getText().toString());
                gameObj.setNota(Integer.parseInt(nota.getText().toString()));
                gameObj.setDescricao(Integer.parseInt(descricao.getText().toString()));
                gameObj.setImagem(Util.ImagetoBase64 (((BitmapDrawable)poster.getDrawable()).getBitmap()));
                crud.alteraRegistro(gameObj);
                Intent intent = new Intent(AlterarGameActivity.this,ConsultaGameActivity.class);
                startActivity(intent);
                finish();
            }
        });

        deletar = (Button)findViewById(R.id.button3);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.deletaRegistro(Integer.parseInt(codigo));
                Intent intent = new Intent(AlterarGameActivity.this,ConsultaGameActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == ACTIVITY_SELECT_IMAGE){
            if(resultCode == Activity.RESULT_OK){
                if(data != null){
                    try{
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(),data.getData());
                        poster.setImageBitmap(bitmap);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }else if(resultCode == Activity.RESULT_CANCELED){
                    Toast.makeText(getApplicationContext(), "Cancelado.", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}
