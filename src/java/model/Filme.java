/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import Controller.ConectarDao;
import Controller.IcrudDao;
import com.mysql.cj.jdbc.Blob;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 *
 * @author Megas-XRL8
 */
public class Filme extends ConectarDao implements IcrudDao {

    public int id;
    public String nome;
    public String sinopse;
    public String duracao;
    public String dataLancamento;
    public double avaliacao;
    public int classificacao;
    public int pkuser;

    public InputStream capa; // Imagem guardada no InputStream
    public long capatamanho; // Guarda o tamanho da imagem em bytes
    public String capaimagemBase64;

    public InputStream banner; // Imagem guardada no InputStream
    public long bannertamanho; // Guarda o tamanho da imagem em bytes

    public String bannerimagemBase64;
    public String genero;

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public int getPkuser() {
        return pkuser;
    }

    public void setPkuser(int pkuser) {
        this.pkuser = pkuser;
    }

    public String getCapaimagemBase64() {
        return capaimagemBase64;
    }

    public void setCapaimagemBase64(String capaimagemBase64) {
        this.capaimagemBase64 = capaimagemBase64;
    }

    public String getBannerimagemBase64() {
        return bannerimagemBase64;
    }

    public void setBannerimagemBase64(String bannerimagemBase64) {
        this.bannerimagemBase64 = bannerimagemBase64;
    }

    public ResultSet getTab() {
        return tab;
    }

    public void setTab(ResultSet tab) {
        this.tab = tab;
    }

    public Filme(int id, String nome, String sinopse, String duracao, String dataLancamento, double avaliacao, int classificacao, String capaimagemBase64) {
        this.id = id;
        this.nome = nome;
        this.sinopse = sinopse;
        this.duracao = duracao;
        this.dataLancamento = dataLancamento;
        this.avaliacao = avaliacao;
        this.classificacao = classificacao;
        this.capaimagemBase64 = capaimagemBase64;
    }

    public Filme(String capaimagemBase64){
        this.capaimagemBase64 = capaimagemBase64;
    }
    
    public Filme() {
    }

    public void gravar() {
        statusSQL = null;
        try {
            sql = "SELECT * FROM TB_FILME WHERE ID_FILME = '" + this.id + "'";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet consul = ps.executeQuery();
            if (consul.next()) {
                alterar();
            } else {
                incluirFilme();
            }

        } catch (SQLException ex) {
            statusSQL = "Erro ao gravar filme  " + ex.getMessage();
        }

    }

    public void incluirFilme() {

        try {
            sql = "INSERT INTO TB_FILME (NM_FILME, DS_SINOPSE, HR_DURACAO, DT_LANCAMENTO, VL_AVALIACAO, NR_CLASSIFICACAO_INDICATIVA, IMG_CAPA, IMG_BANNER) VALUES (?,?,?,?,?,?,?,?)";

            ps = con.prepareStatement(sql);
            ps.setString(1, nome); // Configura Parametros
            ps.setString(2, sinopse); // Configura Parametros
            ps.setString(3, duracao); // Configura Parametros
            ps.setString(4, dataLancamento); // Configura Parametros
            ps.setDouble(5, avaliacao); // Configura Parametros
            ps.setInt(6, classificacao); // Configura Parametros

            if (capatamanho > 0) {
                ps.setBlob(7, capa, capatamanho); // foto
            } else {
                ps.setNull(7, java.sql.Types.BLOB);
            }
            if (bannertamanho > 0) {
                ps.setBlob(8, banner, bannertamanho); // foto
            } else {
                ps.setNull(8, java.sql.Types.BLOB);
            }
            ps.executeUpdate();
            this.statusSQL = null;

        } catch (SQLException ex) {
            this.statusSQL = "Erro ao incluir usuario ! <br> " + ex.getMessage();
        }

    }

