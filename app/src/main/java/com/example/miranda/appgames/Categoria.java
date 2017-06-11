package com.example.miranda.appgames;

/**
 * Created by Miranda on 11/06/2017.
 */

public class Categoria {
    private int _ID;
    private String categoria;

    public Categoria(String categoria) {
        this.categoria = categoria;
    }

    public Categoria() {
    }

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
