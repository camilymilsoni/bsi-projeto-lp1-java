package br.com.avaliacao_2.dto;

public class ExemplarDTO {

    private int id_exemplar, ano_public_exemplar, nro_pags_exemplar;
    private String edicao_exemplar;

    public int getId_exemplar() {
        return id_exemplar;
    }

    public void setId_exemplar(int id_exemplar) {
        this.id_exemplar = id_exemplar;
    }

    public int getAno_public_exemplar() {
        return ano_public_exemplar;
    }

    public void setAno_public_exemplar(int ano_public_exemplar) {
        this.ano_public_exemplar = ano_public_exemplar;
    }

    public int getNro_pags_exemplar() {
        return nro_pags_exemplar;
    }

    public void setNro_pags_exemplar(int nro_pags_exemplar) {
        this.nro_pags_exemplar = nro_pags_exemplar;
    }

    public String getEdicao_exemplar() {
        return edicao_exemplar;
    }

    public void setEdicao_exemplar(String edicao_exemplar) {
        this.edicao_exemplar = edicao_exemplar;
    }
    
}
