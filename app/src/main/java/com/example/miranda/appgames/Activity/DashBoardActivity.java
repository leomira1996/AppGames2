package com.example.miranda.appgames.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.miranda.appgames.R;

public class DashBoardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
    }
    public void inserirGame(View v){
        Intent intent = new Intent(DashBoardActivity.this, InsereGameActivity.class);
        startActivity(intent);
    }
    public void listarGame(View v){
        Intent intent = new Intent(DashBoardActivity.this,ConsultaGameActivity.class);
        startActivity(intent);
    }
    public void inserirCategoria(View v){
        Intent intent  = new Intent(DashBoardActivity.this,InsereCategoriaActivity.class);
        startActivity(intent);

    }
}
