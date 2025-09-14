/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import Controller.ConectarDao;
import Controller.IcrudDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Megas-XRL8
 */
public class Comentario extends ConectarDao implements IcrudDao {
    
    public int idComent;
    public int idFilme;
    public int idUser;
    public String nomeUser;
    public String comentario;
    public String dataComentario;
    

    public int getIdComent() {
        return idComent;
    }

    public void setIdComent(int idComent) {
        this.idComent = idComent;
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
    
     public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }
    
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getDataComentario() {
        return dataComentario;
    }

    public void setDataComentario(String dataComentario) {
        this.dataComentario = dataComentario;
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
    
    public Comentario() {
      
    }
    
    public Comentario(String nomeUser, String comentario, String dataComentario) {
        this.nomeUser = nomeUser;
        this.comentario = comentario;
        this.dataComentario = dataComentario;
    }
    
    
    public void IncluirComentario(){
        try {
            sql = "INSERT INTO TB_COMENTARIO (ID_FILME, ID_USUARIO, DS_COMENTARIO, DT_COMENTARIO) VALUES (?,?,?,NOW())";

            ps = con.prepareStatement(sql);
            
            ps.setInt(1, idFilme); // Configura Parametros
            ps.setInt(2, idUser); // Configura Parametros
            ps.setString(3, comentario); // Configura Parametros
        
            ps.executeUpdate();
            
            this.statusSQL = null;

        } catch (SQLException ex) {
            this.statusSQL = "Erro ao incluir comentário ! <br> " + ex.getMessage();
        } 
    }
    
    
    public ArrayList<Comentario> listarComentarios() {

        ArrayList<Comentario> comentarios = new ArrayList<>();

        try {

            sql =   " SELECT        NM_USUARIO,\n" +
                    "               DS_COMENTARIO,\n" +
                    "               DATE_FORMAT(DT_COMENTARIO, '%d/%m/%Y') AS DATA_COMENTARIO\n" +
                    " FROM          TB_COMENTARIO\n" +
                    " INNER JOIN    TB_FILME 	ON 	TB_FILME.ID_FILME = TB_COMENTARIO.ID_FILME\n" +
                    " INNER JOIN    TB_USUARIO 	ON 	TB_USUARIO.ID_USUARIO = TB_COMENTARIO.ID_USUARIO\n" +
                    " WHERE     TB_FILME.ID_FILME = ? ORDER BY ID_COMENTARIO DESC";

            ps = con.prepareStatement(sql);

            ps.setInt(1, idFilme); // Configura Parametros
             
            tab = ps.executeQuery();

            while (tab.next()) {
                String nomeUser = tab.getString(1);
                String coment = tab.getString(2);
                String dtComentario = tab.getString(3);

                comentarios.add(new Comentario(nomeUser, coment, dtComentario));
            }

            this.statusSQL = null;
        } catch (SQLException ex) {
            this.statusSQL = "Erro ao incluir usuario ! <br> " + ex.getMessage();
        }

        return comentarios;
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