    public ArrayList<Filme> listarFilmes() {

        ArrayList<Filme> filmes = new ArrayList<>();

        try {

            sql = "SELECT * FROM TB_FILME";

            ps = con.prepareStatement(sql);

            tab = ps.executeQuery();

            String fotinho = "";

            while (tab.next()) {
                int id = tab.getInt(1);
                String nome = tab.getString(2);
                String sinopse = tab.getString(3);
                String duracao = tab.getString(4);
                String dtLancamento = tab.getString(5);
                double avaliacao = tab.getDouble(6);
                int classificacao = tab.getInt(7);
                Blob blob = (Blob) tab.getBlob(8);

                if (blob == null) {
                    capaimagemBase64 = null;
                } else {
                    capa = blob.getBinaryStream();
                    byte[] buffer = new byte[(int) blob.length()];
                    capa.read(buffer);
                    fotinho = capaimagemBase64 = Base64.getEncoder().encodeToString(buffer);
                }

                filmes.add(new Filme(id, nome, sinopse, duracao, dtLancamento, avaliacao, classificacao, fotinho));
            }

            this.statusSQL = null;
        } catch (SQLException ex) {
            this.statusSQL = "Erro ao Listar Filmes ! <br> " + ex.getMessage();
        } catch (IOException ex) {
            Logger.getLogger(Filme.class.getName()).log(Level.SEVERE, null, ex);
        }

        return filmes;
    }

    public ArrayList<Filme> listarFilmes_PorGenero() {

        ArrayList<Filme> filmes = new ArrayList<>();

        try {

            sql = "SELECT	FM.ID_FILME,\n"
                    + "NM_FILME,\n"
                    + "DS_SINOPSE,\n"
                    + "HR_DURACAO,\n"
                    + "DT_LANCAMENTO,\n"
                    + "VL_AVALIACAO,\n"
                    + "NR_CLASSIFICACAO_INDICATIVA,\n"
                    + "IMG_CAPA\n"
                    + "FROM 		`TB_GENERO_FILME` GENFM\n"
                    + "INNER JOIN 	TB_FILME 	FM 	ON 	FM.ID_FILME = GENFM.ID_FILME\n"
                    + "INNER JOIN	TB_GENERO 	GEN	ON 	GEN.ID_GENERO = GENFM.ID_GENERO\n"
                    + "WHERE 		UCASE(TRIM(NM_GENERO)) = UCASE(TRIM(?))";

            ps = con.prepareStatement(sql);

            ps.setString(1, genero); // Configura Parametros

            tab = ps.executeQuery();

            String capaFilm = "";

            while (tab.next()) {
                int id = tab.getInt(1);
                String nome = tab.getString(2);
                String sinopse = tab.getString(3);
                String duracao = tab.getString(4);
                String dtLancamento = tab.getString(5);
                double avaliacao = tab.getDouble(6);
                int classificacao = tab.getInt(7);
                Blob blob = (Blob) tab.getBlob(8);

                if (blob == null) {
                    capaimagemBase64 = null;
                    bannerimagemBase64 = null;
                } else {
                    capa = blob.getBinaryStream();
                    byte[] buffer = new byte[(int) blob.length()];
                    capa.read(buffer);
                    capaFilm = capaimagemBase64 = Base64.getEncoder().encodeToString(buffer);
                }

                filmes.add(new Filme(id, nome, sinopse, duracao, dtLancamento, avaliacao, classificacao, capaFilm));
            }

            this.statusSQL = null;
        } catch (SQLException ex) {
            this.statusSQL = "Erro ao Listar Filmes ! <br> " + ex.getMessage();
        } catch (IOException ex) {
            Logger.getLogger(Filme.class.getName()).log(Level.SEVERE, null, ex);
        }

        return filmes;
    }

