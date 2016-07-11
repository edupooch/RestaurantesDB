package br.edu.ufcspa.edupooch.db.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import br.edu.ufcspa.edupooch.db.R;
import br.edu.ufcspa.edupooch.db.modelo.Restaurante;

/**
 * Classe que simula um banco de dados
 */
public class RestauranteDAO {

    public static Restaurante[] restaurantesDaSemana = new Restaurante[7];

    public List<Restaurante> getRestaurantes() {
        List<Restaurante> restaurantes = new ArrayList<>();
        restaurantes.add(new Restaurante("Pizzaria", R.drawable.icon_pizzaria, new Random().nextInt(10)));
        restaurantes.add(new Restaurante("Churrascaria", R.drawable.icon_churrascaria, new Random().nextInt(10)));
        restaurantes.add(new Restaurante("Hamburgueria", R.drawable.icon_hamburgueria, new Random().nextInt(10)));
        restaurantes.add(new Restaurante("Vegetariano", R.drawable.icon_vegetariano, new Random().nextInt(10)));
        restaurantes.add(new Restaurante("Oriental", R.drawable.icon_oriental, new Random().nextInt(10)));
        restaurantes.add(new Restaurante("Fast Food", R.drawable.icon_fastfood, new Random().nextInt(10)));


        //Caso tenha algum restaurante na lista que já foi escolhido essa semana, este é removido.
        for (Restaurante restaurante : restaurantesDaSemana) {
            restaurantes.remove(restaurante);
        }

        return restaurantes;
    }

}
