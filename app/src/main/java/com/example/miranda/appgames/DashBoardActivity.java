package com.example.miranda.appgames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.os.Bundle;

public class DashBoardActivity extends AppCompatActivity {

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
        Intent intent = new Intent(DashBoardActivity.this,InsereCategoriaActivity.class);

    }
}
