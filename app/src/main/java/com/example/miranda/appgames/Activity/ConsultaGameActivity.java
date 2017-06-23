package com.example.miranda.appgames.Activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.miranda.appgames.Util.BancoUtil;
import com.example.miranda.appgames.DAO.GameDAO;
import com.example.miranda.appgames.R;

public class ConsultaGameActivity extends Activity {

    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_game);

        GameDAO crud = new GameDAO(getBaseContext());
        final Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String[] {BancoUtil.ID_GAME, BancoUtil.TITULO_GAME,BancoUtil.NOTA_GAME};
        int[] idViews = new int[] {R.id.idGame, R.id.nomeGame,R.id.notGame};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.game_layout,cursor,nomeCampos,idViews, 0);
        lista = (ListView)findViewById(R.id.listView);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            /*
             * Questão 14: Explique os parâmetros da função onItemClicl
             * */
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.ID_GAME));
                Intent intent = new Intent(ConsultaGameActivity.this, AlterarGameActivity.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });
    }
}
