/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Douglaz
 */
public class ProdutoDAO extends Conexao {
    
    public boolean registrar(Produto p){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "INSERT INTO produtos(codigo, nome, preco, quantidade) VALUES(?, ?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getNome());
            ps.setDouble(3, p.getPreco());
            ps.setInt(4, p.getQuantidade());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
        finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
                e.printStackTrace();
            }
        }
    }
    
    public boolean alterar(Produto p){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "UPDATE produtos SET codigo = ?, nome = ?, preco = ?, quantidade = ? WHERE id = ?;";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getNome());
            ps.setDouble(3, p.getPreco());
            ps.setInt(4, p.getQuantidade());
            ps.setInt(5, p.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
        finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean excluir(Produto p){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "DELETE FROM produtos WHERE id =?;";
        
        try {
            ps = con.prepareStatement(sql);            
            ps.setInt(1, p.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
        finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean buscar(Produto p){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConnection();
        
        String sql = "SELECT * FROM produtos WHERE codigo = ?;";
        
        try {
            ps = con.prepareStatement(sql);            
            ps.setString(1, p.getCodigo());
            rs = ps.executeQuery();
            
            if(rs.next()){
                p.setId(Integer.parseInt(rs.getString("id")));
                p.setCodigo(rs.getString("codigo"));
                p.setNome(rs.getString("nome"));
                p.setPreco(Double.parseDouble(rs.getString("preco")));
                p.setQuantidade(Integer.parseInt(rs.getString("quantidade")));
                return true;
            }            
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
        finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
}
