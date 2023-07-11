package br.com.avaliacao_2.dto;

import java.util.Date;

public class EmprestimoDTO {

    private int id_emprest, qtd_exemplar_emprest;
    private Date data_emprest;

    public int getId_emprest() {
        return id_emprest;
    }

    public void setId_emprest(int id_emprest) {
        this.id_emprest = id_emprest;
    }

    public Date getData_emprest() {
        return data_emprest;
    }

    public void setData_emprest(Date data_emprest) {
        this.data_emprest = data_emprest;
    }

    public int getQtd_exemplar_emprest() {
        return qtd_exemplar_emprest;
    }

    public void setQtd_exemplar_emprest(int qtd_exemplar_emprest) {
        this.qtd_exemplar_emprest = qtd_exemplar_emprest;
    }
    
}