    public ArrayList<Filme> listarFilmes_PorNome() {

        ArrayList<Filme> filmes = new ArrayList<>();

        try {

            sql = "SELECT * FROM TB_FILME WHERE NM_FILME LIKE ?";

            ps = con.prepareStatement(sql);

            ps.setString(1, "%" + nome + "%");

            tab = ps.executeQuery();

            String fotinho = "";

            while (tab.next()) {
                int id = tab.getInt(1);
                String nome = tab.getString(2);
                String sinopse = tab.getString(3);
                String duracao = tab.getString(4);
                String dtLancamento = tab.getString(5);
                double avaliacao = tab.getDouble(6);
                int classificacao = tab.getInt(7);
                Blob blob = (Blob) tab.getBlob(8);

                if (blob == null) {
                    capaimagemBase64 = null;
                } else {
                    capa = blob.getBinaryStream();
                    byte[] buffer = new byte[(int) blob.length()];
                    capa.read(buffer);
                    fotinho = capaimagemBase64 = Base64.getEncoder().encodeToString(buffer);
                }

                filmes.add(new Filme(id, nome, sinopse, duracao, dtLancamento, avaliacao, classificacao, fotinho));
            }

            this.statusSQL = null;
        } catch (SQLException ex) {
            this.statusSQL = "Erro ao incluir usuario ! <br> " + ex.getMessage();
        } catch (IOException ex) {
            Logger.getLogger(Filme.class.getName()).log(Level.SEVERE, null, ex);
        }

        return filmes;
    }

    public void deletar(int idzinho) {
        try {
            
            String sqlDeleteGenero = "DELETE FROM tb_genero_filme WHERE ID_FILME = ?";
            ps = con.prepareStatement(sqlDeleteGenero);
            ps.setInt(1, idzinho);
            ps.executeUpdate();
        
            sql = "DELETE FROM TB_FILME WHERE ID_FILME = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idzinho);
            ps.executeUpdate();
            this.statusSQL = null;
        } catch (SQLException ex) {
            this.statusSQL = "Errro ao encontrar  usuario ! <br>" + ex.getMessage();
        }
    }

    public void alterar() {
        try {
            sql = "UPDATE TB_FILME SET NM_FILME= ?, DS_SINOPSE = ?, HR_DURACAO = ?, DT_LANCAMENTO = ?, VL_AVALIACAO = ?, NR_CLASSIFICACAO_INDICATIVA = ?";
            if (capatamanho > 0) {
                sql += ",IMG_CAPA = ?";
            }
            if (bannertamanho > 0) {
                sql += ",IMG_BANNER = ?";
            }
            sql += " WHERE ID_FILME = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, nome); // Configura Parametros
            ps.setString(2, sinopse); // Configura Parametros
            ps.setString(3, duracao); // Configura Parametros
            ps.setString(4, dataLancamento); // Configura Parametros
            ps.setDouble(5, avaliacao); // Configura Parametros
            ps.setInt(6, classificacao); // Configura Parametros
            if(capatamanho <= 0 && bannertamanho <= 0){
                ps.setInt(7, id);
            }else if( capatamanho <= 0){
                ps.setBlob(7, banner,bannertamanho);
                ps.setInt(8, id);
            }else if( bannertamanho <= 0){
                ps.setBlob(7, capa,capatamanho);
                ps.setInt(8, id);
            }else{
                ps.setBlob(7, capa,capatamanho);
                ps.setBlob(8, banner,bannertamanho);
                ps.setInt(9, id);
            }

            ps.executeUpdate();
            this.statusSQL = null;

        } catch (SQLException ex) {
            this.statusSQL = "Errro ao encontrar  usuario ! <br>" + ex.getMessage() + nome + sinopse;
        }
    }

