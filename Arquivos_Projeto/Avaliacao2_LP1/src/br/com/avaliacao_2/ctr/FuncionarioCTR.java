package br.com.avaliacao_2.ctr;

import java.sql.ResultSet;
import br.com.avaliacao_2.dto.FuncionarioDTO;
import br.com.avaliacao_2.dao.FuncionarioDAO;
import br.com.avaliacao_2.dao.ConexaoDAO;

public class FuncionarioCTR {

    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public FuncionarioCTR() {
    }

    public String inserirFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            if (funcionarioDAO.inserirFuncionario(funcionarioDTO)) {
                return "Funcionário cadastrado com sucesso!!!";
            } else {
                return "Funcionário NÃO cadastrado!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Funcionário NÃO cadastrado!";
        }
    }

    public String alterarFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            if (funcionarioDAO.alterarFuncionario(funcionarioDTO)) {
                return "Funcionário alterado com sucesso!";
            } else {
                return "Funcionário NÃO alterado!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Funcionário NÃO alterado!";
        }
    }

    public String excluirFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            if (funcionarioDAO.excluirFuncionario(funcionarioDTO)) {
                return "Funcionário excluído com sucesso!";
            } else {
                return "Funcionário NÃO excluído!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Funcionário NÃO excluído!";
        }
    }

    public ResultSet consultarFuncionario(FuncionarioDTO funcionarioDTO, int opcao) {
        ResultSet rs = null;
        rs = funcionarioDAO.consultarFuncionario(funcionarioDTO, opcao);
        return rs;
    }

    public String logarFuncionario(FuncionarioDTO funcionarioDTO) {

        return funcionarioDAO.logarFuncionario(funcionarioDTO);

    }

    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }

}
