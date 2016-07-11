package br.edu.ufcspa.edupooch.db.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Particular on 10/07/2016.
 */
public class Usuario implements Serializable {
    private String nome;
    private Date ultimoVoto;


    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }


    public Date getUltimoVoto() {
        return ultimoVoto;
    }

    public void setUltimoVoto(Date ultimoVoto) {
        this.ultimoVoto = ultimoVoto;
    }
}
