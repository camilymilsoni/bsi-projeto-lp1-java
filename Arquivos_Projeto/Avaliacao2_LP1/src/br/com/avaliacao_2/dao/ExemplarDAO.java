package br.com.avaliacao_2.dao;

import java.sql.*;
import br.com.avaliacao_2.dto.ExemplarDTO;
import br.com.avaliacao_2.dto.LivroDTO;

public class ExemplarDAO {

    public ExemplarDAO(){
    }
    
    private ResultSet rs = null;
    
    private Statement stmt = null;
    
    public boolean inserirExemplar(ExemplarDTO exemplarDTO, LivroDTO livroDTO){
        try{
            ConexaoDAO.ConectDB();
          
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Insert into exemplar (edicao_exemplar, ano_public_exemplar, "
                    + "nro_pags_exemplar, id_livro) values ( "
                    + "'" + exemplarDTO.getEdicao_exemplar() + "', "
                    + exemplarDTO.getAno_public_exemplar()+ ", "
                    + exemplarDTO.getNro_pags_exemplar()+ ", "
                    + livroDTO.getId_livro()+ ") ";
            
            stmt.execute(comando.toUpperCase());
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            ConexaoDAO.CloseDB();
        }
    }
    
    public boolean alterarExemplar(ExemplarDTO exemplarDTO, LivroDTO livroDTO){
        try{
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Update exemplar set "
                    + "edicao_exemplar = '" + exemplarDTO.getEdicao_exemplar()+ "', "
                    + "ano_public_exemplar = " + exemplarDTO.getAno_public_exemplar()+ ", "
                    + "nro_pags_exemplar = " + exemplarDTO.getNro_pags_exemplar()+ ", "
                    + "id_livro = " + livroDTO.getId_livro()+ " "
                    + "where id_exemplar = " + exemplarDTO.getId_exemplar();
                       
            stmt.execute(comando.toUpperCase());
            
            ConexaoDAO.con.commit();

            stmt.close();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        } 
        finally{
            ConexaoDAO.CloseDB();
        }
    }
    
    public boolean excluirExemplar(ExemplarDTO exemplarDTO){
        try{
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Delete from exemplar where id_exemplar = " 
                             + exemplarDTO.getId_exemplar();

            stmt.execute(comando);
            
            ConexaoDAO.con.commit();

            stmt.close();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            ConexaoDAO.CloseDB();
        }
    }
    
    public ResultSet consultarExemplar(ExemplarDTO exemplarDTO, int opcao){
        try{
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "";
            
            switch(opcao){
                case 1:
                    comando = "Select e.* "+
                              "from exemplar e "+
                              "where e.edicao_exemplar ilike '" + exemplarDTO.getEdicao_exemplar()+ "%' " +
                              "order by e.edicao_exemplar";    
                break;
                case 2:
                    comando = "Select e. *, l.titulo_livro, l.id_livro "+
                              "from exemplar e, livro l " +
                              "where e.id_livro = l.id_livro and " +
                              "e.id_exemplar = " + exemplarDTO.getId_exemplar();
                break;
                case 3:
                    comando = "Select e.*, l.titulo_livro "+
                              "from exemplar e " +
                              "left join livro l on e.id_livro = l.id_livro " +
                              "where e.edicao_exemplar ilike '" + exemplarDTO.getEdicao_exemplar()+ "%' " +
                              "order by e.edicao_exemplar"; 
                break;
            }
            
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return rs;
        }
    }
}