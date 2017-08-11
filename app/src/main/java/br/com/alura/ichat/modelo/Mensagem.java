package br.com.alura.ichat.modelo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Millfford Bradshaw on 07/08/2017.
 */

public class Mensagem {
    @SerializedName("text")
    private String texto;
    private int id;

    public Mensagem(int id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public int getId() {
        return id;
    }
}
