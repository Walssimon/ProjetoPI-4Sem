/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.DriverManager; // Driver para abrir Conexão
import java.sql.ResultSet;
import java.sql.SQLException; // Tratamento de Erros SQL
import java.sql.Connection; // Armazena a Conexão Aberta
import java.sql.PreparedStatement;

public class ConectarDao {

    public Connection con = null;
    public String sql = null;
    public PreparedStatement ps = null;
    public String htmlError = null;
    public ResultSet tab = null;
    public String MeuBanco = "catalogofilms";
    public String statusSQL; /* Na variável statusSQl conterá null quando não tiver erros
Mas quando haver erros conterá a mensagem de erro capturada
Pela excessão da clausula try. */

    public ConectarDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            criarBanco();
            statusSQL = null;
        } catch (ClassNotFoundException ex) {
            htmlError = "Driver JDBC não encontrado! " + ex.getMessage();
        } catch (SQLException ex) {
            htmlError = "Servidor fora do ar ou Erro no comando SQL !" + ex.getMessage();
        }

    }

    public void criarBanco() {
        try {
            //VOU CONCERTAR ISSO DEPOIS DE VER O DIAGRAMA DO BANCO
            ps = con.prepareStatement("create database if not exists CATALOGOFILMS");
            ps.executeUpdate();
            //VOU CONCERTAR ISSO DEPOIS DE VER O DIAGRAMA DO BANCO
            ps = con.prepareStatement("use CATALOGOFILMS");
            ps.executeUpdate(); // seleciona banco
            //VOU CONCERTAR ISSO DEPOIS DE VER O DIAGRAMA DO BANCO
            sql = "CREATE TABLE IF NOT EXISTS TB_USUARIO ("
                    + "ID_USUARIO int not null AUTO_INCREMENT ,"
                    + "NM_USUARIO varchar(200) not null ,"
                    + "DS_EMAIL varchar(200) not null UNIQUE,"
                    + "DS_SENHA varchar(200) not null ,"
                    + "IMG_FOTO longblob null ,"
                    + "primary key (ID_USUARIO))";
            ps = con.prepareStatement(sql); // prepara o objeto que irá executar o comando SQL
            ps.executeUpdate();// Executa o comando SQL

            sql = "CREATE TABLE IF NOT EXISTS TB_FILME ("
                    + "ID_FILME INT NOT NULL AUTO_INCREMENT,"
                    + "NM_FILME VARCHAR(200) NOT NULL,"
                    + "DS_SINOPSE VARCHAR(1000) NULL,"
                    + "HR_DURACAO VARCHAR(200) NULL,"
                    + "DT_LANCAMENTO DATE  NULL,"
                    + "VL_AVALIACAO DECIMAL(3, 1) NULL,"
                    + "NR_CLASSIFICACAO_INDICATIVA INT NULL,"
                    + "IMG_CAPA longblob NULL,"
                    + "IMG_BANNER longblob NULL,"
                    + "primary key (ID_FILME)"
                    + ");";


            ps = con.prepareStatement(sql); // prepara o objeto que irá executar o comando SQL
            ps.executeUpdate();// Executa o comando SQL

            sql = "CREATE TABLE IF NOT EXISTS TB_ATOR ("
                    + "ID_ATOR INT NOT NULL AUTO_INCREMENT,"
                    + "NM_ATOR VARCHAR(200) NOT NULL,"
                    + "IMG_CAPA longblob NULL,"
                    + "NR_IDADE INT NOT NULL,"
                    + "primary key (ID_ATOR)"
                    + ");";

            ps = con.prepareStatement(sql); // prepara o objeto que irá executar o comando SQL
            ps.executeUpdate();// Executa o comando SQL

            sql = "CREATE TABLE IF NOT EXISTS TB_FILME_ATOR ("
                    + "ID_FILME_ATOR INT NOT NULL AUTO_INCREMENT,"
                    + "ID_FILME INT NOT NULL,"
                    + "ID_ATOR INT NOT NULL,"
                    + "primary key (ID_FILME_ATOR),"
                    + "FOREIGN KEY (ID_FILME) REFERENCES TB_FILME(ID_FILME),"
                    + "FOREIGN KEY (ID_ATOR) REFERENCES TB_ATOR(ID_ATOR)"
                    + ");";

            ps = con.prepareStatement(sql); // prepara o objeto que irá executar o comando SQL
            ps.executeUpdate();// Executa o comando SQL

                    sql = "CREATE TABLE IF NOT EXISTS TB_GENERO ("
                    + "ID_GENERO INT NOT NULL AUTO_INCREMENT,"
                    + "NM_GENERO VARCHAR(200) UNIQUE NOT NULL,"
                    + "primary key (ID_GENERO)"
                    + ");";

            ps = con.prepareStatement(sql); // prepara o objeto que irá executar o comando SQL
            ps.executeUpdate();// Executa o comando SQL

            sql = "CREATE TABLE IF NOT EXISTS TB_GENERO_FILME ("
                    + "ID_GENERO_FILME INT NOT NULL AUTO_INCREMENT,"
                    + "ID_FILME INT NOT NULL,"
                    + "ID_GENERO INT NOT NULL,"
                    + "primary key (ID_GENERO_FILME),"
                    + "FOREIGN KEY (ID_FILME) REFERENCES TB_FILME(ID_FILME),"
                    + "FOREIGN KEY (ID_GENERO) REFERENCES TB_GENERO(ID_GENERO)"
                    + ");";

            ps = con.prepareStatement(sql); // prepara o objeto que irá executar o comando SQL
            ps.executeUpdate();// Executa o comando SQL
            
            sql = "CREATE TABLE IF NOT EXISTS TB_COMENTARIO ("
                    + "ID_COMENTARIO INT NOT NULL AUTO_INCREMENT,"
                    + "ID_FILME INT NOT NULL,"
                    + "ID_USUARIO INT NOT NULL,"
                    + "DS_COMENTARIO VARCHAR(400) NOT NULL,"
                    + "DT_COMENTARIO DATE NOT NULL,"
                    + "primary key (ID_COMENTARIO),"
                    + "FOREIGN KEY (ID_FILME) REFERENCES TB_FILME(ID_FILME),"
                    + "FOREIGN KEY (ID_USUARIO) REFERENCES TB_USUARIO(ID_USUARIO)"
                    + ");";

            ps = con.prepareStatement(sql); // prepara o objeto que irá executar o comando SQL
            ps.executeUpdate();// Executa o comando SQL
            
            sql = "CREATE TABLE IF NOT EXISTS TB_FAVORITO ("
                    + "ID_FAVORITO INT NOT NULL AUTO_INCREMENT,"
                    + "ID_FILME INT NOT NULL,"
                    + "ID_USUARIO INT NOT NULL,"
                    + "primary key (ID_FAVORITO),"
                    + "FOREIGN KEY (ID_FILME) REFERENCES TB_FILME(ID_FILME),"
                    + "FOREIGN KEY (ID_USUARIO) REFERENCES TB_USUARIO(ID_USUARIO)"
                    + ");";

            ps = con.prepareStatement(sql); // prepara o objeto que irá executar o comando SQL
            ps.executeUpdate();// Executa o comando SQL
            
            sql = "INSERT INTO TB_USUARIO (NM_USUARIO, DS_EMAIL, DS_SENHA)SELECT 'admin', 'admin', '1234'WHERE NOT EXISTS (SELECT 1 FROM TB_USUARIO WHERE DS_EMAIL = 'admin');";

            ps = con.prepareStatement(sql); // prepara o objeto que irá executar o comando SQL
            ps.executeUpdate();// Executa o comando SQL
            
                        sql = "INSERT INTO TB_GENERO (NM_GENERO) VALUE('ACAO'),('COMEDIA'),('ROMANCE'),('AVENTURA'),('ANIMACAO'),('FICCAO'),('DRAMA'),"
                    + "('TERROR'),('FANTASIA'),('SUSPENSE'),('MISTERIO'),('DOCUMENTARIO'),('MUSICAL'),('HISTORICO'),('FAMILIA');";
            ps = con.prepareStatement(sql); // prepara o objeto que irá executar o comando SQL
            ps.executeUpdate();// Executa o comando SQL
            
            sql = "INSERT INTO TB_FILME (NM_FILME, DS_SINOPSE, HR_DURACAO, DT_LANCAMENTO, VL_AVALIACAO, NR_CLASSIFICACAO_INDICATIVA) "
            + "SELECT 'Homem de Ferro', "
            + "'Tony Stark é um industrial bilionário e inventor brilhante que realiza testes bélicos no exterior, mas é sequestrado por terroristas que o forçam a construir uma arma devastadora. Em vez disso, ele constrói uma armadura blindada e enfrenta seus sequestradores. Quando volta aos Estados Unidos, Stark aprimora a armadura e a utiliza para combater o crime.', "
            + "'206', '20080430', 4.5, 12 "
            + "WHERE NOT EXISTS (SELECT 1 FROM TB_FILME WHERE NM_FILME = 'Homem de Ferro')"
            + "UNION ALL "
            + "SELECT 'Homem de Ferro 2', "
            + "'Em um mundo ciente da existência do Homem de Ferro, o inventor bilionário Tony Stark sofre pressão de todos os lados para compartilhar sua tecnologia com as forças armadas. Ele resiste para divulgar os segredos de sua inigualável armadura, com medo de que estas informações caiam nas mãos erradas. Com a bela Pepper Potts e o amigo Rhodes ao seu lado, Tony precisa forjar novas alianças e confrontar um inimigo poderoso.', "
            + "'205', '20100430', 4.3, 12 "
            + "WHERE NOT EXISTS (SELECT 1 FROM TB_FILME WHERE NM_FILME = 'Homem de Ferro 2')"
            + "UNION ALL "
            + "SELECT 'Homem de Ferro 3', "
            + "'Depois de um inimigo reduzir o mundo de Tony Stark a destroços, o Homem de Ferro precisa aprender a confiar em seus instintos para proteger aqueles que ama, especialmente sua namorada, e lutar contra seu maior medo: o fracasso.', "
            + "'210', '20130426', 4.2, 12 "
            + "WHERE NOT EXISTS (SELECT 1 FROM TB_FILME WHERE NM_FILME = 'Homem de Ferro 3')"
            + "UNION ALL "
            + "SELECT 'Sonic: O Filme', "
            + "'O porco-espinho Sonic é teletransportado para a Terra. Após causar uma pane elétrica em parte dos Estados Unidos, ele precisa escapar do maligno cientista Dr. Robotnik.', "
            + "'139', '20200213', 4.0, 10 "
            + "WHERE NOT EXISTS (SELECT 1 FROM TB_FILME WHERE NM_FILME = 'Sonic: O Filme')"
            + "UNION ALL "
            + "SELECT 'Sonic 2 - O Filme', "
            + "'O Dr. Robotnik retorna à procura de uma esmeralda mística que tem o poder de destruir civilizações. Para detê-lo, Sonic se une a seu antigo parceiro, Tails, e parte em uma jornada para encontrar a joia antes que ela caia em mãos erradas.', "
            + "'202', '20220407', 4.2, 10 "
            + "WHERE NOT EXISTS (SELECT 1 FROM TB_FILME WHERE NM_FILME = 'Sonic 2 - O Filme')"
            + "UNION ALL "
            + "SELECT 'Orgulho e Preconceito', "
            + "'Elizabeth Bennet vive com sua mãe, pai e irmãs no campo, na Inglaterra. Por ser uma das filhas mais velhas, ela enfrenta uma crescente pressão de seus pais para se casar. Quando Elizabeth é apresentada ao belo e rico Darcy, faíscas voam. Embora haja uma química óbvia entre os dois, a natureza excessivamente reservada de Darcy ameaça a relação.', "
            + "'207', '20060210', 4.5, 12 "
            + "WHERE NOT EXISTS (SELECT 1 FROM TB_FILME WHERE NM_FILME = 'Orgulho e Preconceito')"
            + "UNION ALL "
            + "SELECT 'A Baleia', "
            + "'Homem obeso e solitário busca se reconectar com filha adolescente em uma última chance de redenção.', "
            + "'207', '20230209', 3.9, 16 "
            + "WHERE NOT EXISTS (SELECT 1 FROM TB_FILME WHERE NM_FILME = 'A Baleia')"
            + "UNION ALL "
            + "SELECT 'Os Caras Malvados', "
            + "'Depois de uma vida inteira de assaltos lendários, os notórios criminosos Sr. Lobo, Sr. Cobra, Sr. Piranha, Sr. Tubarão e Srta. Tarântula são finalmente capturados. Para evitar uma sentença de prisão, os bandidos animais devem realizar seu golpe mais desafiador até agora - tornando-se cidadãos-modelo. Sob a tutela de seu mentor, Professor Marmelada, a duvidosa gangue se propõe a enganar o mundo e mostrar que está se tornando boa.', "
            + "'140', '20220317', 4.0, 0 "
            + "WHERE NOT EXISTS (SELECT 1 FROM TB_FILME WHERE NM_FILME = 'Os Caras Malvados')"
            + "UNION ALL "
            + "SELECT 'Space Jam: O Jogo do Seculo', "
            + "'Um parque de diversões intergalático precisa de novas atrações! Um perigoso vilão espacial decide que Pernalonga e sua turma são os personagens mais divertidos do mundo e envia um grupo de 5 pequenos alienígenas, os Nerdlucks, para capturá-los.', "
            + "'128', '19961225', 3.9, 0 "
            + "WHERE NOT EXISTS (SELECT 1 FROM TB_FILME WHERE NM_FILME = 'Space Jam: O Jogo do Seculo')"
            + "UNION ALL "
            + "SELECT 'O Chamado', "
            + "'A jornalista Rachel Keller decide investigar a morte de sua sobrinha e descobre que quatro adolescentes morreram misteriosamente sete dias depois de assistir a um vídeo com imagens assustadoras. Agora ela tenta solucionar o mistério e impedir que a profecia se realize, já que ela e seu filho também assistiram ao vídeo.', "
            + "'115', '20030131', 3.9, 14 "
            + "WHERE NOT EXISTS (SELECT 1 FROM TB_FILME WHERE NM_FILME = 'O Chamado')"
            + "UNION ALL "
            + "SELECT 'Interestelar', "
            + "'As reservas naturais da Terra estão chegando ao fim e um grupo de astronautas recebe a missão de verificar possíveis planetas para receberem a população mundial, possibilitando a continuação da espécie. Cooper é chamado para liderar o grupo e aceita a missão sabendo que pode nunca mais ver os filhos. Ao lado de Brand, Jenkins e Doyle, ele seguirá em busca de um novo lar.', "
            + "'249', '20141106', 4.6, 12 "
            + "WHERE NOT EXISTS (SELECT 1 FROM TB_FILME WHERE NM_FILME = 'Interestelar')"
            + "UNION ALL "
            + "SELECT 'Click', "
            + "'Um arquiteto casado e com filhos está cada vez mais frustrado por passar a maior parte de seu tempo trabalhando. Um dia, ele encontra um inventor excêntrico que lhe dá um controle remoto universal, com capacidade de acelerar o tempo. No início, ele usa o aparelho para acelerar qualquer momento tedioso, mas se dá conta de que está acelerando o tempo demais e deixando de viver preciosos momentos em família. Desesperado, ele procura o inventor para ajudá-lo a reverter o que fez.', "
            + "'148', '20060811', 4.1, 14 "
            + "WHERE NOT EXISTS (SELECT 1 FROM TB_FILME WHERE NM_FILME = 'Click')"
            + "UNION ALL "
            + "SELECT 'Laranja Mecânica', "
            + "'O jovem Alex passa as noites com uma gangue de amigos briguentos. Depois que é preso, se submete a uma técnica de modificação de comportamento para poder ganhar sua liberdade.', "
            + "'216', '19720426', 4.5, 16 "
            + "WHERE NOT EXISTS (SELECT 1 FROM TB_FILME WHERE NM_FILME = 'Laranja Mecânica')"
            + "UNION ALL "
            + "SELECT 'Truque de Mestre', "
            + "'Um grupo de ilusionistas encanta o público com suas mágicas e também rouba bancos em outro continente, distribuindo a quantia para os próprios espectadores. O agente do FBI Dylan Hobbs está determinado a capturá-los e conta com a ajuda de Alma Vargas, uma detetive da Interpol, e também de Thaddeus Bradley, um veterano desmistificador de mágicos que insiste que os assaltos são realizados a partir de disfarces e jogos envolvendo vídeos.', "
            + "'115', '20130705', 4.4, 12 "
            + "WHERE NOT EXISTS (SELECT 1 FROM TB_FILME WHERE NM_FILME = 'Truque de Mestre')"
            + "UNION ALL "
            + "SELECT 'Batman Begins', "
            + "'O jovem Bruce Wayne viaja para o Oriente e recebe treinamento em artes marciais do mestre Henri Ducard, um membro da misteriosa Liga das Sombras. Quando Ducard revela que a verdadeira proposta da Liga é a destruição completa de Gotham City, Wayne retorna à sua cidade natal com o intuito de livrá-la de criminosos e assassinos. Bruce assume a persona de Batman, o Cavaleiro das Trevas, e conta com a ajuda do mordomo Alfred e do expert Lucius Fox.', "
            + "'220', '20050617', 4.5, 14 "
            + "WHERE NOT EXISTS (SELECT 1 FROM TB_FILME WHERE NM_FILME = 'Batman Begins')"
            + "UNION ALL "
            + "SELECT 'Batman: O Cavaleiro das Trevas', "
            + "'Batman tem conseguido manter a ordem em Gotham com a ajuda de Jim Gordon e Harvey Dent. No entanto, um jovem e anárquico criminoso, conhecido apenas como Coringa, pretende testar o Cavaleiro das Trevas e mergulhar a cidade em um verdadeiro caos.', "
            + "'232', '20080718', 4.7, 12 "
            + "WHERE NOT EXISTS (SELECT 1 FROM TB_FILME WHERE NM_FILME = 'Batman: O Cavaleiro das Trevas')"
            + "UNION ALL "
            + "SELECT 'Batman: O Cavaleiro das Trevas Ressurge', "
            + "'Após ser culpado pela morte de Harvey Dent e passar de herói a vilão, Batman desaparece. As coisas mudam com a chegada de uma ladra misteriosa, a Mulher-Gato, e Bane, um terrorista mascarado, que fazem Batman abandonar seu exílio forçado.', "
            + "'242', '20120727', 4.6, 12 "
            + "WHERE NOT EXISTS (SELECT 1 FROM TB_FILME WHERE NM_FILME = 'Batman: O Cavaleiro das Trevas Ressurge')"
            + "UNION ALL "
            + "SELECT 'O Feitiço de Áquila', "
            + "'O bispo de Áquila descobre que a sua amada, Isabeau está apaixonada por um cavaleiro. Enciumado, o bispo resolve lançar uma maldição sobre o casal, na qual durante o dia Isabeau se transforma em um falcão e à noite o cavaleiro toma a forma de um lobo. Dessa maneira o casal não pode se entregar um ao outro. A única esperança do apaixonado casal é o amigo Phillipe, conhecido como Rato, o único que conseguiu escapar das muralhas de Áquila.', "
            + "'201', '19850412', 4.3, 12 "
            + "WHERE NOT EXISTS (SELECT 1 FROM TB_FILME WHERE NM_FILME = 'O Feitiço de Áquila')"
            + "UNION ALL "
            + "SELECT 'Eu Sou a Lenda', "
            + "'Robert Neville é um brilhante cientista e o único sobrevivente de uma epidemia que transformou os humanos em mutantes sedentos por sangue. Andando pela cidade de Nova York, ele procura por outros possíveis sobreviventes e tenta achar a cura da praga usando seu próprio sangue, que é imune.', "
            + "'141', '20080118', 4.4, 14 "
            + "WHERE NOT EXISTS (SELECT 1 FROM TB_FILME WHERE NM_FILME = 'Eu Sou a Lenda');";

            ps = con.prepareStatement(sql); // prepara o objeto que irá executar o comando SQL
            ps.executeUpdate();// Executa o comando SQL
            
            sql = "INSERT INTO TB_GENERO_FILME (ID_FILME, ID_GENERO) VALUES (1,6),(2,6),(3,6),(4,4),(5,4),(6,3),(7,7),(8,5),(9,5),(10,8),(11,6),(12,2),(13,10),(14,11),(15,1),(16,1),(17,1),(18,9),(19,10),(1,1);";
            ps = con.prepareStatement(sql); // prepara o objeto que irá executar o comando SQL
            ps.executeUpdate();// Executa o comando SQL
            
            sql = "SET GLOBAL max_allowed_packet = 16 * 1024 * 1024; -- Aumenta para 16 MB";
            ps = con.prepareStatement(sql); // prepara o objeto que irá executar o comando SQL
            ps.executeUpdate();
            
        } catch (SQLException err) {
            htmlError = err.getMessage();
        }
    }

}

