/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import Controller.ConectarDao;
import Controller.IcrudDao;
import com.mysql.cj.jdbc.Blob;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Megas-XRL8
 */
public class Ator extends ConectarDao implements IcrudDao {

    public String nomeAtor;
    public int idade;
    public int id;
    
    
    public InputStream capa; // Imagem guardada no InputStream
    public long capatamanho; // Guarda o tamanho da imagem em bytes
    public String capaimagemBase64;

    Filme film = new Filme();    
    
    public Ator(int id , String nome, String fotinho) {
        this.id = id;
        this.nomeAtor = nome;
        this.capaimagemBase64 = fotinho;
    }


    
    public Ator(){
    }
    
    public void IncluirAtor(){
            try{setSql("INSERT INTO TB_ATOR (NM_ATOR, NR_IDADE, IMG_CAPA)"
             + "VALUES (?,?,?);");
     setPs(getCon().prepareStatement(getSql()));
     getPs().setString(1, nomeAtor); // Configura Parametros
     getPs().setInt(2, idade); // Configura Parametros

     if(capatamanho > 0){
                getPs().setBlob(3, capa, capatamanho);
            }else{
                getPs().setNull(3, java.sql.Types.BLOB);
            }

        getPs().executeUpdate(); // executa comando SQL
        this.setStatusSQL(null);
            
        }catch(SQLException ex){
            this.setStatusSQL("Erro ao incluir Ator ! <br> " + ex.getMessage());
        }
    }
    
     public void IncluirAtorFilme(){
         try{setSql("INSET INTO TB_FILME_ATOR(ID_FILME, ID_ATOR) VALUES (?,?)");
         setPs(getCon().prepareStatement(getSql()));
         getPs().setInt(1, film.id);
         getPs().setInt(2, id);
         
         }catch(SQLException ex){
            this.setStatusSQL("Erro ao incluir Ator ! <br> " + ex.getMessage());
        }
     }
    
        public void deletar() {
        try {
            setSql("DELETE FROM TB_ATOR WHERE UCASE(TRIM(NM_ATOR)) = UCASE(TRIM(?))");
            setPs(getCon().prepareStatement(getSql()));
            getPs().setString(1, nomeAtor);
            getPs().executeUpdate();
            this.setStatusSQL(null);
        } catch (SQLException ex) {
            this.setStatusSQL("Errro ao encontrar Ator ! <br>" + ex.getMessage());
        }
    }
        
        public ArrayList<Ator> listarAtores() {

        ArrayList<Ator> atores = new ArrayList<>();

        try {

            sql = "SELECT * FROM TB_ATOR";

            ps = con.prepareStatement(sql);

            tab = ps.executeQuery();

            String fotinho = "";

            while (tab.next()) {
                int id = tab.getInt(1);
                String nome = tab.getString(2);
                Blob blob = (Blob) tab.getBlob(3);

                if (blob == null) {
                    capaimagemBase64 = null;
                } else {
                    capa = blob.getBinaryStream();
                    byte[] buffer = new byte[(int) blob.length()];
                    capa.read(buffer);
                    fotinho = capaimagemBase64 = Base64.getEncoder().encodeToString(buffer);
                }

                atores.add(new Ator(id, nome, fotinho));
            }

            this.statusSQL = null;
        } catch (SQLException ex) {
            this.statusSQL = "Erro ao incluir usuario ! <br> " + ex.getMessage();
        } catch (IOException ex) {
            Logger.getLogger(Filme.class.getName()).log(Level.SEVERE, null, ex);
        }

        return atores;
    }

        
        
    public String getNomeAtor() {
        return nomeAtor;
    }

    public void setNomeAtor(String nomeAtor) {
        this.nomeAtor = nomeAtor;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
        public String getCapaimagemBase64() {
        return capaimagemBase64;
    }

    public void setCapaimagemBase64(String capaimagemBase64) {
        this.capaimagemBase64 = capaimagemBase64;
    }

    public ResultSet getTab() {
        return getTab();
    }

    public void setTab(ResultSet tab) {
        this.setTab(tab);
    }

    public InputStream getCapa() {
        return capa;
    }

    public void setCapa(InputStream capa) {
        this.capa = capa;
    }

    public long getCapatamanho() {
        return capatamanho;
    }

    public void setCapatamanho(long capatamanho) {
        this.capatamanho = capatamanho;
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
        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
