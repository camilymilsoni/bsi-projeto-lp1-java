package br.com.avaliacao_2.ctr;

import java.sql.ResultSet;
import br.com.avaliacao_2.dto.LivroDTO;
import br.com.avaliacao_2.dto.ExemplarDTO;
import br.com.avaliacao_2.dao.ExemplarDAO;
import br.com.avaliacao_2.dao.ConexaoDAO;

public class ExemplarCTR {

    ExemplarDAO exemplarDAO = new ExemplarDAO();
    
    public ExemplarCTR(){
    }
    
    public String inserirExemplar(ExemplarDTO exemplarDTO, LivroDTO livroDTO){
        try{
            if(exemplarDAO.inserirExemplar(exemplarDTO, livroDTO)){
                return "Exemplar cadastrado com sucesso!!!";
            }else{
                return "Exemplar NÃO cadastrado!!!";
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Exemplar NÃO cadastrado";
        }
    }
    
    public String alterarExemplar(ExemplarDTO exemplarDTO, LivroDTO livroDTO){
        try{
            if(exemplarDAO.alterarExemplar(exemplarDTO, livroDTO)){
                return "Exemplar alterado com sucesso!!!";
            }else{
                return "Exemplar NÃO alterado!!!";
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Exemplar NÃO alterado!!!";
        }
    }
    
    public String excluirExemplar(ExemplarDTO exemplarDTO){
        try{
            if(exemplarDAO.excluirExemplar(exemplarDTO)){
                return "Exemplar excluído com sucesso!!!";
            }else{
                return "Exemplar NÃO excluído!!!";
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Exemplar NÃO excluído!!!";
        }
    }
    
    public ResultSet consultarExemplar(ExemplarDTO exemplarDTO, int opcao){
        ResultSet rs = null;
        
        rs = exemplarDAO.consultarExemplar(exemplarDTO, opcao);
        
        return rs;
    }

    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
}