    public Filme getFilmeById(int id) throws IOException {
        Filme FilmeBuscado = new Filme();
        try {
            sql = "SELECT * FROM TB_FILME WHERE ID_FILME = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            tab = ps.executeQuery();

            String capaFilm = "";
            String bannerFilm = "";

            if (tab.next()) {
                nome = tab.getString(2);
                sinopse = tab.getString(3);
                duracao = tab.getString(4);
                dataLancamento = tab.getString(5);
                avaliacao = tab.getDouble(6);
                classificacao = tab.getInt(7);
                Blob blobCapa = (Blob) tab.getBlob(8);
                Blob blobBanner = (Blob) tab.getBlob(9);

                FilmeBuscado.setNome(nome);
                FilmeBuscado.setSinopse(sinopse);
                FilmeBuscado.setDuracao(duracao);
                FilmeBuscado.setDataLancamento(dataLancamento);
                FilmeBuscado.setAvaliacao(avaliacao);
                FilmeBuscado.setClassificacao(classificacao);

                if (blobCapa == null && blobBanner == null) {
                    capaimagemBase64 = null;
                    bannerimagemBase64 = null;
                } else {
                    capa = blobCapa.getBinaryStream();
                    byte[] buffer = new byte[(int) blobCapa.length()];
                    capa.read(buffer);
                    capaFilm = capaimagemBase64 = Base64.getEncoder().encodeToString(buffer);

                    banner = blobBanner.getBinaryStream();
                    byte[] buffer2 = new byte[(int) blobBanner.length()];
                    banner.read(buffer2);
                    bannerFilm = Base64.getEncoder().encodeToString(buffer2);
                }
                FilmeBuscado.setCapaimagemBase64(capaFilm);
                FilmeBuscado.setBannerimagemBase64(bannerFilm);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            this.statusSQL = "Erro ao buscar filme: " + ex.getMessage();
            return null;
        }
        return FilmeBuscado;
    }
    
    public ArrayList<Filme> getFavsByIdUser(int idUser) throws IOException {
        
        ArrayList<Filme> filmes = new ArrayList<>();
        
        try {
            
            sql =   "SELECT IMG_CAPA FROM TB_FAVORITO INNER JOIN TB_FILME ON TB_FILME.ID_FILME = TB_FAVORITO.ID_FILME WHERE ID_USUARIO = ?";
            
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUser);
            tab = ps.executeQuery();

            String capaFilm = "";

            while(tab.next()) {
                
                Blob blobCapa = (Blob) tab.getBlob(1);

                if (blobCapa == null) {
                    capaimagemBase64 = null;
                } else {
                    capa = blobCapa.getBinaryStream();
                    byte[] buffer = new byte[(int) blobCapa.length()];
                    capa.read(buffer);
                    capaFilm = capaimagemBase64 = Base64.getEncoder().encodeToString(buffer);
                }
                
                filmes.add(new Filme(capaFilm));
                
            }
            this.statusSQL = null;
        } catch (SQLException ex) {
            this.statusSQL = "Erro ao buscar filmes: " + ex.getMessage();
            return null;
        }
        return filmes;
    }
    
        public ArrayList<Filme> buscaFilmes_Destaques() throws IOException {
        
        ArrayList<Filme> filmes = new ArrayList<>();
        
        try {
            
            sql =  "SELECT IMG_CAPA FROM TB_FILME WHERE VL_AVALIACAO >= 5;";
            
            ps = con.prepareStatement(sql);
        
            tab = ps.executeQuery();

            String capaFilm = "";

            while(tab.next()) {
                
                Blob blobCapa = (Blob) tab.getBlob(1);

                if (blobCapa == null) {
                    capaimagemBase64 = null;
                } else {
                    capa = blobCapa.getBinaryStream();
                    byte[] buffer = new byte[(int) blobCapa.length()];
                    capa.read(buffer);
                    capaFilm = capaimagemBase64 = Base64.getEncoder().encodeToString(buffer);
                }
                
                filmes.add(new Filme(capaFilm));
                
            }
            this.statusSQL = null;
        } catch (SQLException ex) {
            this.statusSQL = "Erro ao buscar filmes: " + ex.getMessage();
            return null;
        }
        return filmes;
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
