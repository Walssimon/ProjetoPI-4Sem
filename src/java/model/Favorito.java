/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import Controller.ConectarDao;
import Controller.IcrudDao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Favorito extends ConectarDao implements IcrudDao {
    
    public int idFavorito;
    public int idFilme;
    public int idUser;
    

    public int getIdFavorito() {
        return idFavorito;
    }

    public void setIdFavorito(int idFavorito) {
        this.idFavorito = idFavorito;
    }

    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    public String getHtmlError() {
        return htmlError;
    }

    public void setHtmlError(String htmlError) {
        this.htmlError = htmlError;
    }

    public ResultSet getTab() {
        return tab;
    }

    public void setTab(ResultSet tab) {
        this.tab = tab;
    }

    public String getMeuBanco() {
        return MeuBanco;
    }

    public void setMeuBanco(String MeuBanco) {
        this.MeuBanco = MeuBanco;
    }

    public String getStatusSQL() {
        return statusSQL;
    }

    public void setStatusSQL(String statusSQL) {
        this.statusSQL = statusSQL;
    }
    
    public Favorito() {
      
    }
    
    public Favorito(int idFilme, int idUser) {
        this.idFilme = idFilme;
        this.idUser = idUser;
    }
    
    
    public void IncluirFavorito(){
        try {
            sql = "INSERT INTO TB_FAVORITO (ID_FILME, ID_USUARIO) VALUES (?, ?)";

            ps = con.prepareStatement(sql);
            
            ps.setInt(1, idFilme); // Configura Parametros
            ps.setInt(2, idUser); // Configura Parametros
        
            ps.executeUpdate();
            
            this.statusSQL = null;

        } catch (SQLException ex) {
            this.statusSQL = "Erro ao incluir comentário ! <br> " + ex.getMessage();
        } 
    }
    
    public boolean getFavorito() throws IOException {

        try {
            
            sql = "SELECT * FROM TB_FAVORITO WHERE ID_FILME = ? AND ID_USUARIO = ?";
            
            ps = con.prepareStatement(sql); // prepara SQL
            
            ps.setInt(1, idFilme); // Configura Parametros
            ps.setInt(2, idUser); // Configura Parametros
            
            tab = ps.executeQuery(); // Executa comando SQL
            
            if (tab.next()){
               idFilme = tab.getInt(2);
               idUser = tab.getInt(3);
               
               return true;
            }
            
            this.statusSQL = null;// armazena null se deu tudo certo
          
        } catch (SQLException ex) {
            this.statusSQL = "Erro ao tentar buscar Favorito! " + ex.getMessage();
        }
        
        return false;
    }
    
    
     public void deletar() {
        try {
            sql = "DELETE FROM TB_FAVORITO WHERE ID_FILME = ? AND ID_USUARIO = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idFilme);
            ps.setInt(2, idUser);
            ps.executeUpdate();
            this.statusSQL = null;
        } catch (SQLException ex) {
            this.statusSQL = "Errro ao deletar Favorito ! <br>" + ex.getMessage();
        }
    }
        
        
    @Override
    public boolean salvar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean buscar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean buscarSQL() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
