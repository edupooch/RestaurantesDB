package br.edu.ufcspa.edupooch.db.modelo;

import java.util.ArrayList;

/**
 * Created by Particular on 10/07/2016.
 */
public class ListaUsuarios {

    static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
}
