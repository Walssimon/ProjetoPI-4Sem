/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import Controller.ConectarDao;
import Controller.IcrudDao;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author wersington.sssacrame
 */
public class GeneroFilme extends ConectarDao implements IcrudDao {
    
   public int idFilme; 
   public int idGenero;

    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }
    
    public void incluir(){
        try { sql = "insert into TB_GENERO_FILME (ID_FILME, ID_GENERO)"
           + "values (?,?);";
        
           ps = con.prepareStatement(sql);
           
           ps.setInt(1, idFilme); // Configura Parametros
           ps.setInt(2,idGenero); // Configura Parametros
           
           ps.executeUpdate(); // executa comando SQL
           
           this.statusSQL = null; // armazena null se deu tudo certo
       } catch (SQLException ex) {
           this.statusSQL = "Erro ao incluir usuario ! <br> " + ex.getMessage();
       } 
    }
    
    
     public int buscarUltimoFilme(){
    try {
        sql = "SELECT * FROM TB_FILME ORDER BY ID_FILME DESC LIMIT 1;";
        ps = con.prepareStatement(sql);
        tab = ps.executeQuery();

        if (tab.next()) {
            return tab.getInt("ID_FILME");
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Log do erro
    } finally {
        try {
            if (tab != null) tab.close();
            if (ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Log para erros ao fechar os recursos
        }
    }
    return 0; // Retorna 0 se algo falhar
}
     
     public void alterar(){
    try { 
        sql = "UPDATE TB_GENERO_FILME SET ID_GENERO=? WHERE ID_FILME = ?";
        ps = con.prepareStatement(sql); // prepara SQL
        ps.setInt(1, idGenero); // Configura Parametros
        ps.setInt(2, idFilme); // Configura Parametros // Configura Parametros
        
        
        ps.executeUpdate(); // executa comando SQL
            this.statusSQL = null; // armazena null se deu tudo certo
        } catch (SQLException ex) {
    this.statusSQL = "Erro ao Alterar Genero ! <br> " +    ex.getMessage();    
    } 
     }
     public String buscarGeneroPorId_Filme (int id) throws IOException{
         
         String nomeGenero = "";
         
         try {
            sql = "SELECT       NM_GENERO\n" +
                  "FROM 	TB_GENERO_FILME\n" +
                  "INNER JOIN   TB_GENERO ON TB_GENERO_FILME.ID_GENERO = TB_GENERO.ID_GENERO\n" +
                  "WHERE ID_FILME = ?";
            
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            tab = ps.executeQuery();

            if (tab.next()) {
                nomeGenero = tab.getString(1);
            }
            
            this.statusSQL = null;
        } catch (SQLException ex) {
            this.statusSQL = "Erro ao buscar filme: " + ex.getMessage();
            return null;
        }
         return nomeGenero;
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
