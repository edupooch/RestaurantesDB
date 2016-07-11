package br.edu.ufcspa.edupooch.db.controle;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import br.edu.ufcspa.edupooch.db.R;
import br.edu.ufcspa.edupooch.db.controle.adapter.RestaurantesAdapter;
import br.edu.ufcspa.edupooch.db.dao.RestauranteDAO;
import br.edu.ufcspa.edupooch.db.modelo.Restaurante;
import br.edu.ufcspa.edupooch.db.modelo.Usuario;

public class VotacaoActivity extends AppCompatActivity {

    Usuario user;
    Button btResultado;
    private List<Restaurante> restaurantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RestauranteDAO dao = new RestauranteDAO();
        restaurantes = dao.getRestaurantes();

        user = (Usuario) getIntent().getSerializableExtra("usuario");

        //Se as duas datas são iguais não pode mais votar
        if (user.getUltimoVoto() == null) {
            //Usuario nunca votou
            carregaLista();
        } else if (user.getUltimoVoto().compareTo(new Date()) == 0) {
            //Usuário já votou hoje
            jaVotou();
        } else {
            //usuario nunca votou
            carregaLista();
        }


    }

    private void jaVotou() {

        findViewById(R.id.text_javotou).setVisibility(View.VISIBLE);
        btResultado = (Button) findViewById(R.id.bt_resultado);
        btResultado.setVisibility(View.VISIBLE);
        btResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ao clicar no botao é exibido o resultado (aleaotório)
                Restaurante vencedor = restaurantes.get(new Random().nextInt(6));

                RestauranteDAO.restaurantesDaSemana[Calendar.getInstance().get(Calendar.DAY_OF_WEEK)] = vencedor;

                findViewById(R.id.layoutVencedor).setVisibility(View.VISIBLE);

                TextView txtNomeVencedor = (TextView) findViewById(R.id.textNomeRestauranteVencedor);
                txtNomeVencedor.setText(vencedor.getNome());

                ImageView imagemVencedor = (ImageView) findViewById(R.id.imagem_vencedor);
                imagemVencedor.setImageResource(vencedor.getFoto());

                btResultado.setVisibility(View.GONE);
                TextView textJaVotou = (TextView) findViewById(R.id.text_javotou);
                textJaVotou.setText(R.string.vencedor);

            }
        });
    }

    /**
     * Método para carregar a lista de restaurantes, carregamento assíncrono
     * com uma thread, para melhorar o desempenho
     */
    private void carregaLista() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final ListView listaRestaurantes = (ListView) findViewById(R.id.lista_restaurantes);
                RestaurantesAdapter adapter = new RestaurantesAdapter(VotacaoActivity.this, restaurantes);
                listaRestaurantes.setAdapter(adapter);

                listaRestaurantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(final AdapterView<?> lista, View item, int position, long id) {
                        final Restaurante restaurante = (Restaurante) listaRestaurantes.getItemAtPosition(position);

                        AlertDialog.Builder builder = new AlertDialog.Builder(VotacaoActivity.this);
                        builder.setCancelable(false);
                        builder.setTitle(restaurante.getNome());
                        builder.setMessage(getString(R.string.dialog_votar) + " " + restaurante.getNome() + "?");
                        builder.setPositiveButton(getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                restaurante.incrementaVoto();
                                user.setUltimoVoto(new Date());

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        listaRestaurantes.setVisibility(View.GONE);
                                        findViewById(R.id.text_instrucoes).setVisibility(View.GONE);
                                        jaVotou();

                                    }
                                });

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


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listaRestaurantes.setVisibility(View.VISIBLE);
                        findViewById(R.id.text_instrucoes).setVisibility(View.VISIBLE);

                    }
                });


            }
        }).start();


    }

}
