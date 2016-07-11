package br.edu.ufcspa.edupooch.db.controle;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import br.edu.ufcspa.edupooch.db.R;
import br.edu.ufcspa.edupooch.db.controle.adapter.RestaurantesAdapter;
import br.edu.ufcspa.edupooch.db.dao.RestauranteDAO;
import br.edu.ufcspa.edupooch.db.modelo.Restaurante;
import br.edu.ufcspa.edupooch.db.modelo.Usuario;

public class VotacaoActivity extends AppCompatActivity {

    Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = (Usuario) getIntent().getSerializableExtra("usuario");

        //Se as duas datas são iguais não pode mais votar
        if (user.getUltimoVoto().compareTo(new Date()) == 0) {
            findViewById(R.id.text_javotou).setVisibility(View.VISIBLE);
        } else {
            carregaLista();
        }




    }

    private void carregaLista() {
        final ListView listaRestaurantes = (ListView) findViewById(R.id.lista_restaurantes);

        RestauranteDAO dao = new RestauranteDAO();
        List<Restaurante> restaurantes = dao.getRestaurantes();

        RestaurantesAdapter adapter = new RestaurantesAdapter(this, restaurantes);
        listaRestaurantes.setAdapter(adapter);

        listaRestaurantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> lista, View item, int position, long id) {
                final Restaurante restaurante = (Restaurante) listaRestaurantes.getItemAtPosition(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setCancelable(false);
                builder.setTitle(restaurante.getNome());
                builder.setMessage(getString(R.string.dialog_votar) + restaurante.getNome() + "?");
                builder.setPositiveButton(getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restaurante.incrementaVoto();
                        user.setUltimoVoto(new Date());
                        listaRestaurantes.setVisibility(View.GONE);
                        findViewById(R.id.text_instrucoes).setVisibility(View.GONE);
                        findViewById(R.id.text_javotou).setVisibility(View.VISIBLE);
                    }
                });

                builder.setNegativeButton(getString(R.string.dialog_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });
    }

}
