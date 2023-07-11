package br.com.avaliacao_2.dao;

import java.sql.*;
import br.com.avaliacao_2.dto.LivroDTO;

public class LivroDAO {

    public LivroDAO(){
    }
    
    private ResultSet rs = null;
    
    private Statement stmt = null;
    
    public boolean inserirLivro(LivroDTO livroDTO){
        try{
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Insert into livro (titulo_livro, autor_livro, editora_livro, "
                    + "categoria_livro, idioma_livro ) values ( "
                    + "'" + livroDTO.getTitulo_livro()+ "', "
                    + "'" + livroDTO.getAutor_livro()+ "', "
                    + "'" + livroDTO.getEditora_livro()+ "', "
                    + "'" + livroDTO.getCategoria_livro()+ "', "
                    + "'" + livroDTO.getIdioma_livro()+ "') ";
            
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
    
    public boolean alterarLivro(LivroDTO livroDTO) {
        try{
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Update livro set "
                    + "titulo_livro = '" + livroDTO.getTitulo_livro()+ "', "
                    + "autor_livro = '" + livroDTO.getAutor_livro()+ "', "
                    + "editora_livro = '" + livroDTO.getEditora_livro()+ "', "
                    + "categoria_livro = '" + livroDTO.getCategoria_livro()+ "', "
                    + "idioma_livro = '" + livroDTO.getIdioma_livro()+ "' "
                    + "where id_livro = " + livroDTO.getId_livro();
                       
            stmt.execute(comando.toUpperCase());
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        } 
        finally {
            ConexaoDAO.CloseDB();
        }
    }
    
    public boolean excluirLivro(LivroDTO livroDTO){
        try{
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Delete from livro where id_livro = " 
                             + livroDTO.getId_livro();

            stmt.execute(comando);
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        } 
        finally {
            ConexaoDAO.CloseDB();
        }
    }
    
    public ResultSet consultarLivro(LivroDTO livroDTO, int opcao){
        try{
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "";
            
            switch(opcao){
                case 1:
                    comando = "Select l.id_livro, l.titulo_livro "+
                              "from livro l "+
                              "where l.titulo_livro ilike '" + livroDTO.getTitulo_livro()+ "%' " +
                              "order by l.titulo_livro";    
                break;
                case 2:
                    comando = "Select l.* "+
                              "from livro l " +
                              "where l.id_livro = " + livroDTO.getId_livro();
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