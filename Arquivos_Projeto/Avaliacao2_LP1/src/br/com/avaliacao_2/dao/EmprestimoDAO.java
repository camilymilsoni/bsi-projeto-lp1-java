package br.com.avaliacao_2.dao;

import br.com.avaliacao_2.dto.ClienteDTO;
import br.com.avaliacao_2.dto.EmprestimoDTO;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JTable;

public class EmprestimoDAO {

    public EmprestimoDAO(){
    }
    
    private ResultSet rs = null;
    
    Statement stmt = null;
    Statement stmt1 = null;
    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    
    public boolean inserirEmprestimo(EmprestimoDTO emprestimoDTO, ClienteDTO clienteDTO, JTable emprestimo){
        try{
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            stmt1 = ConexaoDAO.con.createStatement();
            
            String comando1 = "Insert into Emprestimo (data_emprest, qtd_exemplar_emprest, "
                    + "id_cli) values ( "
                    + "to_date('" + date.format(emprestimoDTO.getData_emprest()) + "', 'DD/MM/YYYY'), "
                    + emprestimoDTO.getQtd_exemplar_emprest()+ ", "
                    + clienteDTO.getId_cli()+ ") ";
            
            stmt.execute(comando1.toUpperCase(), Statement.RETURN_GENERATED_KEYS);
            rs = stmt.getGeneratedKeys();
            
            rs.next();
            for (int cont = 0; cont < emprestimo.getRowCount(); cont++) {
                String comando2 = "Insert into exemplar_emprestimo (id_emprest, id_exemplar, "
                        + "data_devoluc_exemplar) values ( "
                        + rs.getInt("id_emprest") + ", "
                        + emprestimo.getValueAt(cont, 1) + ", "
                        + "to_date('" + emprestimo.getValueAt(cont, 4)+"','dd/MM/yyyy')) ";
                
                stmt1.execute(comando2);
            }
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            stmt1.close();
            
            rs.close();
            
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        finally {
            ConexaoDAO.CloseDB();
        }
    }
    
}
