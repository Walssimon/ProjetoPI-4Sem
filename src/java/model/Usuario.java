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

public class Usuario extends ConectarDao implements IcrudDao  {

    public String id;
    public String nome;
    public String email;
    public String senha;
    public int pkuser;
    public InputStream foto; // Imagem guardada no InputStream
    public long tamanho; // Guarda o tamanho da imagem em bytes
    public String imagemBase64; 


    public int getPkuser() {
        return pkuser;
    }

    public void setPkuser(int pkuser) {
        this.pkuser = pkuser;
    }

    public String getImagemBase64() {
        return imagemBase64;
    }

    public void setImagemBase64(String imagemBase64) {
        this.imagemBase64 = imagemBase64;
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



     public String getId() {
        return id;
    }

    public void setId(String idUser) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    public void incluir(){
        try { 
            sql = "insert into TB_USUARIO (NM_USUARIO, DS_EMAIL, DS_SENHA,IMG_FOTO) "
            + "values (?,?,?,?)";

            ps = con.prepareStatement(sql);

            ps.setString(1, nome); // Configura Parametros
            ps.setString(2, email); // Configura Parametros
            ps.setString(3, senha); // Configura Parametros
            ps.setBlob(4, foto,tamanho ); // Configura Parametros

            ps.executeUpdate(); // executa comando SQL

            this.statusSQL = null; // armazena null se deu tudo certo
        } catch (SQLException ex) {
            this.statusSQL = "Erro ao incluir usuario ! <br> " + ex.getMessage();
        } 
    }
    
    public boolean getLogin() throws IOException {
        
      //  if (email.equals("admin") && senha.equals("1234")) 
      //  return true;
        
        try {
            
            sql = "SELECT * FROM TB_USUARIO WHERE UCASE(TRIM(DS_EMAIL)) = UCASE(TRIM(?)) AND UCASE(TRIM(DS_SENHA)) = UCASE(TRIM(?))";
            ps = con.prepareStatement(sql); // prepara SQL
            ps.setString(1, email); // Configura Parametros
            ps.setString(2, senha); // Configura Parametros
            tab = ps.executeQuery(); // Executa comando SQL
            
            if (tab.next()){
                nome = tab.getString("NM_USUARIO");
                email = tab.getString("DS_EMAIL");
                pkuser = tab.getInt("ID_USUARIO");
                
                
            Blob blob = (Blob) tab.getBlob("IMG_FOTO"); // tab = Consulta SQL
            if (blob == null) {
                imagemBase64 = null; }
            else {
                foto = blob.getBinaryStream();
                byte[] buffer = new byte[(int) blob.length()];
                foto.read(buffer);
                imagemBase64 = Base64.getEncoder().encodeToString(buffer);
            }

             return true;
            }
          /*  if (email.equals("admin") && senha.equals("1234")) {
                nome = "";
                email = "";
                pkuser = 0;
                foto = "";
                return true;}*/
                this.statusSQL = null;// armazena null se deu tudo certo
        } catch (SQLException ex) {
            this.statusSQL = "Erro ao tentar buscar Usuário! " + ex.getMessage();
        }catch (IOException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Usuario retornarUserLogado() throws IOException {
        
        Usuario userLogado = new Usuario();
    
        try {
            sql = "SELECT * FROM TB_USUARIO WHERE DS_EMAIL = ?";
            ps = con.prepareStatement(sql); // prepara SQL
            ps.setString(1, email); // Configura Parametros

            tab = ps.executeQuery(); // Executa comando SQL

            int pkUser = 0 ;
            String idUser = "";
            String nmUser = "";
            String emailUser = "";
            String fotinho = "";


            if(tab.next()) { // Usa if para verificar se há um usuário encontrado
                idUser = tab.getString(1);  // Corrige o índice para começar de 1
                pkUser = tab.getInt(1);
                nmUser = tab.getString(2);
                emailUser = tab.getString(3);
                Blob blob = (Blob) tab.getBlob(5);

                userLogado.setPkuser(pkUser);
                userLogado.setId(idUser);
                userLogado.setNome(nmUser);
                userLogado.setEmail(emailUser);

                if (blob == null) {
                    imagemBase64 = null; }
                else {
                    foto = blob.getBinaryStream();
                    byte[] buffer = new byte[(int) blob.length()];
                    foto.read(buffer);
                   fotinho = imagemBase64 = Base64.getEncoder().encodeToString(buffer);

                }
                userLogado.setImagemBase64(fotinho);
            } else {
                return null; // Retorna null se nenhum usuário foi encontrado
            }
        } catch (SQLException ex) {
            this.statusSQL = "Erro ao tentar buscar Usuário! " + ex.getMessage();
            return null;
        }       
    
        return userLogado;
    }

    //esse aqui a gente vai usar isso aqui pra outra coisa, pra substituir a imagem.
    public void alterar(){
    try { 
        sql = "UPDATE TB_USUARIO SET NM_USUARIO=?, DS_EMAIL=?, DS_SENHA=?"; if(tamanho > 0) sql+=", IMG_FOTO=?"; sql+= "WHERE UCASE(TRIM(DS_EMAIL)) = UCASE(TRIM(?))";
        ps = con.prepareStatement(sql); // prepara SQL
        ps.setString(1, nome); // Configura Parametros
        ps.setString(2, email); // Configura Parametros
        ps.setString(3, senha ); // Configura Parametros
        
        if (tamanho > 0) { 
            ps.setBlob(4, foto, tamanho); // foto
            ps.setString(5, email);
        } else { 
            ps.setString(4, email); }
        
        ps.executeUpdate(); // executa comando SQL
            this.statusSQL = null; // armazena null se deu tudo certo
        } catch (SQLException ex) {
    this.statusSQL = "Erro ao Alterar usuario ! <br> " +    ex.getMessage();    
    } 
 }
    
    public void alterarNome(){
    try { 
         sql = "UPDATE TB_USUARIO SET NM_USUARIO=? WHERE UCASE(TRIM(DS_EMAIL)) = UCASE(TRIM(?))";
        ps = con.prepareStatement(sql); // prepara SQL
        

        // Configura os parâmetros para a atualização da foto
        ps.setString(1, nome);
        ps.setString(2, email);
        

        // Executa o comando SQL (comando UPDATE)
        int linhasAfetadas = ps.executeUpdate();
        
        // Verifica se alguma linha foi afetada
        if (linhasAfetadas > 0) {
            this.statusSQL = null;  // Atualização bem-sucedida
        } else {
            this.statusSQL = "Nenhuma linha foi atualizada.";  // Caso não tenha alterado nada
        }
        
            this.statusSQL = null; // armazena null se deu tudo certo
        } catch (SQLException ex) {
        this.statusSQL = "Erro ao Alterar usuario ! <br> " +    ex.getMessage();    
    } 
 }
    
    public void deletar() {
        try { sql = "DELETE FROM TB_USUARIO WHERE UCASE(TRIM(DS_EMAIL)) = UCASE(TRIM(?))";
        ps = con.prepareStatement(sql); // prepara SQL
        ps.setString(1, email); // Configura Parametros
        ps.executeUpdate(); // executa comando SQL
            this.statusSQL = null; // armazena null se deu tudo certo
         } catch (SQLException ex) {
        this.statusSQL = "Erro ao encontrar usuario ! <br> " + ex.getMessage();
 }}
     public void gravar() {
    try {   sql = "SELECT * FROM TB_USUARIO WHERE UCASE(TRIM(DS_EMAIL)) = UCASE(TRIM(?))";
            ps = con.prepareStatement(sql); // prepara SQL
            ps.setString(1, email); // Configura Parametros
            tab = ps.executeQuery();
            if (tab.next())   
                atualizarFoto();
            else              
                atualizarFoto();
            this.statusSQL = null; 
        } catch (SQLException ex) {
            this.statusSQL = sql + " <br> Erro ao gravar o registro ! <br> " + ex.getMessage();
        }
    }
    public void atualizarFoto() {
    try {
        // Construção da SQL
        sql = "UPDATE TB_USUARIO SET IMG_FOTO=? WHERE UCASE(TRIM(DS_EMAIL)) = UCASE(TRIM(?))";
        
        // Prepara o SQL
        ps = con.prepareStatement(sql);

        // Verifica se a foto foi fornecida
        if (foto != null) {
            // Configura os parâmetros para a atualização da foto
            ps.setBlob(1, foto);
            ps.setString(2, email);
            
        } else {
            // Caso a foto seja nula, atualiza com valor nulo
            ps.setNull(1, java.sql.Types.BLOB);
            ps.setString(2, email);
        }

        // Executa o comando SQL (comando UPDATE)
        int linhasAfetadas = ps.executeUpdate();
        
        // Verifica se alguma linha foi afetada
        if (linhasAfetadas > 0) {
            this.statusSQL = null;  // Atualização bem-sucedida
        } else {
            this.statusSQL = "Nenhuma linha foi atualizada.";  // Caso não tenha alterado nada
        }
        
    } catch (SQLException ex) {
        // Tratar exceções e armazenar a mensagem de erro
        this.statusSQL = sql + " <br> Erro ao gravar o registro! <br> " + ex.getMessage();
    }
}
   public boolean buscarEmail() throws IOException {
            
        try {
                sql = "SELECT * FROM TB_USUARIO WHERE UCASE(TRIM(DS_EMAIL)) = UCASE(TRIM(?)) ";
                ps = con.prepareStatement(sql); // prepara SQL
                ps.setString(1, email); // Configura Parametros
                tab = ps.executeQuery(); // Executa comando SQL
                if (tab.next()) {
                pkuser = tab.getInt("ID_USUARIO");
                nome = tab.getString("NM_USUARIO");
                email = tab.getString("DS_EMAIL");
                Blob blob = (Blob) tab.getBlob("IMG_FOTO");  // tab = Consulta SQL 
                if (blob == null) { 
                      imagemBase64 = null; }
                else {
                       foto = blob.getBinaryStream();
                       byte[] buffer = new byte[(int) blob.length()];
                       foto.read(buffer);
                       imagemBase64 = Base64.getEncoder().encodeToString(buffer);
                     }
                return true;}
                this.statusSQL = null; // armazena null se deu tudo certo
                 } catch (SQLException ex) {
                    this.statusSQL = "Erro ao tentar buscar Usuário! " + ex.getMessage();
                    } return false;
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
