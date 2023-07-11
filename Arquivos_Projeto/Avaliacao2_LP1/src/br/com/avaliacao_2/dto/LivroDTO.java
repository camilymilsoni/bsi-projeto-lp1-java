package br.com.avaliacao_2.dto;

public class LivroDTO {

    private String titulo_livro, autor_livro, editora_livro, categoria_livro, idioma_livro;
    private int id_livro;

    public String getTitulo_livro() {
        return titulo_livro;
    }

    public void setTitulo_livro(String titulo_livro) {
        this.titulo_livro = titulo_livro;
    }

    public String getAutor_livro() {
        return autor_livro;
    }

    public void setAutor_livro(String autor_livro) {
        this.autor_livro = autor_livro;
    }

    public String getEditora_livro() {
        return editora_livro;
    }

    public void setEditora_livro(String editora_livro) {
        this.editora_livro = editora_livro;
    }

    public String getCategoria_livro() {
        return categoria_livro;
    }

    public void setCategoria_livro(String categoria_livro) {
        this.categoria_livro = categoria_livro;
    }

    public int getId_livro() {
        return id_livro;
    }

    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public String getIdioma_livro() {
        return idioma_livro;
    }

    public void setIdioma_livro(String idioma_livro) {
        this.idioma_livro = idioma_livro;
    }
    
}
