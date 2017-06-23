package com.example.miranda.appgames.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miranda.appgames.Model.Categoria;
import com.example.miranda.appgames.DAO.CategoriaDAO;
import com.example.miranda.appgames.R;

public class InsereCategoriaActivity extends Activity {

    private EditText editCategoria;
    private Button btnSalvarCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere_categoria);
        editCategoria = (EditText) findViewById(R.id.editCategoria);
        btnSalvarCategoria = (Button) findViewById(R.id.btnSalvarCategoria);

        btnSalvarCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoriaDAO crud = new CategoriaDAO(getBaseContext());
                String categoriaString = editCategoria.getText().toString();

                String resultado = crud.insereDado(new Categoria(categoriaString));

                Toast.makeText(InsereCategoriaActivity.this, resultado, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(InsereCategoriaActivity.this,DashBoardActivity.class);
                startActivity(intent);
            }
        });

    }
}
