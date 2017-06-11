package com.example.miranda.appgames;

/**
 * Created by Miranda on 11/06/2017.
 */

public class Game {
    private int _ID;
    private String titulo;
    private int nota;
    private int descricao;
    private String imagem;
    private Categoria categoria;

    public Game() {
        categoria = new Categoria();
    }

    public Game(String titulo, int nota, int descricao) {
        this.titulo = titulo;
        this.nota = nota;
        this.descricao = descricao;
        categoria = new Categoria();
    }

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getDescricao() {
        return descricao;
    }

    public void setDescricao(int descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
