/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfacegrafica3.repository;

import interfacegrafica3.model.Fornecedor;
import interfacegrafica3.model.Pessoa;
import interfacegrafica3.model.Uf;
//import static interfacegrafica3.repository.ConexaoMySQL.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Professor
 */
public class FornecedorRepository implements Crud<Fornecedor> {
    
    //Crud - inserir
    @Override
    public boolean inserir(Connection connection, Fornecedor fornecedor) {
        PreparedStatement stmt = null;
        try {
            String comando = "INSERT INTO fornecedor(nome, endereco, email, telefone, uf_id, cnpj, inscricao_estadual, nome_fantasia, categoria) " +
                             "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

            stmt = connection.prepareStatement(comando);

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getEndereco());
            stmt.setString(3, fornecedor.getEmail());
            stmt.setString(4, fornecedor.getTelefone());
            // Verifica se o fornecedor possui UF antes de definir
            stmt.setInt(5, fornecedor.getUf() != null ? fornecedor.getUf().getId() : 0);
            stmt.setString(6, fornecedor.getCnpj());
            stmt.setString(7, fornecedor.getInscricaoEstadual());
            stmt.setString(8, fornecedor.getNomeFantasia());
            stmt.setString(9, fornecedor.getCategoria());

            stmt.executeUpdate(); // Executa a inserção

            return true;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao inserir fornecedor: " + ex.getMessage(),
                    "Erro ao inserir",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;  // Retorna false caso haja erro
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();  // Fecha o PreparedStatement
                }
            } catch (Exception ex) {
                // Aqui, você pode logar a falha no fechamento, se necessário
            }
        }
    }

    //Crud - atualizar
    @Override
    public boolean atualizar(Connection connection, Fornecedor fornecedor) {
        PreparedStatement stmt = null;
        try{
            String comando = "UPDATE fornecedor SET " +
                             "nome = ?, endereco = ?, email = ?, telefone = ?, uf_id = ?, cnpj = ?, inscricao_estadual = ?, nome_fantasia = ?, categoria = ? " +
                             "WHERE id = ?";
            stmt = connection.prepareStatement(comando);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getEndereco());
            stmt.setString(3, fornecedor.getEmail());
            stmt.setString(4, fornecedor.getTelefone());
            stmt.setInt(5, fornecedor.getUf() != null ? fornecedor.getUf().getId() : 0);
            stmt.setString(6, fornecedor.getCnpj());
            stmt.setString(7, fornecedor.getInscricaoEstadual());
            stmt.setString(8, fornecedor.getNomeFantasia());
            stmt.setString(9, fornecedor.getCategoria());
            stmt.setInt(10, fornecedor.getId());
            stmt.executeUpdate();
            stmt.close();
            return true;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao atualizar fornecedor: " + ex.getMessage(),
                    "Erro ao atualizar",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }        
    }

    //Crud - deletar
    @Override
    public boolean deletar(Connection connection, Fornecedor fornecedor) {
        PreparedStatement stmt = null;
        try{
            String comando = "DELETE FROM fornecedor " +
                             "WHERE id = ?";
            stmt = connection.prepareStatement(comando);
            stmt.setInt(1, fornecedor.getId());
            stmt.executeUpdate();
            stmt.close();
            return true;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao excluir fornecedor: " + ex.getMessage(),
                    "Erro ao excluir",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }        
    }

    @Override
    public Fornecedor selecionar(Connection connection, String operador, int id) {
        try{
            Fornecedor fornecedor = new Fornecedor();
            PreparedStatement stmt = null;
            String comando = "SELECT * FROM fornecedor WHERE id " + 
                             operador + " ? ";
            if(operador.equals("<"))
                comando += " ORDER BY id DESC";
            stmt = connection.prepareStatement(comando);
            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();
            if(res != null){
                while(res.next()){
                    fornecedor.setId(Integer.parseInt(res.getString("id") ));
                    fornecedor.setNome(res.getString("nome"));
                    fornecedor.setEndereco(res.getString("endereco"));
                    fornecedor.setEmail(res.getString("email"));
                    fornecedor.setTelefone(res.getString("telefone"));                         
                    fornecedor.setUf(new Uf(res.getInt("uf_id"))); 
                    fornecedor.setCnpj(res.getString("cnpj")); 
                    fornecedor.setInscricaoEstadual(res.getString("inscricao_estadual"));
                    fornecedor.setNomeFantasia(res.getString("nome_fantasia"));
                    fornecedor.setCategoria(res.getString("categoria"));
                    break;
                }
            }
            return fornecedor;
        }catch(Exception ex){
            
            return null;
        }
    }
    
    
        
}
