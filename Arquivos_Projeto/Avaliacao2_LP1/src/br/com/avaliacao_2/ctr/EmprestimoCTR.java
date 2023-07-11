package br.com.avaliacao_2.ctr;

import br.com.avaliacao_2.dao.ConexaoDAO;
import br.com.avaliacao_2.dao.EmprestimoDAO;
import br.com.avaliacao_2.dto.EmprestimoDTO;
import br.com.avaliacao_2.dto.ClienteDTO;
import javax.swing.JTable;

public class EmprestimoCTR {
 
    EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
    
    public EmprestimoCTR(){
    }
    
    public String inserirEmprestimo(EmprestimoDTO emprestimoDTO, ClienteDTO clienteDTO, JTable emprestimo) {
        try{
            if(emprestimoDAO.inserirEmprestimo(emprestimoDTO, clienteDTO, emprestimo))
                return "Empréstimo cadastrado com sucesso!!!";
            else
                return "Empréstimo NÃO cadastrado!!!";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Empréstimo NÃO cadastrado";
        }
    }
    
    public void CloseDB(){
        ConexaoDAO.CloseDB();
    }
    
}
