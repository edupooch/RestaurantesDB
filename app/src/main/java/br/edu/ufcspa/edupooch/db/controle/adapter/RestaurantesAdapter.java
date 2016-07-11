package br.edu.ufcspa.edupooch.db.controle.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.edu.ufcspa.edupooch.db.R;
import br.edu.ufcspa.edupooch.db.modelo.Restaurante;

/**
 * Created by Particular on 10/07/2016.
 */

public class RestaurantesAdapter extends BaseAdapter {

    private final List<Restaurante> restaurantes;
    private final Context context;

    public RestaurantesAdapter(Context context, List<Restaurante> restaurantes) {
        this.context = context;
        this.restaurantes = restaurantes;
    }

    @Override
    public int getCount() {
        return restaurantes.size();
    }

    @Override
    public Object getItem(int position) {
        return restaurantes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return restaurantes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Restaurante restaurante = restaurantes.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if (view == null) { //usa -se o convertView para otimizar o carregamento de listas muito grandes
            view = inflater.inflate(R.layout.list_item, parent, false);
            //parent passa os valores do pai para funcionar os tamanhos pré-definidos no xml
            //false é usado para não adicionar a view duas vezes
        }


        TextView textNome = (TextView) view.findViewById(R.id.textNomeRestauranteLista);
        textNome.setText(restaurante.getNome());

        TextView textTestes = (TextView) view.findViewById(R.id.textNumeroVotosLista);
        textTestes.setText(String.valueOf(restaurante.getVotos()));

        ImageView foto = (ImageView) view.findViewById(R.id.imagem_item);
        foto.setImageResource(restaurante.getFoto());

        TextView textPlural = (TextView) view.findViewById(R.id.textVotosPluralLista);
        if (restaurante.getVotos() == 1) textPlural.setText(R.string.votos_singular);


        return view;
    }
}