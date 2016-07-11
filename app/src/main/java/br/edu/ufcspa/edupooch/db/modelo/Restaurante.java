package br.edu.ufcspa.edupooch.db.modelo;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

import br.edu.ufcspa.edupooch.db.R;

/**
 * Created by Particular on 10/07/2016.
 */
public class Restaurante implements Serializable{
    int id;
    String nome;
    int foto;
    int votos;

    public Restaurante(String nome, int foto,int votos) {
        this.nome = nome;
        this.foto = foto;
        this.votos = votos;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getFoto() {
        return foto;
    }

    public int getVotos() {
        return votos;
    }

    public void incrementaVoto() {
        this.votos++;
    }
}
