package com.example.miranda.appgames.Activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.miranda.appgames.DAO.CategoriaDAO;
import com.example.miranda.appgames.DAO.GameDAO;
import com.example.miranda.appgames.Model.Game;
import com.example.miranda.appgames.R;
import com.example.miranda.appgames.Util.BancoUtil;

import java.io.IOException;

public class InsereGameActivity extends Activity {
    final int ACTIVITY_SELECT_IMAGE = 1234;
    ImageView imageView;
    String base64Image;
    Spinner spinnerCategoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere_game);
        base64Image = null;
        Button botao = (Button)findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.imageView);
        spinnerCategoria = (Spinner) findViewById(R.id.spinnerCategoria);

        Cursor cursor = new CategoriaDAO(getBaseContext()).carregaDados();
        String[] columns = new String[] {BancoUtil.CATEGORIA_GAME};
        int[] idViews = new int[] { android.R.id.text1};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                android.R.layout.simple_dropdown_item_1line,cursor,columns,idViews, 0);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adaptador);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Selecione a Imagem"),ACTIVITY_SELECT_IMAGE);
            }
        });

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    GameDAO crud = new GameDAO(getBaseContext());
                    EditText titulo = (EditText) findViewById(R.id.editTituloGame);
                    EditText nota = (EditText) findViewById((R.id.editNotaGame));
                    EditText descricao = (EditText) findViewById(R.id.editDescricaoGame);
                    String tituloString = titulo.getText().toString();
                    int notaInt = Integer.parseInt(nota.getText().toString());
                    String descricaoInt = (descricao.getText().toString());
                    //String imagemString = Util.ImagetoBase64(((BitmapDrawable) imageView.getDrawable()).getBitmap());
                    String resultado;

                    Game game = new Game(tituloString, notaInt, descricaoInt);
                    //game.setImagem(imagemString);
                    resultado = crud.insereDado(game);

                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(InsereGameActivity.this, ConsultaGameActivity.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                }
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
                        imageView.setImageBitmap(bitmap);
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
