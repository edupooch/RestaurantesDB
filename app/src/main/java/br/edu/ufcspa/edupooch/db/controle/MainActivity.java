package br.edu.ufcspa.edupooch.db.controle;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.w3c.dom.Text;

import java.util.List;

import br.edu.ufcspa.edupooch.db.R;
import br.edu.ufcspa.edupooch.db.modelo.ListaUsuarios;
import br.edu.ufcspa.edupooch.db.modelo.Usuario;

public class MainActivity extends AppCompatActivity {

    private EditText campoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        campoUsuario = (EditText) findViewById(R.id.campo_usuario);

        Button btEntrar = (Button) findViewById(R.id.bt_entrar);
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!campoUsuario.getText().toString().isEmpty()) {
                    Usuario user = new Usuario(campoUsuario.getText().toString().toLowerCase().trim());

                    //verifica se há um usuário com esse username na lista dos que já logaram
                    int indiceLista = estaNaLista(user);
                    if (indiceLista != -1) {
                        //Se ele está na lista, é passado para a próxima activity um objeto desse usuário
                        user = ListaUsuarios.getUsuarios().get(indiceLista);

                    } else {
                        //Se não estiver na lista,  ele é adicionado
                        ListaUsuarios.getUsuarios().add(user);
                    }

                    Intent intent = new Intent(MainActivity.this, VotacaoActivity.class);
                    intent.putExtra("usuario", user);
                    startActivity(intent);
                    finish();

                }
            }
        });

    }

    /**
     * Método para verificar se um usuário está na lista, para depois verificar se ele já votou hoje.
     * Se não estiver retorna -1, se estiver retorna a posição.
     *
     * @param user
     * @return posicao
     */
    private int estaNaLista(Usuario user) {
        for (int i = 0; i < ListaUsuarios.getUsuarios().size(); i++) {
            if (ListaUsuarios.getUsuarios().get(i).getNome().equals(user.getNome())) {
                return i;
            }
        }
        return -1;
    }


}
